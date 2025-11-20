package com.tcx.tcx_base.method

import android.util.Log
import com.tcx.tcx_base.MethodHandler
import com.tcx.tcx_base.api.Api
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