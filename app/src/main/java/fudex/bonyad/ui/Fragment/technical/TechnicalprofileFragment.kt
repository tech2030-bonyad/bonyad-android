package fudex.bonyad.ui.Fragment.technical

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.databinding.TechnicalprofileViewModelBinding
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.viewmodel.technical.TechnicalprofileViewModel


class TechnicalprofileFragment : Fragment() {
    lateinit var technicalprofileViewModel: TechnicalprofileViewModel
    lateinit var binding: TechnicalprofileViewModelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return try {
            if (binding == null) {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.fragment_technicalprofile,
                    container,
                    false
                )
                technicalprofileViewModel = TechnicalprofileViewModel(this@TechnicalprofileFragment)
                binding.model = technicalprofileViewModel
            }
            binding.root
        } catch (e: Exception) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_technicalprofile, container, false)
            technicalprofileViewModel = TechnicalprofileViewModel(this@TechnicalprofileFragment)
            binding.model = technicalprofileViewModel
            return binding.root
        }
    }

    override fun onResume() {
        super.onResume()
        if (LoginSession.isLogin) {
            technicalprofileViewModel.getuserinfo()
        }
        (activity as TechnicalHomeActivity).technicalhomeViewModel.type.set(3)
    }
}