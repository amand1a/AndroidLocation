package com.example.androidtestlocation.presentation.viewModels

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtestlocation.data.LocationRepository
import com.example.androidtestlocation.domain.models.ImageModel
import com.example.androidtestlocation.domain.models.LocationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LocationItemViewModel @Inject constructor(
    private val  rep: LocationRepository
): ViewModel() {

    private val _state = MutableStateFlow<LocationModel>(LocationModel(0, "", listOf()))

    val state = _state.asStateFlow()

    private val _selectedImagesForDelete = MutableStateFlow<MutableList<Int>>(mutableListOf())

    val selectedImagesForDelete = _selectedImagesForDelete.asStateFlow()
    fun  init(location: LocationModel){
        _state.value  = location
    }



    fun updateLocationName(str: String){
        _state.update {
            it.copy(name = str)
        }
    }
    fun saveLocationName(){
        viewModelScope.launch {
            rep.setNameOfLocation(state.value.id , state.value.name)
        }
    }

    fun updateNewImage(images: List<Bitmap>){
        viewModelScope.launch {
            rep.addImages(state.value.id , images)
        }
    }

    fun deleteImages(){
        viewModelScope.launch {
            rep.deleteImages(selectedImagesForDelete.value)
            _selectedImagesForDelete.value = mutableListOf()
        }
    }

    fun addImageToDelete(id: Int){
        _selectedImagesForDelete.update {
            it.apply { add(id) }
        }
    }


}



