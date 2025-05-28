package fudex.bonyad.ui.Activity

import android.content.Intent
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

    override fun onBackPressed() {
        var intent: Intent = Intent(this@LoginActivity, SelecttypeActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
        finish()
        return
        super.onBackPressed()
    }
}