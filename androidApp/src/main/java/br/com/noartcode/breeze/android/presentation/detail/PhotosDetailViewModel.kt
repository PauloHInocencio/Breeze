package br.com.noartcode.breeze.android.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.noartcode.breeze.android.domain.PhotosRepository
import br.com.noartcode.breeze.android.domain.model.Photo
import br.com.noartcode.breeze.android.util.doIfError
import br.com.noartcode.breeze.android.util.doIfSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class PhotosDetailEvent {
    data class OnLoadPhoto(val id:Long) : PhotosDetailEvent()
    data object OnDismissError : PhotosDetailEvent()
}

data class PhotosDetailUiState(
    val photo: Photo? = null,
    val isLoading: Boolean = false,
    val errorMessage:String? = null
)

@HiltViewModel
class PhotosDetailViewModel @Inject constructor(
    private val repository: PhotosRepository
) : ViewModel() {

    private val _photo = MutableStateFlow<Photo?>(null)
    private val _errorMessage = MutableStateFlow<String?>(null)
    private val _isLoading = MutableStateFlow(false)

    val state = combine(_photo, _errorMessage, _isLoading) {
        photo, error, loading ->

        PhotosDetailUiState(
            photo = photo,
            errorMessage = error,
            isLoading = loading
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500),
        initialValue = PhotosDetailUiState()
    )


    fun onEvent(event: PhotosDetailEvent) {
        when(event) {
            PhotosDetailEvent.OnDismissError -> TODO()
            is PhotosDetailEvent.OnLoadPhoto -> {
                viewModelScope.launch {
                    _isLoading.update { true }
                    repository
                        .getPhoto(event.id)
                        .doIfSuccess { photo ->
                            _photo.update { photo }
                        }
                        .doIfError { error ->
                            _errorMessage.update { error.message }
                        }
                    _isLoading.update { false }
                }
            }
        }
    }

}