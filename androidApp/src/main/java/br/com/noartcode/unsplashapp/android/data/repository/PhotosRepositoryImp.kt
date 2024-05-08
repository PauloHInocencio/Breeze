package br.com.noartcode.unsplashapp.android.data.repository

import br.com.noartcode.unsplashapp.android.data.local.PhotosDao
import br.com.noartcode.unsplashapp.android.data.local.mapper.toDomain
import br.com.noartcode.unsplashapp.android.data.remote.PhotosRetrofitApi
import br.com.noartcode.unsplashapp.android.data.remote.dto.PhotosResponse
import br.com.noartcode.unsplashapp.android.data.remote.mapper.toEntity
import br.com.noartcode.unsplashapp.android.data.remote.safeApiCall
import br.com.noartcode.unsplashapp.android.domain.PhotosRepository
import br.com.noartcode.unsplashapp.android.domain.model.Photo
import br.com.noartcode.unsplashapp.android.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

class PhotosRepositoryImp @Inject constructor(
    private val api: PhotosRetrofitApi,
    private val photosDao: PhotosDao,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
): PhotosRepository {

    override fun getPhotos(query: String, page: Int): Flow<Resource<PhotosResponse>> = flow {
        emit( safeApiCall(defaultDispatcher) { api.fetchPhotos(query = query, page = page)})
    }

    override fun getRandomPhotos(): Flow<Resource<List<Photo>>>  = flow {
        val photos = photosDao.fetchRandomPhotos(limit = PAGE_SIZE, offset = PAGE_SIZE * currentPage).map { it.toDomain() }
        if (photos.isEmpty()) {
            when(val result = safeApiCall(defaultDispatcher) { api.fetchRandomPhotos()} ){
                is Resource.Success -> {
                    photosDao.insertPhotos(result.data.map { it.toEntity()})
                    emit(Resource.Success(data = photosDao.fetchRandomPhotos(limit = PAGE_SIZE, offset = PAGE_SIZE * currentPage).map { it.toDomain() }))
                }
                is Resource.Error -> {
                    emit(result)
                }
            }
        } else {
            emit(Resource.Success(photos))
        }
    }
        .onCompletion { currentPage += 1 }
        .flowOn(defaultDispatcher)

    override suspend fun cleanPhotos(): Resource<Unit> {
        currentPage = 0
        return try {
            Resource.Success(photosDao.deletePhotos())
        } catch (e:Throwable) {
            Resource.Error(
                message = e.message,
                exception = e
            )
        }
    }

    private companion object {
        const val PAGE_SIZE = 20
        var currentPage = 0
    }
}