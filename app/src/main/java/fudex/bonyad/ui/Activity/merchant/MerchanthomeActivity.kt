package fudex.bonyad.ui.Activity.merchant

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.databinding.MerchanthomeviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.viewmodel.merchant.MerchanthomeViewModel

class MerchanthomeActivity : BaseActivity() {
    lateinit var merchanthomeViewModel: MerchanthomeViewModel
    lateinit var binding : MerchanthomeviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_merchanthome)
        merchanthomeViewModel = MerchanthomeViewModel(this@MerchanthomeActivity)
        binding.model = merchanthomeViewModel
    }

}