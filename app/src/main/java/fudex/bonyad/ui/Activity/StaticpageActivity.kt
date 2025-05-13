package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.StaticpageviewModelBinding
import fudex.bonyad.viewmodel.StaticpageViewModel

class StaticpageActivity : BaseActivity() {
    lateinit var termsViewModel: StaticpageViewModel
    lateinit var binding : StaticpageviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_staticpage)
        termsViewModel = StaticpageViewModel(this@StaticpageActivity)
        binding.model = termsViewModel

    }
}