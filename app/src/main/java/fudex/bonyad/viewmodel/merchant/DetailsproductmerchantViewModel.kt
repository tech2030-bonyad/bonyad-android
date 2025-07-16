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
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Craetereserve
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.CalendarDay
import fudex.bonyad.Model.Certificate
import fudex.bonyad.Model.DetailsProductsModel
import fudex.bonyad.Model.DetailstechnicalModel
import fudex.bonyad.Model.ProductsDatum
import fudex.bonyad.Model.ScheduleResponse
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.RatingActivity
import fudex.bonyad.ui.Activity.merchant.AddproductActivity
import fudex.bonyad.ui.Activity.merchant.DetailsproductmerchantActivity
import fudex.bonyad.ui.Activity.user.LocationmapActivity
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


class DetailsproductmerchantViewModel(var catogaryFragment: DetailsproductmerchantActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: DetailsproductmerchantActivity = DetailsproductmerchantActivity()
    var img = ObservableField<String>("")
    var name = ObservableField<String>("")
    var sliderimg = ObservableField<String>("")
    var productdata = ObservableField<ProductsDatum>()
    var images: ArrayList<Certificate> = ArrayList()
    private val imagesadapter = Imagesadapter()

    init {
        this.context = catogaryFragment
        var linearlayout = LinearLayoutManager(context)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.imagesList.layoutManager = linearlayout
        context.binding.imagesList.adapter = imagesadapter
        getproductdetails()
    }
    fun getproductdetails() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java
        )

        val call: Call<DetailsProductsModel?>? =
            apiService.getproductdetails(context.intent.getIntExtra("id", 0))
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
      //  intent.putExtra("type","User")
        context?.startActivity(intent)
    }
    fun deleteproduct(id: Int){
        Utilities.disabletouch(context)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        var serviceids: ArrayList<Int> = ArrayList()
        serviceids.add(id)
        val call: Call<ErrorResponse?>? = apiService.deleteproduct(id.toString())
        call?.enqueue(object : Callback<ErrorResponse?> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                    Dialogs.showToast(response.body()!!.message ?: "" , context)
                    back()
                } else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            deleteproduct(id)
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
    fun delete(){
        var fragment = DeletetFragment()
        var bundle = Bundle()
        fragment.arguments = bundle
        fragment.show(context.supportFragmentManager , "deleteproduct")
    }
    fun edit(){
        var intent: Intent = Intent(context, AddproductActivity::class.java)
        intent.putExtra("data",Gson().toJson(productdata.get()!!))
        context?.startActivity(intent)
    }
}