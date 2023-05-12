package com.ngikut.u_future.model.remote.response.base

data class SingleJurusanResponse(
    val id:String,
    val user_id:String,
    val percentage:Float,
    val nama_jurusan:String,
    val tag_jurusan:String,
    val jurusan:String
)
