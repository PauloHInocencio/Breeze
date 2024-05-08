package br.com.noartcode.unsplashapp.android.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.noartcode.unsplashapp.android.data.local.entity.PhotoEntity

@Dao
interface PhotosDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhotos(photos: List<PhotoEntity>)

    @Query("SELECT * FROM photos  ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun fetchRandomPhotos(limit: Int, offset:Int) : List<PhotoEntity>

    @Query("SELECT * FROM photos WHERE id == :id")
    suspend fun getPhoto(id: Long) : PhotoEntity?

    @Query("DELETE FROM photos")
    suspend fun deletePhotos()


}