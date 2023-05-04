package com.ngikut.u_future.model.remote.response.student

import com.ngikut.u_future.model.remote.response.base.MetaResponse


data class RegisterResponse(
    val meta: MetaResponse,
    val data: RegisterResponseData
)

data class RegisterResponseData(
    val token: String
)
