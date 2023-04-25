package com.ngikut.u_future

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Scaffold
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ngikut.u_future.component.AppBottomBar
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
            }

            Scaffold(
                bottomBar = {
                    AppBottomBar(
                        currentRoute = rootViewmodel.currentRoute.value,
                        onClick = {
                            navController.navigate(it)
                        }
                    )
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = NavRoute.Home.name
                ) {
                    composable(NavRoute.Home.name){

                    }

                    composable(NavRoute.UBot.name){

                    }

                    composable(NavRoute.Profile.name){

                    }
                }
            }
        }
    }
}

@HiltAndroidApp
class MyApplication : Application()