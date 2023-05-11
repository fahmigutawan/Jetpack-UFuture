package com.ngikut.u_future.data.repository

import com.ngikut.u_future.data.datastore.DatastoreSource
import com.ngikut.u_future.data.remote.RemoteSource
import com.ngikut.u_future.model.remote.request.quiz.SingleSendQuizSectionOneAnswerDataRequest
import com.ngikut.u_future.model.remote.request.quiz.SingleSendQuizSectionTwoAndThreeAnswerDataRequest
import com.ngikut.u_future.model.remote.request.student.LoginRequest
import com.ngikut.u_future.model.remote.request.student.RegisterRequest
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteSource: RemoteSource,
    private val datastoreSource: DatastoreSource
) {
    suspend fun setFirstTimeState(firstTime: Boolean) = datastoreSource.setFirstTimeState(firstTime)

    fun getFirstTimeState() = datastoreSource.getFirstTimeState()

    suspend fun setToken(token: String) = datastoreSource.setToken(token)

    fun getToken() = datastoreSource.getToken()

    fun login(email: String, password: String) =
        remoteSource.login(
            LoginRequest(
                email = email,
                password = password
            )
        )

    fun register(school_id: String, name: String, email: String, password: String) =
        remoteSource.register(
            RegisterRequest(
                school_id = school_id,
                name = name,
                email = email,
                password = password
            )
        )

    fun checkPenjurusanState() = remoteSource.checkPenjurusanState()

    fun getProfile() = remoteSource.getProfile()

    fun getQuizQuestion(title:String) = remoteSource.getQuizQuestion(title)

    fun sendSectionOneQuizAnswer(title:String, request:List<SingleSendQuizSectionOneAnswerDataRequest>) = remoteSource.sendSectionOneQuizAnswer(title, request)

    fun sendSectionTwoAndThreeQuizAnswer(title:String, request:List<SingleSendQuizSectionTwoAndThreeAnswerDataRequest>) = remoteSource.sendSectionTwoAndThreeQuizAnswer(title, request)

    fun getQuizAnalysis() = remoteSource.getQuizAnalysis()
}