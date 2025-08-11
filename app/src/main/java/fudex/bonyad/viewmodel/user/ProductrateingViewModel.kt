package fudex.bonyad.viewmodel.user


import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Makeratingdata
import fudex.bonyad.Data.Ratingdata
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
import fudex.bonyad.ui.Activity.user.DetailsuserorderActivity
import fudex.bonyad.ui.Activity.user.RatingproductActivity
import fudex.bonyad.ui.Adapter.merchant.Imagesadapter
import fudex.bonyad.ui.Adapter.merchant.Productsorderadapter
import fudex.bonyad.ui.Adapter.user.Productrateadapter
import fudex.bonyad.ui.Fragment.DeletetFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProductrateingViewModel(var catogaryFragment: RatingproductActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: RatingproductActivity = RatingproductActivity()
    var img = ObservableField<String>("")
    var name = ObservableField<String>("")
    var quantity = ObservableField<String>("")
    var sliderimg = ObservableField<String>("")
    var orderdata = ObservableField<MerchantorderdetailsModel>()
    var productList: ArrayList<ProductElement> = ArrayList()
    private val productsorderadapter = Productrateadapter()
    var color = ObservableField<Int>(Color.parseColor("#E51D35"))
    var textcolor = ObservableField<Int>(Color.parseColor("#E51D35"))
    init {
        this.context = catogaryFragment
        var linearlayout = LinearLayoutManager(context)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.productList.layoutManager = linearlayout
        context.binding.productList.adapter = productsorderadapter
        val data = Gson().fromJson(context.intent.getStringExtra("data"), MerchantorderdetailsModel::class.java)
        orderdata.set(data)
        productList.clear()
        productList.addAll(data?.data?.items!!)
        if (data?.data?.items?.size!! > 0 ) {
            img.set(data?.data?.items?.get(0)?.merchant?.business_logo ?: "")
        }
        notifyChange()
    }


    fun back(){
        context.onBackPressed()
    }

    fun validateInput() {
        var error = ""
        for (item in productList){
            if (item.rate ?: "" == "" || item.rate ?: "" == "0"){
                error = error + context.getString(R.string.make_rate_to_product_that_his_name_is) + " " + item.product?.name ?: "" + "\n"
            }
            if (item.comment ?: "" == "" ){
                error = error + context.getString(R.string.make_comment_to_product_that_his_name_is) + " " + item.product?.name ?: ""  + "\n"
            }
        }
        if (error == "") {
            ratetrip()
        }else {
            Dialogs.showToast(error,context)
        }


    }
    fun ratetrip() {
        Utilities.disabletouch(context)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java
        )
        var rating : ArrayList<Ratingdata> = ArrayList()
        for (item in productList){
            var ratingdata = Ratingdata(item.product?.id.toString() ,"Product" , item.rate ?: "" , item.comment ?: "")
            rating.add(ratingdata)
        }
        val call: Call<ErrorResponse?>? =
            apiService.rateproducts(Makeratingdata(rating))
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(
                call: Call<ErrorResponse?>,
                response: Response<ErrorResponse?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    Dialogs.showToast(response.body()?.message ?: "" , context)
                    back()
                } else {
                    val errorText = response.errorBody()?.string()
                    Log.e("data", errorText!!)
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(
                        context,
                        response.code(),
                        errorResponse,
                        object : APIModel.RefreshTokenListener {
                            override fun onRefresh() {
                                ratetrip()
                            }
                        })
                }
                isloading.set(false)
                Utilities.enabletouch(context)

            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection), context)
                isloading.set(false)
                Utilities.enabletouch(context)
            }
        })
    }
}