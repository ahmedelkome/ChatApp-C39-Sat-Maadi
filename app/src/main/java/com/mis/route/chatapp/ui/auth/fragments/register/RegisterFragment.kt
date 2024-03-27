package com.mis.route.chatapp.ui.auth.fragments.register

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.databinding.FragmentRegisterBinding
import com.mis.route.chatapp.ui.home.HomeActivity
import kotlinx.coroutines.flow.combine

class RegisterFragment : BaseFragment<RegisterViewModel, FragmentRegisterBinding>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.event.observe(viewLifecycleOwner) {
            when (it) {
                RegisterScreenEvents.navigateToHomeScreen -> {
                    val intent = Intent(activity, HomeActivity::class.java)
                    startActivity(intent)
                    requireActivity().finish()
                }
            }
        }
        viewModel.success.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireActivity(), "Successful", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireActivity(), "already used", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun initViewModel(): RegisterViewModel =
        ViewModelProvider(this)[RegisterViewModel::class.java]

    override fun getLayout(): Int = R.layout.fragment_register

}