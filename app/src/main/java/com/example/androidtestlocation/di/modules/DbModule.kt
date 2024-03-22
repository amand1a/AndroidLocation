package com.example.androidtestlocation.di.modules

import android.content.Context
import com.example.androidtestlocation.data.dataBase.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DbModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.invoke(context) as AppDatabase

    @Provides
    fun provideLocationDao(database: AppDatabase) = database.LocationDao()

    @Provides
    fun provideImageDao(database: AppDatabase) = database.imageDao()

}