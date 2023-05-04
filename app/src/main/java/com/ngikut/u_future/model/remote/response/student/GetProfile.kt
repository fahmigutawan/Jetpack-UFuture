package com.ngikut.u_future.model.remote.response.student

import com.ngikut.u_future.model.remote.response.base.MetaResponse
import com.ngikut.u_future.model.remote.response.base.SingleStudentResponse

data class GetProfile(
    val meta:MetaResponse,
    val data:SingleStudentResponse
)
