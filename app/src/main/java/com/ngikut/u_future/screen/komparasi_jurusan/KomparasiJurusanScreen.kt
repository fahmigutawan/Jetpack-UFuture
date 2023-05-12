package com.ngikut.u_future.screen.komparasi_jurusan

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ngikut.u_future.R
import com.ngikut.u_future.component.AppButton
import com.ngikut.u_future.component.AppText
import com.ngikut.u_future.component.CompareJurusanItem
import com.ngikut.u_future.component.PickedCompareItem
import com.ngikut.u_future.screen.penjurusan.PenjurusanDetailSections
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType
import com.ngikut.u_future.util.NavRoute
import com.ngikut.u_future.viewmodel.komparasi_jurusan.KomparasiJurusanViewmodel
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun KomparasiJurusanScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<KomparasiJurusanViewmodel>()
    val jurusanPagerState = rememberPagerState()
    val density = LocalDensity.current
    val compareSectionWidth = remember { mutableStateOf(0.dp) }
    val vsItemWidth = remember { mutableStateOf(0.dp) }
    val isPickedFirst = remember { mutableStateOf(false) }

    LaunchedEffect(key1 = viewModel.jurusan1Picked.value) {
        if (isPickedFirst.value) {
            if (viewModel.jurusan1Picked.value != null && viewModel.jurusan2Picked.value != null) {
                navController.navigate("${NavRoute.ComparationResult.name}/${viewModel.jurusan1Picked.value?.nama_jurusan ?: ""}/${viewModel.jurusan2Picked.value?.nama_jurusan ?: ""}")
                isPickedFirst.value = false
            }
        }
    }

    LaunchedEffect(key1 = viewModel.jurusan2Picked.value) {
        if (isPickedFirst.value) {
            if (viewModel.jurusan1Picked.value != null && viewModel.jurusan2Picked.value != null) {
                navController.navigate("${NavRoute.ComparationResult.name}/${viewModel.jurusan1Picked.value?.nama_jurusan ?: ""}/${viewModel.jurusan2Picked.value?.nama_jurusan ?: ""}")
                isPickedFirst.value = false
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.grey50)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColor.primary400)
            ) {
                Column {
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

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .onSizeChanged {
                                density.run {
                                    compareSectionWidth.value = it.width.toDp()
                                }
                            }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            PickedCompareItem(
                                width = compareSectionWidth.value / 2 - vsItemWidth.value,
                                item = viewModel.jurusan1Picked.value,
                                placeholder = "Jurusan 1",
                                onCloseClicked = {
                                    viewModel.jurusan1Picked.value = null
                                }
                            )

                            Box(
                                modifier = Modifier.onSizeChanged {
                                    density.run {
                                        vsItemWidth.value = it.width.toDp()
                                    }
                                },
                                contentAlignment = Alignment.Center
                            ) {
                                AppText(
                                    modifier = Modifier.padding(8.dp),
                                    text = "VS",
                                    style = AppType.h3,
                                    color = AppColor.grey50
                                )
                            }

                            PickedCompareItem(
                                width = compareSectionWidth.value / 2 - vsItemWidth.value,
                                item = viewModel.jurusan2Picked.value,
                                placeholder = "Jurusan 2",
                                onCloseClicked = {
                                    viewModel.jurusan2Picked.value = null
                                }
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

        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColor.primary600)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topEnd = 25.dp, topStart = 25.dp))
                        .background(AppColor.grey50)
                ) {
                    val density = LocalDensity.current
                    val topButtonWidth = remember { mutableStateOf(0.dp) }
                    val coroutineScope = rememberCoroutineScope()

                    Box(
                        modifier = Modifier
                            .padding(20.dp)
                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(Int.MAX_VALUE.dp))
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(Int.MAX_VALUE.dp))
                            .background(AppColor.grey50),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(6.dp)
                                .onSizeChanged {
                                    density.run {
                                        topButtonWidth.value = (it.width / 2).toDp()
                                    }
                                },
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            KomparasiJurusanSection
                                .values()
                                .forEachIndexed { index, item ->
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(Int.MAX_VALUE.dp))
                                            .width(topButtonWidth.value)
                                            .background(
                                                if (jurusanPagerState.currentPage == index) AppColor.primary400
                                                else AppColor.grey50
                                            )
                                            .clickable(
                                                interactionSource = MutableInteractionSource(),
                                                indication = rememberRipple(
                                                    color = AppColor.grey800,
                                                    bounded = true
                                                ),
                                                onClick = {
                                                    coroutineScope.launch {
                                                        jurusanPagerState.animateScrollToPage(index)
                                                    }
                                                }
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxHeight()
                                                .padding(12.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                                        ) {
                                            Icon(
                                                painter = rememberAsyncImagePainter(model = item.iconId),
                                                contentDescription = "",
                                                tint = if (jurusanPagerState.currentPage != index) AppColor.primary400
                                                else AppColor.grey50
                                            )
                                            AppText(
                                                text = item.word,
                                                style = AppType.h5,
                                                color = if (jurusanPagerState.currentPage != index) AppColor.primary400
                                                else AppColor.grey50
                                            )
                                        }
                                    }
                                }
                        }
                    }
                }
            }
        }

        item {
            HorizontalPager(
                state = jurusanPagerState,
                count = KomparasiJurusanSection.values().size
            ) { index ->
                Column(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    viewModel.jurusanRecommendation.forEach { item ->
                        CompareJurusanItem(
                            onPlusClicked = {
                                when {
                                    (viewModel.jurusan1Picked.value == null) -> viewModel.jurusan1Picked.value =
                                        it

                                    (viewModel.jurusan2Picked.value == null) -> viewModel.jurusan2Picked.value =
                                        it
                                }

                                isPickedFirst.value = true
                            },
                            picked = viewModel.jurusan1Picked.value == item || viewModel.jurusan2Picked.value == item,
                            item = item,
                            plusEnabled = viewModel.jurusan1Picked.value == null || viewModel.jurusan2Picked.value == null
                        )
                    }
                }
            }
        }
    }
}

enum class KomparasiJurusanSection(
    val word: String,
    val iconId: Int
) {
    Rekomendasi(
        "Rekomendasi",
        R.drawable.penjurusansuccess_rekomendasi_icon
    ),
    Favorite(
        "Favorit",
        R.drawable.bottombar_favorite_selected
    )
}