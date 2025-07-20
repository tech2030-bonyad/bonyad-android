package fudex.bonyad.viewmodel.user

import android.app.Activity
import android.content.Intent
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import fudex.bonyad.Model.ProductsDatum
import fudex.bonyad.ui.Activity.merchant.AddproductActivity
import fudex.bonyad.ui.Activity.merchant.DetailsproductmerchantActivity
import fudex.bonyad.ui.Activity.merchant.MyproductActivity
import fudex.bonyad.ui.Activity.user.DetailsproductsActivity


/**
 * Created by BEST BUY on 5/8/2018.
 */

class ProductitemViewModel : BaseObservable() {
    var onservse = ObservableField<ProductsDatum>()
    var catModel : ProductsDatum? = null
    var context : Activity? = null
    fun setdata(catModel: ProductsDatum , context : Activity) {
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){
        var intent: Intent = Intent(context, DetailsproductsActivity::class.java)
        intent.putExtra("id",onservse.get()?.id ?: 0)
        context?.startActivity(intent)
    }

}