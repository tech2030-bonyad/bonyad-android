package fudex.bonyad.ui.Activity.technical

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.PlanviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.technical.SubscriptionsViewModel

class SubscriptionsActivity : BaseActivity(), DialogListener {
    lateinit var subscriptionsViewModel: SubscriptionsViewModel
    lateinit var binding : PlanviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_subscriptions)
        subscriptionsViewModel = SubscriptionsViewModel(this@SubscriptionsActivity)
        binding.model = subscriptionsViewModel

    }

    override fun onDataReceived(data: String) {
        if (data == "subscribe"){
            subscriptionsViewModel.cancelsubsribe()
        }
    }
}