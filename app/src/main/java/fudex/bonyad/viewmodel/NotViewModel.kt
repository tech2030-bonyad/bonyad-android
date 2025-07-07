package fudex.bonyad.viewmodel

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import fudex.bonyad.Model.AddressesDatum
import fudex.bonyad.Model.NotDatum
import fudex.bonyad.Model.OrdersDatum
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.ChatActivity
import fudex.bonyad.ui.Activity.NotificationsActivity
import fudex.bonyad.ui.Activity.technical.TechnicaldetailsapointmentActivity
import fudex.bonyad.ui.Activity.user.DetailsappointmentActivity
import fudex.bonyad.ui.Fragment.technical.TechnicalappointmentFragment
import fudex.bonyad.ui.Fragment.user.UserappointmentFragment
import java.io.IOException
import java.util.Locale


/**
 * Created by BEST BUY on 5/8/2018.
 */

class NotViewModel : BaseObservable() {
    var onservse = ObservableField<NotDatum>()
    var catModel : NotDatum? = null
    var context : Activity? = null
    fun setdata(catModel: NotDatum, context : Activity) {
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){
        if (LoginSession.gettype(context!!) == 1) {
            if (onservse.get()!!.type?.contains("reservation") == true){
                var intent: Intent = Intent(context, DetailsappointmentActivity::class.java)
                intent.putExtra("id",onservse.get()!!.item_id ?: 0)
                context?.startActivity(intent)
            }else if (onservse.get()!!.type == "send_message"){
                var intent: Intent = Intent(context, ChatActivity::class.java)
                intent.putExtra("id",onservse.get()!!.item_id ?: 0)
                context?.startActivity(intent)
            }
        }else if (LoginSession.gettype(context!!) == 3) {
            if (onservse.get()!!.type?.contains("reservation") == true){
                var intent: Intent = Intent(context, TechnicaldetailsapointmentActivity::class.java)
                intent.putExtra("id",onservse.get()!!.item_id ?: 0)
                context?.startActivity(intent)
            }else if (onservse.get()!!.type == "send_message"){
                var intent: Intent = Intent(context, ChatActivity::class.java)
                intent.putExtra("id",onservse.get()!!.item_id ?: 0)
                context?.startActivity(intent)
            }
        }
    }


}