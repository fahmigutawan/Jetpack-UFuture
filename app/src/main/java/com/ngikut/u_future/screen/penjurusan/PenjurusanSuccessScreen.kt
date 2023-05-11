package com.ngikut.u_future.screen.penjurusan

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ngikut.u_future.viewmodel.penjurusan.PenjurusanSuccessViewmodel

@Composable
fun PenjurusanSuccessScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<PenjurusanSuccessViewmodel>()
    val getQuizAnalysisState = viewModel.getQuizAnalysisState.collectAsState()

    LaunchedEffect(key1 = true){
        viewModel.getQuizAnalysis()
    }
}

