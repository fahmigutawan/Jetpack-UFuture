package com.ngikut.u_future.screen.komparasi_jurusan

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ngikut.u_future.component.AppText
import com.ngikut.u_future.component.AppTopBarMidTitle
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.model.remote.response.komparasi_jurusan.CompareTwoJurusanResponse
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType
import com.ngikut.u_future.viewmodel.komparasi_jurusan.KomparasiResultViewmodel
import kotlin.math.roundToInt

@Composable
fun KomparasiResultScreen(
    navController: NavController,
    jurusan1: String,
    jurusan2: String
) {
    val viewModel = hiltViewModel<KomparasiResultViewmodel>()
    val compareJurusanState = viewModel.compareTwoJurusanState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.compare(jurusan1, jurusan2)
    }

    when (compareJurusanState.value) {
        is Resource.Error -> {/*TODO*/
        }

        is Resource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(128.dp),
                        color = AppColor.primary400
                    )

                    AppText(text = "AI sedang memproses", style = AppType.h3)
                }
            }
        }

        is Resource.Success -> {
            compareJurusanState.value.data?.let {
                KomparasiResultContent(
                    navController = navController,
                    data = it,
                    jurusan1 = jurusan1,
                    jurusan2 = jurusan2
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun KomparasiResultContent(
    navController: NavController,
    data: CompareTwoJurusanResponse,
    jurusan1: String,
    jurusan2: String
) {
    val density = LocalDensity.current

    Scaffold(
        topBar = {
            AppTopBarMidTitle(
                onBackClicked = {
                    navController.popBackStack()
                }, title = "Hasil Komparasi"
            )
        }
    ) {
        LazyColumn {
            item {
                val containerWidth = remember { mutableStateOf(0.dp) }
                val vsWidth = remember { mutableStateOf(0.dp) }
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
                                    containerWidth.value = it.width.toDp()
                                }
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .width(containerWidth.value / 2 - vsWidth.value)
                                .heightIn(min = 150.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(AppColor.primary50),
                            contentAlignment = Alignment.Center
                        ) {
                            AppText(text = jurusan1, style = AppType.h5)
                        }

                        Box(
                            modifier = Modifier.onSizeChanged {
                                density.run {
                                    vsWidth.value = it.width.toDp()
                                }
                            }
                        ) {
                            AppText(
                                modifier = Modifier.padding(4.dp),
                                text = "VS",
                                style = AppType.h3
                            )
                        }

                        Box(
                            modifier = Modifier
                                .width(containerWidth.value / 2 - vsWidth.value)
                                .heightIn(min = 150.dp)
                                .clip(RoundedCornerShape(14.dp))
                                .background(AppColor.primary50),
                            contentAlignment = Alignment.Center
                        ) {
                            AppText(text = jurusan2, style = AppType.h5)
                        }
                    }
                }
            }

            item {
                Column(modifier = Modifier.padding(20.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp))
                            .background(AppColor.primary400),
                        contentAlignment = Alignment.Center
                    ) {
                        AppText(
                            modifier = Modifier.padding(vertical = 4.dp),
                            text = "Ringkasan",
                            style = AppType.h4,
                            color = AppColor.grey50
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp))
                            .background(AppColor.primary50),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                            val middleGap = 16.dp
                            val containerWidth = remember { mutableStateOf(0.dp) }

                            //kecocokan
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .onSizeChanged {
                                            density.run {
                                                containerWidth.value = it.width.toDp()
                                            }
                                        },
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    AppText(
                                        text = "${(data.data.data_one.percentage * 100).roundToInt()}%",
                                        style = AppType.subheading3,
                                        color = if (data.data.data_one.percentage >= data.data.data_two.percentage) AppColor.success500 else AppColor.grey800
                                    )

                                    AppText(text = "Kecocokan", style = AppType.body2)

                                    AppText(
                                        text = "${(data.data.data_two.percentage * 100).roundToInt()}%",
                                        style = AppType.subheading3,
                                        color = if (data.data.data_one.percentage <= data.data.data_two.percentage) AppColor.success500 else AppColor.grey800
                                    )
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Box(contentAlignment = Alignment.CenterEnd) {
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    containerWidth.value / 2 - middleGap / 2
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.grey200
                                        )
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    (containerWidth.value / 2 - middleGap / 2) * data.data.data_one.percentage
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.primary400
                                        )
                                    }

                                    Box {
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    containerWidth.value / 2 - middleGap / 2
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.grey200
                                        )
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    (containerWidth.value / 2 - middleGap / 2) * data.data.data_two.percentage
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.primary400
                                        )
                                    }
                                }
                            }

                            //prospek kerja
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .onSizeChanged {
                                            density.run {
                                                containerWidth.value = it.width.toDp()
                                            }
                                        },
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    AppText(
                                        text = "${(data.data.data_one.tingkat_prospek_kerja * 100).roundToInt()}%",
                                        style = AppType.subheading3,
                                        color = if (data.data.data_one.tingkat_prospek_kerja >= data.data.data_two.tingkat_prospek_kerja) AppColor.success500 else AppColor.grey800
                                    )

                                    AppText(text = "Kecocokan", style = AppType.body2)

                                    AppText(
                                        text = "${(data.data.data_two.tingkat_prospek_kerja * 100).roundToInt()}%",
                                        style = AppType.subheading3,
                                        color = if (data.data.data_one.tingkat_prospek_kerja <= data.data.data_two.tingkat_prospek_kerja) AppColor.success500 else AppColor.grey800
                                    )
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Box(contentAlignment = Alignment.CenterEnd) {
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    containerWidth.value / 2 - middleGap / 2
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.grey200
                                        )
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    (containerWidth.value / 2 - middleGap / 2) * data.data.data_one.tingkat_prospek_kerja
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.primary400
                                        )
                                    }

                                    Box {
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    containerWidth.value / 2 - middleGap / 2
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.grey200
                                        )
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    (containerWidth.value / 2 - middleGap / 2) * data.data.data_two.tingkat_prospek_kerja
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.primary400
                                        )
                                    }
                                }
                            }

                            //tingkat keketatan
                            Box {
                                val keketatanContainerWidth = remember { mutableStateOf(0.dp) }

                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .onSizeChanged {
                                            density.run {
                                                keketatanContainerWidth.value = it.width.toDp()
                                            }
                                        },
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(4.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box {
                                            CircularProgressIndicator(
                                                modifier = Modifier.size(keketatanContainerWidth.value / 4),
                                                progress = 1f,
                                                color = AppColor.grey200
                                            )

                                            CircularProgressIndicator(
                                                modifier = Modifier.size(keketatanContainerWidth.value / 4),
                                                progress = data.data.data_one.tingkat_keketatan,
                                                color = AppColor.primary400
                                            )
                                        }

                                        AppText(
                                            text = "${(data.data.data_one.tingkat_keketatan * 100).roundToInt()}%",
                                            style = AppType.subheading3,
                                            color = if (data.data.data_one.tingkat_keketatan >= data.data.data_two.tingkat_keketatan) AppColor.success500 else AppColor.danger500
                                        )
                                    }

                                    Box(
                                        modifier = Modifier.widthIn(max = keketatanContainerWidth.value / 3),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        AppText(
                                            text = "Tingkat keketatan",
                                            style = AppType.body2,
                                            textAlign = TextAlign.Center
                                        )
                                    }

                                    Column(
                                        verticalArrangement = Arrangement.spacedBy(4.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Box {
                                            CircularProgressIndicator(
                                                modifier = Modifier.size(keketatanContainerWidth.value / 4),
                                                progress = 1f,
                                                color = AppColor.grey200
                                            )

                                            CircularProgressIndicator(
                                                modifier = Modifier.size(keketatanContainerWidth.value / 4),
                                                progress = data.data.data_two.tingkat_keketatan,
                                                color = AppColor.primary400
                                            )
                                        }

                                        AppText(
                                            text = "${(data.data.data_two.tingkat_keketatan * 100).roundToInt()}%",
                                            style = AppType.subheading3,
                                            color = if (data.data.data_one.tingkat_keketatan <= data.data.data_two.tingkat_keketatan) AppColor.success500 else AppColor.danger500
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item {
                Column(modifier = Modifier.padding(20.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp))
                            .background(AppColor.primary400),
                        contentAlignment = Alignment.Center
                    ) {
                        AppText(
                            modifier = Modifier.padding(vertical = 4.dp),
                            text = "Prospek Kerja",
                            style = AppType.h4,
                            color = AppColor.grey50
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp))
                            .background(AppColor.primary50),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                            val middleGap = 16.dp
                            val containerWidth = remember { mutableStateOf(0.dp) }

                            //keselarasan
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .onSizeChanged {
                                            density.run {
                                                containerWidth.value = it.width.toDp()
                                            }
                                        },
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    AppText(
                                        text = "${(data.data.data_one.tingkat_keselarasan * 100).roundToInt()}%",
                                        style = AppType.subheading3,
                                        color = if (data.data.data_one.tingkat_keselarasan >= data.data.data_two.tingkat_keselarasan) AppColor.success500 else AppColor.grey800
                                    )

                                    Box(
                                        modifier = Modifier.widthIn(max = containerWidth.value / 3),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        AppText(
                                            text = "Tingkat keselarasan pekerjaan",
                                            style = AppType.body2,
                                            textAlign = TextAlign.Center
                                        )
                                    }

                                    AppText(
                                        text = "${(data.data.data_two.tingkat_keselarasan * 100).roundToInt()}%",
                                        style = AppType.subheading3,
                                        color = if (data.data.data_one.tingkat_keselarasan <= data.data.data_two.tingkat_keselarasan) AppColor.success500 else AppColor.grey800
                                    )
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Box(contentAlignment = Alignment.CenterEnd) {
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    containerWidth.value / 2 - middleGap / 2
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.grey200
                                        )
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    (containerWidth.value / 2 - middleGap / 2) * data.data.data_one.tingkat_keselarasan
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.primary400
                                        )
                                    }

                                    Box {
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    containerWidth.value / 2 - middleGap / 2
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.grey200
                                        )
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    (containerWidth.value / 2 - middleGap / 2) * data.data.data_two.tingkat_keselarasan
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.primary400
                                        )
                                    }
                                }
                            }

                            //alumni dapat kerja < 6 bulan
                            Column {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .onSizeChanged {
                                            density.run {
                                                containerWidth.value = it.width.toDp()
                                            }
                                        },
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    AppText(
                                        text = "${(data.data.data_one.tingkat_dapat_pekerjaan * 100).roundToInt()}%",
                                        style = AppType.subheading3,
                                        color = if (data.data.data_one.tingkat_dapat_pekerjaan >= data.data.data_two.tingkat_dapat_pekerjaan) AppColor.success500 else AppColor.grey800
                                    )

                                    Box(
                                        modifier = Modifier.widthIn(max = containerWidth.value / 3),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        AppText(
                                            text = "Alumni mendapat kerja <6 bulan",
                                            style = AppType.body2,
                                            textAlign = TextAlign.Center
                                        )
                                    }

                                    AppText(
                                        text = "${(data.data.data_two.tingkat_dapat_pekerjaan * 100).roundToInt()}%",
                                        style = AppType.subheading3,
                                        color = if (data.data.data_one.tingkat_dapat_pekerjaan <= data.data.data_two.tingkat_dapat_pekerjaan) AppColor.success500 else AppColor.grey800
                                    )
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Box(contentAlignment = Alignment.CenterEnd) {
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    containerWidth.value / 2 - middleGap / 2
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.grey200
                                        )
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    (containerWidth.value / 2 - middleGap / 2) * data.data.data_one.tingkat_dapat_pekerjaan
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.primary400
                                        )
                                    }

                                    Box {
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    containerWidth.value / 2 - middleGap / 2
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.grey200
                                        )
                                        LinearProgressIndicator(
                                            progress = 1f,
                                            modifier = Modifier
                                                .width(
                                                    (containerWidth.value / 2 - middleGap / 2) * data.data.data_two.tingkat_dapat_pekerjaan
                                                )
                                                .height(8.dp)
                                                .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                            color = AppColor.primary400
                                        )
                                    }
                                }
                            }

                            //gaji rata per tahun
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .onSizeChanged {
                                        density.run {
                                            containerWidth.value = it.width.toDp()
                                        }
                                    },
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                AppText(
                                    text = data.data.data_one.gaji,
                                    style = AppType.subheading3,
                                    color = AppColor.success500
                                )

                                Box(
                                    modifier = Modifier.widthIn(max = containerWidth.value / 3),
                                    contentAlignment = Alignment.Center
                                ) {
                                    AppText(
                                        text = "Gaji rata-rata per tahun",
                                        style = AppType.body2,
                                        textAlign = TextAlign.Center
                                    )
                                }

                                AppText(
                                    text = data.data.data_two.gaji,
                                    style = AppType.subheading3,
                                    color = AppColor.success500
                                )
                            }

                            //list pekerjaan
                            Column {
                                AppText(text = "List Pekerjaan:", style = AppType.h4)

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Box(modifier = Modifier.widthIn(max = containerWidth.value / 2 - middleGap)) {
                                        AppText(
                                            text = data.data.data_one.pekerjaan,
                                            style = AppType.body1
                                        )
                                    }

                                    Box(modifier = Modifier.widthIn(max = containerWidth.value / 2 - middleGap)) {
                                        AppText(
                                            text = data.data.data_two.pekerjaan,
                                            style = AppType.body1
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            item {
                Column(modifier = Modifier.padding(20.dp)) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(topEnd = 12.dp, topStart = 12.dp))
                            .background(AppColor.primary400),
                        contentAlignment = Alignment.Center
                    ) {
                        AppText(
                            modifier = Modifier.padding(vertical = 4.dp),
                            text = "Hasil Analisis (AI)",
                            style = AppType.h4,
                            color = AppColor.grey50
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp))
                            .background(AppColor.primary50),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
                            AppText(text = "Hasil Analisis:", style = AppType.h4)
                            AppText(text = data.data.analysis, style = AppType.body1)
                        }
                    }
                }
            }
        }
    }
}