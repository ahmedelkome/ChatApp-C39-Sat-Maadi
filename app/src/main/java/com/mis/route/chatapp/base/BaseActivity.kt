package com.mis.route.chatapp.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewbinding.ViewBinding
import com.mis.route.chatapp.Extensions.showDialog

abstract class BaseActivity<VB : ViewBinding, VM : BaseViewModel> : AppCompatActivity() {
    lateinit var viewModel: VM

    private var _binding: VB? = null
    protected val binding: VB get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.inflate(
            layoutInflater,
            getLayoutId(),
            null,
            false
        )
        setContentView(binding.root)
        viewModel = initViewModel()
        viewModel.viewMessage.observe(this) { showDialog(it) }
    }

    abstract fun initViewModel(): VM
    abstract fun getLayoutId(): Int

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
