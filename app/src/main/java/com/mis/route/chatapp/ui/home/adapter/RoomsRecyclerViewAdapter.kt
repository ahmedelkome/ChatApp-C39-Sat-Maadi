package com.mis.route.chatapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mis.route.chatapp.database.Room
import com.mis.route.chatapp.databinding.ItemRoomBinding

class RoomsRecyclerViewAdapter(private var roomsList: List<Room>?) :
    RecyclerView.Adapter<RoomsRecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemRoomBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRoomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = roomsList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room = roomsList?.get(position) ?: Room()
        holder.binding.room = room
        holder.binding.root.setOnClickListener {
            onRoomClickListener?.onRoomClicked(room)
        }
    }

    private var onRoomClickListener: OnRoomClickListener? = null

    fun setOnRoomClickListener(listener: OnRoomClickListener) {
        onRoomClickListener = listener
    }

    fun updateRooms(roomsList: List<Room>?) {
        this.roomsList = roomsList
        notifyItemRangeInserted(0, roomsList?.size ?: 0)
    }

    fun interface OnRoomClickListener {
        fun onRoomClicked(room: Room)
    }
}