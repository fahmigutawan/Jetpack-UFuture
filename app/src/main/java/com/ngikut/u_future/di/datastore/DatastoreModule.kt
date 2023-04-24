package com.ngikut.u_future.di.datastore

import android.content.Context
import com.ngikut.u_future.data.datastore.DatastoreSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatastoreModule {
    @Provides
    fun provideDatastoreSource(
        @ApplicationContext context: Context
    ) = DatastoreSource(context)
}