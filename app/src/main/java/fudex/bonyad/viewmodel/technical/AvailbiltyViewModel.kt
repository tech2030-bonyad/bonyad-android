package fudex.bonyad.viewmodel.technical

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import fudex.bonyad.Model.Availability
import fudex.bonyad.Model.Certificate
import fudex.bonyad.ui.Activity.technical.EdittechnicaldataActivity
import fudex.bonyad.ui.Activity.technical.TechnicalregisterActivity
import fudex.bonyad.Model.DistanceModel
import fudex.bonyad.Model.RegisterimageModel
import fudex.bonyad.ui.Activity.technical.Appointmentmanagectivity


/**
 * Created by BEST BUY on 5/8/2018.
 */

class AvailbiltyViewModel : BaseObservable() {
    var onservse = ObservableField<Availability>()
    var catModel : Availability? = null
    var context : Activity? = null
    fun setdata(catModel: Availability , context : Activity) {

        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){
    }
    fun fromclickitem(){
        (context as Appointmentmanagectivity).appointmentmanageViewModel.changetime("from",onservse.get()!!.id!!,onservse.get()!!.start_time!!)
    }
    fun tolickitem(){
        (context as Appointmentmanagectivity).appointmentmanageViewModel.changetime("to",onservse.get()!!.id!!,onservse.get()!!.end_time!!)
    }
    fun delete(){
        (context as Appointmentmanagectivity).appointmentmanageViewModel.delete(onservse.get()?.id!!)
    }


}