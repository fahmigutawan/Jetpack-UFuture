package com.ngikut.u_future.screen.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ngikut.u_future.R
import com.ngikut.u_future.component.AppTextInputNormal
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType
import com.ngikut.u_future.viewmodel.login.LoginViewmodel

@Composable
fun LoginScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<LoginViewmodel>()
    val iconWidth = LocalConfiguration.current.screenWidthDp / 2

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(64.dp)
    ) {
        Spacer(modifier = Modifier)

        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            AsyncImage(
                modifier = Modifier.width(iconWidth.dp),
                model = R.drawable.splashscreen_icon,
                contentDescription = ""
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Column {
                Text(
                    text = "Masuk",
                    style = AppType.h3,
                    color = AppColor.grey800
                )
                Text(
                    text = "Masuk menggunakan akun siswa yang diberikan oleh sekolahmu",
                    style = AppType.subheading2,
                    color = AppColor.grey500
                )
            }

            AppTextInputNormal(
                placeHolder = "Email",
                value = viewModel.emailState.value,
                onValueChange = { viewModel.emailState.value = it }
            )

            AppTextInputNormal(
                placeHolder = "Password",
                value = viewModel.passwordState.value,
                onValueChange = { viewModel.passwordState.value = it }
            )
        }
    }
}