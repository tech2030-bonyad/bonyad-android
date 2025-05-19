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
import onnetysolutions.sadded.Model.RegisterimageModel


/**
 * Created by BEST BUY on 5/8/2018.
 */

class ServiceViewModel : BaseObservable() {
    var onservse = ObservableField<StatesDatum>()
    var catModel : StatesDatum? = null
    var context : Fragment? = null
    fun setdata(catModel: StatesDatum , context : Fragment) {
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){
        var intent: Intent = Intent(context?.requireActivity(), TechnicalservicedetailsActivity::class.java)
        intent.putExtra("id", onservse.get()!!.id!!)
        context?.startActivity(intent)
    }




}