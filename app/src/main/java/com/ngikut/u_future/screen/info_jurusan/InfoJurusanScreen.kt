package com.ngikut.u_future.screen.info_jurusan

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.ngikut.u_future.R
import com.ngikut.u_future.component.AppText
import com.ngikut.u_future.component.AppTextButton
import com.ngikut.u_future.component.AppTextInputNormal
import com.ngikut.u_future.component.InfoJurusanRecommendationByAI
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.model.dummy.DummyAiInfoJurusanRecommendation
import com.ngikut.u_future.model.dummy.DummyFakultasInfoJurusan
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType
import com.ngikut.u_future.util.NavRoute
import com.ngikut.u_future.viewmodel.info_jurusan.InfoJurusanViewmodel

@OptIn(ExperimentalPagerApi::class)
@Composable
fun InfoJurusanScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<InfoJurusanViewmodel>()
    val localFocus = LocalFocusManager.current
    val recommendationItemWidth = LocalConfiguration.current.screenWidthDp * 7 / 10
    val fakultasItemWidth = LocalConfiguration.current.screenWidthDp / 6
    val fakultasItemMinHeight = fakultasItemWidth * 3 / 2
    val top3JurusanState = viewModel.top3JurusanState.collectAsState()
    val listOfDummyFakultas = listOf(
        DummyFakultasInfoJurusan(
            icon = R.drawable.infojurusan_fak_komputer_icon,
            text = "Komputer & Teknologi"
        ),
        DummyFakultasInfoJurusan(
            icon = R.drawable.infojurusan_fak_ekonomibisnis_icon,
            text = "Ekonomi & Bisnis"
        ),
        DummyFakultasInfoJurusan(
            icon = R.drawable.infojurusan_fak_sosial_icon,
            text = "Sosial & Humaniora"
        ),
        DummyFakultasInfoJurusan(
            icon = R.drawable.infojurusan_fak_mipa_icon,
            text = "Matematika & IPA"
        ),
        DummyFakultasInfoJurusan(
            icon = R.drawable.infojurusan_fak_kesehatan_icon,
            text = "Kedokteran & Kesehatan"
        ),
        DummyFakultasInfoJurusan(
            icon = R.drawable.infojurusan_fak_teknik_icon,
            text = "Teknik"
        )
    )

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.grey50),
        columns = GridCells.Fixed(3)
    ) {
        item(
            span = { GridItemSpan(3) }
        ) {
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
                            value = viewModel.searchValueState.value,
                            onValueChange = {
                                viewModel.searchValueState.value = it
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
                                                if (viewModel.searchValueState.value
                                                        .trim()
                                                        .isNotEmpty()
                                                ) {
                                                    navController.navigate(route = "${NavRoute.InfoJurusanOnSearch.name}/${viewModel.searchValueState.value.trim()}")
                                                }
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

        item(
            span = {
                GridItemSpan(3)
            }
        ) {
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
                            text = "Berikut adalah rekomendasi untukmu!",
                            style = AppType.subheading2,
                            color = AppColor.grey50
                        )
                    }
                }
            }
        }

        item(
            span = { GridItemSpan(3) }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColor.primary600)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp))
                        .background(AppColor.grey50)
                ) {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(
                                    top = 20.dp,
                                    start = 20.dp,
                                    end = 20.dp
                                ),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            AppText(text = "Rekomendasi oleh AI", style = AppType.h3)
                            AppTextButton(onClick = { /*TODO*/ }) {
                                AppText(
                                    text = "Lihat semua",
                                    style = AppType.body2,
                                    color = AppColor.grey600
                                )
                            }
                        }

                        Row(
                            modifier = Modifier.horizontalScroll(state = rememberScrollState()),
                            horizontalArrangement = Arrangement.spacedBy(20.dp)
                        ) {
                            Spacer(modifier = Modifier)
                            when(top3JurusanState.value){
                                is Resource.Error -> {/*TODO*/}
                                is Resource.Loading -> {/*TODO*/}
                                is Resource.Success -> {
                                    top3JurusanState.value.data?.let {
                                        it.data.forEach { item ->
                                            InfoJurusanRecommendationByAI(
                                                item = item,
                                                recommendationItemWidth = recommendationItemWidth,
                                                onClick = {/*TODO*/ })
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier)
                        }
                    }
                }
            }
        }

        item(
            span = { GridItemSpan(3) }
        ) {
            AppText(
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp
                ),
                text = "Fakultas",
                style = AppType.h3
            )
        }

        items(listOfDummyFakultas) { item ->
            Box(
                modifier = Modifier
                    .width(fakultasItemWidth.dp)
                    .heightIn(min = fakultasItemMinHeight.dp)
                    .padding(12.dp)
                    .border(
                        color = AppColor.grey100,
                        width = 2.dp,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = rememberRipple(color = AppColor.grey800, bounded = true),
                        onClick = {
                            navController.navigate(route = "${NavRoute.InfoJurusanByFakultas.name}/${item.text}")
                        }
                    )
            ) {
                Column(
                    modifier = Modifier
//                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(16.dp))
                        .clip(RoundedCornerShape(16.dp))
                        .background(AppColor.grey50)
                        .padding(12.dp)
                        .fillMaxWidth()
                        .heightIn(min = fakultasItemMinHeight.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        modifier = Modifier.size(42.dp),
                        painter = rememberAsyncImagePainter(model = item.icon),
                        contentDescription = "",
                        tint = Color.Unspecified
                    )

                    AppText(
                        text = item.text,
                        style = AppType.subheading3,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}