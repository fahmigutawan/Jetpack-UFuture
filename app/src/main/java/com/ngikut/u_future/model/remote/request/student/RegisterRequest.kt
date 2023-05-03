package com.ngikut.u_future.model.remote.request.student

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val school_id: String,
    val name: String,
    val email: String,
    val password: String
)
