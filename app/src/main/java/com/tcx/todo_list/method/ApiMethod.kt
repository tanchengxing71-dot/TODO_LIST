package com.tcx.todo_list.method

import android.util.Log
import com.tcx.todo_list.MethodHandler
import com.tcx.todo_list.api.Api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


object ApiMethod {

    val testRequestHandler: MethodHandler = { args, _,_->
        val key = args["key"] as? String ?: ""
        val type = args["type"] as? String ?: ""
        val response = Api.testApi.test(key, type)
        withContext(Dispatchers.Main) {
            Log.d("tcx_test", response.toString())
        }
    }


}