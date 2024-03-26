package com.mis.route.chatapp.data.Repo

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.mis.route.chatapp.data.model.MyUser
import kotlinx.coroutines.tasks.await

class AuthRepoImpl : AuthRepo {
    override suspend fun login(email: String, password: String): MyUser {
        return MyUser("", "", "")
    }

    override suspend fun register(userName: String, email: String, password: String): MyUser {
        val authResult =
            Firebase.auth.createUserWithEmailAndPassword(email, password).await()
        val user = MyUser(id = authResult.user?.uid, userName = userName, email = email)
        val db = Firebase.firestore
        db.collection(MyUser.COLLECTION_NAME).document(user.id!!)
            .set(user)
        return user
    }
}