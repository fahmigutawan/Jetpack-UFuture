package com.ngikut.u_future.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.ArrowRightAlt
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SwipeRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.ngikut.u_future.R
import com.ngikut.u_future.component.AppButton
import com.ngikut.u_future.component.AppText
import com.ngikut.u_future.component.AppTextButton
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType
import com.ngikut.u_future.util.NavRoute
import com.ngikut.u_future.viewmodel.home.HomeViewmodel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = hiltViewModel<HomeViewmodel>()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColor.primary400),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .clip(CircleShape)
                                .background(AppColor.grey500),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                modifier = Modifier.size(32.dp),
                                imageVector = Icons.Default.Person,
                                contentDescription = "",
                                tint = AppColor.grey50
                            )
                        }
                        Column {
                            AppText(
                                text = "Hi, Fahmi",
                                style = AppType.h4,
                                color = AppColor.grey50
                            )
                            AppText(
                                text = "Selamat datang",
                                style = AppType.subheading3,
                                color = AppColor.grey50
                            )
                        }
                    }

                    AppButton(
                        onClick = { /*TODO*/ },
                        backgroundColor = AppColor.grey50,
                        shape = RoundedCornerShape(Int.MAX_VALUE.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                painter = rememberAsyncImagePainter(model = R.drawable.home_lihattes_icon),
                                contentDescription = "",
                                tint = AppColor.primary400
                            )
                            AppText(
                                text = "Lihat Tes",
                                style = AppType.subheading3,
                                color = AppColor.primary400
                            )
                        }
                    }
                }
            }
        }

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColor.primary400),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(AppColor.grey50)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(24.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        AsyncImage(
                            model = R.drawable.home_tespenjurusan_image,
                            contentDescription = ""
                        )
                        Column {
                            AppText(text = "Tes Penjurusan", style = AppType.h3)
                            AppText(text = "Temukan jurusan yang sesuai", style = AppType.body2)
                            AppButton(onClick = { /*TODO*/ }) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    AppText(
                                        text = "Ambil Tes",
                                        style = AppType.h5,
                                        color = AppColor.grey50
                                    )
                                    Icon(
                                        imageVector = Icons.Default.ArrowRightAlt,
                                        contentDescription = "",
                                        tint = AppColor.grey50
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            Box(modifier = Modifier
                .fillMaxWidth()
                .background(AppColor.primary400)) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                        .background(AppColor.primary600)
                ) {
                        AppTextButton(onClick = { /*TODO*/ }) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(CircleShape)
                                            .background(AppColor.grey50)
                                            .size(32.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            painter = rememberAsyncImagePainter(model = R.drawable.bottombar_ubot),
                                            contentDescription = "",
                                            tint = AppColor.primary400
                                        )
                                    }

                                    AppText(
                                        text = "Yuk review hasil tes terakhirmu!",
                                        style = AppType.subheading2,
                                        color = AppColor.grey50
                                    )
                                }

                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    imageVector = Icons.Default.ArrowRight,
                                    contentDescription = "",
                                    tint = AppColor.grey50
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
                    .background(AppColor.primary600),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp))
                        .background(AppColor.grey50)
                ) {
                    val threeMenuWidth = remember { mutableStateOf(0.dp) }
                    val density = LocalDensity.current

                    Row(
                        modifier = Modifier
                            .padding(46.dp)
                            .fillMaxWidth()
                            .onSizeChanged {
                                density.run {
                                    threeMenuWidth.value = (it.width / 3).toDp()
                                }
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        HomeInfoMenuItems.values().forEach { item ->
                            AppTextButton(
                                modifier = Modifier.width(threeMenuWidth.value),
                                onClick = { navController.navigate(route = item.route) }
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(AppColor.primary400),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            modifier = Modifier.padding(16.dp),
                                            painter = rememberAsyncImagePainter(model = item.iconId),
                                            contentDescription = "",
                                            tint = AppColor.grey50
                                        )
                                    }

                                    AppText(text = item.word, style = AppType.subheading3)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class HomeInfoMenuItems(
    val iconId: Int,
    val word: String,
    val route: String
) {
    InfoJurusan(
        R.drawable.home_infojurusan_menu_icon,
        "Info Jurusan",
        NavRoute.InfoJurusan.name
    ),
    InfoKampus(
        R.drawable.home_infokampus_menu_icon,
        "Info Kampus",
        NavRoute.InfoKampus.name
    ),
    InfoBeasiswa(
        R.drawable.home_infobeasiswa_menu_item,
        "Info Beasiswa",
        NavRoute.InfoBeasiswa.name
    )
}

enum class HomeUniversityNewsDummyItems(
    val title:String,
    val iconId:Int,
    val imageId:Int
) {

}