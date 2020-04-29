package com.example.surfandroidschool.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.surfandroidschool.memes.MemeData
import io.reactivex.Completable
import io.reactivex.Flowable


@Dao
interface MemeDao {
    @Query("SELECT * from memes_table  ORDER BY id ASC")
    fun getAllMemes(): LiveData<List<MemeData>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(meme:MemeData)

    @Query("DELETE  from memes_table")
    fun delete()


}