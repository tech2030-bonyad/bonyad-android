package fudex.bonyad.viewmodel.user


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.PopupMenu
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Cartdata
import fudex.bonyad.Data.Craetereserve
import fudex.bonyad.Data.Userdata
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.CalendarDay
import fudex.bonyad.Model.Certificate
import fudex.bonyad.Model.DetailsProductsModel
import fudex.bonyad.Model.DetailstechnicalModel
import fudex.bonyad.Model.DistanceModel
import fudex.bonyad.Model.LoginData
import fudex.bonyad.Model.ProductsDatum
import fudex.bonyad.Model.ScheduleResponse
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.ActiveuserActivity
import fudex.bonyad.ui.Activity.RatingActivity
import fudex.bonyad.ui.Activity.merchant.AddproductActivity
import fudex.bonyad.ui.Activity.merchant.DetailsproductmerchantActivity
import fudex.bonyad.ui.Activity.merchant.MerchanthomeActivity
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Activity.user.DetailsproductsActivity
import fudex.bonyad.ui.Activity.user.LocationmapActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.ui.Adapter.merchant.Imagesadapter
import fudex.bonyad.ui.Adapter.merchant.Myproductsadapter
import fudex.bonyad.ui.Adapter.user.CalendarAdapter
import fudex.bonyad.ui.Fragment.DeletetFragment
import fudex.bonyad.ui.Fragment.user.CalenderdialogFragment
import fudex.bonyad.ui.Fragment.user.SpeciallistreservedoneFragment
import org.joda.time.LocalDate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale


class DetailsproductViewModel(var catogaryFragment: DetailsproductsActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: DetailsproductsActivity = DetailsproductsActivity()
    var img = ObservableField<String>("")
    var name = ObservableField<String>("")
    var quantity = ObservableField<String>("1")
    var sliderimg = ObservableField<String>("")
    var productdata = ObservableField<ProductsDatum>()
    var images: ArrayList<Certificate> = ArrayList()
    var quantites: ArrayList<DistanceModel> = ArrayList()
    private val imagesadapter = Imagesadapter()

    init {
        this.context = catogaryFragment
        var linearlayout = LinearLayoutManager(context)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.imagesList.layoutManager = linearlayout
        context.binding.imagesList.adapter = imagesadapter
        getproductdetails()
        quantites.add(DistanceModel(1, "1"))
        quantites.add(DistanceModel(2, "2"))
        quantites.add(DistanceModel(3,"3"))
        quantites.add(DistanceModel(4, "4"))
        quantites.add(DistanceModel(5, "5"))
        quantites.add(DistanceModel(6, "6"))
        quantites.add(DistanceModel(7, "7"))
        quantites.add(DistanceModel(8, "8"))
        quantites.add(DistanceModel(9, "9"))
        quantites.add(DistanceModel(10, "10"))
    }
    fun getproductdetails() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java
        )

        val call: Call<DetailsProductsModel?>? =
            apiService.getuserproductdetails(context.intent.getIntExtra("id", 0))
        call?.enqueue(object : Callback<DetailsProductsModel?> {
            override fun onResponse(
                call: Call<DetailsProductsModel?>,
                response: Response<DetailsProductsModel?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    productdata.set(data?.data)
                    if (data?.data?.images?.size ?: 0 > 0 ){
                        sliderimg.set(data?.data?.images?.get(0)?.url ?: "")
                    }
                    img.set(LoginSession.getUserData(context).user.business_logo ?: "")
                    name.set(LoginSession.getUserData(context).user.trade_name ?: "")
                    images.clear()
                    images.addAll(data?.data?.images!!)
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
                                getproductdetails()
                            }
                        })
                }
                isloading.set(false)
            }

            override fun onFailure(call: Call<DetailsProductsModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection), context)
                isloading.set(false)

            }
        })
    }


    fun back(){
        context.onBackPressed()
    }

    fun rate(){
        var intent: Intent = Intent(context, RatingActivity::class.java)
        intent.putExtra("id",productdata.get()!!?.id ?: 0)
        intent.putExtra("type","Product")
        context?.startActivity(intent)
    }
    fun quantityclick() {
        var secPopUp = PopupMenu(context, context.binding.quantityLin)
        for (i in quantites.indices) {
            secPopUp?.menu?.add(i, i, i, quantites[i].title)

        }
        secPopUp?.setOnMenuItemClickListener { item ->
            quantity.set(quantites[item.itemId].id!!.toString())
            false
        }
        secPopUp.show()
    }
    fun addcart(){
        Utilities.disabletouch(context)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)

        var userdata = Cartdata(context.intent.getIntExtra("id", 0),quantity.get()?.toInt())
        val call: Call<ErrorResponse?>? = apiService.addcart(userdata)
        call?.enqueue(object : Callback<ErrorResponse?> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                    Dialogs.showToast(response.body()?.message ?: "" , context)
                } else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            addcart()
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
}