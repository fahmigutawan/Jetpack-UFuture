package com.ngikut.u_future.screen.komparasi_jurusan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ngikut.u_future.R
import com.ngikut.u_future.component.AppButton
import com.ngikut.u_future.component.AppText
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType
import com.ngikut.u_future.viewmodel.komparasi_jurusan.KomparasiJurusanViewmodel

@Composable
fun KomparasiJurusanScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<KomparasiJurusanViewmodel>()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.grey50)
    ) {
        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(AppColor.primary400)) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AppText(
                        text = "Komparasi Jurusan",
                        style = AppType.h3,
                        color = AppColor.grey50
                    )
                    AppButton(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(Int.MAX_VALUE.dp),
                        backgroundColor = AppColor.grey50
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.History,
                                contentDescription = "",
                                tint = AppColor.primary400
                            )
                            AppText(
                                text = "Riwayat",
                                style = AppType.subheading3,
                                color = AppColor.primary400
                            )
                        }
                    }
                }
            }
        }

        item{
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColor.primary400)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp))
                        .background(AppColor.primary600)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(AppColor.primary50)
                        ) {
                            Icon(
                                modifier = Modifier.padding(8.dp),
                                painter = rememberAsyncImagePainter(model = R.drawable.bottombar_ubot),
                                contentDescription = "",
                                tint = AppColor.primary400
                            )
                        }

                        AppText(
                            text = "Pilih 2 jurusan untuk dikomparasi",
                            style = AppType.subheading2,
                            color = AppColor.grey50
                        )
                    }
                }
            }
        }
    }
}