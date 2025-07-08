package com.tcx.tcx_video_call

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostDelegate
import com.idlefish.flutterboost.FlutterBoostRouteOptions
import com.idlefish.flutterboost.containers.FlutterBoostActivity
import io.flutter.Log
import io.flutter.embedding.android.FlutterActivityLaunchConfigs

object RouterMap{
    val origin = Const.scheme+ Const.host

    val routerMaps = mapOf<String, Class<out Activity>>(
        origin + MainActivity.PATH to MainActivity::class.java,
    )

    fun resolveActivityByPath(path: String): Class<out Activity>? {
        return routerMaps[path]
    }

    fun setupFlutterBoost(app: Application){
        Log.d("FlutterBoostTcx", "成功注册我的FlutterBoost路由")
        FlutterBoost.instance().setup(app, object : FlutterBoostDelegate {
            override fun pushNativeRoute(options: FlutterBoostRouteOptions) {
                val context = FlutterBoost.instance().currentActivity() ?: return
                val target = resolveActivityByPath(options.pageName())
                if(target == null) {
                    Toast.makeText(context, "找不到对应的原生页面: ${options.pageName()}", Toast.LENGTH_SHORT).show()
                    return
                }
                val intent = Intent(context, target)
                context.startActivityForResult(intent, options.requestCode())
            }

            override fun pushFlutterRoute(options: FlutterBoostRouteOptions) {
                val context = FlutterBoost.instance().currentActivity() ?: return
                val intent = FlutterBoostActivity.CachedEngineIntentBuilder(FlutterBoostActivity::class.java)
                    .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent)
                    .destroyEngineWithActivity(false)
                    .uniqueId(options.uniqueId())
                    .url(options.pageName())
                    .urlParams(options.arguments())
                    .build(context)
                context.startActivity(intent)
            }
        }) { engine ->
            // FlutterEngine 配置
        }
    }

}