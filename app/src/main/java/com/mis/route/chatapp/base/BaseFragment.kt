package com.mis.route.chatapp.base

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.mis.route.chatapp.R
import com.mis.route.chatapp.data.model.ViewMessage

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding> : Fragment() {

    lateinit var viewModel: VM
    lateinit var binding: DB
    var dialog: AlertDialog? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = initViewModel()
        binding = DataBindingUtil.inflate(inflater, getLayout(), container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()
    }

    abstract fun initViewModel(): VM

    abstract fun getLayout(): Int

    fun showLoaging() {
        val builder = AlertDialog.Builder(activity)
            .setView(R.layout.loading_layout)
        dialog = builder.create()
        dialog?.show()
    }

    fun hideLoading() {
        dialog?.dismiss()
    }

    fun showError(
        viewMessage: ViewMessage
    ) {
        val dialog = AlertDialog.Builder(activity)
            .setTitle(viewMessage.title)
            .setMessage(viewMessage.message)
            .setPositiveButton(
                viewMessage.posButtonTitle
            ) { dialog, which ->
                viewMessage.posButtonClick?.invoke()
            }
            .setNegativeButton(
                viewMessage.negButtonTitle
            ) { dialog, which ->
                viewMessage.negButtonClick?.invoke()
            }
            .create()
            .show()
    }

    open fun observeLiveData() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner) {
            if (it) {
                showLoaging()
            } else {
                hideLoading()
            }
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            showError(it)
        }
    }


}