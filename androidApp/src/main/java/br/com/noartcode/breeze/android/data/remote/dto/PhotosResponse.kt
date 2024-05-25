package br.com.noartcode.breeze.android.data.remote.dto

import com.squareup.moshi.Json

data class PhotosResponse(
    @field:Json(name= "results")  val photos: List<PhotoDto>,
    @field:Json(name = "total") val totalPhotos: Int,
    @field:Json(name = "total_pages") val totalPages: Int
)