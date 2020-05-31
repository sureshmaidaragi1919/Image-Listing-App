package com.maida.imagelistapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.maida.imagelistapp.database.AppDB
import com.maida.imagelistapp.database.ImageEntity
import com.maida.imagelistapp.database.ImageListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class ImagePathViewModel(application: Application) : AndroidViewModel(application) {

    private val imageListRepository: ImageListRepository
    var allImagePaths: LiveData<List<ImageEntity>>
    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CloseableCoroutineScope(Dispatchers.IO + viewModelJob)

    init {
        val imagePathDao = AppDB.getDatabase(application, viewModelScope).ImagePathDao()
        imageListRepository = ImageListRepository(imagePathDao)
        allImagePaths = imageListRepository.allImages
    }

    fun insert(imageEntity: ImageEntity) = viewModelScope.launch(Dispatchers.IO) {
        imageListRepository.insert(imageEntity)
    }

}