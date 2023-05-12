package com.ngikut.u_future.model.remote.response.chatbot

import com.ngikut.u_future.model.remote.response.base.MetaResponse

data class SendChatBotResponse(
    val meta:MetaResponse,
    val data:SendChatBotDataResponse
)

data class SendChatBotDataResponse(
    val text:String
)