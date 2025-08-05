package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.AddaccountbankviewModelBinding
import fudex.bonyad.ui.Activity.ContactusActivity
import fudex.bonyad.viewmodel.AddaccountbankViewModel

class AddaccountbankActivity : BaseActivity() {
    lateinit var addaccountbankViewModel: AddaccountbankViewModel
    lateinit var binding : AddaccountbankviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_addaccountbank)
        addaccountbankViewModel = AddaccountbankViewModel(this@AddaccountbankActivity)
        binding.model = addaccountbankViewModel
        binding.main.setOnClickListener {
            it.hideKeyboard()
        }
    }
}