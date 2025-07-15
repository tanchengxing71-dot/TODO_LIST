package com.tcx.tcx_video_call.api

import kotlin.getValue

object Api {

    val testApi: TestService by lazy {
        RetrofitManager.getRetrofit(
            key = "test",
            baseUrl = "https://apis.juhe.cn/fapig/football/"
        ).create(TestService::class.java)
    }


}