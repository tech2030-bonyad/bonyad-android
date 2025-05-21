package fudex.bonyad.viewmodel.technical

import android.app.Activity
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import fudex.bonyad.ui.Activity.technical.TechnicalregisterActivity
import fudex.bonyad.Model.RegisterimageModel


/**
 * Created by BEST BUY on 5/8/2018.
 */

class ImagesViewModel : BaseObservable() {
    var onservse = ObservableField<RegisterimageModel>()
    var catModel : RegisterimageModel? = null
    var context : Activity? = null
    fun setdata(catModel: RegisterimageModel , context : Activity) {
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){

    }

    fun delete() {
        (context as TechnicalregisterActivity).technicalregisterViewModel.images.remove(onservse.get())
        (context as TechnicalregisterActivity).technicalregisterViewModel.notifyChange()
    }


}