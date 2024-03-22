package com.example.androidtestlocation.data.dataBase.dao

import androidx.room.*
import com.example.androidtestlocation.data.dataBase.entity.ImageEntity

@Dao
interface ImageDao {
    @Query("SELECT * FROM image WHERE locationId = :locationId")
    suspend fun getImagesByLocationId(locationId: Int): List<ImageEntity>

    @Insert
    suspend fun insertImages(images: List<ImageEntity>)

    @Query("DELETE FROM image WHERE id = :imageId")
    suspend fun deleteImageById(imageId: Int)
}