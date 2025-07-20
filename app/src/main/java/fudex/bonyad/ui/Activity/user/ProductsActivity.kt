package fudex.bonyad.ui.Activity.user

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.ProductsListviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.user.ProductslistViewModel

class ProductsActivity : BaseActivity() {
    lateinit var productslistViewModel: ProductslistViewModel
    lateinit var binding : ProductsListviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_products)
        productslistViewModel = ProductslistViewModel(this@ProductsActivity)
        binding.model = productslistViewModel
    }

    override fun onResume() {
        super.onResume()
        productslistViewModel.getproducts()
    }
}