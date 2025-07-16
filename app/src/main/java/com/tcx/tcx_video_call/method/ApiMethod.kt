package com.tcx.tcx_video_call.method

import android.util.Log
import com.tcx.tcx_video_call.MethodHandler
import com.tcx.tcx_video_call.api.Api
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