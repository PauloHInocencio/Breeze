@file:OptIn(FlowPreview::class)

package br.com.noartcode.breeze.android.presentation.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.noartcode.breeze.android.domain.PhotosRepository
import br.com.noartcode.breeze.android.domain.model.Photo
import br.com.noartcode.breeze.android.util.doIfError
import br.com.noartcode.breeze.android.util.doIfSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
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


sealed class RandomPhotosEvent {
    data object OnLoadMore : RandomPhotosEvent()
    data object OnDismissError : RandomPhotosEvent()
}

data class RandomPhotosUiState(
    val photos: List<Photo> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage:String? = null
)

@HiltViewModel
class RandomPhotosViewModel @Inject constructor(
    private val repository: PhotosRepository
) : ViewModel() {


    private val _photos = MutableStateFlow<List<Photo>>(emptyList())
    private val _errorMessage = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow(false)

    private var job:Job? = null

    val state = combine(_photos, _errorMessage, _isLoading) {
        photos, errorMessage, isLoading ->
        RandomPhotosUiState(
            photos = photos,
            isLoading = isLoading,
            errorMessage = errorMessage
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = RandomPhotosUiState()
    )

    fun onEvent(event: RandomPhotosEvent) {
        when(event) {
            RandomPhotosEvent.OnLoadMore -> {
                job?.cancel()
                loadMore()
            }

            RandomPhotosEvent.OnDismissError -> {
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