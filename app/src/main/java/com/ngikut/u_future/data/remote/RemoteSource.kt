package com.ngikut.u_future.data.remote

import io.ktor.client.*
import javax.inject.Inject

class RemoteSource @Inject constructor(
    private val client:HttpClient
) {
}