package com.tcx.tcx_video_call

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.idlefish.flutterboost.containers.FlutterBoostActivity
import com.tcx.tcx_video_call.databinding.ActivityMainBinding
import io.flutter.Log
import io.flutter.embedding.android.FlutterActivityLaunchConfigs

class MainActivity : AppCompatActivity() {

    companion object {
        const val PATH = "main"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonJumpComplex.setOnClickListener {
            val intent = FlutterBoostActivity.CachedEngineIntentBuilder(FlutterBoostActivity::class.java)
                .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent) // or transparent
                .destroyEngineWithActivity(false)
                .url("${RouterMap.origin}complexPage") // Flutter 页面对应的路由名
                .urlParams(mapOf("data" to "tcx from native buttonJumpComplex"))
                .build(this) // this 是当前 Activity 或 Fragment 的 Context
            startActivity(intent)
        }

        binding.buttonJumpSimple.setOnClickListener {
            val intent = FlutterBoostActivity.CachedEngineIntentBuilder(FlutterBoostActivity::class.java)
                .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent) // or transparent
                .destroyEngineWithActivity(false)
                .url("${RouterMap.origin}simplePage") // Flutter 页面对应的路由名
                .urlParams(mapOf("data" to "tcx from native buttonJumpSimple"))
                .build(this) // this 是当前 Activity 或 Fragment 的 Context
            startActivity(intent)
        }

    }

    @Override
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("acticity_main_tcx","出发了main页面的回调")

    }


}