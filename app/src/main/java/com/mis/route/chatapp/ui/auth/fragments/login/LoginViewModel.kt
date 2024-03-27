package com.mis.route.chatapp.ui.auth.fragments.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.data.Repo.AuthRepo
import com.mis.route.chatapp.data.Repo.AuthRepoImpl
import com.mis.route.chatapp.data.model.ViewMessage
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {
    val loginEmailLiveData = MutableLiveData<String>("")
    val loginPasswordLiveDate = MutableLiveData<String>("")
    val loginEmailLiveDataError = MutableLiveData<String?>()
    val loginPasswordLiveDateError = MutableLiveData<String?>()
    var eventsLogin = MutableLiveData<LoginScreenEvents>()
    val authRepo: AuthRepo = AuthRepoImpl()

    fun login() {
        if (!validate()) return
        viewModelScope.launch {
            loadingLiveData.value = true
            try {
                authRepo.login(
                    loginEmailLiveData.value!!,
                    loginPasswordLiveDate.value!!
                )
                eventsLogin.value = LoginScreenEvents.navigateToHomeScreen
            } catch (e: Exception) {
                loadingLiveData.value = false
                errorLiveData.value = ViewMessage(
                    title = "Error",
                    message = e.localizedMessage
                )
            }
            loadingLiveData.value = false
        }
    }

    fun createNewAccount() {
        eventsLogin.value = LoginScreenEvents.navigateToCreateNewAccount
    }

    fun validate(): Boolean {
        var isValide = true
        if (loginEmailLiveData.value.isNullOrEmpty()) {

            loginEmailLiveDataError.value = "Please Enter Correct Email"
            isValide = false
        } else {
            loginEmailLiveDataError.value = null
        }
        if (loginPasswordLiveDate.value.isNullOrEmpty()) {

            loginPasswordLiveDateError.value = "Please Enter Correct Password"
            isValide = false
        } else {
            loginPasswordLiveDateError.value = null
        }
        return isValide
    }
}