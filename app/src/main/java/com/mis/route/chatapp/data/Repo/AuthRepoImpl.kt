package com.mis.route.chatapp.data.Repo

import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.toObject
import com.mis.route.chatapp.data.model.MyUser
import kotlinx.coroutines.tasks.await

class AuthRepoImpl : AuthRepo {
    override suspend fun login(email: String, password: String): MyUser {
        val authResult = Firebase.auth.signInWithEmailAndPassword(email, password).await()
        val userSignIn = MyUser(
            id = authResult.user?.uid,
            email = email
        )
        val db = Firebase.firestore.collection(MyUser.COLLECTION_NAME).document()
            .get().await()
        db.toObject(MyUser::class.java)
        return userSignIn
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