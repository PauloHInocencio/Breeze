@file:OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)

package br.com.noartcode.unsplashapp.android.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.noartcode.unsplashapp.android.api.entry.Photo
import br.com.noartcode.unsplashapp.android.domain.PhotosRepository
import br.com.noartcode.unsplashapp.android.util.doIfError
import br.com.noartcode.unsplashapp.android.util.doIfSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class PhotosRandomLisEvent {
    data object OnLoadMore : PhotosRandomLisEvent()
    data object OnDismissError : PhotosRandomLisEvent()
}

data class PhotosRandomListUiState(
    val photos: List<Photo> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage:String? = null
)

@HiltViewModel
class PhotosRandomListViewModel @Inject constructor(
    private val repository: PhotosRepository
) : ViewModel() {


    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    private val _errorMessage = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow(false)

    private var job:Job? = null

    val state = combine(_photos, _errorMessage, _isLoading) {
        photos, errorMessage, isLoading ->
        PhotosRandomListUiState(
            photos = photos,
            isLoading = isLoading,
            errorMessage = errorMessage
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = PhotosRandomListUiState()
    )

    fun onEvent(event:PhotosRandomLisEvent) {
        when(event) {
            PhotosRandomLisEvent.OnLoadMore -> {
                job?.cancel()
                loadMore()
            }

            PhotosRandomLisEvent.OnDismissError -> {
                _errorMessage.update { null }
            }
        }
    }

    init {
        loadMore()
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }


    private fun loadMore() {
       job =  viewModelScope.launch {
            repository
                .getRandomPhotos()
                .debounce(1000)
                .onStart { _isLoading.update { true } }
                .onCompletion { _isLoading.update { false } }
                .collect { result ->
                    result
                        .doIfSuccess { newPhotos ->
                            _photos.update { it + newPhotos }
                        }
                        .doIfError { error ->
                            _errorMessage.update { error.message }
                        }
                }
        }
    }

}