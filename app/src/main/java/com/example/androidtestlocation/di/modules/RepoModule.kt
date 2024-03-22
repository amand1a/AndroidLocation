package com.example.androidtestlocation.di.modules

import com.example.androidtestlocation.data.LocationRepository
import com.example.androidtestlocation.domain.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepoModule {

    @Binds
    fun bindsCocktailRepo(
        impl: LocationRepository
    ): Repository
}