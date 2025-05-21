package fudex.bonyad.viewmodel.technical

import android.content.Intent
import android.os.Build
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
import fudex.bonyad.Helper.Utilities.isOverlapping
import fudex.bonyad.Model.Availability
import fudex.bonyad.Model.Availabilityadd
import fudex.bonyad.Model.Dateadd
import fudex.bonyad.Model.DayAvailability

import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R

import fudex.bonyad.Model.DistanceModel
import fudex.bonyad.Model.MYSubsribeModel
import fudex.bonyad.Model.PlanData
import fudex.bonyad.Model.PlanModel
import fudex.bonyad.Model.SubsribeModel
import fudex.bonyad.ui.Activity.WebviewActivity
import fudex.bonyad.ui.Activity.technical.SubscriptionsActivity
import fudex.bonyad.ui.Adapter.technical.Avalibiltyadapter
import fudex.bonyad.ui.Adapter.technical.Daysadapter
import fudex.bonyad.ui.Adapter.technical.Planadapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import kotlin.collections.ArrayList


class SubscriptionsViewModel(activity: SubscriptionsActivity) : BaseObservable() {
    var activity: SubscriptionsActivity = SubscriptionsActivity()
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    var type = ObservableField<Int>(1)
    var mydata = ObservableField<PlanData>()
    var planList: ArrayList<PlanData> = ArrayList()
    private val planadapter = Planadapter()

    init {
        this.activity = activity
        var linearlayout = LinearLayoutManager(activity)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        activity.binding.packageList.layoutManager = linearlayout
        activity.binding.packageList.adapter = planadapter
        getpalns()
        getmysubscibe()
    }
    fun back(){
        activity.onBackPressed()
    }
    fun getpalns() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var call: Call<PlanModel?>? = null
        call = apiService.getplans()
        call?.enqueue(object : Callback<PlanModel?> {
            override fun onResponse(call: Call<PlanModel?>, response: Response<PlanModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    planList.clear()
                    planList.addAll(data?.data!!)
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getpalns()
                        }
                    })
                }

                isloading.set(false)
            }

            override fun onFailure(call: Call<PlanModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
            }
        })
    }
    fun settype(type : Int){
        this.type.set(type)
        notifyChange()
    }
    fun subscribe(id:Int) {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var call: Call<SubsribeModel?>? = null
        call = apiService.subsribe(id)
        call?.enqueue(object : Callback<SubsribeModel?> {
            override fun onResponse(call: Call<SubsribeModel?>, response: Response<SubsribeModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    var intent: Intent = Intent(activity, WebviewActivity::class.java)
                    intent.putExtra("url",response.body()!!.data?.redirectUrl ?: "")
//                    intent.putExtra("success",response.body()!!.data?.success_url ?: "")
//                    intent.putExtra("fail",response.body()!!.data?.fail_url ?: "")
                    activity.startActivity(intent)
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getpalns()
                        }
                    })
                }

                isloading.set(false)
            }

            override fun onFailure(call: Call<SubsribeModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
            }
        })
    }
    fun getmysubscibe() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var call: Call<MYSubsribeModel?>? = null
        call = apiService.getmyplan()
        call?.enqueue(object : Callback<MYSubsribeModel?> {
            override fun onResponse(call: Call<MYSubsribeModel?>, response: Response<MYSubsribeModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    mydata.set(data?.data!!)
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getpalns()
                        }
                    })
                }

                isloading.set(false)
            }

            override fun onFailure(call: Call<MYSubsribeModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
            }
        })
    }
}
