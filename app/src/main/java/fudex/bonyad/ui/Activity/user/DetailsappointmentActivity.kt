package fudex.bonyad.ui.Activity.user

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.databinding.DetailappointmentsviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.user.DetailsappointmentViewModel

class DetailsappointmentActivity : BaseActivity() {
    lateinit var detailsappointmentViewModel: DetailsappointmentViewModel
    lateinit var binding: DetailappointmentsviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailsappointment)
        detailsappointmentViewModel = DetailsappointmentViewModel(this@DetailsappointmentActivity)
        binding.model = detailsappointmentViewModel

    }
    val messageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Update the UI with the received data
            if (LoginSession.gettype(this@DetailsappointmentActivity) == 3){
                return
            }
            if (intent.getStringExtra("type")?.contains("reservation") == true && intent.getIntExtra("item_id",0) == detailsappointmentViewModel.detailsdata.get()!!.data?.id ?: 0) {
                detailsappointmentViewModel.getappointmentdetails()
            }
            // Process the data map as needed
        }
    }
}