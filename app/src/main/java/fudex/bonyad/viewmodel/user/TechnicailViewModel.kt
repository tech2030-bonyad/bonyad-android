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
import fudex.bonyad.ui.Fragment.technical.TechnicalservicesFragment


/**
 * Created by BEST BUY on 5/8/2018.
 */

class TechnicailViewModel : BaseObservable() {
    var onservse = ObservableField<Technician>()
    var catModel : Technician? = null
    var context : Fragment? = null
    fun setdata(catModel: Technician , context : Fragment) {
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){
        var intent: Intent = Intent(context?.requireActivity(), DetailsspeciallistActivity::class.java)
        intent.putExtra("id",onservse.get()!!.id ?: 0)
        context?.startActivity(intent)
    }


}