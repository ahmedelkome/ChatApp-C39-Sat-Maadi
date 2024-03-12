package com.mis.route.chatapp.ui.chat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mis.route.chatapp.database.ChatMessage
import com.mis.route.chatapp.databinding.ItemReceivedMessageBinding
import com.mis.route.chatapp.databinding.ItemSentMessageBinding

class MessagesAdapter(private var messagesList: MutableList<ChatMessage?>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class SentMessageViewHolder(val binding: ItemSentMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            binding.message = message
        }
    }

    class ReceivedMessageViewHolder(val binding: ItemReceivedMessageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(message: ChatMessage) {
            binding.message = message
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (viewType) {
            MessageType.SentMessage.value -> {
                val binding = ItemSentMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return SentMessageViewHolder(binding)
            }

            else -> {
                val binding = ItemReceivedMessageBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return ReceivedMessageViewHolder(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val user = Firebase.auth.currentUser ?: throw Exception("user not found")
        val senderId = messagesList[position]?.senderId ?: ""

        return if (senderId == user.uid) MessageType.SentMessage.value else MessageType.ReceivedMessage.value
    }

    override fun getItemCount() = messagesList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message = messagesList[position] ?: ChatMessage()

        when (holder) {
            is SentMessageViewHolder -> holder.bind(message)
            is ReceivedMessageViewHolder -> holder.bind(message)
        }
    }

    fun setMessagesList(list: List<ChatMessage?>) {
        messagesList = list.toMutableList()
//        notifyItemRangeInserted(0, list.size)
        notifyDataSetChanged()
    }

    fun addMessages(list: ChatMessage?) {
        val oldSize = messagesList.size
        messagesList.add(list)
//        notifyItemRangeInserted(oldSize - 1, 1)
        notifyDataSetChanged()
    }
}