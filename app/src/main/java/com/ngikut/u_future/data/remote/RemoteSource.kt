package com.ngikut.u_future.data.remote

import com.ngikut.u_future.model.remote.request.quiz.SingleSendQuizSectionOneAnswerDataRequest
import com.ngikut.u_future.model.remote.request.quiz.SingleSendQuizSectionTwoAndThreeAnswerDataRequest
import com.ngikut.u_future.model.remote.request.student.LoginRequest
import com.ngikut.u_future.model.remote.request.student.RegisterRequest
import com.ngikut.u_future.model.remote.response.base.SingleQuizOptionResponse
import com.ngikut.u_future.model.remote.response.base.SingleQuizResponse
import com.ngikut.u_future.model.remote.response.jurusan.PredictJurusanResponse
import com.ngikut.u_future.model.remote.response.quiz.CheckPenjurusanStateResponse
import com.ngikut.u_future.model.remote.response.quiz.GetQuizAnalysisResponse
import com.ngikut.u_future.model.remote.response.quiz.GetQuizQuestionResponse
import com.ngikut.u_future.model.remote.response.quiz.SendQuizAnswerResponse
import com.ngikut.u_future.model.remote.response.student.GetProfileResponse
import com.ngikut.u_future.model.remote.response.student.LoginResponse
import com.ngikut.u_future.model.remote.response.student.RegisterResponse
import com.ngikut.u_future.util.getResponse
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import javax.inject.Inject

class RemoteSource @Inject constructor(
    private val client: HttpClient
) {
    fun login(
        request: LoginRequest
    ) = getResponse {
        val res = client.post {
            url(HttpEndpoint.LOGIN)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<LoginResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun register(
        request: RegisterRequest
    ) = getResponse {
        val res = client.post {
            url(HttpEndpoint.LOGIN)
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<RegisterResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun checkPenjurusanState() = getResponse {
        val res = client.get {
            url(HttpEndpoint.CHECK_PENJURUSAN)
            contentType(ContentType.Application.Json)
        }.body<CheckPenjurusanStateResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun getProfile() = getResponse {
        val res = client.get {
            url(HttpEndpoint.GET_USER)
            contentType(ContentType.Application.Json)
        }.body<GetProfileResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun getQuizQuestion(title: String) = getResponse {
        val res = client.get {
            url("${HttpEndpoint.GET_QUIZ_QUESTION}?title=$title")
            contentType(ContentType.Application.Json)
        }.body<GetQuizQuestionResponse>()

        when {
            title == "SectionOne" -> {
                if (res.meta.success) {

                    Resource.Success(res)
                } else {
                    Resource.Error(res.meta.message)
                }
            }

            title == "SectionTwo" || title == "SectionThree" -> {
                if (res.meta.success) {
                    Resource.Success(
                        res.copy(
                            data = res.data.copy(
                                questions = res.data.questions.map {
                                    SingleQuizResponse(
                                        id = it.id,
                                        text = it.text,
                                        options = listOf(
                                            SingleQuizOptionResponse(
                                                id = "HARDCODED_1",
                                                text = "Sangat Setuju",
                                                description = "1",
                                                createdAt = "",
                                                updatedAt = "",
                                                deletedAt = ""
                                            ),
                                            SingleQuizOptionResponse(
                                                id = "HARDCODED_2",
                                                text = "Setuju",
                                                description = "2",
                                                createdAt = "",
                                                updatedAt = "",
                                                deletedAt = ""
                                            ),
                                            SingleQuizOptionResponse(
                                                id = "HARDCODED_3",
                                                text = "Tidak Setuju",
                                                description = "3",
                                                createdAt = "",
                                                updatedAt = "",
                                                deletedAt = ""
                                            ),
                                            SingleQuizOptionResponse(
                                                id = "HARDCODED_4",
                                                text = "Sangat Tidak Setuju",
                                                description = "4",
                                                createdAt = "",
                                                updatedAt = "",
                                                deletedAt = ""
                                            )
                                        ),
                                        createdAt = it.createdAt,
                                        updatedAt = it.updatedAt,
                                        deletedAt = it.deletedAt
                                    )
                                }
                            )
                        )
                    )
                } else {
                    Resource.Error(res.meta.message)
                }
            }

            else -> {
                Resource.Error(res.meta.message)
            }
        }
    }

    fun sendSectionOneQuizAnswer(
        title: String,
        request: List<SingleSendQuizSectionOneAnswerDataRequest>
    ) = getResponse {
        val res = client.post {
            url("${HttpEndpoint.SEND_QUIZ_ANSWER}?title=$title")
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<SendQuizAnswerResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun sendSectionTwoAndThreeQuizAnswer(
        title: String,
        request: List<SingleSendQuizSectionTwoAndThreeAnswerDataRequest>
    ) = getResponse {
        val res = client.post {
            url("${HttpEndpoint.SEND_QUIZ_ANSWER}?title=$title")
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body<SendQuizAnswerResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun getQuizAnalysis() = getResponse {
        val res = client.get {
            url(HttpEndpoint.GET_QUIZ_ANALYSIS)
            contentType(ContentType.Application.Json)
        }.body<GetQuizAnalysisResponse>()

        if (res.meta.success) {
            Resource.Success(res)
        } else {
            Resource.Error(res.meta.message)
        }
    }

    fun getPredictJurusan() = getResponse {
        val res = client.get {
            url(HttpEndpoint.PREDICT_JURUSAN)
            contentType(ContentType.Application.Json)
        }.body<PredictJurusanResponse>()

        if(res.meta.success){
            Resource.Success(res)
        }else{
            Resource.Error(res.meta.message)
        }
    }

    fun getTop3Jurusan() = getResponse {
        val res = client.get {
            url(HttpEndpoint.GET_TOP_3_JURUSAN)
            contentType(ContentType.Application.Json)
        }.body<PredictJurusanResponse>()

        if(res.meta.success){
            Resource.Success(res)
        }else{
            Resource.Error(res.meta.message)
        }
    }
}