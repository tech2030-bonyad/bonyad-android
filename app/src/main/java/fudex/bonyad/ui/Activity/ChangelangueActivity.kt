package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.ChangelanguageviewModelBinding
import fudex.bonyad.viewmodel.ChangelanguageViewModel

class ChangelangueActivity : BaseActivity() {
    lateinit var changelanguageViewModel: ChangelanguageViewModel
    lateinit var binding : ChangelanguageviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_changelangue)
        changelanguageViewModel = ChangelanguageViewModel(this@ChangelangueActivity)
        binding.model = changelanguageViewModel
    }
}