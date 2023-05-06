package com.ngikut.u_future

import android.annotation.SuppressLint
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.rememberAsyncImagePainter
import com.ngikut.u_future.component.AppBottomBar
import com.ngikut.u_future.component.AppButton
import com.ngikut.u_future.component.AppSnackbar
import com.ngikut.u_future.component.AppText
import com.ngikut.u_future.screen.home.HomeScreen
import com.ngikut.u_future.screen.info_jurusan.InfoJurusanByFakultasScreen
import com.ngikut.u_future.screen.info_jurusan.InfoJurusanOnSearchScreen
import com.ngikut.u_future.screen.info_jurusan.InfoJurusanScreen
import com.ngikut.u_future.screen.komparasi_jurusan.KomparasiJurusanScreen
import com.ngikut.u_future.screen.login.LoginScreen
import com.ngikut.u_future.screen.onboarding.OnboardingScreen
import com.ngikut.u_future.screen.penjurusan.PenjurusanLandingScreen
import com.ngikut.u_future.screen.penjurusan.PenjurusanScreen
import com.ngikut.u_future.screen.splash.SplashScreen
import com.ngikut.u_future.screen.ubot.UbotScreen
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType
import com.ngikut.u_future.util.NavRoute
import com.ngikut.u_future.viewmodel.RootViewmodel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class UFutureActivity : ComponentActivity() {
    private lateinit var navController: NavHostController
    private lateinit var rootViewmodel: RootViewmodel

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            navController = rememberNavController()
            rootViewmodel = viewModel()
            val scaffoldState = rememberScaffoldState()
            val showSnackbar: (message: String) -> Unit = { message ->
                rootViewmodel.snackbarMessage.value = message
                rootViewmodel.snackbarActive.value = true
            }
            val showSnackbarWithAction: (
                message: String,
                actionLabel: String,
                action: (SnackbarData?) -> Unit,
            ) -> Unit = { message, actionLabel, action ->
                rootViewmodel.snackbarActionLabel.value = actionLabel
                rootViewmodel.snackbarAction.value = action
                rootViewmodel.snackbarMessage.value = message
                rootViewmodel.snackbarActive.value = true
            }
            if (rootViewmodel.snackbarActive.value) {
                LaunchedEffect(key1 = true) {
                    val resetSnackbarState = {
                        rootViewmodel.snackbarAction.value = {}
                        rootViewmodel.snackbarActionLabel.value = "Tutup"
                        rootViewmodel.snackbarMessage.value = ""
                        rootViewmodel.snackbarActive.value = false
                    }
                    val snackbarData = scaffoldState.snackbarHostState.showSnackbar(
                        message = rootViewmodel.snackbarMessage.value,
                        actionLabel = rootViewmodel.snackbarActionLabel.value,
                        duration = SnackbarDuration.Short
                    )

                    when (snackbarData) {
                        SnackbarResult.Dismissed -> {
                            resetSnackbarState()
                        }

                        SnackbarResult.ActionPerformed -> {
                            when (rootViewmodel.snackbarActionLabel.value) {
                                "Tutup" -> {
                                    scaffoldState.snackbarHostState.currentSnackbarData?.performAction()
                                    resetSnackbarState()
                                }

                                else -> {
                                    rootViewmodel.snackbarAction.value(scaffoldState.snackbarHostState.currentSnackbarData)
                                    resetSnackbarState()
                                }
                            }
                        }
                    }
                }
            }
            if (rootViewmodel.showLoginSessionEndedDialog.value) {
                Dialog(
                    onDismissRequest = { /*TODO*/ },
                    properties = DialogProperties(
                        dismissOnBackPress = false,
                        dismissOnClickOutside = false
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .background(AppColor.grey50)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AppText(text = "Sesi Login Habis", style = AppType.h3)
                            AppText(
                                text = "Harap lakukan login ulang untuk melanjutkan menggunakan aplikasi U-Future",
                                style = AppType.subheading3,
                                textAlign = TextAlign.Center
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                AppButton(
                                    onClick = { System.exit(0) },
                                    text = "Keluar",
                                    textColor = AppColor.primary400,
                                    backgroundColor = AppColor.grey50,
                                    borderColor = AppColor.primary400,
                                    borderWidth = 1.dp
                                )
                                AppButton(
                                    onClick = {
                                        navController.backQueue.clear()
                                        navController.navigate(NavRoute.Login.name)
                                        rootViewmodel.showLoginSessionEndedDialog.value = false
                                    },
                                    text = "Login"
                                )
                            }
                        }
                    }
                }
            }

            navController.addOnDestinationChangedListener { _, destination, _ ->
                rootViewmodel.currentRoute.value = destination.route ?: ""

                rootViewmodel.showBottombar.value = when (destination.route ?: "") {
                    NavRoute.Home.name -> true
                    NavRoute.Comparation.name -> true
                    NavRoute.Favorite.name -> true
                    NavRoute.Profile.name -> true
                    else -> false
                }
            }

            Scaffold(
                scaffoldState = scaffoldState,
                snackbarHost = {
                    AppSnackbar(hostState = it)
                },
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
                NavHost(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = it.calculateBottomPadding()),
                    navController = navController,
                    startDestination = NavRoute.Splash.name
                ) {
                    composable(NavRoute.Splash.name) {
                        SplashScreen(navController = navController)
                    }

                    composable(NavRoute.Home.name) {
                        HomeScreen(navController = navController)
                    }

                    composable(NavRoute.Comparation.name) {
                        KomparasiJurusanScreen(navController = navController)
                    }

                    composable(NavRoute.UBot.name) {
                        UbotScreen(navController = navController)
                    }

                    composable(NavRoute.Favorite.name) {

                    }

                    composable(NavRoute.Profile.name) {

                    }

                    composable(NavRoute.Onboard.name) {
                        OnboardingScreen(navController = navController)
                    }

                    composable(NavRoute.Login.name) {
                        LoginScreen(navController = navController, showSnackbar = showSnackbar)
                    }

                    composable(NavRoute.Register.name) {

                    }

                    composable(NavRoute.PenjurusanLanding.name) {
                        PenjurusanLandingScreen(
                            navController = navController
                        )
                    }

                    composable(NavRoute.Penjurusan.name){
                        PenjurusanScreen(navController = navController)
                    }

                    composable(NavRoute.InfoJurusan.name) {
                        InfoJurusanScreen(
                            navController = navController
                        )
                    }

                    composable(NavRoute.InfoKampus.name) {

                    }

                    composable(NavRoute.InfoBeasiswa.name) {

                    }

                    composable(
                        "${NavRoute.InfoJurusanOnSearch.name}/{query}",
                        arguments = listOf(
                            navArgument(name = "query") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val query = it.arguments?.getString("query") ?: ""
                        InfoJurusanOnSearchScreen(
                            navController = navController,
                            query = query
                        )
                    }

                    composable(
                        "${NavRoute.InfoJurusanByFakultas.name}/{name}",
                        arguments = listOf(
                            navArgument(name = "name") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val name = it.arguments?.getString("name") ?: ""
                        InfoJurusanByFakultasScreen(
                            navController = navController,
                            fakultasName = name
                        )
                    }
                }
            }
        }
    }

    fun showSessionEndedDialog() {
        rootViewmodel.showLoginSessionEndedDialog.value = true
    }
}

@HiltAndroidApp
class MyApplication : Application()