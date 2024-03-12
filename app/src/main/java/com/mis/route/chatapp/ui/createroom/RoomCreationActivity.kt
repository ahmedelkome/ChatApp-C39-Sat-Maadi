package com.mis.route.chatapp.ui.createroom

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.mis.route.chatapp.Constants
import com.mis.route.chatapp.R
import com.mis.route.chatapp.base.BaseActivity
import com.mis.route.chatapp.database.Room
import com.mis.route.chatapp.databinding.ActivityRoomCreationBinding
import com.mis.route.chatapp.ui.chat.ChatActivity

class RoomCreationActivity : BaseActivity<ActivityRoomCreationBinding, RoomCreationViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBindingVariables()
        observeLiveData()
    }

    private fun observeLiveData() {
        viewModel.states.observe(this) { state ->
            when (state) {
                is RoomCreationState.RoomCreated -> navigateToChat(state.room)
            }
        }
    }

    private fun navigateToChat(room: Room) {
        startActivity(Intent(this, ChatActivity::class.java).putExtra(Constants.PASSED_ROOM, room))
        finish()
    }

    private fun initBindingVariables() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    override fun initViewModel() = ViewModelProvider(this)[RoomCreationViewModel::class.java]

    override fun getLayoutId() = R.layout.activity_room_creation
}