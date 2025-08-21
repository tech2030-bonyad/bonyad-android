package fudex.bonyad.ui.Activity.user

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
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
    val messageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Update the UI with the received data
            if (LoginSession.gettype(this@DetailsuserorderActivity) == 2){
                return
            }
            if (intent.getStringExtra("type")?.contains("order") == true && intent.getIntExtra("item_id",0) == detailsorderuserViewModel.orderdata.get()!!.data?.id ?: 0) {
                detailsorderuserViewModel.getorderdetails()
            }
            // Process the data map as needed
        }
    }
}