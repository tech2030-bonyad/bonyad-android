package fudex.bonyad.ui.Activity.merchant

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.MyproductlistviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.merchant.MyproductsViewModel

class MyproductActivity : BaseActivity() , DialogListener {
    lateinit var myproductsViewModel: MyproductsViewModel
    lateinit var binding : MyproductlistviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myproduct)
        myproductsViewModel = MyproductsViewModel(this@MyproductActivity)
        binding.model = myproductsViewModel
    }

    override fun onDataReceived(data: String) {
        if (data.contains("deleteproduct")){
            myproductsViewModel.deleteproduct(myproductsViewModel.productId)
        }
    }

    override fun onResume() {
        super.onResume()
        myproductsViewModel.page = 1
        myproductsViewModel.getmyproducts()
    }
}