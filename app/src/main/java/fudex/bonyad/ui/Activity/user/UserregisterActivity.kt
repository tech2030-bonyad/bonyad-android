package fudex.bonyad.ui.Activity.user

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.UserregisterviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.user.UserregisterViewModel

class UserregisterActivity : BaseActivity() {
    lateinit var userregisterViewModel: UserregisterViewModel
    lateinit var binding : UserregisterviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_userregister)
        userregisterViewModel = UserregisterViewModel(this@UserregisterActivity)
        binding.model = userregisterViewModel
        binding.main.setOnClickListener {
            it.hideKeyboard()
        }
    }
}