package com.ngikut.u_future.viewmodel.splash

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.data.repository.Repository
import com.ngikut.u_future.model.remote.response.quiz.CheckPenjurusanStateResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewmodel @Inject constructor(
    private val repository: Repository
):ViewModel() {
    val checkPenjurusanState = MutableStateFlow<Resource<CheckPenjurusanStateResponse>>(Resource.Loading())
    val startCheckingPenjurusanState = mutableStateOf(false)

    fun getFirstTimeState(
        onRetrieved:(Boolean) -> Unit
    ) = viewModelScope.launch {
        delay(2000)

        repository.getFirstTimeState().collectLatest {
            onRetrieved(it)
        }
    }

    fun getToken(
        onRetrieved: (String) -> Unit
    ) = viewModelScope.launch {
        repository.getToken().collectLatest {
            onRetrieved(it)
        }
    }

    fun checkPenjurusanState(){
        viewModelScope.launch {
            repository.checkPenjurusanState().collect{
                checkPenjurusanState.value = it
            }
        }
    }
}