package fudex.bonyad.viewmodel

import android.app.Activity
import android.content.Intent
import android.location.Geocoder
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import fudex.bonyad.Model.AddressesDatum
import fudex.bonyad.Model.OrdersDatum
import fudex.bonyad.Model.RatingDatum
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.user.DetailsappointmentActivity
import fudex.bonyad.ui.Fragment.technical.TechnicalappointmentFragment
import fudex.bonyad.ui.Fragment.user.UserappointmentFragment
import java.io.IOException
import java.util.Locale


/**
 * Created by BEST BUY on 5/8/2018.
 */

class RatingViewModel : BaseObservable() {
    var onservse = ObservableField<RatingDatum>()
    var catModel : RatingDatum? = null
    var context : Activity? = null
    fun setdata(catModel: RatingDatum , context : Activity) {
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
}