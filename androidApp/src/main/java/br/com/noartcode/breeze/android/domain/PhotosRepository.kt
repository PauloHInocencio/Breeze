package br.com.noartcode.breeze.android.domain

import br.com.noartcode.breeze.android.data.remote.dto.PhotosResponse
import br.com.noartcode.breeze.android.domain.model.Photo
import br.com.noartcode.breeze.android.util.Resource
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    fun getPhotos(query: String, page:Int) : Flow<Resource<PhotosResponse>>

    fun getRandomPhotos() : Flow<Resource<List<Photo>>>

    suspend fun getPhoto(id:Long) : Resource<Photo>

    suspend fun cleanPhotos() : Resource<Unit>

}