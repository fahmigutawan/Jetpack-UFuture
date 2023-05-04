package com.ngikut.u_future.model.remote.request.student

data class RegisterRequest(
    val school_id: String,
    val name: String,
    val email: String,
    val password: String
)
