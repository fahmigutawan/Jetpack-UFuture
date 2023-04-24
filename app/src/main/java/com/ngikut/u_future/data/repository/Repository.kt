package com.ngikut.u_future.data.repository

import com.ngikut.u_future.data.datastore.DatastoreSource
import com.ngikut.u_future.data.remote.RemoteSource
import javax.inject.Inject

class Repository @Inject constructor(
    remoteSource: RemoteSource,
    datastoreSource: DatastoreSource
) {
}