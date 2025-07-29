package fudex.bonyad.viewmodel.user

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
import fudex.bonyad.Model.Technician
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Activity.user.DetailsspeciallistActivity
import fudex.bonyad.ui.Activity.user.SpecialistsActivity
import fudex.bonyad.ui.Fragment.merchant.FilterproductsFragment
import fudex.bonyad.ui.Fragment.technical.TechnicalservicesFragment
import fudex.bonyad.ui.Fragment.user.FilterspecialFragment


/**
 * Created by BEST BUY on 5/8/2018.
 */

class TechnicalservicesViewModel : BaseObservable() {
    var onservse = ObservableField<StatesDatum>()
    var catModel : StatesDatum? = null
    var context : Fragment? = null
    var serviceId = 0
    fun setdata(catModel: StatesDatum , context : Fragment) {
        if (context is FilterspecialFragment){
           serviceId = (context as FilterspecialFragment).filtercenterViewModel.serviceId
        }else if (context is FilterproductsFragment){
            serviceId = (context as FilterproductsFragment).filtertmyproductsViewModel.serviceId
        }
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){
        if (context is FilterspecialFragment){
            (context as FilterspecialFragment).filtercenterViewModel.serviceId = onservse.get()!!.id ?: 0
            (context as FilterspecialFragment).filtercenterViewModel.notifyChange()
        }else if (context is FilterproductsFragment){
            (context as FilterproductsFragment).filtertmyproductsViewModel.serviceId = onservse.get()!!.id ?: 0
            (context as FilterproductsFragment).filtertmyproductsViewModel.notifyChange()
        }


    }


}