package com.example.androidtestlocation.data.dataBase.entity

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "image",
    foreignKeys = [
        ForeignKey(
            entity = LocationEntity::class,
            parentColumns = ["id"],
            childColumns = ["locationId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["locationId"])]
)
data class ImageEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val locationId: Int,
    val bitmap: Bitmap
)