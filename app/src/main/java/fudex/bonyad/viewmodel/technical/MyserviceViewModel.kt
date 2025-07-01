package fudex.bonyad.viewmodel.technical

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.AddserviceModel
import fudex.bonyad.Model.StatesDatum
import fudex.bonyad.Model.StatesModel
import fudex.bonyad.Model.UserModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.technical.MyservicesActivity
import fudex.bonyad.ui.Adapter.technical.Service1adapter

import fudex.bonyad.ui.Adapter.technical.Serviceadapter
import fudex.bonyad.ui.Fragment.DeletetFragment
import fudex.bonyad.ui.Fragment.technical.TechnicalservicesFragment

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MyserviceViewModel(var catogaryFragment: MyservicesActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: MyservicesActivity = MyservicesActivity()
    var img = ObservableField<String>("")
    var islogin = ObservableField<Boolean>(false)
    var userdata = ObservableField<UserModel>()
    var servicesList: ArrayList<StatesDatum> = ArrayList()
    var  linearlayout: LinearLayoutManager? = null
    private val serviceadapter = Service1adapter()
    var handler: Handler = Handler()
    var page = 1
    private var mLoading = false
    var serviceId = 0
    init {
        this.context = catogaryFragment
        linearlayout = LinearLayoutManager(context)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.serviceList.layoutManager = linearlayout
        context.binding.serviceList.adapter = serviceadapter
        getservices()
    }
    fun back(){
        context.onBackPressed()
    }
    fun getservices() {
        if (page == 1) {
            context.binding.serviceList.showShimmer()
        }
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        var call: Call<StatesModel?>? = null
        call = apiService.getmyservices()
        call?.enqueue(object : Callback<StatesModel?> {
            override fun onResponse(call: Call<StatesModel?>, response: Response<StatesModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    if (page == 1) {
                        servicesList.clear()
                    }
                    servicesList.addAll(data?.data!!)
                    if (response.body()!!.data!!.size > 0) {
                        page++
                        mLoading = false
                    }
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getservices()
                        }
                    })
                }
                context.binding.serviceList.hideShimmer()
                isloading.set(false)
            }

            override fun onFailure(call: Call<StatesModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
                isloading.set(false)
                context.binding.serviceList.hideShimmer()
            }
        })
    }
    fun deleteservice(id: Int){
        Utilities.disabletouch(context)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        var serviceids: ArrayList<Int> = ArrayList()
        serviceids.add(id)
        var services  = AddserviceModel(serviceids)
        val call: Call<ErrorResponse?>? = apiService.deleteservices(services)
        call?.enqueue(object : Callback<ErrorResponse?> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                    Dialogs.showToast(response.body()!!.message ?: "" , context)
                    var index = 0
                    for (item in servicesList){
                        if (item.id == id){
                            servicesList.removeAt(index)
                        }
                        index = index + 1
                    }
                    notifyChange()
                } else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            deleteservice(id)
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
        fragment.show(context.supportFragmentManager , "deleteservice")
    }
}