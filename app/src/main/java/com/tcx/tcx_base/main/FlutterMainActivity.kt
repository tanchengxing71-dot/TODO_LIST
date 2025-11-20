package com.tcx.tcx_base.main

import android.content.Context
import android.os.Bundle
import androidx.core.content.edit
import com.idlefish.flutterboost.containers.FlutterBoostActivity
import com.tcx.tcx_base.Const

class FlutterMainActivity : FlutterBoostActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    fun isFirstLaunch(context: Context): Boolean {
        val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
        val isFirst = prefs.getBoolean("is_first_launch", true)

        if (isFirst) {
            prefs.edit() { putBoolean("is_first_launch", false) }
        }
        return isFirst
    }

    override fun getUrl(): String {
        return if(isFirstLaunch(this)){
            Const.ORIGIN+"/pages/simplePage"
        }else{
            Const.ORIGIN+"/pages/complexPage"
        }
    }
    override fun getUrlParams(): MutableMap<String, Any>? = mutableMapOf("data" to "from Flutter Main activity")
}
