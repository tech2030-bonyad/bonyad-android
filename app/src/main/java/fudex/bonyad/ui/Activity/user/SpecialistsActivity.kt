package fudex.bonyad.ui.Activity.user

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.google.gson.Gson
import fudex.bonyad.Model.FilterModel
import fudex.bonyad.NetWorkConnction.DialogListener
import fudex.bonyad.R
import fudex.bonyad.databinding.TechnicalListviewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.user.TechnicallistViewModel

class SpecialistsActivity : BaseActivity(),DialogListener {
    lateinit var technicallistViewModel: TechnicallistViewModel
    lateinit var binding : TechnicalListviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_specialists)
        technicallistViewModel = TechnicallistViewModel(this@SpecialistsActivity)
        binding.model = technicallistViewModel
    }

    override fun onDataReceived(data: String) {
        if (data.contains("filter")) {
            val filter = Gson().fromJson(data.replace("filter", ""), FilterModel::class.java)
            technicallistViewModel.filter = filter
            technicallistViewModel.serviceId = filter.serviceId ?: 0
            technicallistViewModel.zoneId = filter.cityId ?: 0
            technicallistViewModel.page = 1
            technicallistViewModel.gettechnicals()
        }
    }
}