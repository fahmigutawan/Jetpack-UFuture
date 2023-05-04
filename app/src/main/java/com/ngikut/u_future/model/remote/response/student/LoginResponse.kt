package com.ngikut.u_future.model.remote.response.student

import com.ngikut.u_future.model.remote.response.base.MetaResponse

data class LoginResponse(
    val meta: MetaResponse,
    val data: LoginResponseData? = null
)

data class LoginResponseData(
    val token: String
)
