package fudex.bonyad.ui.Activity.technical

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.MyerviceviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.technical.MyserviceViewModel

class MyservicesActivity : BaseActivity(), DialogListener {
    lateinit var myserviceViewModel: MyserviceViewModel
    lateinit var binding : MyerviceviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_myservices)
        myserviceViewModel = MyserviceViewModel(this@MyservicesActivity)
        binding.model = myserviceViewModel
    }

    override fun onDataReceived(data: String) {
        if (data.contains("deleteservice")){
            myserviceViewModel.deleteservice(myserviceViewModel.serviceId)
        }
    }
}