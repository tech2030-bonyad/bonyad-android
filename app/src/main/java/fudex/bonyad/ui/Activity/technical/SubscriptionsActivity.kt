package fudex.bonyad.ui.Activity.technical

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.PlanviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.technical.SubscriptionsViewModel

class SubscriptionsActivity : BaseActivity() {
    lateinit var subscriptionsViewModel: SubscriptionsViewModel
    lateinit var binding : PlanviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_subscriptions)
        subscriptionsViewModel = SubscriptionsViewModel(this@SubscriptionsActivity)
        binding.model = subscriptionsViewModel

    }
}