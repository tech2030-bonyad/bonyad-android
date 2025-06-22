package fudex.bonyad.viewmodel

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import fudex.bonyad.Model.AddressesDatum
import fudex.bonyad.Model.MessageItem
import fudex.bonyad.Model.OrdersDatum
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.user.DetailsappointmentActivity
import fudex.bonyad.ui.Fragment.technical.TechnicalappointmentFragment
import fudex.bonyad.ui.Fragment.user.UserappointmentFragment
import java.io.IOException
import java.util.Locale


/**
 * Created by BEST BUY on 5/8/2018.
 */

class ChatViewModel : BaseObservable() {
    var onservse = ObservableField<MessageItem>()
    var catModel : MessageItem? = null
    var context : Activity? = null
    fun setdata(catModel: MessageItem, context : Activity) {
        if (catModel.sender?.id ?: 0 == LoginSession.getUserData(context).user?.id ?:0) {
            catModel.is_me = true
        }else {
            catModel.is_me = false
        }
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }


}