package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.EditphoneViewModelBinding
import fudex.bonyad.viewmodel.EditphoneViewModel

class EditphoneActivity : BaseActivity() {
    lateinit var editphoneViewModel: EditphoneViewModel
    lateinit var binding : EditphoneViewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_editphone)
        editphoneViewModel = EditphoneViewModel(this@EditphoneActivity)
        binding.model = editphoneViewModel
        binding.main.setOnClickListener {
            it.hideKeyboard()
        }
    }
}