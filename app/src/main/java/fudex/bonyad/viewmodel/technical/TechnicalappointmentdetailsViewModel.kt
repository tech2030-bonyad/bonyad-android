package fudex.bonyad.viewmodel.technical

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Userdata
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Model.AppointmentdetailsModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.ChatActivity
import fudex.bonyad.ui.Activity.RatingActivity
import fudex.bonyad.ui.Activity.technical.TechnicaldetailsapointmentActivity
import fudex.bonyad.ui.Fragment.technical.RefuseFragment
import fudex.bonyad.ui.Fragment.user.RatingdialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.Locale


class TechnicalappointmentdetailsViewModel(var catogaryFragment: TechnicaldetailsapointmentActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isshow: ObservableBoolean = ObservableBoolean(false)
    var isvisble: ObservableBoolean = ObservableBoolean(false)
    var context: TechnicaldetailsapointmentActivity = TechnicaldetailsapointmentActivity()
    private var mLoading = false
    var detailsdata = ObservableField<AppointmentdetailsModel>()
    var title = ObservableField<String>("")
    var img = ObservableField<String>("")
    var address = ObservableField<String>("")
    init {
        this.context = catogaryFragment
        getappointmentsdetails()
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

    fun getappointmentsdetails() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java
        )

        val call: Call<AppointmentdetailsModel?>? =
            apiService.gettechnicalappointmentdetails(context.intent.getIntExtra("id", 0))
        call?.enqueue(object : Callback<AppointmentdetailsModel?> {
            override fun onResponse(
                call: Call<AppointmentdetailsModel?>,
                response: Response<AppointmentdetailsModel?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    img.set(data?.data?.user?.avatar ?: "")
                    data?.data?.date = data?.data?.date_of_reservation!! + " " + data?.data?.start_time!!
                    detailsdata.set(data!!)

                    val geocoder = Geocoder(context, Locale(context.getString(R.string.lang)))
                    var address1 = "Unknown location"
                    try {
                        val addresses = geocoder.getFromLocation(detailsdata.get()!!.data?.lat!!.toDouble() , detailsdata.get()!!.data?.long!!.toDouble(), 1)
                        if (addresses!!.isNotEmpty()) {
                            address1 = addresses[0].getAddressLine(0)
                            address.set(address1)
                        }
                    } catch (e: IOException) {
                        // Handle any errors that occur during the geocoding process
                    }
                    var txt = ""
                    var item = 0
                    for (index in data?.data?.technician?.services!!){
                        if (item ==data?.data?.technician?.services?.size!! - 1) {
                            txt = txt + index.name!!
                        }else {
                            txt = txt + index.name!! + " , "
                        }
                        item = item + 1
                    }
                    title.set(txt)
                }else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getappointmentsdetails()
                        }
                    })
                }
                isvisble.set(true)
                isloading.set(false)
                notifyChange()
            }

            override fun onFailure(call: Call<AppointmentdetailsModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection), context)
                isloading.set(false)

            }
        })
    }
    fun refuseorder(notes:String) {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java
        )
        var userdata = Userdata(notes = notes)
        val call: Call<ErrorResponse?>? =
            apiService.refusetechnicalappointment(context.intent.getIntExtra("id", 0),userdata)
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(
                call: Call<ErrorResponse?>,
                response: Response<ErrorResponse?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    getappointmentsdetails()
                }else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            refuseorder(notes)
                        }
                    })
                }
                isvisble.set(true)
                isloading.set(false)
                notifyChange()
            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection), context)
                isloading.set(false)

            }
        })
    }
    fun accept() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java
        )
        val call: Call<ErrorResponse?>? =
            apiService.accepttechnicalappointment(context.intent.getIntExtra("id", 0))
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(
                call: Call<ErrorResponse?>,
                response: Response<ErrorResponse?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                   getappointmentsdetails()
                } else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            accept()
                        }
                    })
                }
                isvisble.set(true)
                isloading.set(false)
                notifyChange()
            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection), context)
                isloading.set(false)

            }
        })
    }


    fun back() {
        context.onBackPressed()
    }

    fun makereject(){
        var fragment = RefuseFragment()
        fragment.show(context.supportFragmentManager , "gift")
    }
    fun rateuser(){
        var fragment = RatingdialogFragment()
        var bundle = Bundle()
        bundle.putInt("id",detailsdata.get()?.data?.user?.id ?: 0)
        bundle.putString("type","User")
        fragment.arguments = bundle
        fragment.show(context.supportFragmentManager , "rate")
    }
    fun chat(){
        var intent: Intent = Intent(context, ChatActivity::class.java)
        intent.putExtra("id", detailsdata.get()?.data?.user?.id ?: 0)
        intent.putExtra("name", detailsdata.get()?.data?.user?.name ?: "")
        intent.putExtra("img", detailsdata.get()?.data?.user?.avatar ?: "")
        context?.startActivity(intent)
    }
    fun rate(){
        var intent: Intent = Intent(context, RatingActivity::class.java)
        intent.putExtra("id",detailsdata.get()!!.data?.user?.id ?: 0)
        intent.putExtra("type","User")
        context?.startActivity(intent)
    }
}