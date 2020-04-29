package com.example.surfandroidschool.memes

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "memes_table")
data class MemeData(
    var title: String, var photoUrl: String, var isFavorite: Boolean,
    var description: String, var createdDate: String, @PrimaryKey var id: String
) : Serializable