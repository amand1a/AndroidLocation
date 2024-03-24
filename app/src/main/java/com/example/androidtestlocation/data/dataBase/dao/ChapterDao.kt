package com.example.androidtestlocation.data.dataBase.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidtestlocation.data.dataBase.entity.ChapterEntity


@Dao
interface ChapterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(chapterEntity: ChapterEntity)

    @Query("SELECT * FROM chapter")
    fun getChapter(): List<ChapterEntity>
}