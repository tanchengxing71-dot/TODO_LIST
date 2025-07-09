package com.tcx.tcx_video_call.base
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.tcx.tcx_video_call.BR
import io.flutter.Log

abstract class BaseActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {

    protected lateinit var viewModel: VM
    protected lateinit var binding: DB

    abstract fun getViewModelClass(): Class<VM>

    abstract fun getLayoutId(): Int

    @Suppress("UNCHECKED_CAST", "DEPRECATION")
    private fun getRawArguments(): Map<String, Any?> {
        return intent.getSerializableExtra("args") as? Map<String, Any?> ?: emptyMap()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("tcx_test","触发了onCreate生命周期")

        // DataBinding 和 ViewModel 初始化
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
        viewModel = ViewModelProvider(this)[getViewModelClass()]

        val args = getRawArguments()
        if (args.isNotEmpty()) {
            viewModel.initArgs(args)
        }

        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var args = data?.extras?.get("ActivityResult") as Map<String, Any?>
        viewModel.initArgs(args)
    }
}


