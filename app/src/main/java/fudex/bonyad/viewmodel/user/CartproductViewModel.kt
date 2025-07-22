package fudex.bonyad.viewmodel.user

import android.app.Activity
import android.content.Intent
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import fudex.bonyad.Model.ProductElement
import fudex.bonyad.Model.ProductsDatum
import fudex.bonyad.ui.Activity.RatingActivity
import fudex.bonyad.ui.Activity.merchant.AddproductActivity
import fudex.bonyad.ui.Activity.merchant.DetailsproductmerchantActivity
import fudex.bonyad.ui.Activity.merchant.MyproductActivity
import fudex.bonyad.ui.Activity.user.CartActivity
import fudex.bonyad.ui.Activity.user.DetailsproductsActivity


/**
 * Created by BEST BUY on 5/8/2018.
 */

class CartproductViewModel : BaseObservable() {
    var onservse = ObservableField<ProductElement>()
    var catModel : ProductElement? = null
    var context : Activity? = null
    fun setdata(catModel: ProductElement , context : Activity) {
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }

    fun edit(){
        var intent: Intent = Intent(context, DetailsproductsActivity::class.java)
        intent.putExtra("id",onservse.get()?.product?.id ?: 0)
        intent.putExtra("quantity",onservse.get()?.quantity ?: 0)
        intent.putExtra("cartid",onservse.get()?.id ?: 0)
        context?.startActivity(intent)
    }
    fun delete(){
        (context as CartActivity).cartListViewModel.cartId = onservse.get()?.id ?: 0
        (context as CartActivity).cartListViewModel.deletepop()
    }
    fun rate(){
        var intent: Intent = Intent(context, RatingActivity::class.java)
        intent.putExtra("id",onservse.get()?.product_id ?: 0)
        intent.putExtra("type","Product")
        context?.startActivity(intent)
    }
}