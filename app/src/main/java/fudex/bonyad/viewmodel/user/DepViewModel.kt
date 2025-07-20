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
import fudex.bonyad.Model.StatesDatum
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.RatingActivity
import fudex.bonyad.ui.Activity.user.DetailsappointmentActivity
import fudex.bonyad.ui.Activity.user.ProductsActivity
import fudex.bonyad.ui.Fragment.technical.TechnicalappointmentFragment
import fudex.bonyad.ui.Fragment.user.UserappointmentFragment
import java.io.IOException
import java.util.Locale


/**
 * Created by BEST BUY on 5/8/2018.
 */

class DepViewModel : BaseObservable() {
    var onservse = ObservableField<StatesDatum>()
    var catModel : StatesDatum? = null
    var context : Fragment? = null
    fun setdata(catModel: StatesDatum , context : Fragment) {
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){
        var intent: Intent = Intent(context?.requireActivity(), ProductsActivity::class.java)
        intent.putExtra("id",onservse.get()!!.id ?: 0)
        context?.startActivity(intent)
    }


}