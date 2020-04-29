package com.example.surfandroidschool.vm

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.surfandroidschool.db.MemesRoomDb
import com.example.surfandroidschool.memes.MemeData
import com.example.surfandroidschool.repository.MemesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MemesViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: MemesRepository
    val allMemes: LiveData<List<MemeData>>

    init {
        val memeDao = MemesRoomDb.getDb(application).memeDao()
        repository = MemesRepository(memeDao)
        allMemes = repository.allMemes
    }

    fun insert(meme: MemeData) = scope.launch(Dispatchers.IO) {
        repository.insert(meme)
    }

    fun delete() = scope.launch(Dispatchers.IO) {
        repository.delete()
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}