package fudex.bonyad.ui.Activity.user

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.DetailorderuserviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.ui.Activity.merchant.DetailsordermerchantActivity
import fudex.bonyad.viewmodel.user.DetailsorderuserViewModel

class DetailsuserorderActivity : BaseActivity() {
    lateinit var detailsorderuserViewModel: DetailsorderuserViewModel
    lateinit var binding : DetailorderuserviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailsuserorder)
        detailsorderuserViewModel = DetailsorderuserViewModel(this@DetailsuserorderActivity)
        binding.model = detailsorderuserViewModel
    }
}