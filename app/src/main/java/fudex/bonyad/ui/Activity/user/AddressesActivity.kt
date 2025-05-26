package fudex.bonyad.ui.Activity.user

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.AddressesListViewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.user.AddressListViewModel

class AddressesActivity : BaseActivity() , DialogListener {
    lateinit var addressListViewModel: AddressListViewModel
    lateinit var binding : AddressesListViewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_addresses)
        addressListViewModel = AddressListViewModel(this@AddressesActivity)
        binding.model = addressListViewModel
    }

    override fun onResume() {
        super.onResume()
        addressListViewModel.page = 1
        addressListViewModel.getaddresses()
    }
    override fun onDataReceived(data: String) {
        if (data.contains("address")){
            addressListViewModel.deleteaddress()
        }
    }
}