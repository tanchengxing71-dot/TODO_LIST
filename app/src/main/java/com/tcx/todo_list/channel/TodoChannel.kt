package com.tcx.todo_list.channel

import android.content.Context
import com.tcx.todo_list.Const
import com.tcx.todo_list.MethodHandler
import com.tcx.todo_list.method.TodoMethod.deleteToDoHandler
import com.tcx.todo_list.method.TodoMethod.insertToDoHandler
import com.tcx.todo_list.method.TodoMethod.queryAllToDoHandler
import com.tcx.todo_list.method.TodoMethod.queryToDoByTypeHandler
import com.tcx.todo_list.method.TodoMethod.updateToDoHandler
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext



object TodoChannel {

    private const val CHANNEL_NAME = Const.TODO_CHANNEL_NAME
    const val INSERT_TODO = "insert_todo"
    const val DELETE_TODO = "delete_todo"
    const val QUERY_ALL_TODO = "query_todo"
    const val QUERY_TODO_BY_TYPE = "query_todo_by_type"
    const val UPDATE_TODO = "update_todo"


    private val methodMap: Map<String, MethodHandler> = mapOf(
        INSERT_TODO to insertToDoHandler,
        DELETE_TODO to deleteToDoHandler,
        UPDATE_TODO to updateToDoHandler,
        QUERY_ALL_TODO to queryAllToDoHandler,
        QUERY_TODO_BY_TYPE to queryToDoByTypeHandler,
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