package com.ngikut.u_future.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ngikut.u_future.R
import com.ngikut.u_future.component.AppButton
import com.ngikut.u_future.component.AppText
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType
import com.ngikut.u_future.util.NavRoute
import com.ngikut.u_future.viewmodel.profile.ProfileViewmodel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<ProfileViewmodel>()
    val scrWidth = LocalConfiguration.current.screenWidthDp
    val listOfDummyBtn = listOf(
        "Hubungi Kami",
        "Kebijakan Privasi",
        "Ubah Password",
        "Keluar"
    )

    Scaffold(
        topBar = {
            TopAppBar(modifier = Modifier.fillMaxWidth(), backgroundColor = AppColor.grey50) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    AppText(text = "Profil", style = AppType.h3)
                }
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
        ) {
            item {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        modifier = Modifier.width((scrWidth / 3).dp),
                        model = R.drawable.profile_dummy_pp,
                        contentDescription = ""
                    )
                    AppText(text = "Alfin Rizky", style = AppType.h2)
                    AppText(
                        text = "alfinrizky@gmail.com",
                        style = AppType.subheading2,
                        color = AppColor.grey500
                    )
                }
            }

            item {
                Box(
                    modifier = Modifier
                        .padding(vertical = 16.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(AppColor.primary50)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            AppText(text = "Asal sekolah:", style = AppType.h5)
                            AppText(text = "SMA Nusantara", style = AppType.h4)
                        }
                        AppButton(onClick = { /*TODO*/ }, text = "Edit")
                    }
                }
            }

            items(listOfDummyBtn) {
                Column {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = rememberRipple(
                                    color = AppColor.grey800,
                                    bounded = true
                                ),
                                onClick = {
                                    /*TODO*/
                                    if (it == "Keluar") {
                                        viewModel.setToken()
                                        navController.backQueue.clear()
                                        navController.navigate(NavRoute.Login.name)
                                    }
                                })
                    ) {
                        Column() {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                AppText(
                                    text = it,
                                    style = AppType.subheading1,
                                    color = if (it == "Keluar") AppColor.danger400 else AppColor.grey800
                                )
                                Icon(
                                    imageVector = Icons.Default.ArrowForwardIos,
                                    contentDescription = "",
                                    tint = if (it == "Keluar") AppColor.danger400 else AppColor.grey800
                                )
                            }
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                                    .background(
                                        if (it == "Keluar") AppColor.danger400 else Color(
                                            0xffd2d2d2
                                        )
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}