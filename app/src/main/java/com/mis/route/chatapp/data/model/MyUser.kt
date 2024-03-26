package com.mis.route.chatapp.data.model

data class MyUser(
    val id: String? = null,
    val userName: String? = null,
    val email: String? = null
) {
    companion object {
        const val COLLECTION_NAME = "My User"
    }
}
