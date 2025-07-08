package com.tcx.tcx_video_call

import android.app.Application

class VideoCallApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        RouterMap.setupFlutterBoost(this)
    }
}
