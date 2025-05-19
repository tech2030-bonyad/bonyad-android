package fudex.bonyad.ui.Activity.technical

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.TechnicalservicedetailsviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.technical.TechnicalservicedetailsViewModel

class TechnicalservicedetailsActivity : BaseActivity() {
    lateinit var technicalservicedetailsViewModel: TechnicalservicedetailsViewModel
    lateinit var binding : TechnicalservicedetailsviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_technicalservicedetails)
        technicalservicedetailsViewModel = TechnicalservicedetailsViewModel(this@TechnicalservicedetailsActivity)
        binding.model = technicalservicedetailsViewModel

    }
}