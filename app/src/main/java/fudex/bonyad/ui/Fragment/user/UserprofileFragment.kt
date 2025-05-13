package fudex.bonyad.ui.Fragment.user

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.databinding.UserprofileViewModelBinding
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.viewmodel.user.UserprofileViewModel

class UserprofileFragment : Fragment() {
    lateinit var userprofileViewModel: UserprofileViewModel
    lateinit var binding: UserprofileViewModelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return try {
            if (binding == null) {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.fragment_userprofile,
                    container,
                    false
                )
                userprofileViewModel = UserprofileViewModel(this@UserprofileFragment)
                binding.model = userprofileViewModel
            }
            binding.root
        } catch (e: Exception) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_userprofile, container, false)
            userprofileViewModel = UserprofileViewModel(this@UserprofileFragment)
            binding.model = userprofileViewModel
            return binding.root
        }
    }

    override fun onResume() {
        super.onResume()
        if (LoginSession.isLogin) {
            userprofileViewModel.getuserinfo()
        }
        (activity as UserhomeActivity).userhomeViewModel.type.set(3)
    }
}