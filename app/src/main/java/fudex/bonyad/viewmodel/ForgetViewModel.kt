package fudex.bonyad.viewmodel

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import android.view.Gravity
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.adapters.TextViewBindingAdapter
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson

import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Userdata
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.ActiveuserActivity
import fudex.bonyad.ui.Activity.ForgetpassActivity
import okhttp3.MultipartBody
import okhttp3.RequestBody
import fudex.bonyad.Model.ProfileModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ForgetViewModel(activity: ForgetpassActivity) : BaseObservable() {
    // observable fields on the
    var activity: ForgetpassActivity = ForgetpassActivity()
    val phoneObserv = ObservableField<String>()
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    val phone = ObservableField<String>()
    val dialcode = ObservableField<String>()
    var maxlenght = 9
    @SuppressLint("RestrictedApi")
    fun onphoneChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            phoneObserv.set(s.toString())
        }
    }

    
    init {
        this.activity = activity
        if (activity.getString(R.string.lang) == "ar"){
            activity.binding.phone.gravity = Gravity.RIGHT
        }
        dialcode.set("+966")

    }
    fun verifycode(){
        var isgood = true
        if (phoneObserv.get() == null || phoneObserv.get()!!.isEmpty()) {
            isgood = false
            activity.binding.phone.setError(activity.getString(R.string.required))
        }
        var phone1 = phoneObserv.get()
        if (phone1?.length ?: 0 > 0) {
            if (phone1.toString().substring(0, 1) == "0") {
                if (phone1?.length != maxlenght + 1) {
                    isgood = false
                    activity.binding.phone.setError(activity.getString(R.string.length_must_be) + " " + (maxlenght + 1).toString())
                }
            } else {
                if (phone1?.length != maxlenght) {
                    isgood = false
                    activity.binding.phone.setError(activity.getString(R.string.length_must_be) + " " + maxlenght.toString())
                }
            }
        }
        if (isgood){
            Utilities.closeKeyboard(activity)
            sendsms()
        }
    }

    fun sendsms() {
        Utilities.disabletouch(activity)
        isenable.set(false)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var requestBody: RequestBody? = null
        var phone1 = phoneObserv.get()
        if (phone1.toString().substring(0,1) == "0"){
            phone1 = phoneObserv.get().toString().substring(1 , phoneObserv.get()!!.length)
        }

        var type = ""
        if (LoginSession.gettype(activity) == 1){
            type = "user"
        }else if (LoginSession.gettype(activity) == 2){
            type = "merchant"
        }else if (LoginSession.gettype(activity) == 3){
            type = "technician"
        }
        var userdata = Userdata(type,phone1)
        val call: Call<ProfileModel?>? = apiService.sendotp(userdata)
        call?.enqueue(object : Callback<ProfileModel?> {
            override fun onResponse(call: Call<ProfileModel?>, response: Response<ProfileModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                   Dialogs.showToast(response.body()!!.message!! , activity)
                    var intent: Intent = Intent(activity, ActiveuserActivity::class.java)
                    intent.putExtra("phone",phone1)
                    intent.putExtra("dialcode",dialcode.get())
                    activity.startActivity(intent)
                }else {
                    val errorText = response.errorBody()?.string()
                    Log.e("data",errorText!!)
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            sendsms()
                        }
                    })
                }
                isloading.set(false)
                isenable.set(true)
                Utilities.enabletouch(activity)

            }

            override fun onFailure(call: Call<ProfileModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                isenable.set(true)
                Utilities.enabletouch(activity)
            }
        })
    }
    fun back(){
        activity.onBackPressed()
    }



}
