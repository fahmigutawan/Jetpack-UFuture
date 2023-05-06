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
import io.ktor.client.plugins.*
import io.ktor.client.plugins.auth.*
import io.ktor.client.plugins.auth.providers.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.gson.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    fun provideHttpClient(
        datastoreSource: DatastoreSource,
        @ApplicationContext context: Context
    ) = HttpClient(Android) {
        install(ContentNegotiation){
            gson()
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 30000
            connectTimeoutMillis = 30000
            socketTimeoutMillis = 30000
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