package com.mis.route.chatapp.data.Repo

import com.mis.route.chatapp.data.model.MyUser

interface AuthRepo {

    suspend fun login(email: String, password: String):MyUser

    suspend fun register(userName: String, email: String, password: String):MyUser
}