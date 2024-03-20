package com.mis.route.chatapp.ui.auth.fragments.register

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun BindingAdapterError(textInputLayout: TextInputLayout, error: String?) {
    textInputLayout.error = error
}