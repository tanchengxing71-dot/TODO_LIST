package com.tcx.tcx_video_call.api

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

interface TestService {
    @FormUrlEncoded
    @POST("query")
    @Headers(
        "Need-Token: false",
    )
    suspend fun test(
        @Field("key") key: String,
        @Field("type") type: String
    ): Response<Any>
}