package com.mis.route.chatapp.ui.chat

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mis.route.chatapp.Constants
import com.mis.route.chatapp.base.BaseViewModel
import com.mis.route.chatapp.database.ChatMessage
import com.mis.route.chatapp.database.Room

class ChatViewModel : BaseViewModel() {

    val messageContent = MutableLiveData<String>()
    val messagesState = MutableLiveData<MessageState>()

    fun sendMessage(room: Room) {
        val user = Firebase.auth.currentUser ?: return

        val message = ChatMessage(
            content = messageContent.value,
            senderId = user.uid,
            timestamp = Timestamp.now()
        )

        Firebase
            .firestore
            .collection(Constants.ROOMS_COLLECTION)
            .document(room.uid!!)
            .collection(Constants.MESSAGES_COLLECTION)
            .add(message)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    //
                } else {
                    //
                }
            }

    }

    fun listenToMessagesChanges(room: Room) {
        Firebase.firestore
            .collection(Constants.ROOMS_COLLECTION)
            .document(room.uid!!)
            .collection(Constants.MESSAGES_COLLECTION)
            .addSnapshotListener { value, error ->
                for (document in value?.documentChanges!!) {
                    when (document.type) {
                        DocumentChange.Type.ADDED -> {
                            messagesState.value =
                                MessageState.MessagesAdded(document.document.toObject(ChatMessage::class.java))
                        }

                        DocumentChange.Type.MODIFIED -> {

                        }

                        DocumentChange.Type.REMOVED -> {

                        }
                    }
                }
            }
    }

    fun fetchMessages(room: Room) {
        Firebase.firestore
            .collection(Constants.ROOMS_COLLECTION)
            .document(room.uid!!)
            .collection(Constants.MESSAGES_COLLECTION)
            .orderBy(ChatMessage.timestampField)
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    messagesState.value =
                        MessageState.MessagesListFetched(task.result.toObjects(ChatMessage::class.java))
                }
            }
    }
}
