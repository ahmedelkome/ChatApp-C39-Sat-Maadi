package com.mis.route.chatapp.ui.auth.fragments.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.databinding.FragmentLoginBinding
import com.mis.route.chatapp.ui.auth.AuthActivity
import com.mis.route.chatapp.ui.home.HomeActivity


class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel

    }

    override fun observeLiveData() {
        super.observeLiveData()
        viewModel.eventsLogin.observe(viewLifecycleOwner) {
            when (it) {
                LoginScreenEvents.navigateToCreateNewAccount -> {
                    findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                }

                LoginScreenEvents.navigateToHomeScreen -> {
                    val intent = Intent(activity, HomeActivity::class.java)
                    startActivity(intent)
                    activity?.finish()
                }
            }
        }
    }

    override fun initViewModel(): LoginViewModel =
        ViewModelProvider(this)[LoginViewModel::class.java]

    override fun getLayout(): Int = R.layout.fragment_login
}