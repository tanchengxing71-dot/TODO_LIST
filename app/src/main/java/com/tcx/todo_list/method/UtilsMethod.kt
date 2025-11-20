package com.tcx.todo_list.method

import android.util.Log
import android.widget.Toast
import com.tcx.todo_list.MethodHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object UtilsMethod {

    val showToastHandler: MethodHandler = {args,_,context->
        val msg = args["msg"] as? String ?: ""
        withContext(Dispatchers.Main) {
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
            Log.d("tcx_test", args.toString())
        }
    }
}