package com.example.androidtestlocation.data

import android.graphics.Bitmap
import com.example.androidtestlocation.data.dataBase.dao.ChapterDao
import com.example.androidtestlocation.data.dataBase.dao.ImageDao
import com.example.androidtestlocation.data.dataBase.dao.LocationDao
import com.example.androidtestlocation.data.dataBase.entity.ChapterEntity
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
    private val chapterDao: ChapterDao
) : Repository {
    override fun getLocations(): Flow<List<LocationModel>> {
      return   locationDao.getAllLocationsWithImages().map{
          it.map {elem->
            val images = elem.images.map { img ->  ImageModel(id = img.id , bitmap = img.bitmap) }
              LocationModel(id = elem.location.id , name = elem.location.name , images = images)
          }
      }
    }

    override suspend fun deleteImages(list: List<Int>) {
        list.forEach{
            imageDao.deleteImageById(it)
        }

    }

    override suspend fun setChapter(string: String) {
        chapterDao.insertOrUpdate(ChapterEntity(1,string))
    }

    override suspend fun addImages(idL: Int, images: List<Bitmap>  ) {
       val arr  = images.map { ImageEntity(0 , idL, it) }
        imageDao.insertImages(arr)
    }

    override suspend fun setNameOfLocation(id: Int, string: String) {
        locationDao.updateLocationName(id, string)
    }

    override suspend fun addLocation() {
        locationDao.insertLocation(LocationEntity(0,""))
    }

    override suspend fun getChapter(): String {
       return if(chapterDao.getChapter().isEmpty()){
             ""
        }
        else {

           chapterDao.getChapter()[0].name
       }
    }
}