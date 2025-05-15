package fudex.bonyad.ui.Activity.technical

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.TechnicalhomeviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.technical.TechnicalhomeViewModel

class TechnicalHomeActivity : BaseActivity() {
    lateinit var technicalhomeViewModel: TechnicalhomeViewModel
    lateinit var binding : TechnicalhomeviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_technical_home)
        technicalhomeViewModel = TechnicalhomeViewModel(this@TechnicalHomeActivity)
        binding.model = technicalhomeViewModel
    }
}