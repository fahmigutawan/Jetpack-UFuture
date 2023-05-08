package com.ngikut.u_future.model.remote.response.quiz

import com.ngikut.u_future.model.remote.response.base.MetaResponse

data class SendQuizAnswerResponse(
    val meta:MetaResponse,
    val data:SendQuizAnswerDataResponse
)

data class SendQuizAnswerDataResponse(
    val quiz_attempt:String
)