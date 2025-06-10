package fudex.bonyad.ui.Activity.user

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.DetailappointmentsviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.user.DetailsappointmentViewModel

class DetailsappointmentActivity : BaseActivity(), DialogListener {
    lateinit var detailsappointmentViewModel: DetailsappointmentViewModel
    lateinit var binding: DetailappointmentsviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailsappointment)
        detailsappointmentViewModel = DetailsappointmentViewModel(this@DetailsappointmentActivity)
        binding.model = detailsappointmentViewModel

    }

    override fun onDataReceived(data: String) {
        if (data.contains("refuse")) {
            detailsappointmentViewModel.refuseorder(data.replace("refuse", ""))
        }
    }
}