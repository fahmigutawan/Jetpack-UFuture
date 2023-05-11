package com.ngikut.u_future.util

import com.ngikut.u_future.data.remote.Resource
import io.ktor.client.plugins.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

inline fun <reified T> getResponse(
    crossinline httpCall: suspend () -> Resource<T>
): Flow<Resource<T>> = flow {
    emit(Resource.Loading())

    try {
        emit(httpCall())
    } catch (e: RedirectResponseException) {
        // 3xx - responses
        emit(Resource.Error("Error: ${e.response.status.description}"))
    } catch (e: ClientRequestException) {
        // 4xx - responses
        emit(Resource.Error("Error: ${e.response.status.description}"))
    } catch (e: ServerResponseException) {
        // 5xx - responses
        emit(Resource.Error("Error: ${e.response.status.description}"))
    } catch (e: Exception) {
        // 6xx - responses
        emit(Resource.Error("Error: ${e.message}"))
    }
}.flowOn(Dispatchers.IO)