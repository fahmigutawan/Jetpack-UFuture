package com.ngikut.u_future.viewmodel.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngikut.u_future.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewmodel @Inject constructor(
    private val repository: Repository
):ViewModel() {
    fun setFirstTimeState(firstTime:Boolean, onFinished:() -> Unit) = viewModelScope.launch {
        repository.setFirstTimeState(firstTime)
        delay(1500)
        onFinished()
    }
}