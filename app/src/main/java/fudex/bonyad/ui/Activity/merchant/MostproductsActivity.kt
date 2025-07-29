package fudex.bonyad.ui.Activity.merchant

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.MostproductlistviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.ui.Activity.merchant.MyproductActivity
import fudex.bonyad.viewmodel.merchant.MostproductsViewModel

class MostproductsActivity : BaseActivity() {
    lateinit var mostproductsViewModel: MostproductsViewModel
    lateinit var binding : MostproductlistviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mostproducts)
        mostproductsViewModel = MostproductsViewModel(this@MostproductsActivity)
        binding.model = mostproductsViewModel
    }
    
    override fun onResume() {
        super.onResume()
        mostproductsViewModel.page = 1
        mostproductsViewModel.getmyproducts()
    }
}