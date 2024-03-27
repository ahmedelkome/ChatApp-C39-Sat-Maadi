package com.mis.route.chatapp.ui.auth.fragments.login

sealed class LoginScreenEvents {
    data object navigateToHomeScreen : LoginScreenEvents()
    data object navigateToCreateNewAccount : LoginScreenEvents()
}