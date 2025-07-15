package fudex.bonyad.viewmodel.merchant

import android.app.Activity
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import fudex.bonyad.Model.Certificate
import fudex.bonyad.ui.Activity.technical.EdittechnicaldataActivity
import fudex.bonyad.ui.Activity.technical.TechnicalregisterActivity
import fudex.bonyad.Model.RegisterimageModel
import fudex.bonyad.ui.Activity.merchant.AddproductActivity
import fudex.bonyad.ui.Activity.merchant.DetailsproductmerchantActivity


/**
 * Created by BEST BUY on 5/8/2018.
 */

class ImagesViewModel : BaseObservable() {
    var onservse = ObservableField<Certificate>()
    var catModel : Certificate? = null
    var context : Activity? = null
    var issletct = false
    fun setdata(catModel: Certificate , context : Activity) {
        if (context is DetailsproductmerchantActivity){
           if ((context as DetailsproductmerchantActivity).detailsproductmerchantViewModel.sliderimg.get() ?: "" == catModel.url ?: ""){
               issletct = true
           }else {
               issletct = false
           }
        }
        onservse.set(catModel)
        notifyChange()
        this.catModel = catModel
        this.context = context
    }
    @RequiresApi(Build.VERSION_CODES.R)
    fun clickitem(){
       if (context is DetailsproductmerchantActivity){
           (context as DetailsproductmerchantActivity).detailsproductmerchantViewModel.sliderimg.set(onservse.get()!!.url ?: "")
           (context as DetailsproductmerchantActivity).detailsproductmerchantViewModel.notifyChange()
       }
    }




}