package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.OnboardingListViewModelBinding
import fudex.bonyad.viewmodel.OnboardingListViewModel

class OnboardingActivity : BaseActivity() {
    lateinit var onboardingListViewModel: OnboardingListViewModel
    lateinit var binding : OnboardingListViewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_onboarding)
        onboardingListViewModel = OnboardingListViewModel(this@OnboardingActivity)
        binding.model = onboardingListViewModel
    }
}