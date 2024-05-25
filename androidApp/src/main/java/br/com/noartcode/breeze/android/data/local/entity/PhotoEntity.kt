package br.com.noartcode.breeze.android.data.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "photos",
    /*foreignKeys = [
        ForeignKey(
            entity = PhotographerEntity::class,
            parentColumns = ["id"],
            childColumns = ["photographerId"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]*/
)
data class PhotoEntity(
    @PrimaryKey(autoGenerate = true) val id:Long = 0,
    val originalId:String,
    val photographerId: String,
    val altDescription: String?,
    val description: String?,
    val color: String?,
    val createdAt: String,
    val width: Int,
    val height: Int,
    val likes: Int,
    @Embedded
    val urls: UrlsEntity,
) {
    data class UrlsEntity(
        val full: String,
        val raw: String,
        val regular: String,
        val small: String,
        val thumb: String
    )
}