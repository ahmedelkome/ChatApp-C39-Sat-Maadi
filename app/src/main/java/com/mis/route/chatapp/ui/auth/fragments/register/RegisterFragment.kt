package com.mis.route.chatapp.ui.auth.fragments.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.Constants
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseFragment
import com.mis.route.chatapp.database.User
import com.mis.route.chatapp.databinding.FragmentRegisterBinding
import com.mis.route.chatapp.ui.home.HomeActivity

class RegisterFragment : BaseFragment<FragmentRegisterBinding, RegisterViewModel>() {

    override fun getLayoutId(): Int = R.layout.fragment_register
    override fun initViewModel(): RegisterViewModel =
        ViewModelProvider(this)[RegisterViewModel::class.java]

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.events.observe(viewLifecycleOwner, ::onEventChange)
    }

    private fun onEventChange(event: RegisterViewEvents) {
        when (event) {
            is RegisterViewEvents.NavigateToHome -> {
                navigateToHome(event.user)
            }
        }
    }

    private fun navigateToHome(user: User) {
        startActivity(
            Intent(
                requireActivity(),
                HomeActivity::class.java
            ).putExtra(Constants.PASSED_USER, user)
        )
        requireActivity().finish()
    }

    private fun initView() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }
}
