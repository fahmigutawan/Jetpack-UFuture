package com.ngikut.u_future.model.remote.response.quiz

import com.ngikut.u_future.model.remote.response.base.MetaResponse

data class CheckPenjurusanStateResponse(
    val meta:MetaResponse,
    val data:CheckPenjurusanStateDataResponse
)

data class CheckPenjurusanStateDataResponse(
    val already_taken:Boolean
)