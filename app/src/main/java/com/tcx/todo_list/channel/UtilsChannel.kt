package com.tcx.todo_list.channel

import android.content.Context
import com.tcx.todo_list.Const
import com.tcx.todo_list.MethodHandler
import com.tcx.todo_list.method.UtilsMethod.showToastHandler
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

fun MethodCall.toArgMap(): Map<String, Any?> {
    return (this.arguments as? Map<*, *>)?.mapNotNull { entry ->
        val key = entry.key as? String
        key?.let { k -> k to entry.value }
    }?.toMap() ?: emptyMap()
}

object UtilsChannel {
    private const val CHANNEL_NAME = Const.UTILS_CHANNEL_NAME
    const val SHOW_TOAST = "show_toast"

    private val methodMap: Map<String, MethodHandler> = mapOf(
        SHOW_TOAST to showToastHandler
    )


    fun register(context: Context, engine: FlutterEngine) {
        MethodChannel(engine.dartExecutor.binaryMessenger, CHANNEL_NAME)
            .setMethodCallHandler { call, result ->
                val handler = methodMap[call.method]

                if (handler != null) {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            handler(call.toArgMap(), result, context)
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