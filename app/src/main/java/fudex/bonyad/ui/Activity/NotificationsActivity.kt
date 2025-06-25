package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.NotsListViewModelBinding
import fudex.bonyad.viewmodel.NotsListViewModel

class NotificationsActivity : BaseActivity() {
    lateinit var notsListViewModel: NotsListViewModel
    lateinit var binding : NotsListViewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_notifications)
        notsListViewModel = NotsListViewModel(this@NotificationsActivity)
        binding.model = notsListViewModel
    }
}