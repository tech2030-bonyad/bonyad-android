package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
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

    override fun onResume() {
        super.onResume()
        chatListViewModel.page = 1
        chatListViewModel.getchats()
    }
}