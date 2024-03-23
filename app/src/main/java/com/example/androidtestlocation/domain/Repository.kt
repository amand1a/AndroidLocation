package com.example.androidtestlocation.domain

import android.graphics.Bitmap
import com.example.androidtestlocation.domain.models.LocationModel
import kotlinx.coroutines.flow.Flow


interface Repository {

    fun getLocations(): Flow<List<LocationModel>>


    suspend fun deleteImages(list: List<Int>)

    suspend fun setChapter(string: String)

    suspend fun addImages(idL: Int ,images: List<Bitmap>)

    suspend fun setNameOfLocation(id: Int , string: String)

    suspend fun addLocation()

}