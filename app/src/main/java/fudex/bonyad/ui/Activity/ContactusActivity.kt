package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.ContactusviewModelBinding
import fudex.bonyad.viewmodel.ContactusViewModel

class ContactusActivity : BaseActivity() {
    lateinit var contactusViewModel: ContactusViewModel
    lateinit var binding : ContactusviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_contactus)
        contactusViewModel = ContactusViewModel(this@ContactusActivity)
        binding.model = contactusViewModel
        binding.main.setOnClickListener {
            it.hideKeyboard()
        }
    }
}