package com.ngikut.u_future.model.remote.response.komparasi_jurusan

class SingleKomparasiDataResponse(
    val percentage:Float,
    val tingkat_keketatan:Float,
    val tingkat_keselarasan:Float,
    val tingkat_dapat_pekerjaan:Float,
    val tingkat_prospek_kerja:Float,
    val gaji:String,
    val pekerjaan:String,
    val deskripsi:String,
    val tag_jurusan:String,
    val jurusan:String,
    val mata_kulian:String
)