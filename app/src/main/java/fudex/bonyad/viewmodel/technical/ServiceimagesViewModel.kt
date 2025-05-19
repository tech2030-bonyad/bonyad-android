package fudex.bonyad.viewmodel.technical

import android.app.Activity
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import fudex.bonyad.Model.Certificate
import fudex.bonyad.ui.Activity.technical.TechnicalregisterActivity
import onnetysolutions.sadded.Model.RegisterimageModel


/**
 * Created by BEST BUY on 5/8/2018.
 */

class ServiceimagesViewModel : BaseObservable() {
    var onservse = ObservableField<Certificate>()
    var catModel : Certificate? = null
    var context : Activity? = null
    fun setdata(catModel: Certificate , context : Activity) {
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){

    }


}