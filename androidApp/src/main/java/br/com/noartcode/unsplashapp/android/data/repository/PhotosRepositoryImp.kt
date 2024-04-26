package br.com.noartcode.unsplashapp.android.data.repository

import br.com.noartcode.unsplashapp.android.api.PhotosRetrofitApi
import br.com.noartcode.unsplashapp.android.api.entry.Photo
import br.com.noartcode.unsplashapp.android.api.entry.PhotosResponse
import br.com.noartcode.unsplashapp.android.api.safeApiCall
import br.com.noartcode.unsplashapp.android.domain.PhotosRepository
import br.com.noartcode.unsplashapp.android.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotosRepositoryImp @Inject constructor(
    private val api: PhotosRetrofitApi,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
): PhotosRepository {

    override fun getPhotos(query: String, page: Int): Flow<Resource<PhotosResponse>> = flow {
        emit( safeApiCall(defaultDispatcher) { api.fetchPhotos(query = query, page = page)})
    }

    override fun getRandomPhotos(): Flow<Resource<List<Photo>>>  = flow {
        emit( safeApiCall(defaultDispatcher) { api.fetchRandomPhotos()} )
    }
}