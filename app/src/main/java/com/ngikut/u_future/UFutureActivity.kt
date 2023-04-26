package com.ngikut.u_future

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.ngikut.u_future.component.AppBottomBar
import com.ngikut.u_future.screen.login.LoginScreen
import com.ngikut.u_future.screen.onboarding.OnboardingScreen
import com.ngikut.u_future.screen.splash.SplashScreen
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.util.NavRoute
import com.ngikut.u_future.viewmodel.RootViewmodel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class UFutureActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val rootViewmodel by viewModels<RootViewmodel>()
            val navController = rememberNavController()

            navController.addOnDestinationChangedListener { _, destination, _ ->
                rootViewmodel.currentRoute.value = destination.route ?: ""

                rootViewmodel.showBottombar.value = when (destination.route ?: "") {
                    NavRoute.Home.name -> true
                    NavRoute.UBot.name -> true
                    NavRoute.Profile.name -> true
                    else -> false
                }
            }

            Scaffold(
                bottomBar = {
                    if (rootViewmodel.showBottombar.value) {
                        AppBottomBar(
                            currentRoute = rootViewmodel.currentRoute.value,
                            onClick = {
                                navController.navigate(it)
                            }
                        )
                    }
                },
                floatingActionButton = {
                    if (rootViewmodel.showBottombar.value) {
                        FloatingActionButton(
                            backgroundColor = AppColor.primary400,
                            onClick = { navController.navigate(route = NavRoute.UBot.name) }
                        ) {
                            Icon(
                                painter = rememberAsyncImagePainter(model = R.drawable.bottombar_ubot),
                                contentDescription = "",
                                tint = Color.Unspecified
                            )
                        }
                    }
                },
                isFloatingActionButtonDocked = true,
                floatingActionButtonPosition = FabPosition.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = NavRoute.Splash.name
                    ) {
                        composable(NavRoute.Splash.name){
                            SplashScreen(navController = navController)
                        }
                        
                        composable(NavRoute.Home.name) {

                        }

                        composable(NavRoute.UBot.name) {

                        }

                        composable(NavRoute.Profile.name) {

                        }

                        composable(NavRoute.Onboard.name) {
                            OnboardingScreen(navController = navController)
                        }

                        composable(NavRoute.Login.name){
                            LoginScreen(navController = navController)
                        }

                        composable(NavRoute.Register.name){

                        }
                    }
                }
            }
        }
    }
}

@HiltAndroidApp
class MyApplication : Application()