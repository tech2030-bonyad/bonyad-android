package fudex.bonyad.viewmodel.user

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.errorprone.annotations.Var
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Camera.Companion.activity
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.AddressesDatum
import fudex.bonyad.Model.AddressesModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.user.AddadressActivity
import fudex.bonyad.ui.Activity.user.AddressesActivity
import fudex.bonyad.ui.Adapter.user.Addressesadapter
import fudex.bonyad.ui.Fragment.DeletetFragment
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AddressListViewModel(var catogaryFragment: AddressesActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var addressList: ArrayList<AddressesDatum> = ArrayList<AddressesDatum>()
    var context: AddressesActivity = AddressesActivity()
    var linearlayout: LinearLayoutManager? = null
    private val addressesadapter = Addressesadapter()
    var page = 1
    private var mLoading = false
    var addressId = 0
    init {
        this.context = catogaryFragment
        linearlayout = LinearLayoutManager(context)
        linearlayout!!.orientation = LinearLayoutManager.VERTICAL
        context.binding.addresList.layoutManager = linearlayout
        context.binding.addresList.adapter = addressesadapter
        scroll()
        swiptorefresh()
    }

    fun getaddresses() {
        context.binding.addresList.showShimmer()
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        val call: Call<AddressesModel?>? = apiService.getaddress(page)

        call?.enqueue(object : Callback<AddressesModel?> {
            override fun onResponse(call: Call<AddressesModel?>, response: Response<AddressesModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    if (page == 1) {
                        addressList.clear()
                    }
                    addressList.addAll(data?.data!!)
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
                            getaddresses()
                        }
                    })
                }

                isloading.set(false)
                context.binding.addresList.hideShimmer()
            }

            override fun onFailure(call: Call<AddressesModel?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
                isloading.set(false)
                context.binding.addresList.hideShimmer()

            }
        })
    }
    fun back(){
        context.onBackPressed()
    }
    fun addadress() {
        var intent: Intent = Intent(context, AddadressActivity::class.java)
        context?.startActivity(intent)
    }
    fun scroll() {
        context.binding.addresList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val totalItemCount: Int = linearlayout!!.getItemCount()
                val visibleItemCount: Int = linearlayout!!.findLastVisibleItemPosition()
                Log.e("pos", visibleItemCount.toString())
                if (!mLoading && visibleItemCount >= totalItemCount - 3 && page > 1) {
                    mLoading = true
                    getaddresses()
                }

                super.onScrolled(recyclerView, dx, dy)
            }


        })

    }
    fun delete(){
        var fragment = DeletetFragment()
        var bundle = Bundle()
        bundle.putString("address","")
        fragment.arguments = bundle
        fragment.show(context.supportFragmentManager , "logout")
    }
    fun deleteaddress(){
        Utilities.disabletouch(context)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
            ApiInterface::class.java)
        val call: Call<ErrorResponse?>? = apiService.deleteaddress(addressId)
        call?.enqueue(object : Callback<ErrorResponse?> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                    page = 1
                    getaddresses()
                }else {
                    val errorText = response.errorBody()?.string()
                    Log.e("data",errorText!!)
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            deleteaddress()
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
    fun setdefult(){
//        Utilities.disabletouch(context)
//        isloading.set(true)
//        val apiService: ApiInterface = RetrofitClient.getClient(context)!!.create(
//            ApiInterface::class.java)
//        var requestBody: RequestBody? = null
//        requestBody =  MultipartBody.Builder()
//            .setType(MultipartBody.FORM)
//            .addFormDataPart("is_default", "1")
//            .build()
//        val call: Call<ErrorResponse?>? = apiService.setdefualt(addressId,requestBody)
//        call?.enqueue(object : Callback<ErrorResponse?> {
//            @RequiresApi(Build.VERSION_CODES.O)
//            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
//                if (response.code() == 200 || response.code() == 201) {
//                    for (index in addressList){
//                        if(index.is_default == 1 && index.id != addressId){
//                            index.is_default = 0
//                        }
//                        if (index.id == addressId){
//                            index.is_default = 1
//                        }
//                    }
//                    notifyChange()
//                }else {
//                    val errorText = response.errorBody()?.string()
//                    Log.e("data",errorText!!)
//                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
//                    APIModel.handleFailure1(context, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
//                        override fun onRefresh() {
//                            deleteaddress()
//                        }
//                    })
//                }
//                isloading.set(false)
//                Utilities.enabletouch(context)
//
//            }
//
//            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
//                Dialogs.showToast(context.getString(R.string.check_your_connection) , context)
//                isloading.set(false)
//                Utilities.enabletouch(context)
//            }
//        })
    }
    fun swiptorefresh() {
        context.binding.swip.setOnRefreshListener {
            Handler().postDelayed(Runnable {
                context.binding.swip.isRefreshing = false
            }, 1500)
            page = 1
            getaddresses()
        }
    }
}