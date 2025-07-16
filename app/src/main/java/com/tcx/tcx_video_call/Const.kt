package com.tcx.tcx_video_call

import android.content.Context
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel


typealias MethodHandler = suspend (Map<String,Any?>, MethodChannel.Result?, Context?) -> Unit


object Const{

    const val SCHEME = "tcxapp"
    const val HOST= "video-call"

    const val ORIGIN = "$SCHEME://$HOST"

    const val UTILS_CHANNEL_NAME = "Tcx.utils"
    const val API_CHANNEL_NAME = "Tcx.api"

    const val SIMPLE_PAGE = "/pages/simplePage"
    const val COMPLEX_PAGE = "/pages/complexPage"

}