package fudex.bonyad.viewmodel.technical

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


/**
 * Created by BEST BUY on 5/8/2018.
 */

class CertificatViewModel : BaseObservable() {
    var onservse = ObservableField<Certificate>()
    var catModel : Certificate? = null
    var context : Activity? = null
    fun setdata(catModel: Certificate , context : Activity) {
        if (catModel.uri  == null && catModel.url == null){
            catModel.id = -1
        }
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    @RequiresApi(Build.VERSION_CODES.R)
    fun clickitem(){
        if (context is EdittechnicaldataActivity) {
            (context as EdittechnicaldataActivity).edittechnicalprofileViewModel.checkpermission(2)
        }else if (context is AddproductActivity) {
            (context as AddproductActivity).addproductViewModel.checkpermission(2)
        }
    }

    fun delete() {
        if (context is EdittechnicaldataActivity) {
            (context as EdittechnicaldataActivity).edittechnicalprofileViewModel.images.remove(onservse.get())
            (context as EdittechnicaldataActivity).edittechnicalprofileViewModel.notifyChange()
        }else if (context is AddproductActivity) {
            (context as AddproductActivity).addproductViewModel.images.remove(onservse.get())
            (context as AddproductActivity).addproductViewModel.notifyChange()
        }
    }


}