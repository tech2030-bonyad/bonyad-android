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
import fudex.bonyad.Model.TransactionsDatum
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

class TransactionsViewModel : BaseObservable() {
    var onservse = ObservableField<TransactionsDatum>()
    var catModel : TransactionsDatum? = null
    var context : Activity? = null
    var body = ""
    fun setdata(catModel: TransactionsDatum, context : Activity) {
        if (catModel.type == 2){
            body = context.getString(R.string.wallet_has_been_loaded_with_an_amount_of) + " " + catModel.amount + " " + context.getString(R.string.r_s)
        }else if (catModel.type == 6){
            body = context.getString(R.string.a_request_has_been_made_to_withdraw_an_amount) + " " + catModel.amount + " " + context.getString(R.string.r_s)
        }
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }


}
