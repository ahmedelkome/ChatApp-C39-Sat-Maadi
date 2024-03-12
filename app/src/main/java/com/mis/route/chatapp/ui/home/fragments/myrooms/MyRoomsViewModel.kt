package com.mis.route.chatapp.ui.home.fragments.myrooms

import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mis.route.chatapp.Constants
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.database.Room

class MyRoomsViewModel : BaseViewModel() {

    val myRooms = MutableLiveData<List<Room>>()

    fun fetchRooms() {
        val user = Firebase.auth.currentUser ?: return

        Firebase.firestore
            .collection(Constants.ROOMS_COLLECTION)
            .whereEqualTo(Room.ownerIdField, user.uid)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    myRooms.value = task.result.toObjects(Room::class.java)
                } else {
                    //
                }
            }
    }

    fun checkUserInRoom(room: Room): Boolean {
        val user = Firebase.auth.currentUser ?: throw Exception("User not found")
        return room.membersIdList!!.contains(user.uid)
    }
}
