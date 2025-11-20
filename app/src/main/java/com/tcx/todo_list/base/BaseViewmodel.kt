package com.tcx.todo_list.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    abstract fun initArgs(map: Map<String, Any?>)
}
