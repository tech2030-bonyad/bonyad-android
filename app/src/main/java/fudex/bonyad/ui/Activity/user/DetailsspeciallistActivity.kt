package fudex.bonyad.ui.Activity.user

import android.content.Intent
import android.location.Geocoder
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
import java.io.IOException
import java.util.Locale

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
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == RESULT_OK) {
            detailstechnicalViewModel.lat = data?.getStringExtra("lat") ?: ""
            detailstechnicalViewModel.lng = data?.getStringExtra("lng") ?: ""
            val geocoder = Geocoder(this@DetailsspeciallistActivity, Locale.getDefault())
            var address = "Unknown location"
            try {
                val addresses = geocoder.getFromLocation(detailstechnicalViewModel.lat.toDouble() , detailstechnicalViewModel.lng.toDouble(), 1)
                if (addresses!!.isNotEmpty()) {
                    address = addresses[0].getAddressLine(0)
                    detailstechnicalViewModel.addressname.set(address)
                }
            } catch (e: IOException) {
                // Handle any errors that occur during the geocoding process
            }
        }
    }
}