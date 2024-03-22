package com.example.androidtestlocation.data.dataBase.dao

import androidx.room.*
import com.example.androidtestlocation.data.dataBase.entity.LocationEntity
import com.example.androidtestlocation.data.dataBase.model.LocationWithImages
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Transaction
    @Query("SELECT * FROM location")
    fun getAllLocationsWithImages(): Flow< List<LocationWithImages>>

    @Query("UPDATE location SET name = :newName WHERE id = :locationId")
    suspend fun updateLocationName(locationId: Int, newName: String)

    @Insert()
    suspend fun insertLocation(location: LocationEntity)

    @Delete
    suspend fun deleteLocation(location: LocationEntity)
}