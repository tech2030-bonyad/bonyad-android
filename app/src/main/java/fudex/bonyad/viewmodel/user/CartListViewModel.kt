package fudex.bonyad.viewmodel.user


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
import fudex.bonyad.Data.Cartdata
import fudex.bonyad.Data.Orderdata
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.CartData
import fudex.bonyad.Model.CartModel
import fudex.bonyad.Model.Certificate
import fudex.bonyad.Model.DistanceModel
import fudex.bonyad.Model.NotsModel
import fudex.bonyad.Model.ProductElement
import fudex.bonyad.Model.CreateorderModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.RatingActivity
import fudex.bonyad.ui.Activity.WebviewActivity
import fudex.bonyad.ui.Activity.user.CartActivity
import fudex.bonyad.ui.Activity.user.LocationmapActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.ui.Adapter.user.Cartproductadapter
import fudex.bonyad.ui.Fragment.DeletetFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CartListViewModel(var catogaryFragment: CartActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var ishiden: ObservableBoolean = ObservableBoolean(false)
    var context: CartActivity = CartActivity()
    var img = ObservableField<String>("")
    var name = ObservableField<String>("")
    var quantity = ObservableField<String>("1")
    var sliderimg = ObservableField<String>("")
    var cartdata = ObservableField<CartData>()
    var productList: ArrayList<ProductElement> = ArrayList()
    var images: ArrayList<Certificate> = ArrayList()
    var quantites: ArrayList<DistanceModel> = ArrayList()
    private val cartproductadapter = Cartproductadapter()
    var lat = ""
    var lng = ""
    var addressname = ObservableField<String>("")
    var wallet = ObservableField<Double>(0.0)
    var cartId = 0
    init {
        this.context = catogaryFragment
        var linearlayout = LinearLayoutManager(context)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.productList.layoutManager = linearlayout
        context.binding.productList.adapter = cartproductadapter
    }
    fun getcarts() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java
        )

        val call: Call<CartModel?>? =
            apiService.getcarts()
        call?.enqueue(object : Callback<CartModel?> {
            override fun onResponse(
                call: Call<CartModel?>,
                response: Response<CartModel?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    cartdata.set(data?.data)
                    productList.clear()
                    productList.addAll(data?.data?.products!!)
                    if (data?.data?.tax?.toDouble() ?: 0.0 > 0 ){
                        ishiden.set(false)
                    }else {
                        ishiden.set(true)
                    }
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
                                getcarts()
                            }
                        })
                }
                isloading.set(false)
            }

            override fun onFailure(call: Call<CartModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection), context)
                isloading.set(false)

            }
        })
    }

    fun deletecart(){
        Utilities.disabletouch(context)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        val call: Call<ErrorResponse?>? = apiService.deleteitemcart(cartId)
        call?.enqueue(object : Callback<ErrorResponse?> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                   getcarts()
                } else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            deletecart()
                        }
                    })
                }
                isloading.set(false)
                Utilities.enabletouch(context)

            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
                isloading.set(false)
                Utilities.enabletouch(context)
            }
        })
    }
    fun deletepop(){
        var fragment = DeletetFragment()
        var bundle = Bundle()
        fragment.arguments = bundle
        fragment.show(context.supportFragmentManager , "deletecart")
    }
    fun back(){
        context.onBackPressed()
    }

    fun rate(){
        var intent: Intent = Intent(context, RatingActivity::class.java)
        intent.putExtra("id",cartdata.get()!!?.id ?: 0)
        intent.putExtra("type","Product")
        context?.startActivity(intent)
    }
    fun addaddress(){
        val i = Intent(context, LocationmapActivity::class.java)
        if (lat == "") {
            i.putExtra("lat" , 0.0)
            i.putExtra("lng" , 0.0)
        }else {
            i.putExtra("lat" , lat.toDouble())
            i.putExtra("lng" , lng.toDouble())
        }
        context.startActivityForResult(i, 111)
    }
    fun delete(){
        lat = ""
        lng = ""
        addressname.set("")
        notifyChange()
    }

    fun getbalance() {
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)

        val call: Call<NotsModel?>? = apiService.getwalletbalance()
        call?.enqueue(object : Callback<NotsModel?> {
            override fun onResponse(call: Call<NotsModel?>, response: Response<NotsModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    wallet.set(data?.balance?.toDouble())
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getbalance()
                        }
                    })
                }

            }

            override fun onFailure(call: Call<NotsModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
            }
        })
    }
    fun validate(){
        var error = ""
        if (lat == ""){
            error = error + context.getString(R.string.add_address) + "\n"
        }

        if (error == ""){
            makeorder()
        }else {
            Dialogs.showToast(error,context)
        }
    }
    fun makeorder() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        var call: Call<CreateorderModel?>? = null
        var method = ""
        var amount = 0.0
        if (context.binding.usewallet.isChecked == true){
            if (wallet.get()!! >= cartdata.get()?.total?.toDouble() ?: 0.0){
                method = "wallet"
                amount = cartdata.get()?.total?.toDouble() ?: 0.0
            }else {
                method = "combined"
                amount = wallet.get()!!
            }
        }else {
            method = "card"
        }
        var userdata = Orderdata(method,lat,lng,addressname.get(),if (amount > 0){amount.toString()}else{null})
        call = apiService.createorder(userdata)
        call?.enqueue(object : Callback<CreateorderModel?> {
            override fun onResponse(call: Call<CreateorderModel?>, response: Response<CreateorderModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    if (data?.global?.url ?: "" != ""){
                        var intent: Intent = Intent(context, WebviewActivity::class.java)
                        intent.putExtra("url",response.body()!!.global?.url ?: "")
                        intent.putExtra("order","")
//                    intent.putExtra("success",response.body()!!.data?.success_url ?: "")
//                    intent.putExtra("fail",response.body()!!.data?.fail_url ?: "")
                        context.startActivity(intent)
                    }else {
                        Dialogs.showToast(response.body()?.message ?: "" , context)
                        var intent: Intent = Intent(context, UserhomeActivity::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        context.startActivity(intent)
                        context.finish()
                    }
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            makeorder()
                        }
                    })
                }

                isloading.set(false)
            }

            override fun onFailure(call: Call<CreateorderModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
                isloading.set(false)
            }
        })
    }

}