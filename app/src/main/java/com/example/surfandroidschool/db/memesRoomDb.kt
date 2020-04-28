package com.example.surfandroidschool.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

public abstract class MemesRoomDb: RoomDatabase() {

    companion object{
        @Volatile
        private var DB:MemesRoomDb?=null

        fun getDb(context: Context):MemesRoomDb{
            return DB?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MemesRoomDb::class.java,
                    "memes_db"
                ).build()
                DB = instance
                instance
            }
        }
    }

}