package com.ngikut.u_future.screen.penjurusan

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.ngikut.u_future.R
import com.ngikut.u_future.component.AppButton
import com.ngikut.u_future.component.AppIconButton
import com.ngikut.u_future.component.AppText
import com.ngikut.u_future.component.AppTextButton
import com.ngikut.u_future.component.AppTopBar
import com.ngikut.u_future.component.AppTopBarMidTitle
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.model.remote.response.jurusan.PredictJurusanResponse
import com.ngikut.u_future.model.remote.response.quiz.GetQuizAnalysisDataResponse
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType
import com.ngikut.u_future.viewmodel.penjurusan.PenjurusanSuccessViewmodel
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PenjurusanSuccessScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<PenjurusanSuccessViewmodel>()
    val getQuizAnalysisState = viewModel.getQuizAnalysisState.collectAsState()
    val predictJurusanState = viewModel.predictJurusanState.collectAsState()
    val recommendationPagerState = rememberPagerState()

    if (getQuizAnalysisState.value is Resource.Success && predictJurusanState.value is Resource.Success) {
        getQuizAnalysisState.value.data?.let { analysis ->
            predictJurusanState.value.data?.let { predict ->
                PenjurusanSuccessDetail(
                    data = analysis.data,
                    predict = predict,
                    navController = navController,
                    rekomendasiState = recommendationPagerState
                )
            }
        }
    } else {
        PenjurusanSuccessLoading()
    }
}

@Composable
fun PenjurusanSuccessLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(modifier = Modifier.size(48.dp), color = AppColor.primary400)
            AppText(text = "Menganalisis jawaban", style = AppType.h3)
            AppText(text = "Mohon tunggu beberapa detik!", style = AppType.body1)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PenjurusanSuccessDetail(
    data: GetQuizAnalysisDataResponse,
    predict: PredictJurusanResponse,
    navController: NavController,
    rekomendasiState: PagerState
) {
    val density = LocalDensity.current
    val topButtonWidth = remember { mutableStateOf(0.dp) }
    val contentPagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            AppTopBarMidTitle(
                onBackClicked = {
                    navController.popBackStack()
                },
                title = "Hasil Tes"
            )
        }
    ) {
        LazyColumn {
            item {
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
                        PenjurusanDetailSections
                            .values()
                            .forEachIndexed { index, item ->
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(Int.MAX_VALUE.dp))
                                        .width(topButtonWidth.value)
                                        .background(
                                            if (contentPagerState.currentPage == index) AppColor.primary400
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
                                                    contentPagerState.animateScrollToPage(index)
                                                }
                                            }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(12.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        Icon(
                                            painter = rememberAsyncImagePainter(model = item.iconId),
                                            contentDescription = "",
                                            tint = if (contentPagerState.currentPage != index) AppColor.primary400
                                            else AppColor.grey50
                                        )
                                        AppText(
                                            text = item.word,
                                            style = AppType.h5,
                                            color = if (contentPagerState.currentPage != index) AppColor.primary400
                                            else AppColor.grey50
                                        )
                                    }
                                }
                            }
                    }
                }
            }

            item {
                HorizontalPager(
                    state = contentPagerState,
                    count = PenjurusanDetailSections.values().size
                ) { index ->
                    when (index) {
                        0 -> {
                            Column {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 20.dp)
                                        .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp))
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(AppColor.grey50),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(16.dp),
                                        verticalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {
                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalArrangement = Arrangement.spacedBy(16.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            AppText(
                                                text = "KAMU ADALAH SEORANG:",
                                                style = AppType.subheading2,
                                                color = AppColor.grey600
                                            )

                                            AsyncImage(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(horizontal = 48.dp),
                                                model = R.drawable.penjurusansuccess_image,
                                                contentDescription = ""
                                            )

                                            AppText(
                                                text = data.title,
                                                style = AppType.h2,
                                                color = AppColor.primary400
                                            )
                                        }

                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalArrangement = Arrangement.spacedBy(10.dp)
                                        ) {
                                            AppText(text = "Hasil Analisis", style = AppType.h3)
                                            AppText(text = data.description, style = AppType.body1)
                                        }

                                        Column(
                                            modifier = Modifier.fillMaxWidth(),
                                            verticalArrangement = Arrangement.spacedBy(10.dp)
                                        ) {
                                            AppText(text = "Hasil Penilaian", style = AppType.h3)

                                            //investigative
                                            Column {
                                                AppText(
                                                    text = "INVESTIGATIVE",
                                                    style = AppType.h5,
                                                    color = AppColor.grey500
                                                )
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                                ) {
                                                    Box(
                                                        modifier = Modifier.fillMaxWidth(.2f),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        AppText(
                                                            text = "${data.investigative.roundToInt()} %",
                                                            style = AppType.h5,
                                                            color = AppColor.primary400
                                                        )
                                                    }

                                                    Box {
                                                        LinearProgressIndicator(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .height(12.dp)
                                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                                            progress = 1f,
                                                            color = AppColor.grey100
                                                        )
                                                        LinearProgressIndicator(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .height(12.dp)
                                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                                            progress = data.investigative / 100,
                                                            color = AppColor.primary400
                                                        )
                                                    }
                                                }
                                            }

                                            //realistic
                                            Column {
                                                AppText(
                                                    text = "REALISTIC",
                                                    style = AppType.h5,
                                                    color = AppColor.grey500
                                                )
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                                ) {
                                                    Box(
                                                        modifier = Modifier.fillMaxWidth(.2f),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        AppText(
                                                            text = "${data.realistic.roundToInt()} %",
                                                            style = AppType.h5,
                                                            color = AppColor.success500
                                                        )
                                                    }

                                                    Box {
                                                        LinearProgressIndicator(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .height(12.dp)
                                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                                            progress = 1f,
                                                            color = AppColor.grey100
                                                        )
                                                        LinearProgressIndicator(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .height(12.dp)
                                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                                            progress = data.realistic / 100,
                                                            color = AppColor.success500
                                                        )
                                                    }
                                                }
                                            }

                                            //enterprising
                                            Column {
                                                AppText(
                                                    text = "ENTERPRISING",
                                                    style = AppType.h5,
                                                    color = AppColor.grey500
                                                )
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                                ) {
                                                    Box(
                                                        modifier = Modifier.fillMaxWidth(.2f),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        AppText(
                                                            text = "${data.enterprising.roundToInt()} %",
                                                            style = AppType.h5,
                                                            color = Color(0xff9957B7)
                                                        )
                                                    }

                                                    Box {
                                                        LinearProgressIndicator(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .height(12.dp)
                                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                                            progress = 1f,
                                                            color = AppColor.grey100
                                                        )
                                                        LinearProgressIndicator(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .height(12.dp)
                                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                                            progress = data.enterprising / 100,
                                                            color = Color(0xff9957B7)
                                                        )
                                                    }
                                                }
                                            }

                                            //conventional
                                            Column {
                                                AppText(
                                                    text = "CONVENTIONAL",
                                                    style = AppType.h5,
                                                    color = AppColor.grey500
                                                )
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                                ) {
                                                    Box(
                                                        modifier = Modifier.fillMaxWidth(.2f),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        AppText(
                                                            text = "${data.conventional.roundToInt()} %",
                                                            style = AppType.h5,
                                                            color = AppColor.secondary400
                                                        )
                                                    }

                                                    Box {
                                                        LinearProgressIndicator(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .height(12.dp)
                                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                                            progress = 1f,
                                                            color = AppColor.grey100
                                                        )
                                                        LinearProgressIndicator(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .height(12.dp)
                                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                                            progress = data.conventional / 100,
                                                            color = AppColor.secondary400
                                                        )
                                                    }
                                                }
                                            }

                                            //social
                                            Column {
                                                AppText(
                                                    text = "SOCIAL",
                                                    style = AppType.h5,
                                                    color = AppColor.grey500
                                                )
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                                ) {
                                                    Box(
                                                        modifier = Modifier.fillMaxWidth(.2f),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        AppText(
                                                            text = "${data.social.roundToInt()} %",
                                                            style = AppType.h5,
                                                            color = Color(0xffF25E62)
                                                        )
                                                    }

                                                    Box {
                                                        LinearProgressIndicator(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .height(12.dp)
                                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                                            progress = 1f,
                                                            color = AppColor.grey100
                                                        )
                                                        LinearProgressIndicator(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .height(12.dp)
                                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                                            progress = data.social / 100,
                                                            color = Color(0xffF25E62)
                                                        )
                                                    }
                                                }
                                            }

                                            //artistic
                                            Column {
                                                AppText(
                                                    text = "ARTISTIC",
                                                    style = AppType.h5,
                                                    color = AppColor.grey500
                                                )
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                                ) {
                                                    Box(
                                                        modifier = Modifier.fillMaxWidth(.2f),
                                                        contentAlignment = Alignment.Center
                                                    ) {
                                                        AppText(
                                                            text = "${data.artistic.roundToInt()} %",
                                                            style = AppType.h5,
                                                            color = Color(0xff5E76F2)
                                                        )
                                                    }

                                                    Box {
                                                        LinearProgressIndicator(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .height(12.dp)
                                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                                            progress = 1f,
                                                            color = AppColor.grey100
                                                        )
                                                        LinearProgressIndicator(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .height(12.dp)
                                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                                            progress = data.artistic / 100,
                                                            color = Color(0xff5E76F2)
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                                AppButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(20.dp),
                                    onClick = {
                                        coroutineScope.launch {
                                            contentPagerState.animateScrollToPage(1)
                                        }
                                    }
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        AppText(
                                            text = "Rekomendasi Jurusan",
                                            style = AppType.h4,
                                            color = AppColor.grey50
                                        )
                                        Icon(
                                            imageVector = Icons.Default.ArrowForward,
                                            contentDescription = "",
                                            tint = AppColor.grey50
                                        )
                                    }
                                }
                            }
                        }

                        1 -> {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                HorizontalPager(
                                    state = rekomendasiState,
                                    count = predict.data.size,
                                    userScrollEnabled = false
                                ) { index ->
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 20.dp)
                                            .shadow(
                                                elevation = 8.dp,
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                            .clip(RoundedCornerShape(12.dp))
                                            .background(AppColor.grey50),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Column(
                                            horizontalAlignment = Alignment.CenterHorizontally,
                                            verticalArrangement = Arrangement.spacedBy(16.dp)
                                        ) {
                                            val containerWidth = remember { mutableStateOf(0.dp) }
                                            val backIconWidth = remember { mutableStateOf(0.dp) }
                                            val nextIconWidth = remember { mutableStateOf(0.dp) }

                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(20.dp)
                                            ) {
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .onSizeChanged {
                                                            density.run {
                                                                containerWidth.value =
                                                                    it.width.toDp()
                                                            }
                                                        },
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.Center
                                                ) {
                                                    AppIconButton(
                                                        modifier = Modifier
                                                            .shadow(
                                                                5.dp,
                                                                CircleShape
                                                            )
                                                            .onSizeChanged {
                                                                density.run {
                                                                    backIconWidth.value =
                                                                        it.width.toDp()
                                                                }
                                                            },
                                                        backgroundColor = AppColor.grey50,
                                                        imageVector = Icons.Default.ArrowBack,
                                                        onClick = {
                                                            if (index - 1 >= 0) {
                                                                coroutineScope.launch {
                                                                    rekomendasiState.animateScrollToPage(
                                                                        index - 1
                                                                    )
                                                                }
                                                            }
                                                        })

                                                    Column(
                                                        modifier = Modifier.width(
                                                            containerWidth.value - backIconWidth.value - nextIconWidth.value
                                                        ),
                                                        verticalArrangement = Arrangement.spacedBy(
                                                            20.dp
                                                        )
                                                    ) {
                                                        AsyncImage(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .padding(20.dp),
                                                            model = R.drawable.ilustrasi_sisteminformasi,
                                                            contentDescription = ""
                                                        )

                                                        AppText(
                                                            modifier = Modifier.fillMaxWidth(),
                                                            textAlign = TextAlign.Center,
                                                            text = predict.data[index].nama_jurusan,
                                                            style = AppType.h3
                                                        )

                                                        AppText(
                                                            modifier = Modifier.fillMaxWidth(),
                                                            textAlign = TextAlign.Center,
                                                            text = "Description here",
                                                            style = AppType.subheading2
                                                        )
                                                    }

                                                    AppIconButton(
                                                        modifier = Modifier
                                                            .shadow(
                                                                5.dp,
                                                                CircleShape
                                                            )
                                                            .onSizeChanged {
                                                                density.run {
                                                                    nextIconWidth.value =
                                                                        it.width.toDp()
                                                                }
                                                            },
                                                        backgroundColor = AppColor.grey50,
                                                        imageVector = Icons.Default.ArrowForward,
                                                        onClick = {
                                                            if (index + 1 < predict.data.size) {
                                                                coroutineScope.launch {
                                                                    rekomendasiState.animateScrollToPage(
                                                                        index + 1
                                                                    )
                                                                }
                                                            }
                                                        })
                                                }
                                            }

                                            Row(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(
                                                        start = 20.dp,
                                                        end = 20.dp,
                                                        bottom = 20.dp
                                                    ),
                                                verticalAlignment = Alignment.CenterVertically,
                                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                                            ) {
                                                AppButton(
                                                    modifier = Modifier.fillMaxWidth(0.5f),
                                                    onClick = { /*TODO*/ },
                                                    backgroundColor = AppColor.grey50,
                                                    borderColor = AppColor.primary400,
                                                    borderWidth = 2.dp
                                                ) {
                                                    Row(
                                                        verticalAlignment = Alignment.CenterVertically,
                                                        horizontalArrangement = Arrangement.spacedBy(
                                                            8.dp
                                                        )
                                                    ) {
                                                        AppText(
                                                            text = "Favorit",
                                                            style = AppType.h4,
                                                            color = AppColor.primary400
                                                        )
                                                        Icon(
                                                            imageVector = Icons.Default.Favorite,
                                                            contentDescription = "",
                                                            tint = AppColor.primary400
                                                        )
                                                    }
                                                }

                                                AppButton(
                                                    modifier = Modifier.fillMaxWidth(),
                                                    onClick = { /*TODO*/ },
                                                    text = "Detail Info"
                                                )
                                            }

                                            HorizontalPagerIndicator(
                                                modifier = Modifier.padding(
                                                    start = 20.dp,
                                                    end = 20.dp,
                                                    bottom = 20.dp
                                                ),
                                                pagerState = rekomendasiState,
                                                activeColor = AppColor.primary400
                                            )
                                        }
                                    }
                                }

                                AppTextButton(
                                    modifier = Modifier.padding(20.dp),
                                    onClick = {
                                        navController.popBackStack()
                                    }) {
                                    AppText(
                                        text = "Kembali ke Dashboard",
                                        style = AppType.h5,
                                        color = AppColor.grey500
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

enum class PenjurusanDetailSections(
    val word: String,
    val iconId: Int
) {
    Hasil(
        "Hasil Analisis",
        R.drawable.penjurusansuccess_hasilanalisis_icon
    ),
    Rekomendasi(
        "Rekomendasi",
        R.drawable.penjurusansuccess_rekomendasi_icon
    )
}