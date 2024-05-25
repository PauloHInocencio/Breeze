package br.com.noartcode.breeze.android.data.remote.dto

import com.squareup.moshi.Json

data class PhotographerDto(
    val id: String,
    val bio: String,
    val name: String,
    @field:Json(name = "first_name")
    val firstname: String?,
    @field:Json(name = "instagram_username")
    val instagramUsername: String?,
    @field:Json(name = "last_name")
    val lastname: String?,
    val location: String?,
    @field:Json(name = "profile_image")
    val profileImage: ProfileImage,
    @field:Json(name = "total_likes")
    val totalLikes: Int,
    @field:Json(name = "total_photos")
    val totalPhotos: Int
) {
    data class ProfileImage(
        val large: String,
        val medium: String,
        val small: String
    )
}