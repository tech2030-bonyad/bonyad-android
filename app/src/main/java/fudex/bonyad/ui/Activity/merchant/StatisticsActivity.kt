package fudex.bonyad.ui.Activity.merchant

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.StatisticsViewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.merchant.StatisticsViewModel

class StatisticsActivity : BaseActivity() {
    lateinit var statisticsViewModel: StatisticsViewModel
    lateinit var binding : StatisticsViewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_statistics)
        statisticsViewModel = StatisticsViewModel(this@StatisticsActivity)
        binding.model = statisticsViewModel
    }
}