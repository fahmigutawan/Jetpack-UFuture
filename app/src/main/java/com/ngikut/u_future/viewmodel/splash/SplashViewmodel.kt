package com.ngikut.u_future.viewmodel.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngikut.u_future.data.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewmodel @Inject constructor(
    private val repository: Repository
):ViewModel() {
    fun getFirstTimeState(
        onRetrieved:(Boolean) -> Unit
    ) = viewModelScope.launch {
        delay(2000)

        repository.getFirstTimeState().collectLatest {
            onRetrieved(it)
        }
    }

    fun getToken(
        onRetrieved: (String) -> Unit
    ) = viewModelScope.launch {
        repository.getToken().collectLatest {
            onRetrieved(it)
        }
    }
}