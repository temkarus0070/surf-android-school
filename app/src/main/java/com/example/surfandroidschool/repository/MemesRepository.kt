package com.example.surfandroidschool.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.surfandroidschool.dao.MemeDao
import com.example.surfandroidschool.memes.MemeData

class MemesRepository( private val memesDao: MemeDao) {
   val allMemes:LiveData<List<MemeData>> = memesDao.getAllMemes()

    @WorkerThread
    suspend fun insert(meme:MemeData){
        memesDao.insert(meme)
    }
}