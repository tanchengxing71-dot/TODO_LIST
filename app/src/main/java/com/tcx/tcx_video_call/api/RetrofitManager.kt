package com.tcx.tcx_video_call.api
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitManager {

    private val retrofitMap = mutableMapOf<String, Retrofit>()
    private val tokenMap = mutableMapOf<String, String>()

    fun updateToken(key: String, token: String) {
        tokenMap[key] = token
    }

    private fun buildOkHttpClient(key: String): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val headerInterceptor = Interceptor { chain ->
            val original = chain.request()
            val needToken = original.header("Need-Token")?.toBoolean() ?: false
            val contentTypeOverride = original.header("Content-Type-Override") ?: "application/json"

            val builder = original.newBuilder()
                .removeHeader("Need-Token")
                .removeHeader("Content-Type-Override")
                .addHeader("Content-Type", contentTypeOverride)

            if (needToken) {
                val token = tokenMap[key]
                if (!token.isNullOrEmpty()) {
                    builder.addHeader("Authorization", "Bearer $token")
                }
            }

            chain.proceed(builder.build())
        }

        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    fun getRetrofit(key: String, baseUrl: String): Retrofit {
        return retrofitMap.getOrPut(key) {
            Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(buildOkHttpClient(key))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}
