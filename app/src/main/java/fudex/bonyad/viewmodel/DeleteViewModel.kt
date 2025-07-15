package fudex.bonyad.viewmodel

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.ui.Fragment.DeletetFragment
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@SuppressLint("UseRequireInsteadOfGet")
class DeleteViewModel(var catogaryFragment: DeletetFragment) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: DeletetFragment = DeletetFragment()
    var activity = Activity()
    var linearlayout: LinearLayoutManager? = null
    init {
        this.context = catogaryFragment
        activity = context.requireActivity()

        val tag = context.tag  // or getTag() in older versions
        if (tag == "deleteservice") {
            context.binding.title.text = context.getString(R.string.delete_the_service)
            context.binding.body.text =
                context.getString(R.string.are_you_sure_you_want_to_delete_the_service)
        }else if (tag == "deleteproduct") {
            context.binding.title.text = context.getString(R.string.delete_product)
            context.binding.body.text =
                context.getString(R.string.are_you_sure_you_want_to_delete_product)
        }else if (tag == "subscribe") {
            context.binding.title.text = context.getString(R.string.subscriptions)
            context.binding.body.text =
                context.getString(R.string.are_you_sure_you_want_to_cancel_subscription)
        }


    }
    fun logout(){
        val tag = context.tag  // or getTag() in older versions
        if (tag == "deleteservice") {
            context.dialogListener?.onDataReceived("deleteservice")
            context.dismiss()
            return
        }else if (tag == "deleteproduct") {
            context.dialogListener?.onDataReceived("deleteproduct")
            context.dismiss()
            return
        }else if (tag == "subscribe") {
            context.dialogListener?.onDataReceived("subscribe")
            context.dismiss()
            return
        }
        notifyChange()
        Utilities.disabletouch(activity)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var requestBody: RequestBody? = null
        requestBody =  MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("device_id", Utilities.getDeviceId(activity))
            .build()
        val call: Call<ErrorResponse?>? = apiService.logout(requestBody)
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                    LoginSession.clearData(activity)
                    cancel()
                }else {
                    val errorText = response.errorBody()?.string() ?:"{}"
                    Log.e("data",errorText!!)
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            logout()
                        }
                    })
                }

                Utilities.enabletouch(activity)

            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                Utilities.enabletouch(activity)
            }
        })
    }
    fun cancel(){
        context.dismiss()
    }
}