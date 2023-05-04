package com.ngikut.u_future.data.remote

import com.ngikut.u_future.model.remote.request.student.LoginRequest
import com.ngikut.u_future.model.remote.response.student.LoginResponse
import io.ktor.client.*
import io.ktor.client.features.ClientRequestException
import io.ktor.client.features.RedirectResponseException
import io.ktor.client.features.ServerResponseException
import io.ktor.client.request.post
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
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
    ):Flow<ApiResponse<LoginResponse>> = getResponse {
        val res = client.post<LoginResponse> {
            url(HttpEndpoint.LOGIN)
            contentType(ContentType.Application.Json)
            body = request
        }

        if(res.meta.success){
            ApiResponse.Success(res)
        }else{
            ApiResponse.Error(res.meta.message)
        }
    }

    private inline fun <reified T> getResponse(
        crossinline httpCall: suspend () -> ApiResponse<T>
    ): Flow<ApiResponse<T>> = flow {
        try {
            emit(httpCall())
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