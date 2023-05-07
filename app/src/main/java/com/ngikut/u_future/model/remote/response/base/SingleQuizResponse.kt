package com.ngikut.u_future.model.remote.response.base

data class SingleQuizResponse(
    val id:String,
    val text:String,
    val options:List<SingleQuizOptionResponse>,
    val createdAt: String,
    val updatedAt: String?,
    val deletedAt: String?
)

data class SingleQuizOptionResponse(
    val id:String,
    val text:String,
    val description:String,
    val createdAt:String,
    val updatedAt:String?,
    val deletedAt:String?
)