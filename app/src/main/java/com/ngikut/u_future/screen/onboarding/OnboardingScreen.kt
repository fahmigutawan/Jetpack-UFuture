package com.ngikut.u_future.screen.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.ngikut.u_future.util.NavRoute
import com.ngikut.u_future.viewmodel.onboarding.OnboardingViewmodel
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    navController: NavController
) {
    val state = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val viewModel = hiltViewModel<OnboardingViewmodel>()

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
                viewModel.setFirstTimeState(
                    false,
                    onFinished = {
                        navController.navigate(route = NavRoute.Login.name){
                            popUpTo(NavRoute.Onboard.name){
                                inclusive = true
                            }
                        }
                    })
            },
            onRegisterClicked = {
                /*TODO Later*/
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