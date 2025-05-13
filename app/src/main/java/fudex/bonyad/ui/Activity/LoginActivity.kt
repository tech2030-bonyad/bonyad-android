package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.LoginviewModelBinding
import fudex.bonyad.databinding.SelecttypeviewModelBinding
import fudex.bonyad.viewmodel.LoginViewModel
import fudex.bonyad.viewmodel.SelecttypeViewModel

class LoginActivity : BaseActivity() {
    lateinit var loginViewModel: LoginViewModel
    lateinit var binding : LoginviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginViewModel = LoginViewModel(this@LoginActivity)
        binding.model = loginViewModel
        binding.main.setOnClickListener {
            it.hideKeyboard()
        }
    }
}