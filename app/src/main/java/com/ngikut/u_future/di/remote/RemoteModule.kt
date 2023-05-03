package com.ngikut.u_future.di.remote

import android.content.Context
import com.ngikut.u_future.UFutureActivity
import com.ngikut.u_future.data.datastore.DatastoreSource
import com.ngikut.u_future.data.remote.RemoteSource
import com.ngikut.u_future.util.NavRoute
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    fun provideHttpClient(
        datastoreSource: DatastoreSource,
        @ApplicationContext context:Context
    ) = HttpClient(CIO){
        install(ContentNegotiation){
            json()
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 5000
            connectTimeoutMillis = 5000
            socketTimeoutMillis = 5000
        }
        install(Auth){
            bearer {
                loadTokens {
                    BearerTokens(
                        accessToken = datastoreSource.getToken().first(),
                        refreshToken = ""
                    )
                }

                refreshTokens {
                    val activity = context as UFutureActivity
                    datastoreSource.setToken("")
                    delay(2000)
                    activity.navigateAndCleanBackStack(NavRoute.Login.name)

                    BearerTokens("","")
                }
            }
        }
    }

    @Provides
    fun provideRemoteSource(httpClient: HttpClient) = RemoteSource(httpClient)
}