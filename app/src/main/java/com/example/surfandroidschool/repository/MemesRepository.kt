package com.example.surfandroidschool.repository

import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.surfandroidschool.dao.MemeDao
import com.example.surfandroidschool.db.MemesRoomDb
import com.example.surfandroidschool.memes.MemeData
import io.reactivex.Completable
import io.reactivex.Flowable


class MemesRepository(
    private val memesDao: MemeDao
) {
    var allMemes: LiveData<List<MemeData>> = memesDao.getAllMemes()

    @WorkerThread
    fun getAll(): LiveData<List<MemeData>> {
        return memesDao.getAllMemes()
    }

    @WorkerThread
    fun insert(meme: MemeData) {
        return memesDao.insert(meme)
    }

    @WorkerThread
    fun delete() {
        memesDao.delete()
    }


}