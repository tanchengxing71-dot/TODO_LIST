package com.tcx.tcx_video_call.channel

import android.content.Context
import android.widget.Toast
import com.tcx.tcx_video_call.Const
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel

object UtilsChannel{
    private const val CHANNEL_NAME = Const.UTILS_CHANNEL_NAME
    const val SHOW_TOAST = "show_toast"


    fun register(context: Context, engine: FlutterEngine) {
        MethodChannel(engine.dartExecutor.binaryMessenger, CHANNEL_NAME)
            .setMethodCallHandler { call, result ->
                when (call.method) {
                    SHOW_TOAST -> {
                        val msg = call.argument<String>("msg") ?: "来自Flutter"
                        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                        result.success(null)
                    }
                    else -> result.notImplemented()
                }
            }
    }

}