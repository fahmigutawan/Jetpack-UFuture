package com.ngikut.u_future.screen.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    onLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit
) {
    val state = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    HorizontalPager(
        count = OnboardingItem.values().size,
        state = state
    ) { index ->
        OnboardingContent(
            item = OnboardingItem.values()[index],
            state = state,
            onNextClicked = {
                coroutineScope.launch {
                    if (state.currentPage + 1 <= OnboardingItem.values().size - 1) {
                        state.animateScrollToPage(index + 1)
                    }
                }
            },
            onBackClicked = {
                coroutineScope.launch {
                    if (state.currentPage - 1 >= 0) {
                        state.animateScrollToPage(index - 1)
                    }
                }
            },
            onLoginClicked = {

            },
            onRegisterClicked = {

            },
            onSkipClicked = {
                coroutineScope.launch {
                    state.animateScrollToPage(OnboardingItem.values().size - 1)
                }
            },
            isFirst = index == 0,
            isLast = (OnboardingItem.values().size - 1) == index
        )
    }
}