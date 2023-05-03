package com.ngikut.u_future.model.remote.response.base

import kotlinx.serialization.Serializable

@Serializable
data class MetaResponse(
    val success:Boolean,
    val message:String
)
