package fudex.bonyad.ui.Activity.user

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.databinding.DetailproductviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.user.DetailsproductViewModel

class DetailsproductsActivity : BaseActivity() {
    lateinit var detailsproductViewModel: DetailsproductViewModel
    lateinit var binding : DetailproductviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailsproducts)
        detailsproductViewModel = DetailsproductViewModel(this@DetailsproductsActivity)
        binding.model = detailsproductViewModel
    }
    override fun onResume() {
        super.onResume()
        if (LoginSession.isLogin){
            detailsproductViewModel.getcarts()
        }
    }
}