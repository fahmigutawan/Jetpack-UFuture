package com.ngikut.u_future.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.ngikut.u_future.R
import com.ngikut.u_future.component.AppButton
import com.ngikut.u_future.component.AppTextButton
import com.ngikut.u_future.component.AppTextInputNormal
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType
import com.ngikut.u_future.viewmodel.login.LoginViewmodel

@Composable
fun LoginScreen(
    navController: NavController,
    showSnackbar:(String) -> Unit
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
                modifier = Modifier.fillMaxWidth(),
                placeHolder = "Email",
                value = viewModel.emailState.value,
                onValueChange = { viewModel.emailState.value = it }
            )

            AppTextInputNormal(
                modifier = Modifier.fillMaxWidth(),
                placeHolder = "Password",
                value = viewModel.passwordState.value,
                onValueChange = { viewModel.passwordState.value = it },
                visualTransformation = if (viewModel.showPassword.value) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.showPassword.value = !viewModel.showPassword.value
                    }) {
                        Icon(
                            imageVector = if (viewModel.showPassword.value) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = "",
                            tint = AppColor.grey500
                        )
                    }
                }
            )

            AppTextButton(onClick = { /*TODO*/ }) {
                Text(text = "Lupa password?", style = AppType.body2, color = AppColor.grey500)
            }
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                          if(viewModel.passwordState.value.isEmpty() || viewModel.emailState.value.isEmpty()){
                              showSnackbar("Isi semua data dengan benar")
                          }else{
                              /*TODO Connect to API later*/
                          }
                },
                text = "Login"
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sekolahmu belum terdaftar?",
                    style = AppType.subheading3,
                    color = AppColor.grey500
                )

                AppTextButton(onClick = { /*TODO*/ }) {
                    Text(
                        text = "Klik di sini",
                        style = AppType.subheading3,
                        color = AppColor.grey900
                    )
                }
            }

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(AppColor.grey200)
                )

                Box(modifier = Modifier.background(AppColor.grey50)) {
                    Text(
                        modifier = Modifier.padding(horizontal = 8.dp),
                        text = "atau",
                        style = AppType.body1,
                        color = AppColor.grey500
                    )
                }
            }

            AppButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = { /*TODO*/ },
                text = "Daftar akun personal",
                textColor = AppColor.primary400,
                backgroundColor = AppColor.grey50,
                borderColor = AppColor.primary400,
                borderWidth = 1.dp
            )
        }
    }
}