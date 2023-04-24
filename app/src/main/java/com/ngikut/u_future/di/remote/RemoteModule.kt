package com.ngikut.u_future.di.remote

import com.ngikut.u_future.data.remote.RemoteSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    fun provideHttpClient() = HttpClient(CIO){
        install(ContentNegotiation){
            json()
        }
    }

    @Provides
    fun provideRemoteSource(httpClient: HttpClient) = RemoteSource(httpClient)
}