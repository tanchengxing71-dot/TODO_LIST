package com.tcx.tcx_video_call.api

import com.tcx.tcx_video_call.model.TestRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface TestService {
    @FormUrlEncoded
    @POST("query")
    @Headers(
        "Need-Token: false",
        "Content-Type-Override: application/x-www-form-urlencoded"
    )
    suspend fun test(
        @Field("key") key: String,
        @Field("type") type: String
    ): Response<Any>
}