package fudex.bonyad.ui.Fragment.merchant

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.databinding.MerchantprofileViewModelBinding
import fudex.bonyad.ui.Activity.merchant.MerchanthomeActivity
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Fragment.technical.TechnicalprofileFragment
import fudex.bonyad.viewmodel.merchant.MerchantprofileViewModel


class MerchantprofileFragment : Fragment() {

    lateinit var merchantprofileViewModel: MerchantprofileViewModel
    lateinit var binding: MerchantprofileViewModelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return try {
            if (binding == null) {
                binding = DataBindingUtil.inflate(
                    inflater,
                    R.layout.fragment_merchantprofile,
                    container,
                    false
                )
                merchantprofileViewModel = MerchantprofileViewModel(this@MerchantprofileFragment)
                binding.model = merchantprofileViewModel
            }
            binding.root
        } catch (e: Exception) {
            binding =
                DataBindingUtil.inflate(inflater, R.layout.fragment_merchantprofile, container, false)
            merchantprofileViewModel = MerchantprofileViewModel(this@MerchantprofileFragment)
            binding.model = merchantprofileViewModel
            return binding.root
        }
    }

    override fun onResume() {
        super.onResume()
        if (LoginSession.isLogin) {
            merchantprofileViewModel.getuserinfo()
        }
        (activity as MerchanthomeActivity).merchanthomeViewModel.type.set(3)
    }
}