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
import fudex.bonyad.SharedPreferences.LoginSession
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
    var txt = ""
    fun setdata(catModel: OrdersDatum , context : Fragment) {
        txt = LoginSession.getservice(context.requireActivity())
        catModel.date = catModel.date_of_reservation + " " + catModel.start_time
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
        (context as TechnicalappointmentFragment).ordersListViewModel.appointmentId = onservse.get()!!.id!!
        (context as TechnicalappointmentFragment).ordersListViewModel.acceptorder()
    }
    fun reject() {
        (context as TechnicalappointmentFragment).ordersListViewModel.appointmentId = onservse.get()!!.id!!
        (context as TechnicalappointmentFragment).ordersListViewModel.makereject()
    }


}