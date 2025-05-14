package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.ProfileviewModelBinding
import fudex.bonyad.viewmodel.ProfileViewModel

class ProfileActivity : BaseActivity() {
    lateinit var profileViewModel: ProfileViewModel
    lateinit var binding : ProfileviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_profile)
        profileViewModel = ProfileViewModel(this@ProfileActivity)
        binding.model = profileViewModel
    }

    override fun onResume() {
        super.onResume()
        profileViewModel.getuserinfo()
    }
}