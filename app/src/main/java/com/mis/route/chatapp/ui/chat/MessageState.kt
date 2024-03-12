package com.mis.route.chatapp.ui.chat

import com.mis.route.chatapp.database.ChatMessage

sealed class MessageState {

    data class MessagesListFetched(val messagesList: List<ChatMessage?>) : MessageState()
    data class MessagesAdded(val addedMessages: ChatMessage?) : MessageState()
    data class MessagesChanged(val changedMessages: List<ChatMessage?>) : MessageState()
    data class MessagesDeleted(val deletedMessages: List<ChatMessage?>) : MessageState()
}