package com.maida.imagelistapp.database

import androidx.lifecycle.LiveData

class ImageListRepository(private val imagePathDao: ImagePathDao) {

    val allImages: LiveData<List<ImageEntity>> = imagePathDao.getAllImagePath()

    suspend fun insert(imagePathEntity: ImageEntity) {
        imagePathDao.insert(imagePathEntity)
    }

}