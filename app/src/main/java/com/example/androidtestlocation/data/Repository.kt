package com.example.androidtestlocation.data

import android.graphics.Bitmap
import com.example.androidtestlocation.data.dataBase.dao.ImageDao
import com.example.androidtestlocation.data.dataBase.dao.LocationDao
import com.example.androidtestlocation.data.dataBase.entity.ImageEntity
import com.example.androidtestlocation.data.dataBase.entity.LocationEntity
import com.example.androidtestlocation.domain.Repository
import com.example.androidtestlocation.domain.models.ImageModel
import com.example.androidtestlocation.domain.models.LocationModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocationRepository @Inject constructor(
    private val locationDao: LocationDao,
    private val imageDao: ImageDao,
) : Repository {
    override fun getLocations(): Flow<List<LocationModel>> {
      return   locationDao.getAllLocationsWithImages().map{
          it.map {elem->
            val images = elem.images.map { img ->  ImageModel(id = img.id , bitmap = img.bitmap) }
              LocationModel(id = elem.location.id , name = elem.location.name , images = images)
          }
      }
    }

    override suspend fun deleteImages(id: Int) {
        imageDao.deleteImageById(id)
    }

    override suspend fun setChapter(string: String) {
        TODO("Not yet implemented")
    }

    override suspend fun addImages(idL: Int, images: List<Bitmap>  ) {
       val arr  = images.map { ImageEntity(0 , idL, it) }
    }

    override suspend fun setNameOfLocation(id: Int, string: String) {
        locationDao.updateLocationName(id, string)
    }

    override suspend fun addLocation() {
        locationDao.insertLocation(LocationEntity(0,""))
    }
}