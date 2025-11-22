package com.tcx.todo_list

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.widget.Toast
import com.idlefish.flutterboost.FlutterBoost
import com.idlefish.flutterboost.FlutterBoostDelegate
import com.idlefish.flutterboost.FlutterBoostRouteOptions
import com.idlefish.flutterboost.containers.FlutterBoostActivity
import com.tcx.todo_list.channel.ApiChannel
import com.tcx.todo_list.channel.TodoChannel
import com.tcx.todo_list.channel.UtilsChannel
import com.tcx.todo_list.main.MainActivity
import io.flutter.embedding.android.FlutterActivityLaunchConfigs
import java.io.Serializable


object RouterMap{
    const val ORIGIN = Const.ORIGIN

    val routerMaps = mapOf<String, Class<out Activity>>(
        ORIGIN + MainActivity.PATH to MainActivity::class.java,
    )

    fun resolveActivityByPath(path: String): Class<out Activity>? {
        return routerMaps[path]
    }

    fun setupFlutterBoost(app: Application){

        FlutterBoost.instance().setup(app, object : FlutterBoostDelegate {
            override fun pushNativeRoute(options: FlutterBoostRouteOptions) {
                val context = FlutterBoost.instance().currentActivity() ?: return
                val target = resolveActivityByPath(options.pageName())

                if(target == null) {
                    Toast.makeText(context, "找不到对应的原生页面: ${options.pageName()}", Toast.LENGTH_SHORT).show()
                    return
                }
                val args = options.arguments() as Map<String, Any?>
                val serializableMap = HashMap<String, Any?>().apply { putAll(args) }

                val intent = Intent(context, target).apply {
                    putExtra("args", serializableMap as Serializable)
                }


                context.startActivity(intent)
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
            UtilsChannel.register(app,engine)
            ApiChannel.register(app,engine)
            TodoChannel.register(app,engine)
        }
    }

}