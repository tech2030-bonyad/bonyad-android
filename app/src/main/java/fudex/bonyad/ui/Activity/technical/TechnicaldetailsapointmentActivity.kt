package fudex.bonyad.ui.Activity.technical

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
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

    override fun onDataReceived(data: String) {
        if(data.contains("refuse")){
            technicalappointmentdetailsViewModel.refuseorder(data.replace("refuse",""))
        }
    }
}