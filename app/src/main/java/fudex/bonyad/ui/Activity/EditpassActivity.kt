package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.EditpasswordViewModelBinding
import fudex.bonyad.viewmodel.EditpassViewModel

class EditpassActivity : BaseActivity() {
    lateinit var editpassViewModel: EditpassViewModel
    lateinit var binding : EditpasswordViewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_editpass)
        editpassViewModel = EditpassViewModel(this@EditpassActivity)
        binding.model = editpassViewModel
        binding.main.setOnClickListener {
            it.hideKeyboard()
        }
    }
}