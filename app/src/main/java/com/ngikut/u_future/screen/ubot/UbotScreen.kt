package com.ngikut.u_future.screen.ubot

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowCircleLeft
import androidx.compose.material.icons.filled.ArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.ngikut.u_future.R
import com.ngikut.u_future.component.AppButton
import com.ngikut.u_future.component.AppText
import com.ngikut.u_future.component.AppTextInputBasic
import com.ngikut.u_future.component.UBotBubbleType
import com.ngikut.u_future.component.UbotBubble
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.ui.theme.AppColor
import com.ngikut.u_future.ui.theme.AppType
import com.ngikut.u_future.viewmodel.ubot.ChatModel
import com.ngikut.u_future.viewmodel.ubot.UbotViewmodel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UbotScreen(navController: NavController) {
    val viewModel = hiltViewModel<UbotViewmodel>()
    val sendIconSize = 42.dp
    val textInputWidth = LocalConfiguration.current.screenWidthDp / 4 * 3
    val chatBotState = viewModel.chatState.collectAsState()

    LaunchedEffect(key1 = chatBotState.value) {
        when (chatBotState.value) {
            is Resource.Error -> {
                viewModel.listOfChat.add(0,
                    ChatModel(
                        message = "TERJADI MASALAH DALAM MENCARI JAWABAN. COBA LAGI",
                        type = UBotBubbleType.UBOT
                    )
                )
            }

            is Resource.Loading -> {/*TODO*/
            }

            is Resource.Success -> {
                chatBotState.value.data?.let {
                    viewModel.listOfChat.add(0,
                        ChatModel(
                            message = it.data.text,
                            type = UBotBubbleType.UBOT
                        )
                    )
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = AppColor.grey50,
                elevation = 16.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .clip(CircleShape)
                                    .border(1.dp, AppColor.grey500, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "",
                                    tint = AppColor.grey500
                                )
                            }
                        }

                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(AppColor.primary50),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = rememberAsyncImagePainter(model = R.drawable.bottombar_ubot),
                                contentDescription = "",
                                tint = AppColor.primary400
                            )
                        }

                        Column {
                            AppText(text = "U-Bot", style = AppType.h3)
                            AppText(
                                text = "Selalu aktif",
                                style = AppType.body3,
                                color = AppColor.grey500
                            )
                        }
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .border(1.dp, AppColor.grey500, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.MoreHoriz,
                                contentDescription = "",
                                tint = AppColor.grey500
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = AppColor.grey50,
                elevation = 16.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    AppTextInputBasic(
                        modifier = Modifier
                            .width(textInputWidth.dp),
                        placeHolder = "Tulis sesuatu...",
                        value = viewModel.chatValueState.value,
                        onValueChange = { viewModel.chatValueState.value = it })
                    AppButton(
                        modifier = Modifier.size(sendIconSize),
                        onClick = {
                            viewModel.listOfChat.add(0,
                                ChatModel(
                                    viewModel.chatValueState.value,
                                    UBotBubbleType.ME
                                )
                            )
                            viewModel.sendChatBot(viewModel.chatValueState.value)
                            viewModel.chatValueState.value = ""
                        },
                        shape = CircleShape,
                        backgroundColor = AppColor.primary400
                    ) {
                        Icon(
                            painter = rememberAsyncImagePainter(model = R.drawable.ubot_send_icon),
                            contentDescription = "",
                            tint = AppColor.grey50
                        )
                    }
                }
            }
        }
    ) {
        LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp), reverseLayout = true) {
            item {
                if (chatBotState.value is Resource.Loading && viewModel.listOfChat.isNotEmpty()) {
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator(color = AppColor.primary400)
                    }
                }
            }

            items(viewModel.listOfChat) { chat ->
                UbotBubble(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    isError = false,
                    message = chat.message,
                    chatType = chat.type
                )
            }

            item{
                Spacer(modifier = Modifier)
            }
        }
    }
}