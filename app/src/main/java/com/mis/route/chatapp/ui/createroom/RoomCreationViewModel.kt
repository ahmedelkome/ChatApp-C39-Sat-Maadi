package com.mis.route.chatapp.ui.createroom

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mis.route.chatapp.Constants
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.database.Room

class RoomCreationViewModel : BaseViewModel() {

    val roomName = MutableLiveData<String>()
    val roomCategory = MutableLiveData<String>()
    val roomDescription = MutableLiveData<String>()

    val states = MutableLiveData<RoomCreationState>()

    private fun validateInput(): Boolean {
        return true
    }

    fun createRoom() {
        if (!validateInput()) return
        val user = Firebase.auth.currentUser ?: return

        val room = Room(
            title = roomName.value,
            category = roomCategory.value,
            description = roomDescription.value,
            ownerId = user.uid,
            membersIdList = listOf(user.uid),
        )

        Firebase
            .firestore
            .collection(Constants.ROOMS_COLLECTION)
            .add(room)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    updateRoomUid(task.result.id, room)
                } else {
                    Log.d("tt", task.exception?.localizedMessage ?: "wrong")
                }
            }
    }

    private fun updateRoomUid(uid: String, room: Room) {
        Firebase.firestore
            .collection(Constants.ROOMS_COLLECTION)
            .document(uid)
            .update(Room.uidField, uid)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val updatedRoom = room.copy(uid = uid)
                    states.value = RoomCreationState.RoomCreated(updatedRoom)
                } else {
                    //
                }
            }
    }

}
