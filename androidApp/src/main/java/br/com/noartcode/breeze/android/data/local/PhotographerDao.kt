package br.com.noartcode.breeze.android.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.noartcode.breeze.android.data.local.entity.PhotographerEntity

@Dao
interface PhotographerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPhotographers(photographers: List<PhotographerEntity>)

    @Query("SELECT * FROM photographers ORDER BY id DESC LIMIT :limit OFFSET :offset")
    suspend fun fetchPhotographers(limit: Int, offset: Int) : List<PhotographerEntity>

}