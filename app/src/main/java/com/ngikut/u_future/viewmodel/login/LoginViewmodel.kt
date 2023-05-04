package com.ngikut.u_future.viewmodel.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.data.repository.Repository
import com.ngikut.u_future.model.remote.response.student.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor(
    private val repository: Repository
):ViewModel() {
    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
    val showPassword = mutableStateOf(false)

    val loginState = MutableStateFlow<Resource<LoginResponse>>(Resource.Loading())

    fun login(){
        viewModelScope.launch {
            repository.login(emailState.value, passwordState.value).collect{
                loginState.value = it
            }
        }
    }

    fun saveToken(token:String){
        viewModelScope.launch {
            repository.setToken(token)
        }
    }
}