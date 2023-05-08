package com.ngikut.u_future.viewmodel.penjurusan

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.data.repository.Repository
import com.ngikut.u_future.model.remote.response.quiz.GetQuizQuestionResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PenjurusanViewmodel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val startExitProcess = mutableStateOf(false)
    val totalQuestionCount = mutableStateOf( 1f)

    val quizQuestion = MutableStateFlow<Resource<GetQuizQuestionResponse>>(Resource.Loading())

    fun getQuizQuestion(title:String){
        viewModelScope.launch {
            repository.getQuizQuestion(title).collect{
                quizQuestion.value = it
            }
        }
    }
}