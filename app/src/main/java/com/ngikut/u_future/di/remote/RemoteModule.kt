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
import io.ktor.client.engine.android.Android
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.auth.Auth
import io.ktor.client.features.auth.providers.BearerTokens
import io.ktor.client.features.auth.providers.bearer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.ANDROID
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    fun provideHttpClient(
        datastoreSource: DatastoreSource,
        @ApplicationContext context: Context
    ) = HttpClient(Android) {
        install(JsonFeature){
            serializer = KotlinxSerializer(
                Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = false
                }
            )
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 5000
            connectTimeoutMillis = 5000
            socketTimeoutMillis = 5000
        }
        install(Auth) {
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

                    BearerTokens("", "")
                }
            }
        }
        install(Logging){
            logger = Logger.ANDROID
            level = LogLevel.ALL
        }
    }

    @Provides
    fun provideRemoteSource(httpClient: HttpClient) = RemoteSource(httpClient)
}