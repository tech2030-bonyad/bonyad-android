package fudex.bonyad.ui.Activity.merchant

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.DetailmerchantproductviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.ui.Activity.merchant.DetailsproductmerchantActivity
import fudex.bonyad.viewmodel.merchant.DetailsordermerchantViewModel

class DetailsordermerchantActivity : BaseActivity() {
    lateinit var detailsordermerchantViewModel: DetailsordermerchantViewModel
    lateinit var binding : DetailmerchantproductviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailsordermerchant)
        detailsordermerchantViewModel = DetailsordermerchantViewModel(this@DetailsordermerchantActivity)
        binding.model = detailsordermerchantViewModel
    }
}