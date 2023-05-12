package com.ngikut.u_future.viewmodel.ubot

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ngikut.u_future.component.UBotBubbleType
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.data.repository.Repository
import com.ngikut.u_future.model.remote.response.chatbot.SendChatBotResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UbotViewmodel @Inject constructor(
    private val repository: Repository
):ViewModel(){
    val listOfChat = mutableStateListOf<ChatModel>()
    val chatValueState = mutableStateOf("")
    val chatState = MutableStateFlow<Resource<SendChatBotResponse>>(Resource.Loading())

    fun sendChatBot(message:String){
        viewModelScope.launch {
            repository.sendChatBot(message).collect{
                chatState.value = it
                if(it is Resource.Success) return@collect
            }
        }
    }
}

data class ChatModel(
    val message:String,
    val type:UBotBubbleType
)