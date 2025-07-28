package fudex.bonyad.viewmodel.merchant


import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.CartData
import fudex.bonyad.Model.MerchantorderdetailsModel
import fudex.bonyad.Model.ProductElement
import fudex.bonyad.Model.ProductsDatum
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.RatingActivity
import fudex.bonyad.ui.Activity.merchant.AddproductActivity
import fudex.bonyad.ui.Activity.merchant.DetailsordermerchantActivity
import fudex.bonyad.ui.Adapter.merchant.Imagesadapter
import fudex.bonyad.ui.Adapter.merchant.Productsorderadapter
import fudex.bonyad.ui.Fragment.DeletetFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailsordermerchantViewModel(var catogaryFragment: DetailsordermerchantActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: DetailsordermerchantActivity = DetailsordermerchantActivity()
    var img = ObservableField<String>("")
    var name = ObservableField<String>("")
    var quantity = ObservableField<String>("")
    var sliderimg = ObservableField<String>("")
    var orderdata = ObservableField<MerchantorderdetailsModel>()
    var productList: ArrayList<ProductElement> = ArrayList()
    private val productsorderadapter = Productsorderadapter()

    init {
        this.context = catogaryFragment
        var linearlayout = LinearLayoutManager(context)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.productList.layoutManager = linearlayout
        context.binding.productList.adapter = productsorderadapter
        getorderdetails()
    }
    fun getorderdetails() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java
        )

        val call: Call<MerchantorderdetailsModel?>? =
            apiService.getmerchantorderdetails(context.intent.getIntExtra("id", 0))
        call?.enqueue(object : Callback<MerchantorderdetailsModel?> {
            override fun onResponse(
                call: Call<MerchantorderdetailsModel?>,
                response: Response<MerchantorderdetailsModel?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    orderdata.set(data)
                    productList.clear()
                    productList.addAll(data?.data?.items!!)
                    var qty = 0
                    for (item in data.data?.items!!){
                        qty = qty + item.quantity!!.toInt()
                    }
                    quantity.set(qty.toString())
                    notifyChange()
                } else {
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(
                        context,
                        response.code(),
                        errorResponse,
                        object : APIModel.RefreshTokenListener {
                            override fun onRefresh() {
                                getorderdetails()
                            }
                        })
                }
                isloading.set(false)
            }

            override fun onFailure(call: Call<MerchantorderdetailsModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection), context)
                isloading.set(false)

            }
        })
    }


    fun back(){
        context.onBackPressed()
    }


}