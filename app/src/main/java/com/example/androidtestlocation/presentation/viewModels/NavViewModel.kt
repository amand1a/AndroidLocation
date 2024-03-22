package com.example.androidtestlocation.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtestlocation.data.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class NavViewModel @Inject constructor (
    private val  rep: LocationRepository
): ViewModel(){

    fun addLocation(){
        viewModelScope.launch {
        rep.addLocation()}
    }
}