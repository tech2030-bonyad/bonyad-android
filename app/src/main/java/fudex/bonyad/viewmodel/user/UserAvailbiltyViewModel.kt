package fudex.bonyad.viewmodel.user

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.Availability
import fudex.bonyad.Model.Certificate
import fudex.bonyad.ui.Activity.technical.EdittechnicaldataActivity
import fudex.bonyad.ui.Activity.technical.TechnicalregisterActivity
import fudex.bonyad.Model.DistanceModel
import fudex.bonyad.Model.RegisterimageModel
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.technical.Appointmentmanagectivity
import fudex.bonyad.ui.Activity.user.DetailsspeciallistActivity


/**
 * Created by BEST BUY on 5/8/2018.
 */

class UserAvailbiltyViewModel : BaseObservable() {
    var onservse = ObservableField<Availability>()
    var catModel : Availability? = null
    var context : Activity? = null
    var ischeck = false
    var title = ""
    fun setdata(catModel: Availability , context : Activity) {
        title = Utilities.formatMessageDateTime1(catModel.start_time!!,"hh:mm a",context.getString(R.string.lang)) + " : " + Utilities.formatMessageDateTime1(catModel.end_time!!,"hh:mm a",context.getString(R.string.lang))
        if (context is DetailsspeciallistActivity){
            if ((context as DetailsspeciallistActivity).detailstechnicalViewModel.timeId == catModel.id){
                ischeck = true
            }else {
                ischeck = false
            }
        }
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){
        (context as DetailsspeciallistActivity).detailstechnicalViewModel.dayId = onservse.get()!!.day_of_week ?: 0
        (context as DetailsspeciallistActivity).detailstechnicalViewModel.timeId = onservse.get()!!.id ?: 0
        (context as DetailsspeciallistActivity).detailstechnicalViewModel.notifyChange()
    }


}