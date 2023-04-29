package com.ngikut.u_future.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.ngikut.u_future.ui.theme.AppColor
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AppBottomSheetScaffold(
    topBar: @Composable (() -> Unit)? = null,
    scaffoldState: BottomSheetScaffoldState,
    sheetContent: (@Composable () -> Unit) = {},
    sheetPeekHeight: Dp = 0.dp,
    content: @Composable () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        topBar = topBar,
        sheetContent = { sheetContent() },
        sheetPeekHeight = sheetPeekHeight,
        scaffoldState = scaffoldState
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            content()

            val expandProgress = scaffoldState.bottomSheetState.expandProgress
            if (expandProgress > 0f) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppColor.grey900.copy(alpha = expandProgress * 0.3f))
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = {
                                coroutineScope.launch {
                                    scaffoldState.bottomSheetState.collapse()
                                }
                            }
                        )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
val BottomSheetState.expandProgress: Float
    get() {
        return when (progress.from) {
            BottomSheetValue.Collapsed -> {
                when (progress.to) {
                    BottomSheetValue.Collapsed -> 0f
                    BottomSheetValue.Expanded -> progress.fraction
                }
            }
            BottomSheetValue.Expanded -> {
                when (progress.to) {
                    BottomSheetValue.Collapsed -> 1f - progress.fraction
                    BottomSheetValue.Expanded -> 1f
                }
            }
        }
    }