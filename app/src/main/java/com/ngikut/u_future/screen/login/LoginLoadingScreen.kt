package com.ngikut.u_future.screen.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.util.NavRoute
import com.ngikut.u_future.viewmodel.login.LoginViewmodel
import kotlinx.coroutines.delay

@Composable
fun LoginLoadingScreen(navController: NavController) {
    val viewModel = hiltViewModel<LoginViewmodel>()
    val checkPenjurusanState = viewModel.checkPenjurusanState.collectAsState()

    LaunchedEffect(key1 = checkPenjurusanState.value) {
        when(checkPenjurusanState.value){
            is Resource.Error -> {
                navController.backQueue.clear()
                navController.navigate(NavRoute.Home.name)
            }
            is Resource.Loading -> {/**/}
            is Resource.Success -> {
                checkPenjurusanState.value.data?.let {
                    if(it.data.already_taken){
                        navController.backQueue.clear()
                        navController.navigate(NavRoute.Home.name)
                    }else{
                        navController.backQueue.clear()
                        navController.navigate(NavRoute.PenjurusanLanding.name)
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        delay(2000)
        viewModel.checkPenjurusanState()
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(
            color = AppColor.primary400
        )
    }
}