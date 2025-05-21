package fudex.bonyad.ui.Activity.technical

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.AppointmentmanageviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.technical.AppointmentmanageViewModel

class Appointmentmanagectivity : BaseActivity() {
    lateinit var appointmentmanageViewModel: AppointmentmanageViewModel
    lateinit var binding : AppointmentmanageviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_appointmentmanagectivity)
        appointmentmanageViewModel = AppointmentmanageViewModel(this@Appointmentmanagectivity)
        binding.model = appointmentmanageViewModel
       
    }
}