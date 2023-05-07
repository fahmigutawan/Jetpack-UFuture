package com.ngikut.u_future.model.remote.response.quiz

import com.ngikut.u_future.model.remote.response.base.MetaResponse
import com.ngikut.u_future.model.remote.response.base.SingleQuizResponse

data class GetQuizQuestionResponse(
    val meta: MetaResponse,
    val data: GetQuizQuestionDataResponse
)

data class GetQuizQuestionDataResponse(
    val id:String,
    val title:String,
    val questions:List<SingleQuizResponse>
)