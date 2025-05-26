package fudex.bonyad.viewmodel.user

import android.app.Activity
import android.content.Intent
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.google.gson.Gson
import fudex.bonyad.Model.AddressesDatum



/**
 * Created by BEST BUY on 5/8/2018.
 */

class AddressesViewModel : BaseObservable() {
    var onservse = ObservableField<AddressesDatum>()
    var catModel : AddressesDatum? = null
    var context : Activity? = null
    fun setdata(catModel: AddressesDatum , context : Activity) {
        catModel.addressTxt =  catModel.district + " - " + catModel.city_id?.name!! + " - " + catModel.state_id?.name!!
        onservse.set(catModel)
        this.catModel = catModel
        this.context = context
    }
    fun clickitem(){

    }
    fun edit() {
//        var intent: Intent = Intent(context, AddaddressActivity::class.java)
//        intent.putExtra("data",Gson().toJson(onservse.get()!!))
//        context?.startActivity(intent)
    }
    fun delete() {
//        (context as AddressActivity).addressListViewModel.addressId = onservse.get()?.id!!
//        (context as AddressActivity).addressListViewModel.delete()
    }
    fun setdeult(){
//        if (onservse.get()!!.is_default == 1){
//            return
//        }
//        (context as AddressActivity).addressListViewModel.addressId = onservse.get()?.id!!
//        (context as AddressActivity).addressListViewModel.setdefult()
    }

}