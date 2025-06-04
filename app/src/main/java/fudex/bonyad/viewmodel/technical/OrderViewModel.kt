package fudex.bonyad.viewmodel.technical

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import fudex.bonyad.Model.AddressesDatum
import fudex.bonyad.Model.OrdersDatum
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.technical.TechnicaldetailsapointmentActivity
import fudex.bonyad.ui.Activity.user.DetailsappointmentActivity
import fudex.bonyad.ui.Fragment.technical.TechnicalappointmentFragment
import java.io.IOException
import java.util.Locale


/**
 * Created by BEST BUY on 5/8/2018.
 */

class OrderViewModel : BaseObservable() {
    var onservse = ObservableField<OrdersDatum>()
    var catModel : OrdersDatum? = null
    var context : Fragment? = null
    fun setdata(catModel: OrdersDatum , context : Fragment) {
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){
        var intent: Intent = Intent(context?.requireActivity(), TechnicaldetailsapointmentActivity::class.java)
        intent.putExtra("id",onservse.get()!!.id ?: 0)
        context?.startActivity(intent)
    }
    fun accept() {

    }
    fun reject() {
        (context as TechnicalappointmentFragment).ordersListViewModel.appointmentId = onservse.get()!!.id!!
        (context as TechnicalappointmentFragment).ordersListViewModel.makereject()
    }


}