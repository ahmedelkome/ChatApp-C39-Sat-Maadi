package com.mis.route.chatapp.data.model

import android.icu.text.CaseMap.Title

data class ViewMessage(
    val title: String?,
    val message: String?,
    val posButtonTitle: String?=null,
    val negButtonTitle: String?=null,
    val posButtonClick: (() -> Unit)?=null,
    val negButtonClick: (() -> Unit)?=null
)