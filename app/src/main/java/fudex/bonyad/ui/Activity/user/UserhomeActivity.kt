package fudex.bonyad.ui.Activity.user

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.UserhomeviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.user.UserhomeViewModel

class UserhomeActivity : BaseActivity() {
    lateinit var userhomeViewModel: UserhomeViewModel
    lateinit var binding : UserhomeviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_userhome)
        userhomeViewModel = UserhomeViewModel(this@UserhomeActivity)
        binding.model = userhomeViewModel
    }
}