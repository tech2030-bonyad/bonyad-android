package fudex.bonyad.ui.Activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fudex.bonyad.R
import fudex.bonyad.databinding.RatingsListViewModelBinding
import fudex.bonyad.viewmodel.RatingsListViewModel

class RatingActivity : BaseActivity() {
    lateinit var ratingsListViewModel: RatingsListViewModel
    lateinit var binding : RatingsListViewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_rating)
        ratingsListViewModel = RatingsListViewModel(this@RatingActivity)
        binding.model = ratingsListViewModel
    }
}