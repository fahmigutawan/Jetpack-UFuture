package com.ngikut.u_future.viewmodel.info_jurusan

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.data.repository.Repository
import com.ngikut.u_future.model.remote.response.jurusan.PredictJurusanResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoJurusanViewmodel @Inject constructor(
    private val repository: Repository
) :ViewModel(){
    val searchValueState = mutableStateOf("")

    val top3JurusanState = MutableStateFlow<Resource<PredictJurusanResponse>>(Resource.Loading())

    fun getTop3Jurusan(){
        viewModelScope.launch {
            repository.getTop3Jurusan().collect{
                top3JurusanState.value = it
            }
        }
    }

    init {
        getTop3Jurusan()
    }
}