package fudex.bonyad.ui.Activity.user

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.DetailstechnicalModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.user.DetailstechnicalViewModel
import org.joda.time.LocalDate

class DetailsspeciallistActivity : BaseActivity(),DialogListener{
    lateinit var detailstechnicalViewModel: DetailstechnicalViewModel
    lateinit var binding : DetailstechnicalModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailsspeciallist)
        detailstechnicalViewModel = DetailstechnicalViewModel(this@DetailsspeciallistActivity)
        binding.model = detailstechnicalViewModel
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onDataReceived(data: String) {
        if (data.contains("calender")) {
            var date = data.replace("calender", "")
            detailstechnicalViewModel.setcalender(LocalDate.parse(date))
        }
    }
}