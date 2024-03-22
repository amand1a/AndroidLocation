package com.example.androidtestlocation.data.dataBase.model

import androidx.room.Embedded
import androidx.room.Relation
import com.example.androidtestlocation.data.dataBase.entity.ImageEntity
import com.example.androidtestlocation.data.dataBase.entity.LocationEntity

data class LocationWithImages(
    @Embedded val location: LocationEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "locationId"
    )
    val images: List<ImageEntity>
)
