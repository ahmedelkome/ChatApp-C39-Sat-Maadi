package com.mis.route.chatapp.data.Repo

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.mis.route.chatapp.data.model.MyUser
import kotlinx.coroutines.tasks.await

class AuthRepoImpl : AuthRepo {
    override suspend fun login(email: String, password: String): MyUser {
        return MyUser("", "", "")
    }

    override suspend fun register(userName: String, email: String, password: String): MyUser {
        val authResult = Firebase.auth.createUserWithEmailAndPassword(userName, email).await()

        return MyUser(id = authResult.user?.uid, userName = userName, email = email)
    }
}