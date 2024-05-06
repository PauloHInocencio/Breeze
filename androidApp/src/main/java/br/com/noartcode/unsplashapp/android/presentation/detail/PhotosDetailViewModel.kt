package br.com.noartcode.unsplashapp.android.presentation.detail

import androidx.lifecycle.ViewModel
import br.com.noartcode.unsplashapp.android.data.remote.dto.PhotoDto
import br.com.noartcode.unsplashapp.android.domain.PhotosRepository
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

data class PhotosDetailUiState(
    val photos: List<PhotoDto> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage:String? = null
)

class PhotosDetailViewModel @Inject constructor(
    private val repository: PhotosRepository
) : ViewModel() {

    private val _photos = MutableStateFlow<List<PhotoDto>>(emptyList())

}