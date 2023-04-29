package com.ngikut.u_future.screen.penjurusan

import android.widget.Space
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ngikut.u_future.R
import com.ngikut.u_future.component.AppBottomSheetScaffold
import com.ngikut.u_future.component.AppButton
import com.ngikut.u_future.component.AppText
import com.ngikut.u_future.component.AppTextButton
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType
import com.ngikut.u_future.viewmodel.penjurusan.PenjurusanLandingViewmodel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PenjurusanLandingScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<PenjurusanLandingViewmodel>()
    val maxIconWidth = LocalConfiguration.current.screenWidthDp / 3.5
    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    val coroutineScope = rememberCoroutineScope()

    BackHandler {
        if (bottomSheetState.isExpanded) {
            coroutineScope.launch {
                bottomSheetState.collapse()
            }
        } else {
            navController.popBackStack()
        }
    }

    AppBottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColor.grey50)
            ) {
                Column(modifier = Modifier.padding(32.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    Column {
                        AppText(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Tes Penjurusan",
                            style = AppType.h2,
                            textAlign = TextAlign.Center
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            /*TODO Some tags item here*/
                        }
                    }

                    Column {
                        AppText(
                            text = "Deskripsi",
                            style = AppType.h3,
                        )
                        AppText(
                            text = "Tes ini dirancang khusus untuk membantu kamu mencari jurusan yang sesuai dengan kepribadian,minat, serta potensi unik dalam diri kamu.",
                            style = AppType.body2,
                            color = AppColor.grey500
                        )
                    }

                    Column {
                        AppText(
                            text = "Instruksi",
                            style = AppType.h3,
                        )
                        /*TODO some instructions here*/
                        AppText(
                            text = "...",
                            style = AppType.body2,
                            color = AppColor.grey500
                        )
                    }

                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                        AppButton(onClick = { /*TODO*/ }, text = "Mulai Tes")
                    }
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(32.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.padding(vertical = 32.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    AppText(text = "Welcome to", style = AppType.h2)
                    Spacer(modifier = Modifier.width(8.dp))
                    AsyncImage(
                        modifier = Modifier.widthIn(max = maxIconWidth.dp),
                        model = R.drawable.splashscreen_icon,
                        contentDescription = ""
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                AppText(text = "Tes Penjurusan", style = AppType.h2, textAlign = TextAlign.Center)
                AppText(
                    text = "Kenali jurusan yang sesuai dengan kepribadian & potensi dari diri kamu",
                    style = AppType.subheading2,
                    color = AppColor.grey500,
                    textAlign = TextAlign.Center
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Timer,
                        contentDescription = "",
                        tint = AppColor.grey800
                    )
                    AppText(text = "10 - 15 menit", style = AppType.body3)
                }
            }

            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                model = R.drawable.penjurusanlanding_image,
                contentDescription = ""
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppButton(
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetState.expand()
                        }
                    },
                    text = "Ambil tes sekarang"
                )
                AppTextButton(onClick = { /*TODO*/ }) {
                    AppText(text = "Lewati", style = AppType.body2, color = AppColor.grey500)
                }
            }
        }
    }
}