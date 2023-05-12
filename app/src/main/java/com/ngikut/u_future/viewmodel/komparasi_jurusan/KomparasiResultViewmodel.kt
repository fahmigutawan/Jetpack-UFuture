package com.ngikut.u_future.viewmodel.komparasi_jurusan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.data.repository.Repository
import com.ngikut.u_future.model.remote.response.komparasi_jurusan.CompareTwoJurusanResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KomparasiResultViewmodel @Inject constructor(
    private val repository: Repository
):ViewModel(){
    val compareTwoJurusanState = MutableStateFlow<Resource<CompareTwoJurusanResponse>>(Resource.Loading())
    fun compare(jurusan1:String, jurusan2:String) {
        viewModelScope.launch {
            repository.compareTwoJurusan(jurusan1, jurusan2).collect{
                compareTwoJurusanState.value = it
            }
        }
    }
}