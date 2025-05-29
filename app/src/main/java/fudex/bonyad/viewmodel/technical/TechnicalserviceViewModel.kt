package fudex.bonyad.viewmodel.technical

import android.app.Activity
import android.os.Build
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

import fudex.bonyad.ui.Adapter.technical.Serviceadapter
import fudex.bonyad.ui.Fragment.technical.TechnicalservicesFragment

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TechnicalserviceViewModel(var catogaryFragment: TechnicalservicesFragment) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: TechnicalservicesFragment = TechnicalservicesFragment()
    var activity = Activity()
    var img = ObservableField<String>("")
    var islogin = ObservableField<Boolean>(false)
    var userdata = ObservableField<UserModel>()
    var servicesList: ArrayList<StatesDatum> = ArrayList()
    var  linearlayout: LinearLayoutManager? = null
    private val serviceadapter = Serviceadapter()
    var handler: Handler = Handler()
    var page = 1
    private var mLoading = false
    init {
        this.context = catogaryFragment
        activity = context.requireActivity()
        linearlayout = LinearLayoutManager(activity)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.serviceList.layoutManager = linearlayout
        context.binding.serviceList.adapter = serviceadapter
        if (activity.getString(R.string.lang) == "ar"){
            context.binding.searchTxt.gravity = Gravity.RIGHT
        }
        context.binding.searchTxt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                if (handler != null) handler.removeCallbacksAndMessages(null)
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
//                if (edit.getText().toString().equals("")){
//                    subCategoriesBeans.clear();
//                    subCatogaryadapter.notifyDataSetChanged();
//                }else {
//                    gethome();
//                }
            }

            override fun afterTextChanged(s: Editable) {
                handler.postDelayed(Runnable {
                    page = 1
                    getservices()
                }, 1000)
            }
        })
        scroll()
    }
    fun getservices() {
        if (page == 1) {
            context.binding.serviceList.showShimmer()
        }
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var call: Call<StatesModel?>? = null
        call = apiService.getservices(if (context.binding.searchTxt.text.toString() == ""){null}else{context.binding.searchTxt.text.toString()},page,10)
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
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getservices()
                        }
                    })
                }
                context.binding.serviceList.hideShimmer()
                isloading.set(false)
            }

            override fun onFailure(call: Call<StatesModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                context.binding.serviceList.hideShimmer()
            }
        })
    }
    fun scroll() {
        context.binding.serviceList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount: Int = linearlayout!!.getItemCount()
                val visibleItemCount: Int = linearlayout!!.findLastVisibleItemPosition()
                Log.e("pos", visibleItemCount.toString())
                if (!mLoading && visibleItemCount >= totalItemCount - 3 && page > 1) {
                    mLoading = true
                    getservices()
                }

                super.onScrolled(recyclerView, dx, dy)
            }


        })

    }
    fun addservice(id: Int){
        Utilities.disabletouch(activity)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var serviceids: ArrayList<Int> = ArrayList()
        serviceids.add(id)
        var services  = AddserviceModel(serviceids)
        val call: Call<ErrorResponse?>? = apiService.addservices(services)
        call?.enqueue(object : Callback<ErrorResponse?> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var index = 0
                    for (item in servicesList){
                        if (item.id == id){
                            servicesList[index].is_technician_service = 1
                        }
                        index = index + 1
                    }
                    notifyChange()
                } else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            addservice(id)
                        }
                    })
                }
                isloading.set(false)
                Utilities.enabletouch(activity)

            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                Utilities.enabletouch(activity)
            }
        })
    }

    fun deleteservice(id: Int){
        Utilities.disabletouch(activity)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var serviceids: ArrayList<Int> = ArrayList()
        serviceids.add(id)
        var services  = AddserviceModel(serviceids)
        val call: Call<ErrorResponse?>? = apiService.deleteservices(services)
        call?.enqueue(object : Callback<ErrorResponse?> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var index = 0
                    for (item in servicesList){
                        if (item.id == id){
                            servicesList[index].is_technician_service = 0
                        }
                        index = index + 1
                    }
                    notifyChange()
                } else{
                    val errorText = response.errorBody()?.string() ?: "{}"
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            deleteservice(id)
                        }
                    })
                }
                isloading.set(false)
                Utilities.enabletouch(activity)

            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                Utilities.enabletouch(activity)
            }
        })
    }
}