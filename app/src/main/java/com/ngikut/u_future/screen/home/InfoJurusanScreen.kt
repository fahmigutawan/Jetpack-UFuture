package com.ngikut.u_future.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ngikut.u_future.component.AppText
import com.ngikut.u_future.component.AppTextInputNormal
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType
import com.ngikut.u_future.viewmodel.home.InfoJurusanViewmodel

@Composable
fun InfoJurusanScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<InfoJurusanViewmodel>()
    val localFocus = LocalFocusManager.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.grey50)
    ) {
        item {
            Box(modifier = Modifier.background(AppColor.primary400)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(AppColor.grey50),
                    contentAlignment = Alignment.Center
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        AppText(
                            text = "Info Jurusan",
                            style = AppType.h3
                        )
                        AppText(
                            text = "Eksplorasi tentang Deskripsi, Tujuan, Kurikulum dari 100+ jurusan.",
                            style = AppType.body2,
                            color = AppColor.grey500
                        )
                        AppTextInputNormal(
                            modifier = Modifier.fillMaxWidth(),
                            placeHolder = "Kata Kunci",
                            value = viewModel.searchState.value,
                            onValueChange = {
                                viewModel.searchState.value = it
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "",
                                    tint = AppColor.grey500
                                )
                            },
                            trailingIcon = {
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(3.dp))
                                        .background(AppColor.primary50)
                                        .clickable(
                                            interactionSource = MutableInteractionSource(),
                                            indication = rememberRipple(
                                                color = AppColor.grey800,
                                                bounded = true
                                            ),
                                            onClick = {
                                                localFocus.clearFocus(true)
                                                /*TODO Call search function here*/
                                            }
                                        )
                                ) {
                                    Icon(
                                        modifier = Modifier.padding(6.dp),
                                        imageVector = Icons.Default.ArrowForward,
                                        contentDescription = "",
                                        tint = AppColor.primary400
                                    )
                                }
                            })
                    }
                }
            }
        }
    }
}