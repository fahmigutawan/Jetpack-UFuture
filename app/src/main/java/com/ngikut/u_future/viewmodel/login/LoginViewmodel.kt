package com.ngikut.u_future.viewmodel.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.ngikut.u_future.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewmodel @Inject constructor(
    private val repository: Repository
):ViewModel() {
    val emailState = mutableStateOf("")
    val passwordState = mutableStateOf("")
}