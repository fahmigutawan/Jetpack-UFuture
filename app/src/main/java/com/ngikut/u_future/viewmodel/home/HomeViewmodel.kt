package com.ngikut.u_future.viewmodel.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.data.repository.Repository
import com.ngikut.u_future.model.remote.response.quiz.CheckPenjurusanStateResponse
import com.ngikut.u_future.model.remote.response.student.GetProfileResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val repository: Repository
):ViewModel() {
    val checkPenjurusanState = MutableStateFlow<Resource<CheckPenjurusanStateResponse>>(Resource.Loading())
    val getProfileState = MutableStateFlow<Resource<GetProfileResponse>>(Resource.Loading())

    fun getProfile(){
        viewModelScope.launch {
            repository.getProfile().collect{
                getProfileState.value = it
            }
        }
    }

    fun checkPenjurusanState(){
        viewModelScope.launch {
            repository.checkPenjurusanState().collect{
                checkPenjurusanState.value = it
            }
        }
    }
    init {
        getProfile()
        checkPenjurusanState()
    }
}