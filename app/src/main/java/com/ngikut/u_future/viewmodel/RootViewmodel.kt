package com.ngikut.u_future.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RootViewmodel @Inject constructor(

): ViewModel() {
    val currentRoute = mutableStateOf("")
    val showBottombar = mutableStateOf(false)
}