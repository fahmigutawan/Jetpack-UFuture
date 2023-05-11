package com.ngikut.u_future.model.remote.response.quiz

import com.ngikut.u_future.model.remote.response.base.MetaResponse

data class GetQuizAnalysisResponse(
    val meta:MetaResponse,
    val data:GetQuizAnalysisDataResponse
)

data class GetQuizAnalysisDataResponse(
    val title:String,
    val tag:String,
    val description:String,
    val realistic:Float,
    val investigative:Float,
    val artistic:Float,
    val social:Float,
    val enterprising:Float,
    val conventional:Float
)
