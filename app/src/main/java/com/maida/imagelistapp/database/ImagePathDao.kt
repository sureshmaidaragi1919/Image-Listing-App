package com.maida.imagelistapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImagePathDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: ImageEntity)

    @Query("Select * From image_path_table ")
    fun getAllImagePath(): LiveData<List<ImageEntity>>

    @Query("Delete from image_path_table")
    suspend fun deleteAll()
}