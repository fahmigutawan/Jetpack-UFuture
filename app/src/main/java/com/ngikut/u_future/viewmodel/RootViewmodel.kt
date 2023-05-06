package com.ngikut.u_future.viewmodel

import androidx.compose.material.SnackbarData
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RootViewmodel @Inject constructor(

): ViewModel() {
    val snackbarActive = mutableStateOf(false)
    val snackbarMessage = mutableStateOf("")
    val snackbarAction:MutableState<(SnackbarData?) -> Unit> = mutableStateOf({})
    val snackbarActionLabel = mutableStateOf("Tutup")

    val currentRoute = mutableStateOf("")
    val showBottombar = mutableStateOf(false)

    val showLoginSessionEndedDialog = mutableStateOf(false)
}