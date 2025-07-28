package fudex.bonyad.viewmodel.merchant

import android.app.Activity
import android.content.Intent
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.google.gson.Gson
import fudex.bonyad.Model.ProductsDatum
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.RatingActivity
import fudex.bonyad.ui.Activity.merchant.AddproductActivity
import fudex.bonyad.ui.Activity.merchant.DetailsordermerchantActivity
import fudex.bonyad.ui.Activity.merchant.DetailsproductmerchantActivity
import fudex.bonyad.ui.Activity.merchant.MyproductActivity


/**
 * Created by BEST BUY on 5/8/2018.
 */

class ProductsViewModel : BaseObservable() {
    var onservse = ObservableField<ProductsDatum>()
    var catModel : ProductsDatum? = null
    var context : Activity? = null
    var img = ObservableField<String>("")
    var name = ObservableField<String>("")
    fun setdata(catModel: ProductsDatum , context : Activity) {
        if (context is DetailsordermerchantActivity){
            img.set(LoginSession.getUserData(context).user.business_logo ?: "")
            name.set(LoginSession.getUserData(context).user.trade_name ?: "")
        }
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){
        var intent: Intent = Intent(context, DetailsproductmerchantActivity::class.java)
        intent.putExtra("id",onservse.get()?.id ?: 0)
        context?.startActivity(intent)
    }
    fun edit(){
        var intent: Intent = Intent(context, AddproductActivity::class.java)
        intent.putExtra("data",Gson().toJson(onservse.get()!!))
        context?.startActivity(intent)
    }
    fun delete(){
        (context as MyproductActivity).myproductsViewModel.productId = onservse.get()!!.id!!
        (context as MyproductActivity).myproductsViewModel.delete()
    }
    fun rate(){
        var intent: Intent = Intent(context, RatingActivity::class.java)
        intent.putExtra("id",onservse.get()?.id ?: 0)
        intent.putExtra("type","Product")
        context?.startActivity(intent)
    }
}