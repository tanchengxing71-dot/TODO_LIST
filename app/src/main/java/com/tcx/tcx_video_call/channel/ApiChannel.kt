package com.tcx.tcx_video_call.channel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import com.tcx.tcx_video_call.Const
import com.tcx.tcx_video_call.api.Api

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

object ApiChannel {
    private const val CHANNEL_NAME = Const.API_CHANNEL_NAME
    const val TEST_REQUEST = "test_request"


    fun register(context: Context, engine: FlutterEngine) {
        MethodChannel(engine.dartExecutor.binaryMessenger, CHANNEL_NAME)
            .setMethodCallHandler { call, result ->
                when (call.method) {
                    TEST_REQUEST -> {
                        val key = call.argument<String>("key")
                        val type = call.argument<String>("type")
                        CoroutineScope(Dispatchers.IO).launch {
                            val response = Api.testApi.test(key = key.toString(), type = type.toString())
                            withContext(Dispatchers.Main) {
                                Log.d("tcx_test",response.toString())
                            }
                        }
                    }
                    else -> result.notImplemented()
                }
            }
    }
}