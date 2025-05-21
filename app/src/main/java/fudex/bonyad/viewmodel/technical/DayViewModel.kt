package fudex.bonyad.viewmodel.technical

import android.app.Activity
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import fudex.bonyad.Model.Certificate
import fudex.bonyad.ui.Activity.technical.EdittechnicaldataActivity
import fudex.bonyad.ui.Activity.technical.TechnicalregisterActivity
import fudex.bonyad.Model.DistanceModel
import fudex.bonyad.Model.RegisterimageModel
import fudex.bonyad.ui.Activity.technical.Appointmentmanagectivity


/**
 * Created by BEST BUY on 5/8/2018.
 */

class DayViewModel : BaseObservable() {
    var onservse = ObservableField<DistanceModel>()
    var catModel : DistanceModel? = null
    var context : Activity? = null
    var color = ObservableField<Int>(Color.parseColor("#2563EB"))
    var isselect = false
    fun setdata(catModel: DistanceModel , context : Activity) {
        if (catModel.id!! == (context as Appointmentmanagectivity).appointmentmanageViewModel.day){
            isselect = true
            color.set(Color.parseColor("#2563EB"))
        }else {
            isselect = false
            color.set(Color.parseColor("#F3F4F6"))
        }
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){
        (context as Appointmentmanagectivity).appointmentmanageViewModel.selectdate(onservse.get()?.id!!)
    }




}