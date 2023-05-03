package com.ngikut.u_future.model.remote.response.base

import kotlinx.serialization.Serializable

@Serializable
data class SingleStudentResponse(
    val id: String,
    val name: String,
    val email: String,
    val type: String,
    val school_id: String,
    val school: SingleSchoolResponse? = null,
    val createdAt: String,
    val updatedAt: String? = null,
    val deletedAt: String? = null
)
