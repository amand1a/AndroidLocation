package com.example.androidtestlocation.presentation.viewModels

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtestlocation.R
import com.example.androidtestlocation.data.LocationRepository
import com.example.androidtestlocation.domain.models.ImageModel
import com.example.androidtestlocation.domain.models.LocationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LocationViewModel @Inject constructor(private val repository: LocationRepository) : ViewModel() {


    private val _state = MutableStateFlow<String>("")

    val state  = _state.asStateFlow()
    
    val locations = repository.getLocations().stateIn(
        scope =  viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = listOf<LocationModel>()

    )

    fun updateChapter(str: String){
        _state.value = str
    }


    fun changeChapterName(){
        viewModelScope.launch {

        }
    }
}






