package com.mis.route.chatapp.ui.auth.fragments.register

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.data.Repo.AuthRepo
import com.mis.route.chatapp.data.Repo.AuthRepoImpl
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
    val authRepo: AuthRepo = AuthRepoImpl()
    val userNameLiveData = MutableLiveData<String>()
    val emailLiveData = MutableLiveData<String>()
    val passwordLiveData = MutableLiveData<String>()
    val userNameLiveDataError = MutableLiveData<String?>()
    val emailLiveDataError = MutableLiveData<String?>()
    val passwordLiveDataError = MutableLiveData<String?>()

    fun register() {
        if (!validate()) return
        viewModelScope.launch {
            authRepo.register(
                userNameLiveData.value!!,
                emailLiveData.value!!,
                passwordLiveData.value!!
            )
        }
    }

    fun validate(): Boolean {
        var isValid = true
        if (userNameLiveData.value.isNullOrEmpty()) {
            userNameLiveDataError.value = "Please Enter Valid UserName"
            isValid = false
        } else {
            userNameLiveDataError.value = null
        }
        if (emailLiveData.value.isNullOrEmpty()) {
            emailLiveDataError.value = "Please Enter Valid Email"
            isValid = false
        } else {
            emailLiveDataError.value = null
        }
        if (passwordLiveData.value.isNullOrEmpty()) {
            passwordLiveDataError.value = "Please Enter Valid Password"
            isValid = false
        } else {
            passwordLiveDataError.value = null
        }
        return isValid
    }
}