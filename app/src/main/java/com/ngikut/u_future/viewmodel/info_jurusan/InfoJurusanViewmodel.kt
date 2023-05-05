package com.ngikut.u_future.viewmodel.info_jurusan

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ngikut.u_future.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InfoJurusanViewmodel @Inject constructor(
    private val repository: Repository
) :ViewModel(){
    val searchState = mutableStateOf("")
}