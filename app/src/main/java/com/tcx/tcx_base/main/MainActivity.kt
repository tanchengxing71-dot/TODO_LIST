package com.tcx.tcx_base.main

import android.os.Bundle
import com.idlefish.flutterboost.containers.FlutterBoostActivity
import com.tcx.tcx_base.Const
import com.tcx.tcx_base.R
import com.tcx.tcx_base.base.BaseActivity
import com.tcx.tcx_base.databinding.ActivityMainBinding
import io.flutter.embedding.android.FlutterActivityLaunchConfigs

class MainActivity : BaseActivity<MainViewmodel, ActivityMainBinding>() {

    companion object {
        const val PATH = "/main"
    }

    override fun getViewModelClass(): Class<MainViewmodel> = MainViewmodel::class.java
    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.buttonJumpComplex.setOnClickListener {
            val intent = FlutterBoostActivity.CachedEngineIntentBuilder(FlutterBoostActivity::class.java)
                .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent) // or transparent
                .destroyEngineWithActivity(false)
                .url(Const.ORIGIN+ Const.COMPLEX_PAGE) // Flutter 页面对应的路由名
                .urlParams(mapOf("data" to "tcx from native buttonJumpComplex"))
                .build(this) // this 是当前 Activity 或 Fragment 的 Context
            startActivityForResult(intent,1001)
        }
        binding.buttonJumpSimple.setOnClickListener {
            val intent = FlutterBoostActivity.CachedEngineIntentBuilder(FlutterBoostActivity::class.java)
                .backgroundMode(FlutterActivityLaunchConfigs.BackgroundMode.transparent) // or transparent
                .destroyEngineWithActivity(false)
                .url(Const.ORIGIN+ Const.SIMPLE_PAGE)// Flutter 页面对应的路由名
                .urlParams(mapOf("data" to "tcx from native buttonJumpSimple"))
                .build(this) // this 是当前 Activity 或 Fragment 的 Context
            startActivityForResult(intent,1002)
        }


    }

}