package fudex.bonyad.viewmodel.user

import android.app.Activity
import android.content.Intent
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import fudex.bonyad.Helper.Camera.Companion.activity
import fudex.bonyad.Model.StatesDatum
import fudex.bonyad.ui.Activity.technical.TechnicalregisterActivity
import fudex.bonyad.ui.Activity.technical.TechnicalservicedetailsActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.Model.RegisterimageModel
import fudex.bonyad.Model.Technician
import fudex.bonyad.databinding.Technicail1viewModelBinding
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Activity.user.DetailsspeciallistActivity
import fudex.bonyad.ui.Activity.user.SpecialistsActivity
import fudex.bonyad.ui.Adapter.user.Servicesdetailsadapter
import fudex.bonyad.ui.Fragment.technical.TechnicalservicesFragment


/**
 * Created by BEST BUY on 5/8/2018.
 */

class Technicail1ViewModel : BaseObservable() {
    var onservse = ObservableField<Technician>()
    var catModel : Technician? = null
    var context : Activity? = null
    val services: ArrayList<StatesDatum>? = ArrayList()
    private val servicesdetailsadapter = Servicesdetailsadapter()
    fun setdata(catModel: Technician , context : Activity, bind: Technicail1viewModelBinding) {
        services?.clear()
        services?.addAll(catModel.services!!)
        var linearlayout1 = LinearLayoutManager(context)
        linearlayout1!!.orientation = LinearLayoutManager.HORIZONTAL
        bind.serviceList.layoutManager = linearlayout1
        bind.serviceList.adapter = servicesdetailsadapter
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
        notifyChange()
    }
    fun clickitem(){
        var intent: Intent = Intent(context, DetailsspeciallistActivity::class.java)
        intent.putExtra("id",onservse.get()!!.id ?: 0)
        context?.startActivity(intent)
    }


}