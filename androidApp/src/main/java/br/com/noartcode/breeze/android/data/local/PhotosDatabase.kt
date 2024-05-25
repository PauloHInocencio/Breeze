package br.com.noartcode.breeze.android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.noartcode.breeze.android.data.local.entity.PhotoEntity
import br.com.noartcode.breeze.android.data.local.entity.PhotographerEntity


@Database(entities = [PhotoEntity::class, PhotographerEntity::class], version = 1, exportSchema = false)
abstract class PhotosDatabase : RoomDatabase() {
        abstract fun photosDao(): PhotosDao
        abstract fun photographerDao() : PhotographerDao

}