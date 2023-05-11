package com.ngikut.u_future.viewmodel.penjurusan

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.data.repository.Repository
import com.ngikut.u_future.model.remote.response.quiz.GetQuizAnalysisResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PenjurusanSuccessViewmodel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val getQuizAnalysisState = MutableStateFlow<Resource<GetQuizAnalysisResponse>>(Resource.Loading())

    fun getQuizAnalysis() {
        viewModelScope.launch {
            delay(1000)
            repository.getQuizAnalysis().collect{
                getQuizAnalysisState.value = it
            }
        }
    }
}