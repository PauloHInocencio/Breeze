package br.com.noartcode.unsplashapp.android.api.entry

import com.squareup.moshi.Json

data class PhotosResponse(
    @field:Json(name= "results")  val photos: List<Photo>,
    @field:Json(name = "total") val totalPhotos: Int,
    @field:Json(name = "total_pages") val totalPages: Int
)