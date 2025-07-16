package com.tcx.tcx_video_call.channel

import android.content.Context
import com.tcx.tcx_video_call.Const
import com.tcx.tcx_video_call.MethodHandler
import com.tcx.tcx_video_call.method.ApiMethod.testRequestHandler
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


object ApiChannel {
    private const val CHANNEL_NAME = Const.API_CHANNEL_NAME
    const val TEST_REQUEST = "test_request"

    private val methodMap: Map<String, MethodHandler> = mapOf(
        TEST_REQUEST to testRequestHandler
    )


    fun register(context: Context, engine: FlutterEngine) {
        MethodChannel(engine.dartExecutor.binaryMessenger, CHANNEL_NAME)
            .setMethodCallHandler { call, result ->
                val handler = methodMap[call.method]
                if (handler != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            handler(call.toArgMap(),result,context)
                        } catch (e: Exception) {
                            withContext(Dispatchers.Main) {
                                result.error("ERROR", e.message ?: "unknown error", null)
                            }
                        }
                    }
                } else {
                    result.notImplemented()
                }
            }
    }




}