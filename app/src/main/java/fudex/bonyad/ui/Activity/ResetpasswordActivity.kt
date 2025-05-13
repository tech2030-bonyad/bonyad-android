package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.ResetpassviewModelBinding
import fudex.bonyad.viewmodel.ResetpassViewModel

class ResetpasswordActivity : BaseActivity() {
    lateinit var resetpassViewModel: ResetpassViewModel
    lateinit var binding : ResetpassviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_resetpassword)
        resetpassViewModel = ResetpassViewModel(this@ResetpasswordActivity)
        binding.model = resetpassViewModel
        binding.main.setOnClickListener {
            it.hideKeyboard()
        }
    }
}