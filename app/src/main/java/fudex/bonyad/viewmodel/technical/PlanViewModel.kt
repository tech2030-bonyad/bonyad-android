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
import fudex.bonyad.Model.PlanData
import fudex.bonyad.Model.RegisterimageModel
import fudex.bonyad.ui.Activity.technical.Appointmentmanagectivity
import fudex.bonyad.ui.Activity.technical.SubscriptionsActivity


/**
 * Created by BEST BUY on 5/8/2018.
 */

class PlanViewModel : BaseObservable() {
    var onservse = ObservableField<PlanData>()
    var catModel : PlanData? = null
    var context : Activity? = null
    fun setdata(catModel: PlanData , context : Activity) {
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun subscribe(){
        (context as SubscriptionsActivity).subscriptionsViewModel.subscribe(onservse.get()!!.id!!)
    }
}