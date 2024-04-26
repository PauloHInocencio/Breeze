package br.com.noartcode.unsplashapp.android.domain

import br.com.noartcode.unsplashapp.android.api.entry.Photo
import br.com.noartcode.unsplashapp.android.api.entry.PhotosResponse
import br.com.noartcode.unsplashapp.android.util.Resource
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {

    fun getPhotos(query: String, page:Int) : Flow<Resource<PhotosResponse>>

    fun getRandomPhotos() : Flow<Resource<List<Photo>>>

}