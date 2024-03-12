package com.mis.route.chatapp.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    val uid: String? = null,
    val title: String? = null,
    val category: String? = null,
    val description: String? = null,
    val ownerId: String? = null,
    val membersIdList: List<String>? = null
) : Parcelable {

    fun membersCount(): Int = membersIdList?.size ?: 0

    companion object {
        const val uidField = "uid"
        const val ownerIdField = "ownerId"
    }
}
