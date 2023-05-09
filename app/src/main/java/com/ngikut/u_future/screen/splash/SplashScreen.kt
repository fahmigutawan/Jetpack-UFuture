package com.ngikut.u_future.screen.splash

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ngikut.u_future.R
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.util.NavRoute
import com.ngikut.u_future.viewmodel.splash.SplashViewmodel

@Composable
fun SplashScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<SplashViewmodel>()
    val iconWidth = LocalConfiguration.current.screenWidthDp / 2
    val checkPenjurusanState = viewModel.checkPenjurusanState.collectAsState()

    LaunchedEffect(key1 = checkPenjurusanState.value) {
        if (viewModel.startCheckingPenjurusanState.value) {
            when (checkPenjurusanState.value) {
                is Resource.Error -> {
                    navController.navigate(NavRoute.Home.name) {
                        popUpTo(NavRoute.Splash.name) {
                            inclusive = true
                        }
                    }
                    viewModel.startCheckingPenjurusanState.value = false
                }
                is Resource.Loading -> {/*TODO*/
                }
                is Resource.Success -> {
                    checkPenjurusanState.value.data?.let {
                        if (it.data.already_taken) {
                            navController.navigate(NavRoute.Home.name) {
                                popUpTo(NavRoute.Splash.name) {
                                    inclusive = true
                                }
                            }
                        } else {
                            navController.navigate(NavRoute.PenjurusanLanding.name) {
                                popUpTo(NavRoute.Splash.name) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                    viewModel.startCheckingPenjurusanState.value = false
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.getFirstTimeState { firstTime ->
            if (firstTime) {
                navController.navigate(NavRoute.Onboard.name) {
                    popUpTo(NavRoute.Splash.name) {
                        inclusive = true
                    }
                }
            } else {
                viewModel.getToken { token ->
                    if (token.isEmpty()) {
                        navController.navigate(NavRoute.Login.name) {
                            popUpTo(NavRoute.Splash.name) {
                                inclusive = true
                            }
                        }
                    } else {
                        viewModel.startCheckingPenjurusanState.value = true
                    }
                }

            }
        }
    }

    if (viewModel.startCheckingPenjurusanState.value) {
        LaunchedEffect(key1 = true) {
            viewModel.checkPenjurusanState()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.grey50),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier.width(iconWidth.dp),
            model = R.drawable.splashscreen_icon,
            contentDescription = ""
        )
    }
}