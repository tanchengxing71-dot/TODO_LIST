package com.tcx.tcx_base.main

import androidx.lifecycle.MutableLiveData
import com.tcx.tcx_base.base.BaseViewModel

class MainViewmodel : BaseViewModel() {
    var message = MutableLiveData<String>("没有消息来源")

    override fun initArgs(map: Map<String, Any?>) {
        message.value = map["message"] as String?
    }

}