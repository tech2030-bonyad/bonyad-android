package fudex.bonyad.viewmodel.technical

import android.app.Activity
import android.content.Intent
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import fudex.bonyad.Model.StatesDatum
import fudex.bonyad.ui.Activity.technical.TechnicalregisterActivity
import fudex.bonyad.ui.Activity.technical.TechnicalservicedetailsActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.Model.RegisterimageModel
import fudex.bonyad.ui.Activity.technical.MyservicesActivity
import fudex.bonyad.ui.Fragment.technical.TechnicalservicesFragment


/**
 * Created by BEST BUY on 5/8/2018.
 */

class Service1ViewModel : BaseObservable() {
    var onservse = ObservableField<StatesDatum>()
    var catModel : StatesDatum? = null
    var context : Activity? = null
    fun setdata(catModel: StatesDatum , context : Activity) {
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){

    }
    fun changestatus(){
        (context as MyservicesActivity).myserviceViewModel.serviceId = onservse.get()!!.id!!
        (context as MyservicesActivity).myserviceViewModel.delete()
    }




}