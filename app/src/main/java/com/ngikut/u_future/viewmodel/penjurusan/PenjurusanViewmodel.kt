package com.ngikut.u_future.viewmodel.penjurusan

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.data.repository.Repository
import com.ngikut.u_future.model.remote.request.quiz.SingleSendQuizSectionOneAnswerDataRequest
import com.ngikut.u_future.model.remote.request.quiz.SingleSendQuizSectionTwoAndThreeAnswerDataRequest
import com.ngikut.u_future.model.remote.response.quiz.GetQuizQuestionResponse
import com.ngikut.u_future.model.remote.response.quiz.SendQuizAnswerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PenjurusanViewmodel @Inject constructor(
    private val repository: Repository
) : ViewModel() {
    val startExitProcess = mutableStateOf(false)
    val totalQuestionCount = mutableStateOf( 1f)
    val startSendQuizAnswer = mutableStateOf(false)

    val quizQuestion = MutableStateFlow<Resource<GetQuizQuestionResponse>>(Resource.Loading())
    val sendQuizAnswerState = MutableStateFlow<Resource<SendQuizAnswerResponse>>(Resource.Loading())

    fun getQuizQuestion(title:String){
        viewModelScope.launch {
            repository.getQuizQuestion(title).collect{
                quizQuestion.value = it
            }
        }
    }

    fun sendSectionOneQuizAnswer(title:String, data:List<SingleSendQuizSectionOneAnswerDataRequest>){
        viewModelScope.launch {
            repository.sendSectionOneQuizAnswer(
                title,
                data
            ).collect{
                sendQuizAnswerState.value = it
            }
        }
    }

    fun sendSectionTwoAndThreeQuizAnswer(title:String, data:List<SingleSendQuizSectionTwoAndThreeAnswerDataRequest>){
        viewModelScope.launch {
            repository.sendSectionTwoAndThreeQuizAnswer(
                title,
                data
            ).collect{
                sendQuizAnswerState.value = it
            }
        }
    }
}