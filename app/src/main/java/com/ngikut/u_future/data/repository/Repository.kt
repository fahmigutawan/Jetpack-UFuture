package com.ngikut.u_future.data.repository

import com.ngikut.u_future.data.datastore.DatastoreSource
import com.ngikut.u_future.data.remote.ApiResponse
import com.ngikut.u_future.data.remote.RemoteSource
import com.ngikut.u_future.data.remote.Resource
import com.ngikut.u_future.model.remote.request.student.LoginRequest
import com.ngikut.u_future.model.remote.response.student.LoginResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteSource: RemoteSource,
    private val datastoreSource: DatastoreSource
) {
    suspend fun setFirstTimeState(firstTime:Boolean) = datastoreSource.setFirstTimeState(firstTime)

    fun getFirstTimeState() = datastoreSource.getFirstTimeState()

    suspend fun setToken(token:String) = datastoreSource.setToken(token)

    fun getToken() = datastoreSource.getToken()

    fun login(email:String, password:String): Flow<Resource<LoginResponse>> = flow{
        emit(Resource.Loading())
        when(val res = remoteSource.login(LoginRequest(email, password)).first()){
            ApiResponse.Empty -> {
                emit(Resource.Error(""))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error(res.errorMessage))
            }
            is ApiResponse.Success -> {
                emit(Resource.Success(res.data))
            }
        }
    }
}