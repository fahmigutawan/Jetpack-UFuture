package com.ngikut.u_future.model.remote.response.base

data class SingleSchoolResponse(
    val id: String,
    val name: String,
    val type: String,
    val students: List<SingleStudentResponse>? = null /*TODO*/,
    val createdAt: String,
    val updatedAt: String? = null,
    val deletedAt: String? = null
)
