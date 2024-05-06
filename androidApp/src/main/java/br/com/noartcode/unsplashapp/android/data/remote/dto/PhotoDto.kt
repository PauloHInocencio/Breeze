package br.com.noartcode.unsplashapp.android.data.remote.dto

import com.squareup.moshi.Json

data class PhotoDto(
    val id: String,
    @field:Json(name = "alt_description")
    val altDescription: String?,
    val description: String?,
    val color: String?,
    @field:Json(name = "created_at")
    val createdAt: String,
    val width: Int,
    val height: Int,
    val likes: Int,
    val urls: Urls,
    @field:Json(name = "user")
    val photographer: PhotographerDto?,
) {
    data class Urls(
        val full: String,
        val raw: String,
        val regular: String,
        val small: String,
        val thumb: String
    )
}