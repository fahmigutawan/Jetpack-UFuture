package com.ngikut.u_future.model.remote.response.komparasi_jurusan

import com.ngikut.u_future.model.remote.response.base.MetaResponse

data class CompareTwoJurusanResponse(
    val meta:MetaResponse,
    val data:CompareTwoJurusanDataResponse
)

data class CompareTwoJurusanDataResponse(
    val analysis:String,
    val data_one:SingleKomparasiDataResponse,
    val data_two:SingleKomparasiDataResponse
)