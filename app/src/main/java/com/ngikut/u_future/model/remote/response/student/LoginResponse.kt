package com.ngikut.u_future.model.remote.response.student

import com.ngikut.u_future.model.remote.response.base.MetaResponse
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val meta: MetaResponse,
    val data: LoginResponseData
)

@Serializable
data class LoginResponseData(
    val token: String
)
