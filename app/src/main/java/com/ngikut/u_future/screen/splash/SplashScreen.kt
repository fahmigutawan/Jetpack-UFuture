package com.ngikut.u_future.screen.splash

import android.window.SplashScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ngikut.u_future.R
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.util.NavRoute
import com.ngikut.u_future.viewmodel.splash.SplashViewmodel

@Composable
fun SplashScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<SplashViewmodel>()
    val iconWidth = LocalConfiguration.current.screenWidthDp / 2

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
                    if(token.isEmpty()){
                        navController.navigate(NavRoute.Login.name) {
                            popUpTo(NavRoute.Splash.name) {
                                inclusive = true
                            }
                        }
                    }else{
                        navController.navigate(NavRoute.Home.name) {
                            popUpTo(NavRoute.Splash.name) {
                                inclusive = true
                            }
                        }
                    }
                }

            }
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