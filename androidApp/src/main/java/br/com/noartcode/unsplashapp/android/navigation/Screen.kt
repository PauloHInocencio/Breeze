package br.com.noartcode.unsplashapp.android.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object RandomPhotosRoot: Screen()
    @Serializable
    data object RandomPhotosList:Screen()
    @Serializable
    data class RandomPhotosDetail(val id:Long): Screen()

    @Serializable
    data object SearchedPhotosRoot: Screen()

    @Serializable
    data object SearchedPhotosList:Screen()
    @Serializable
    data class SearchedPhotosDetail(val id:Long): Screen()

    @Serializable
    data object LikedPhotosRoot: Screen()

    @Serializable
    data object LikedPhotosList:Screen()
    @Serializable
    data class LikedPhotosDetail(val id:Long): Screen()
}