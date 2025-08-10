package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.WalletListViewModelBinding
import fudex.bonyad.viewmodel.WalletListViewModel

class WalletActivity : BaseActivity() , DialogListener {
    lateinit var walletListViewModel: WalletListViewModel
    lateinit var binding : WalletListViewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wallet)
        walletListViewModel = WalletListViewModel(this@WalletActivity)
        binding.model = walletListViewModel

    }

    override fun onDataReceived(data: String) {
        if (data.contains("charge")){
            walletListViewModel.charge(data.replace("charge",""))
        }else if (data.contains("withdraw")){
            walletListViewModel.withdraw()
        }
    }
}