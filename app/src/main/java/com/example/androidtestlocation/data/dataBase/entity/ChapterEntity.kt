package com.example.androidtestlocation.data.dataBase.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "chapter")
data class ChapterEntity (
    @PrimaryKey
    val id: Int =1,
    val name: String

)