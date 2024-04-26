package br.com.noartcode.unsplashapp.android.api.entry

import com.squareup.moshi.Json

data class Photo(
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
    val photographer: Photographer?,
) {
    data class Urls(
        val full: String,
        val raw: String,
        val regular: String,
        val small: String,
        val thumb: String
    )
}