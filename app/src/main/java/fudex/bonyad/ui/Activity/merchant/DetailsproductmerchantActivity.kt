package fudex.bonyad.ui.Activity.merchant

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.DetailproductmerchantviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.ui.Activity.merchant.MyproductActivity
import fudex.bonyad.viewmodel.merchant.DetailsproductmerchantViewModel

class DetailsproductmerchantActivity : BaseActivity(), DialogListener {
    lateinit var detailsproductmerchantViewModel: DetailsproductmerchantViewModel
    lateinit var binding : DetailproductmerchantviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailsproducttechnical)
        detailsproductmerchantViewModel = DetailsproductmerchantViewModel(this@DetailsproductmerchantActivity)
        binding.model = detailsproductmerchantViewModel
    }

    override fun onDataReceived(data: String) {
        if (data.contains("deleteproduct")){
            detailsproductmerchantViewModel.deleteproduct(detailsproductmerchantViewModel.productdata.get()?.id ?: 0)
        }
    }
}