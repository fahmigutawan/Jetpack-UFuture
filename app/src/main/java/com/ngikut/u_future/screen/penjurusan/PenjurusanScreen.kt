package com.ngikut.u_future.screen.penjurusan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ngikut.u_future.R
import com.ngikut.u_future.component.AppBottomSheetScaffold
import com.ngikut.u_future.component.AppIconButton
import com.ngikut.u_future.component.AppText
import com.ngikut.u_future.component.AppTextButton
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType
import com.ngikut.u_future.viewmodel.penjurusan.PenjurusanViewmodel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PenjurusanScreen(
    navController: NavController
) {
    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    val coroutineContext = rememberCoroutineScope()
    val viewModel = hiltViewModel<PenjurusanViewmodel>()

    AppBottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColor.grey50)
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    AppTextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        onClick = { /*TODO*/ }) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(AppColor.success500),
                            ) {
                                Icon(
                                    modifier = Modifier.padding(8.dp),
                                    painter = rememberAsyncImagePainter(model = R.drawable.penjurusan_refresh_icon),
                                    contentDescription = "",
                                    tint = AppColor.grey50
                                )
                            }

                            AppText(text = "Mulai ulang tes", style = AppType.subheading2)
                        }
                    }

                    AppTextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        onClick = { /*TODO*/ }
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(AppColor.danger400),
                            ) {
                                Icon(
                                    modifier = Modifier.padding(8.dp),
                                    painter = rememberAsyncImagePainter(model = R.drawable.penjurusan_exit_icon),
                                    contentDescription = "",
                                    tint = AppColor.grey50
                                )
                            }

                            AppText(text = "Keluar tes", style = AppType.subheading2)
                        }
                    }
                }
            }
        }
    ) {
        Column {
            Row(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AppIconButton(
                    painter = rememberAsyncImagePainter(model = R.drawable.penjurusan_back_icon),
                    onClick = { /*TODO*/ },
                    backgroundColor = AppColor.secondary100,
                    shape = RoundedCornerShape(10.dp)
                )

                AppIconButton(
                    painter = rememberAsyncImagePainter(model = R.drawable.penjurusan_menu_icon),
                    onClick = {
                        coroutineContext.launch {
                            bottomSheetState.expand()
                        }
                    },
                    backgroundColor = AppColor.primary50,
                    shape = RoundedCornerShape(10.dp)
                )
            }
        }
    }
}