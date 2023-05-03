package com.ngikut.u_future.data.remote

import com.ngikut.u_future.model.remote.request.student.LoginRequest
import com.ngikut.u_future.model.remote.response.student.LoginResponse
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.RedirectResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
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
    ):Flow<ApiResponse<LoginResponse>> = getResponse {
        client.post {
            url(HttpEndpoint.LOGIN)
        }
    }

    private inline fun <reified T> getResponse(
        crossinline httpCall: suspend () -> HttpResponse
    ): Flow<ApiResponse<T>> = flow {
        try {
            val res = httpCall()

            if(res.status.value.toString()[0] == '2'){
                emit(ApiResponse.Success(res.body<T>()))
            }else{
                emit(ApiResponse.Error("Terjadi kesalahan saat mendapatkan data"))
            }

        } catch (e: RedirectResponseException) {
            // 3xx - responses
            emit(ApiResponse.Error("Error: ${e.response.status.description}"))
        } catch (e: ClientRequestException) {
            // 4xx - responses
            emit(ApiResponse.Error("Error: ${e.response.status.description}"))
        } catch (e: ServerResponseException) {
            // 5xx - responses
            emit(ApiResponse.Error("Error: ${e.response.status.description}"))
        } catch (e: Exception) {
            // 6xx - responses
            emit(ApiResponse.Error("Error: ${e.message}"))
        }
    }.flowOn(Dispatchers.IO)
}