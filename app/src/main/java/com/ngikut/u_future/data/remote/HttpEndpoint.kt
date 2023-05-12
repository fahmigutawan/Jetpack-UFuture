package com.ngikut.u_future.data.remote

object HttpEndpoint {
    const val BASE_URL = "http://103.37.124.173:8080/api/v1"
    const val LOGIN = "$BASE_URL/student/login"
    const val REGISTER = "$BASE_URL/student/register"
    const val GET_USER = "$BASE_URL/student/profile"
    const val SEARCH_SCHOOL = "$BASE_URL/school/search"
    const val SINGLE_SCHOOL = "$BASE_URL/school/single"
    const val CHECK_PENJURUSAN = "$BASE_URL/quiz/status-quiz"
    const val GET_QUIZ_QUESTION = "$BASE_URL/quiz/question"
    const val SEND_QUIZ_ANSWER = "$BASE_URL/quiz/attempt-quiz"
    const val GET_QUIZ_ANALYSIS = "$BASE_URL/quiz/analisis"
    const val PREDICT_JURUSAN = "$BASE_URL/jurusan/predict"
    const val GET_TOP_3_JURUSAN = "$BASE_URL/jurusan/recomendation"
    const val COMPARE_TWO_JURUSAN = "$BASE_URL/jurusan/compare"
}