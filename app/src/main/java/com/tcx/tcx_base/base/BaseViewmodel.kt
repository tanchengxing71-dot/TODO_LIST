package com.tcx.tcx_base.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    abstract fun initArgs(map: Map<String, Any?>)
}
