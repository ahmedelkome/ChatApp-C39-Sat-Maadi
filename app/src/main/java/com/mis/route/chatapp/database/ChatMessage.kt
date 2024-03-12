package com.mis.route.chatapp.database

import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.auth
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

data class ChatMessage(
    val uid: String? = null,
    val content: String? = null,
    val timestamp: Timestamp? = null,
    val senderId: String? = null,
) {

    fun getUserName(): String = Firebase.auth.currentUser?.displayName ?: ""

    fun getFormattedTime(): String {
        val date = Date(timestamp?.toDate()?.time!!)
        val timeFormatter = SimpleDateFormat("hh:mm a", Locale.US)
        return timeFormatter.format(date)
    }

    companion object {
        const val timestampField = "timestamp"
    }
}
