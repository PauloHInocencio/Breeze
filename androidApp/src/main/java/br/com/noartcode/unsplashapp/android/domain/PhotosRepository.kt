package br.com.noartcode.unsplashapp.android.domain

import br.com.noartcode.unsplashapp.android.data.remote.dto.PhotoDto
import br.com.noartcode.unsplashapp.android.data.remote.dto.PhotosResponse
import br.com.noartcode.unsplashapp.android.domain.model.Photo
import br.com.noartcode.unsplashapp.android.util.Resource
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    fun getPhotos(query: String, page:Int) : Flow<Resource<PhotosResponse>>

    fun getRandomPhotos() : Flow<Resource<List<Photo>>>

    suspend fun getPhoto(id:Long) : Resource<Photo>

    suspend fun cleanPhotos() : Resource<Unit>

}