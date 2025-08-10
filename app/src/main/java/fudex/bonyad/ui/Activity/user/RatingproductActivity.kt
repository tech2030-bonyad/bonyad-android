package fudex.bonyad.ui.Activity.user

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.RatingsproductsViewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.user.ProductrateingViewModel

class RatingproductActivity : BaseActivity() {
    lateinit var productrateingViewModel: ProductrateingViewModel
    lateinit var binding : RatingsproductsViewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ratingproduct)
        productrateingViewModel = ProductrateingViewModel(this@RatingproductActivity)
        binding.model = productrateingViewModel
        binding.main.setOnClickListener {
            it.hideKeyboard()
        }
    }
}