package fudex.bonyad.ui.Activity.technical

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.databinding.TechnicalappointmentdetailsviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.technical.TechnicalappointmentdetailsViewModel

class TechnicaldetailsapointmentActivity : BaseActivity(),DialogListener {
    lateinit var technicalappointmentdetailsViewModel: TechnicalappointmentdetailsViewModel
    lateinit var binding : TechnicalappointmentdetailsviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_technicaldetailsapointment)
        technicalappointmentdetailsViewModel = TechnicalappointmentdetailsViewModel(this@TechnicaldetailsapointmentActivity)
        binding.model = technicalappointmentdetailsViewModel

    }
    val messageReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            // Update the UI with the received data
            if (LoginSession.gettype(this@TechnicaldetailsapointmentActivity) == 1){
                return
            }
            if (intent.getStringExtra("type")?.contains("reservation") == true && intent.getIntExtra("item_id",0) == technicalappointmentdetailsViewModel.detailsdata.get()!!.data?.id ?: 0) {
                technicalappointmentdetailsViewModel.getappointmentsdetails()
            }
            // Process the data map as needed
        }
    }
    override fun onDataReceived(data: String) {
        if(data.contains("refuse")){
            technicalappointmentdetailsViewModel.refuseorder(data.replace("refuse",""))
        }
    }
}