package com.example.surfandroidschool.db

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.surfandroidschool.dao.MemeDao
import com.example.surfandroidschool.memes.MemeData
import kotlinx.coroutines.internal.synchronized

@Database(entities = [MemeData::class], version = 1)
abstract class MemesRoomDb: RoomDatabase() {

    abstract fun memeDao(): MemeDao

    companion object {
        @Volatile
        private var DB: MemesRoomDb? = null

        fun getDb(context: Context): MemesRoomDb {
            if (DB == null) {
                kotlin.synchronized(MemesRoomDb::class) {
                    DB = Room.databaseBuilder(
                        context.applicationContext,
                        MemesRoomDb
                        ::class.java,
                        "memes_db"
                    ).allowMainThreadQueries()
                        .build()

                }
            }
            return DB!!
        }

        fun destroyDb(){
            DB = null
        }
    }
}
