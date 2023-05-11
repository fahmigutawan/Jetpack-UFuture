package com.ngikut.u_future.screen.penjurusan

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.ngikut.u_future.R
import com.ngikut.u_future.component.*
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.model.remote.request.quiz.SingleSendQuizSectionOneAnswerDataRequest
import com.ngikut.u_future.model.remote.request.quiz.SingleSendQuizSectionTwoAndThreeAnswerDataRequest
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType
import com.ngikut.u_future.util.NavRoute
import com.ngikut.u_future.viewmodel.RootViewmodel
import com.ngikut.u_future.viewmodel.penjurusan.PenjurusanViewmodel
import kotlinx.coroutines.*
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun PenjurusanScreen(
    navController: NavController,
    rootViewmodel: RootViewmodel,
    showSnackbar: (String) -> Unit,
    changeLoadingState: (Boolean) -> Unit,
    title: String
) {
    val bottomSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val bottomSheetScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = bottomSheetState)
    val coroutineContext = rememberCoroutineScope()
    val viewModel = hiltViewModel<PenjurusanViewmodel>()
    val quizQuestion = viewModel.quizQuestion.collectAsState()
    val sendQuizAnswerState = viewModel.sendQuizAnswerState.collectAsState()
    val density = LocalDensity.current
    val topSectionHeight = remember { mutableStateOf(0.dp) }
    val pagerIndicatorHeight = remember { mutableStateOf(0.dp) }
    val screenHeight = LocalConfiguration.current.screenHeightDp
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    if (viewModel.startExitProcess.value) {
        LaunchedEffect(key1 = true) {
            navController.popBackStack()
            if (navController.backQueue.size == 0) {
                navController.backQueue.clear()
                navController.navigate(route = NavRoute.Home.name)
            }
            viewModel.startExitProcess.value = false
        }
    }
    if (viewModel.startSendQuizAnswer.value) {
        LaunchedEffect(key1 = true) {
            when (quizQuestion.value) {
                is Resource.Error -> {/*TODO*/
                }
                is Resource.Loading -> {/*TODO*/
                }
                is Resource.Success -> {
                    quizQuestion.value.data?.let {
                        changeLoadingState(true)
                        when {
                            title == "SectionOne" -> {
                                viewModel.sendSectionOneQuizAnswer(
                                    title,
                                    (rootViewmodel
                                        .mapSectionIdToListOfAnswer[it.data.id]
                                        ?: mutableStateMapOf()
                                            ).map {
                                            SingleSendQuizSectionOneAnswerDataRequest(
                                                question_id = it.key,
                                                data = it.value
                                            )
                                        }
                                )
                            }

                            title == "SectionTwo" || title == "SectionThree"-> {
                                viewModel.sendSectionTwoAndThreeQuizAnswer(
                                    title,
                                    (rootViewmodel
                                        .mapSectionIdToListOfAnswer[it.data.id]
                                        ?: mutableStateMapOf()
                                            ).map {
                                            SingleSendQuizSectionTwoAndThreeAnswerDataRequest(
                                                question_id = it.key,
                                                data = Integer.parseInt(it.value)
                                            )
                                        }
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = sendQuizAnswerState.value) {
        if (viewModel.startSendQuizAnswer.value) {
            when (sendQuizAnswerState.value) {
                is Resource.Error -> {
                    changeLoadingState(false)
                }
                is Resource.Loading -> {/*TODO*/
                }
                is Resource.Success -> {
                    delay(1500)
                    changeLoadingState(false)
                    when (title) {
                        "SectionOne" -> {
                            navController.popBackStack()
                            navController.navigate("${NavRoute.Penjurusan.name}/title=SectionTwo")
                        }
                        "SectionTwo" -> {
                            navController.popBackStack()
                            navController.navigate("${NavRoute.Penjurusan.name}/title=SectionThree")
                        }
                        "SectionThree" -> {
                            navController.popBackStack()
                            navController.navigate(NavRoute.PenjurusanSuccess.name)
                        }
                        else -> {/*TODO*/ }
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.getQuizQuestion(title)
    }

    AppBottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColor.grey50)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp)
                ) {
                    AppTextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        onClick = { /*TODO*/ },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(AppColor.success500),
                            ) {
                                Icon(
                                    modifier = Modifier.padding(8.dp),
                                    painter = rememberAsyncImagePainter(model = R.drawable.penjurusan_refresh_icon),
                                    contentDescription = "",
                                    tint = AppColor.grey50
                                )
                            }

                            AppText(text = "Mulai ulang tes", style = AppType.subheading2)
                        }
                    }

                    AppTextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp),
                        onClick = {
                            viewModel.startExitProcess.value = true
                        },
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(AppColor.danger400),
                            ) {
                                Icon(
                                    modifier = Modifier.padding(8.dp),
                                    painter = rememberAsyncImagePainter(model = R.drawable.penjurusan_exit_icon),
                                    contentDescription = "",
                                    tint = AppColor.grey50
                                )
                            }

                            AppText(text = "Keluar tes", style = AppType.subheading2)
                        }
                    }
                }
            }
        }
    ) {
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            Column(
                modifier = Modifier.onSizeChanged {
                    density.run {
                        topSectionHeight.value = it.height.toDp()
                    }
                }
            ) {
                Row(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AppIconButton(
                        painter = rememberAsyncImagePainter(model = R.drawable.penjurusan_back_icon),
                        onClick = { /*TODO*/ },
                        backgroundColor = AppColor.secondary100,
                        shape = RoundedCornerShape(10.dp)
                    )

                    AppIconButton(
                        painter = rememberAsyncImagePainter(model = R.drawable.penjurusan_menu_icon),
                        onClick = {
                            coroutineContext.launch {
                                bottomSheetState.expand()
                            }
                        },
                        backgroundColor = AppColor.primary50,
                        shape = RoundedCornerShape(10.dp)
                    )
                }

                when (quizQuestion.value) {
                    is Resource.Error -> {/*TODO*/
                    }
                    is Resource.Loading -> {/*TODO*/
                    }
                    is Resource.Success -> {
                        quizQuestion.value.data?.let { item ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 20.dp)
                            ) {
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    LinearProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(10.dp)
                                            .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                        progress = 1f,
                                        color = AppColor.grey100
                                    )
                                    LinearProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(10.dp)
                                            .clip(RoundedCornerShape(Int.MAX_VALUE.dp)),
                                        progress = (
                                                (rootViewmodel
                                                    .mapSectionIdToMapOfQuestionIdToAnswerId[item.data.id]
                                                    ?.values
                                                    ?: listOf()
                                                        ).filter { it.isNotEmpty() }).size / viewModel.totalQuestionCount.value,
                                        color = AppColor.primary400
                                    )
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    AppText(
                                        text = "Bagian ${
                                            when (title) {
                                                "SectionOne" -> 1
                                                "SectionTwo" -> 2
                                                "SectionThree" -> 3
                                                else -> 3
                                            }
                                        }/3",
                                        style = AppType.body2,
                                        color = AppColor.grey500
                                    )
                                    AppText(
                                        text = "${
                                            (rootViewmodel
                                                .mapSectionIdToMapOfQuestionIdToAnswerId[item.data.id]
                                                ?.values
                                                ?: listOf()
                                                    ).filter { it.isNotEmpty() }.size
                                        }/${viewModel.totalQuestionCount.value.roundToInt()} Petanyaan",
                                        style = AppType.body2,
                                        color = AppColor.grey500
                                    )
                                }
                            }
                        }
                    }
                }

            }


            when (quizQuestion.value) {
                is Resource.Error -> {/*TODO*/
                }
                is Resource.Loading -> {/*TODO*/
                }
                is Resource.Success -> {
                    quizQuestion.value.data?.let { item ->
                        LaunchedEffect(key1 = true) {
                            viewModel.totalQuestionCount.value = item.data.questions.size.toFloat()

                            if (rootViewmodel.mapSectionIdToMapOfQuestionIdToAnswerId[item.data.id] == null) {
                                rootViewmodel
                                    .mapSectionIdToMapOfQuestionIdToAnswerId[
                                        item.data.id
                                ] = mutableStateMapOf()

                                rootViewmodel
                                    .mapSectionIdToListOfAnswer[
                                        item.data.id
                                ] = mutableStateMapOf()

                                rootViewmodel
                                    .mapSectionIdToAlreadyOnLatestQuestionState[
                                        item.data.id
                                ] = false

                                item
                                    .data
                                    .questions
                                    .forEach { question ->
                                        rootViewmodel
                                            .mapSectionIdToListOfAnswer[item.data.id]?.put(
                                            question.id,
                                            ""
                                        )

                                        rootViewmodel
                                            .mapSectionIdToMapOfQuestionIdToAnswerId[
                                                item.data.id
                                        ]?.let {
                                            it[question.id] = ""
                                        }
                                    }
                            }

                        }

                        HorizontalPager(
                            state = pagerState,
                            key = { item.data.questions[it].id },
                            count = item.data.questions.size
                        ) { index ->
                            QuizQuestionItem(
                                minHeigth = (screenHeight - topSectionHeight.value.value - pagerIndicatorHeight.value.value).dp,
                                item = item.data.questions[index],
                                onAnswerClick = { id, text ->
                                    coroutineScope.launch {
                                        rootViewmodel
                                            .mapSectionIdToMapOfQuestionIdToAnswerId[item.data.id]
                                            ?.let { it[item.data.questions[index].id] = id }
                                        rootViewmodel
                                            .mapSectionIdToListOfAnswer[item.data.id]
                                            ?.let {
                                                it[item.data.questions[index].id] = text
                                            }

                                        delay(1000)
                                        if (
                                            (rootViewmodel
                                                .mapSectionIdToMapOfQuestionIdToAnswerId[item.data.id]
                                                ?.values ?: listOf()).none { it.isEmpty() }
                                        ) {
                                            viewModel.startSendQuizAnswer.value = true
                                        } else {
                                            when {
                                                (index + 1) <= (item.data.questions.size - 1) -> {
                                                    if (rootViewmodel.mapSectionIdToAlreadyOnLatestQuestionState[item.data.id] == true) {
                                                        if (
                                                            (rootViewmodel
                                                                .mapSectionIdToMapOfQuestionIdToAnswerId[item.data.id]
                                                                ?.values
                                                                ?: listOf()).none { it.isEmpty() }
                                                        ) {
                                                            viewModel.startSendQuizAnswer.value =
                                                                true
                                                        } else {
                                                            for (i in pagerState.currentPage until item.data.questions.size) {
                                                                pagerState.animateScrollToPage(i)
                                                                if ((rootViewmodel
                                                                        .mapSectionIdToMapOfQuestionIdToAnswerId[item.data.id]
                                                                        ?.get(item.data.questions[i].id)
                                                                        ?: "").isEmpty()
                                                                ) {
                                                                    return@launch
                                                                }
                                                                delay(200)
                                                            }
                                                        }
                                                    } else {
                                                        pagerState.animateScrollToPage(index + 1)
                                                    }
                                                }

                                                index == (item.data.questions.size - 1) -> {
                                                    rootViewmodel.mapSectionIdToAlreadyOnLatestQuestionState[item.data.id] =
                                                        true
                                                    if (
                                                        (rootViewmodel
                                                            .mapSectionIdToMapOfQuestionIdToAnswerId[item.data.id]
                                                            ?.values
                                                            ?: listOf()).none { it.isEmpty() }
                                                    ) {
                                                        viewModel.startSendQuizAnswer.value = true
                                                    } else {
                                                        repeat(item.data.questions.size) { i ->
                                                            pagerState.animateScrollToPage(i)
                                                            if ((rootViewmodel
                                                                    .mapSectionIdToMapOfQuestionIdToAnswerId[item.data.id]
                                                                    ?.get(item.data.questions[i].id)
                                                                    ?: "").isEmpty()
                                                            ) {
                                                                return@launch
                                                            }
                                                            delay(200)
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                },
                                pickedAnswerId = (rootViewmodel.mapSectionIdToMapOfQuestionIdToAnswerId[item.data.id]?.get(
                                    item.data.questions[index].id
                                ) ?: ""),
                                onOtherQuestionClick = {
                                    /*TODO*/
                                }
                            )
                        }
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .onSizeChanged {
                        density.run {
                            pagerIndicatorHeight.value = it.height.toDp()
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                HorizontalPagerIndicator(
                    modifier = Modifier.padding(bottom = 20.dp),
                    inactiveColor = AppColor.grey400,
                    activeColor = AppColor.primary400,
                    pagerState = pagerState
                )
            }
        }
    }
}