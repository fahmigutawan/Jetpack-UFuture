package com.ngikut.u_future.viewmodel.komparasi_jurusan

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.data.repository.Repository
import com.ngikut.u_future.model.remote.response.base.SingleJurusanResponse
import com.ngikut.u_future.model.remote.response.jurusan.PredictJurusanResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KomparasiJurusanViewmodel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val jurusanRecommendation = mutableStateListOf<SingleJurusanResponse>()
    val jurusan1Picked = mutableStateOf<SingleJurusanResponse?>(null)
    val jurusan2Picked = mutableStateOf<SingleJurusanResponse?>(null)

    fun getPredictJurusan() {
        viewModelScope.launch {
            repository.getPredictJurusan().collect{
                if(it is Resource.Success){
                    it.data?.let {
                        jurusanRecommendation.addAll(it.data)
                    }
                }
            }
        }
    }

    init {
        getPredictJurusan()
    }
}