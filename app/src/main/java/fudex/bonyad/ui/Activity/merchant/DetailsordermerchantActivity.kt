package fudex.bonyad.ui.Activity.merchant

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
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
    val messageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Update the UI with the received data
            if (LoginSession.gettype(this@DetailsordermerchantActivity) == 1){
                return
            }
            if (intent.getStringExtra("type")?.contains("order") == true && intent.getIntExtra("item_id",0) == detailsordermerchantViewModel.orderdata.get()!!.data?.id ?: 0) {
                detailsordermerchantViewModel.getorderdetails()
            }
            // Process the data map as needed
        }
    }
}