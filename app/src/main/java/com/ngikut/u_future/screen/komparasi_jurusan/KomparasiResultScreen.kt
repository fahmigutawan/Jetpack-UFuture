package com.ngikut.u_future.screen.komparasi_jurusan

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ngikut.u_future.viewmodel.komparasi_jurusan.KomparasiResultViewmodel

@Composable
fun KomparasiResultScreen(
    navController: NavController,
    jurusan1:String,
    jurusan2:String
) {
    val viewModel = hiltViewModel<KomparasiResultViewmodel>()
    val compareJurusanState = viewModel.compareTwoJurusanState.collectAsState()

    LaunchedEffect(key1 = true){
        viewModel.compare(jurusan1, jurusan2)
    }
}