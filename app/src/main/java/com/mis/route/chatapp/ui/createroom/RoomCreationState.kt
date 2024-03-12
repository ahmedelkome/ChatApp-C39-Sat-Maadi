package com.mis.route.chatapp.ui.createroom

import com.mis.route.chatapp.database.Room

sealed class RoomCreationState {
    data class RoomCreated(val room: Room) : RoomCreationState()
}