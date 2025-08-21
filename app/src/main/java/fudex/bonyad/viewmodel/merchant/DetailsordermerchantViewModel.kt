package fudex.bonyad.viewmodel.merchant


import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Changestatus
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
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailsordermerchantViewModel(var catogaryFragment: DetailsordermerchantActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var ishiden: ObservableBoolean = ObservableBoolean(true)
    var context: DetailsordermerchantActivity = DetailsordermerchantActivity()
    var img = ObservableField<String>("")
    var name = ObservableField<String>("")
    var statustitle = ObservableField<String>("")
    var quantity = ObservableField<String>("")
    var sliderimg = ObservableField<String>("")
    var orderdata = ObservableField<MerchantorderdetailsModel>()
    var productList: ArrayList<ProductElement> = ArrayList()
    private val productsorderadapter = Productsorderadapter()
    var status = 0

    init {
        this.context = catogaryFragment
        var linearlayout = LinearLayoutManager(context)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.productList.layoutManager = linearlayout
        context.binding.productList.adapter = productsorderadapter
        getorderdetails()
        val filter = IntentFilter("message_received")
        filter.addCategory(Intent.CATEGORY_DEFAULT)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.registerReceiver(context.messageReceiver, filter, null, null, Context.RECEIVER_NOT_EXPORTED)
        }else {
            ContextCompat.registerReceiver(
                context,
                context.messageReceiver,
                IntentFilter("message_received"),
                ContextCompat.RECEIVER_NOT_EXPORTED
            )
        }
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
                    if (data?.data?.status == 1){
                       statustitle.set(context.getString(R.string.on_hold))
                        ishiden.set(false)
                        status = 6
                    }else if (data?.data?.status == 6){
                        statustitle.set(context.getString(R.string.in_progress))
                        ishiden.set(false)
                        status = 7
                    }else if (data?.data?.status == 7){
                        statustitle.set(context.getString(R.string.in_shipping))
                        ishiden.set(false)
                        status = 8
                    }else if (data?.data?.status == 8){
                        statustitle.set(context.getString(R.string.complete_order))
                        ishiden.set(false)
                        status = 4
                    }else {
                        ishiden.set(true)
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
    fun changestatus() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java
        )

        val call: Call<ErrorResponse?>? =
            apiService.changestatus(context.intent.getIntExtra("id", 0), Changestatus(status))
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(
                call: Call<ErrorResponse?>,
                response: Response<ErrorResponse?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    getorderdetails()
                } else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            changestatus()
                        }
                    })
                }
                isloading.set(false)
                notifyChange()
            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection), context)
                isloading.set(false)

            }
        })
    }

}