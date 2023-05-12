package com.ngikut.u_future.model.remote.response.jurusan

import com.ngikut.u_future.model.remote.response.base.MetaResponse
import com.ngikut.u_future.model.remote.response.base.SingleJurusanResponse

data class PredictJurusanResponse(
    val meta:MetaResponse,
    val data:List<SingleJurusanResponse>
)
