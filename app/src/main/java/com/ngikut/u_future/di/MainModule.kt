package com.ngikut.u_future.di

import com.ngikut.u_future.data.datastore.DatastoreSource
import com.ngikut.u_future.data.remote.RemoteSource
import com.ngikut.u_future.data.repository.Repository
import com.ngikut.u_future.di.datastore.DatastoreModule
import com.ngikut.u_future.di.remote.RemoteModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        DatastoreModule::class,
        RemoteModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    fun provideRepository(
        datastoreSource: DatastoreSource,
        remoteSource: RemoteSource
    ) = Repository(remoteSource, datastoreSource)
}