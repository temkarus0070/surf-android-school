package com.example.surfandroidschool.dao

import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import com.example.surfandroidschool.memes.MemeData

interface MemeDao {
    @Query("SELECT * from memes_table")
    fun getAllMemes():LiveData<List<MemeData>>

    @Insert
    fun insert(meme:MemeData)


}