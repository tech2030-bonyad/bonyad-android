package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.ActivecodeviewModelBinding
import fudex.bonyad.viewmodel.ActivecodeViewModel

class ActiveuserActivity : BaseActivity() {
    lateinit var activecodeViewModel: ActivecodeViewModel
    lateinit var binding : ActivecodeviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_activeuser)
        activecodeViewModel = ActivecodeViewModel(this@ActiveuserActivity)
        binding.model = activecodeViewModel
        binding.main.setOnClickListener {
            it.hideKeyboard()
        }
    }
}