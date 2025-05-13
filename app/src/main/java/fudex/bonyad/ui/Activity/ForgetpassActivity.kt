package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.ForgetviewModelBinding
import fudex.bonyad.viewmodel.ForgetViewModel

class ForgetpassActivity : BaseActivity() {
    lateinit var forgetViewModel: ForgetViewModel
    lateinit var binding : ForgetviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forgetpass)
        forgetViewModel = ForgetViewModel(this@ForgetpassActivity)
        binding.model = forgetViewModel
        binding.main.setOnClickListener {
            it.hideKeyboard()
        }
    }
}