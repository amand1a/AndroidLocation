package com.example.androidtestlocation.data.dataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.androidtestlocation.data.dataBase.converter.BitmapConverter
import com.example.androidtestlocation.data.dataBase.dao.ImageDao
import com.example.androidtestlocation.data.dataBase.dao.LocationDao
import com.example.androidtestlocation.data.dataBase.entity.ImageEntity
import com.example.androidtestlocation.data.dataBase.entity.LocationEntity

@Database(
    entities = [LocationEntity::class , ImageEntity::class],
    version = 1, exportSchema = false
)
@TypeConverters(
    BitmapConverter::class,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun imageDao(): ImageDao
    abstract fun LocationDao(): LocationDao



    companion object {
        private const val DB_NAME = "app.db"

        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context?) = instance ?: synchronized(LOCK) {
            context?.let {
                buildDatabase(it).apply {
                    instance = this
                }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }
}