package br.com.noartcode.unsplashapp.android.data.remote

import br.com.noartcode.unsplashapp.android.data.remote.dto.PhotoDto
import br.com.noartcode.unsplashapp.android.data.remote.dto.PhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotosRetrofitApi {

    companion object {
        const val BASE_URL = "https://api.unsplash.com/"
    }


    @GET("search/photos")
    suspend fun fetchPhotos(
        @Query("query") query: String,
        @Query("page") page:Int,
        @Query("per_page") perPage:Int = 20
    ) : PhotosResponse

    @GET("photos/random")
    suspend fun fetchRandomPhotos(
        @Query("count") count:Int = 20
    ) : List<PhotoDto>
}