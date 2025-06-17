package fudex.bonyad.viewmodel.user

import android.content.Intent
import android.location.Geocoder
import android.os.Bundle
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Userdata
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Model.AppointmentdetailsModel
import fudex.bonyad.Model.StatesDatum
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.user.DetailsappointmentActivity
import fudex.bonyad.ui.Activity.user.DetailsspeciallistActivity
import fudex.bonyad.ui.Adapter.user.Servicesdetailsadapter
import fudex.bonyad.ui.Fragment.technical.RefuseFragment
import fudex.bonyad.ui.Fragment.user.CalenderdialogFragment
import fudex.bonyad.ui.Fragment.user.RatingdialogFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.Locale


class DetailsappointmentViewModel(var catogaryFragment: DetailsappointmentActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isshow: ObservableBoolean = ObservableBoolean(false)
    var isvisble: ObservableBoolean = ObservableBoolean(false)
    var context: DetailsappointmentActivity = DetailsappointmentActivity()
    private var mLoading = false
    var detailsdata = ObservableField<AppointmentdetailsModel>()
    var img = ObservableField<String>("")
    var address = ObservableField<String>("")
    val services: ArrayList<StatesDatum> = ArrayList()
    private val servicesdetailsadapter = Servicesdetailsadapter()

    init {
        this.context = catogaryFragment
        var linearlayout1 = LinearLayoutManager(context)
        linearlayout1!!.orientation = LinearLayoutManager.HORIZONTAL
        context.binding.serviceList.layoutManager = linearlayout1
        context.binding.serviceList.adapter = servicesdetailsadapter
        getappointmentdetails()

    }

    fun getappointmentdetails() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java
        )

        val call: Call<AppointmentdetailsModel?>? =
            apiService.getappointmentdetails(context.intent.getIntExtra("id", 0))
        call?.enqueue(object : Callback<AppointmentdetailsModel?> {
            override fun onResponse(
                call: Call<AppointmentdetailsModel?>,
                response: Response<AppointmentdetailsModel?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    img.set(data?.data?.technician?.avatar ?: "")
                    data?.data?.date = data?.data?.date_of_reservation!! + " " + data?.data?.start_time!!
                    detailsdata.set(data!!)
                    services.clear()
                    services.addAll(data?.data?.technician?.services!!)
                    if (data?.data?.status?.value ?: 0 == 2){
                        context.binding.title.text =
                            context.getString(R.string.complete_reservation)
                    }
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
                    if (data?.data?.status?.value ?:0 == 2){
                        context.binding.title.text = context.getString(R.string.complete_reservation)
                    }else if (data?.data?.status?.value ?:0 == 4){
                        context.binding.title.text = context.getString(R.string.evaluation)
                    }
                }else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getappointmentdetails()
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
    fun cancelorder() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java
        )
        val call: Call<ErrorResponse?>? =
            apiService.cancelorder(context.intent.getIntExtra("id", 0))
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(
                call: Call<ErrorResponse?>,
                response: Response<ErrorResponse?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    getappointmentdetails()
                }else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            cancelorder()
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
                   getappointmentdetails()
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
    fun completereservation() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java
        )
        val call: Call<ErrorResponse?>? =
            apiService.completeappointment(context.intent.getIntExtra("id", 0))
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(
                call: Call<ErrorResponse?>,
                response: Response<ErrorResponse?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    getappointmentdetails()
                }else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            completereservation()
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

    fun back() {
        context.onBackPressed()
    }

    fun edit() {
        if (detailsdata.get()?.data?.status?.value ?: 0 == 1) {
            var intent: Intent = Intent(context, DetailsspeciallistActivity::class.java)
            intent.putExtra("id", detailsdata.get()?.data?.technician?.id ?: 0)
            intent.putExtra("reservaionid", detailsdata.get()?.data?.id ?: 0)
            context?.startActivity(intent)
        } else if (detailsdata.get()?.data?.status?.value ?: 0 == 2) {
            completereservation()
        } else if (detailsdata.get()?.data?.status?.value ?: 0 == 4) {
            rateuser()
        }
    }
    fun rateuser(){
        var fragment = RatingdialogFragment()
        var bundle = Bundle()
        bundle.putInt("id",detailsdata.get()?.data?.technician?.id ?: 0)
        bundle.putString("type","User")
        fragment.arguments = bundle
        fragment.show(context.supportFragmentManager , "rate")
    }

}