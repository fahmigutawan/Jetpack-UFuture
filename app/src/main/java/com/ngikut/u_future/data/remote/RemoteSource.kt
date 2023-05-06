package com.ngikut.u_future.data.remote

import com.ngikut.u_future.model.remote.request.student.LoginRequest
import com.ngikut.u_future.model.remote.request.student.RegisterRequest
import com.ngikut.u_future.model.remote.response.quiz.CheckPenjurusanStateResponse
import com.ngikut.u_future.model.remote.response.student.LoginResponse
import com.ngikut.u_future.model.remote.response.student.RegisterResponse
import com.ngikut.u_future.util.getResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteSource @Inject constructor(
    private val client:HttpClient
) {
    fun login(
        request:LoginRequest
    ):Flow<Resource<LoginResponse>> = getResponse {
        val res = client.post {
            url(HttpEndpoint.LOGIN)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<LoginResponse>()

        if(res.meta.success){
            Resource.Success(res)
        }else{
            Resource.Error(res.meta.message)
        }
    }

    fun register(
        request:RegisterRequest
    ):Flow<Resource<RegisterResponse>> = getResponse {
        val res = client.post {
            url(HttpEndpoint.LOGIN)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<RegisterResponse>()

        if(res.meta.success){
            Resource.Success(res)
        }else{
            Resource.Error(res.meta.message)
        }
    }

    fun checkPenjurusanState() = getResponse {
        val res = client.get {
            url(HttpEndpoint.CHECK_PENJURUSAN)
            contentType(ContentType.Application.Json)
        }.body<CheckPenjurusanStateResponse>()

        if(res.meta.success){
            Resource.Success(res)
        }else{
            Resource.Error(res.meta.message)
        }
    }
}