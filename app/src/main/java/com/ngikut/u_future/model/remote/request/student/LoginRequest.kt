package com.ngikut.u_future.model.remote.request.student

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val email:String,
    val password:String
)
