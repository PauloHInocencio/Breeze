package br.com.noartcode.unsplashapp.android.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photographers")
data class PhotographerEntity(
    @PrimaryKey(autoGenerate = true) val id:Long = 0,
    val originalId: String,
    val bio: String,
    val name: String,
    val firstname: String?,
    val instagramUsername: String?,
    val lastname: String?,
    val location: String?,
    @Embedded
    val profileImage: ProfileImageEntity,
    val totalLikes: Int,
    val totalPhotos: Int
) {
    data class ProfileImageEntity(
        val large: String,
        val medium: String,
        val small: String
    )
}
