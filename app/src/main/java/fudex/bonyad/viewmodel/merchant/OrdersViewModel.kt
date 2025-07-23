package fudex.bonyad.viewmodel.merchant

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.google.gson.Gson
import fudex.bonyad.Model.RecentOrder
import fudex.bonyad.ui.Activity.RatingActivity
import fudex.bonyad.ui.Activity.merchant.AddproductActivity
import fudex.bonyad.ui.Activity.merchant.DetailsproductmerchantActivity
import fudex.bonyad.ui.Activity.merchant.MyproductActivity


/**
 * Created by BEST BUY on 5/8/2018.
 */

class OrdersViewModel : BaseObservable() {
    var onservse = ObservableField<RecentOrder>()
    var catModel : RecentOrder? = null
    var context : Activity? = null
    var color = ObservableField<Int>(Color.parseColor("#E51D35"))
    var textcolor = ObservableField<Int>(Color.parseColor("#E51D35"))
    fun setdata(catModel: RecentOrder , context : Activity) {
        if (catModel.status == 1){
            color.set(Color.parseColor("#10009EFF"))
            textcolor.set(Color.parseColor("#009EFF"))
        }else if (catModel.status == 2){
            color.set(Color.parseColor("#106FC94E"))
            textcolor.set(Color.parseColor("#6FC94E"))
        }else if (catModel.status == 3){
            color.set(Color.parseColor("#10F76834"))
            textcolor.set(Color.parseColor("#F76834"))
        }else if (catModel.status == 4){
            color.set(Color.parseColor("#106FC94E"))
            textcolor.set(Color.parseColor("#6FC94E"))
        }
        if (catModel.id ?: 0 == 0){
            catModel.id = catModel.code
        }
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){
        var intent: Intent = Intent(context, DetailsproductmerchantActivity::class.java)
        intent.putExtra("id",onservse.get()?.code ?: 0)
        context?.startActivity(intent)
    }

}