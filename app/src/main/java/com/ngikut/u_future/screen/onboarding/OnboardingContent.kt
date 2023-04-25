package com.ngikut.u_future.screen.onboarding

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.pager.*
import com.ngikut.u_future.R
import com.ngikut.u_future.component.AppButton
import com.ngikut.u_future.component.AppText
import com.ngikut.u_future.component.AppTextButton
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingContent(
    item: OnboardingItem,
    state: PagerState,
    onNextClicked: () -> Unit,
    onBackClicked: () -> Unit,
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit,
    onSkipClicked: () -> Unit,
    isFirst: Boolean,
    isLast: Boolean
) {
    val bottomSectionHeight = LocalConfiguration.current.screenHeightDp * 4 / 10

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.grey200)
    ) {
        if (!isLast) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                TextButton(onClick = onSkipClicked) {
                    Text(
                        text = "Skip",
                        color = AppColor.grey900,
                        style = AppType.h4
                    )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom
        ) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        modifier = Modifier.width((bottomSectionHeight * 8 / 10).dp),
                        model = item.imageId,
                        contentDescription = ""
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .background(AppColor.grey50)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(min = bottomSectionHeight.dp)
                            .padding(32.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                            Text(text = item.title, style = AppType.h1, color = AppColor.grey800)
                            if (!isLast) {
                                Text(
                                    text = item.description,
                                    style = AppType.subheading1,
                                    color = AppColor.grey600
                                )
                            } else {
                                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                    AppButton(
                                        modifier = Modifier.fillMaxWidth(),
                                        onClick = onLoginClicked,
                                        text = "Masuk dengan akun siswa"
                                    )
                                    AppButton(
                                        modifier = Modifier.fillMaxWidth(),
                                        onClick = onRegisterClicked,
                                        text = "Daftar akun personal",
                                        textColor = AppColor.primary400,
                                        backgroundColor = AppColor.grey50,
                                        borderWidth = 1.dp,
                                        borderColor = AppColor.primary400,
                                        rippleColor = AppColor.grey800
                                    )
                                }
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            HorizontalPagerIndicator(
                                pagerState = state,
                                activeColor = AppColor.primary400,
                                inactiveColor = AppColor.grey200
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                if (!isFirst) {
                                    AppTextButton(onClick = onBackClicked) {
                                        AppText(text = "Back", style = AppType.h4)
                                    }
                                }

                                if (!isLast) {
                                    AppButton(onClick = onNextClicked, text = "Next")
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}

enum class OnboardingItem(
    val title: String,
    val description: String,
    val imageId: Int
) {
    Page1(
        "Temukan Potensi Dalam Dirimu",
        "Mulailah petualangan penemuan diri untuk mengenali potensi tersembunyi dalam dirimu.",
        R.drawable.onboarding_1
    ),
    Page2(
        "Dapatkan Rekomendasi Jurusan dan Karir",
        "Kami akan memberikan saran jurusan dan karir yang sesuai dengan hasil tes minat dan bakat kamu.",
        R.drawable.onboarding_2
    ),
    Page3(
        "Mulai Petualanganmu Sekarang!",
        "",
        R.drawable.onboarding_3
    ),
}