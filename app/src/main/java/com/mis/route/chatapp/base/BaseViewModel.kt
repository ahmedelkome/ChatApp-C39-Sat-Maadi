package com.mis.route.chatapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mis.route.chatapp.data.model.ViewMessage

open class BaseViewModel : ViewModel() {

    var loadingLiveData = MutableLiveData<Boolean>(false)
    var errorLiveData = MutableLiveData<ViewMessage>()
}