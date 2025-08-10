package fudex.bonyad.viewmodel.user

import android.app.Activity
import android.content.Intent
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.google.gson.Gson
import fudex.bonyad.Model.ProductElement
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.RatingActivity
import fudex.bonyad.ui.Activity.merchant.AddproductActivity
import fudex.bonyad.ui.Activity.merchant.DetailsordermerchantActivity
import fudex.bonyad.ui.Activity.merchant.DetailsproductmerchantActivity
import fudex.bonyad.ui.Activity.merchant.MyproductActivity
import fudex.bonyad.ui.Activity.user.DetailsuserorderActivity
import fudex.bonyad.ui.Activity.user.RatingproductActivity


/**
 * Created by BEST BUY on 5/8/2018.
 */

class ProductrateViewModel : BaseObservable() {
    var onservse = ObservableField<ProductElement>()
    var catModel : ProductElement? = null
    var context : Activity? = null
    var img = ObservableField<String>("")
    var name = ObservableField<String>("")
    fun setdata(catModel: ProductElement , context : Activity) {
      if (context is RatingproductActivity){
            img.set((context as RatingproductActivity).productrateingViewModel.img.get())
            name.set((context as RatingproductActivity).productrateingViewModel.orderdata.get()?.data?.items?.get(0)?.merchant?.trade_name ?: "")
        }
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
        notifyChange()
    }

}