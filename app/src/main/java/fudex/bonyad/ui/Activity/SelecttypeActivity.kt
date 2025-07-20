package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.SelecttypeviewModelBinding
import fudex.bonyad.viewmodel.LoginViewModel
import fudex.bonyad.viewmodel.SelecttypeViewModel

class SelecttypeActivity : BaseActivity() {
    lateinit var selecttypeViewModel: SelecttypeViewModel
    lateinit var binding : SelecttypeviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_selecttype)
        selecttypeViewModel = SelecttypeViewModel(this@SelecttypeActivity)
        binding.model = selecttypeViewModel
    }
}