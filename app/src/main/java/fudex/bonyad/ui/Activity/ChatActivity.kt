package fudex.bonyad.ui.Activity

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.Model.MessageItem
import fudex.bonyad.Model.UserInfo
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.databinding.ChatListViewModelBinding
import fudex.bonyad.viewmodel.ChatListViewModel

class ChatActivity : BaseActivity() {
    lateinit var chatListViewModel: ChatListViewModel
    lateinit var binding : ChatListViewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_chat)
        chatListViewModel = ChatListViewModel(this@ChatActivity)
        binding.model = chatListViewModel
        binding.main.setOnClickListener {
            it.hideKeyboard()
        }
    }
    val messageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Update the UI with the received data
            if (intent.getStringExtra("type") == "send_message" && intent.getIntExtra("item_id",0) == chatListViewModel.userId) {
                chatListViewModel.chatList.add(
                    MessageItem(0,0,intent.getStringExtra("message") ,
                    UserInfo(
                       chatListViewModel.userId ,
                        chatListViewModel.name.get() , "" ,"" , chatListViewModel.img.get()),  UserInfo(LoginSession.getUserData(this@ChatActivity).user?.id ?: 0,LoginSession.getUserData(this@ChatActivity).user?.name ?: "" , "" ,"" , LoginSession.getUserData(this@ChatActivity).user?.avatar ?: "")
                            ))
            }
            // Process the data map as needed
        }
    }

    override fun onResume() {
        super.onResume()
        chatListViewModel.page = 1
        chatListViewModel.getchats()
    }
}