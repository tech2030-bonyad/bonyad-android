package fudex.bonyad.viewmodel.user

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
import fudex.bonyad.ui.Activity.RatingActivity
import fudex.bonyad.ui.Activity.user.DetailsappointmentActivity
import fudex.bonyad.ui.Fragment.technical.TechnicalappointmentFragment
import fudex.bonyad.ui.Fragment.user.UserappointmentFragment
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
        val geocoder = Geocoder(context.requireActivity(), Locale(context.getString(R.string.lang)))
        var address = "Unknown location"
        try {
            val addresses = geocoder.getFromLocation(catModel.lat?.toDouble()!! , catModel.long?.toDouble()!!, 1)
            if (addresses!!.isNotEmpty()) {
                address = addresses[0].getAddressLine(0)
                catModel.address = address
            }
        } catch (e: IOException) {
            // Handle any errors that occur during the geocoding process
        }
        catModel.date = catModel.date_of_reservation + " " + catModel.start_time
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){
        var intent: Intent = Intent(context?.requireActivity(), DetailsappointmentActivity::class.java)
        intent.putExtra("id",onservse.get()!!.id ?: 0)
        context?.startActivity(intent)
    }
    fun accept() {

    }
    fun reject() {
        (context as UserappointmentFragment).ordersListViewModel.appointmentId = onservse.get()!!.id!!
        (context as UserappointmentFragment).ordersListViewModel.cancelorder()
    }
    fun rate(){
        var intent: Intent = Intent(context?.requireActivity(), RatingActivity::class.java)
        intent.putExtra("id",onservse.get()!!.technical?.id ?: 0)
        intent.putExtra("type","User")
        context?.startActivity(intent)
    }

}