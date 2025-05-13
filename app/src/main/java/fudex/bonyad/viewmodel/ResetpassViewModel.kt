package fudex.bonyad.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.text.InputType
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
import fudex.bonyad.Model.Loginforsecure
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.LoginActivity
import fudex.bonyad.ui.Activity.ResetpasswordActivity
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ResetpassViewModel(activity: ResetpasswordActivity) : BaseObservable() {
    // observable fields on the
    var activity: ResetpasswordActivity = ResetpasswordActivity()
    val passwordObserv = ObservableField<String>()
    val confirmpasswordObserv = ObservableField<String>()
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    @SuppressLint("RestrictedApi")
    fun onpasswordChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            passwordObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun onconfirmpasswordhanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            confirmpasswordObserv.set(s.toString())
        }
    }
    init {
        this.activity = activity
        if (activity.getString(R.string.lang) == "ar"){
            activity.binding.pass.gravity = Gravity.RIGHT
            activity.binding.confirmpass.gravity = Gravity.RIGHT
        }
    }


    fun validateInput() {
        var error = false
        if (passwordObserv.get() == null || passwordObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.pass.setError(activity.getString(R.string.required))
        }
        if (confirmpasswordObserv.get() == null || confirmpasswordObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.confirmpass.setError(activity.getString(R.string.required))
        }
        if (confirmpasswordObserv.get() != null && confirmpasswordObserv.get()!!.isNotEmpty() && passwordObserv.get() != null && passwordObserv.get()!!.isNotEmpty()) {
            if (confirmpasswordObserv.get() != passwordObserv.get()) {
                error = true
                activity.binding.confirmpass.setError(activity.getString(R.string.confirm_password_must_same_password))
            }
        }
        if (!error) {
            Utilities.closeKeyboard(activity)
            regiterclick()
        }
    }

    fun back(){
        activity.onBackPressed()
    }
    fun regiterclick(){
        Utilities.disabletouch(activity)
        isenable.set(false)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var type = ""
        if (LoginSession.gettype(activity) == 1){
            type = "user"
        }else if (LoginSession.gettype(activity) == 3){
            type = "technician"
        }
        var userdata = Userdata(type, activity.intent.getStringExtra("phone"), otp = activity.intent.getStringExtra("otp"), password = passwordObserv.get(), password_confirmation = passwordObserv.get())
        val call: Call<ErrorResponse?>? = apiService.restpass(userdata)
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                    Dialogs.showToast(response.body()!!.message!!,activity)
                    var loginFile: SharedPreferences? = null
                    loginFile = activity.getSharedPreferences("secureFile", Context.MODE_PRIVATE)
                    if (loginFile!!.getString("login", "") != ""){
                        val data = Gson().fromJson(Utilities.decrypt(loginFile!!.getString("login", "")!!, 6), Loginforsecure::class.java)
                        if (data.phone == activity.intent.getStringExtra("phone") && data.countrycode == activity.intent.getStringExtra("code")) {
                            data.password = passwordObserv.get()
                            val encryptedData = Utilities.encrypt(Gson().toJson(data).toString(), 6)
                            val editor = loginFile!!.edit()
                            editor.putString("login", encryptedData)
                            editor.putBoolean("remember", loginFile.getBoolean("remember", false))
                            editor.apply()
                        }
                    }

                    var intent: Intent = Intent(activity, LoginActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    activity.startActivity(intent)
                    activity.finish()
                }else {
                    val errorText = response.errorBody()?.string()
                    Log.e("data",errorText!!)
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            regiterclick()
                        }
                    })
                }
                isloading.set(false)
                isenable.set(true)
                Utilities.enabletouch(activity)

            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                isenable.set(true)
                Utilities.enabletouch(activity)
            }
        })
    }
    fun passclick(){
        if (activity.binding.pass.inputType == InputType.TYPE_CLASS_TEXT){
            activity.binding.eyePass.setBackgroundResource(R.drawable.ic_mdi_eye_off_outline)
            activity.binding.pass.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        }else {
            activity.binding.pass.setInputType(InputType.TYPE_CLASS_TEXT)
            activity.binding.eyePass.setBackgroundResource(R.drawable.eye)
        }
    }
    fun passconfirmclick(){
        if (activity.binding.confirmpass.inputType == InputType.TYPE_CLASS_TEXT){
            activity.binding.eyeConfirm.setBackgroundResource(R.drawable.ic_mdi_eye_off_outline)
            activity.binding.confirmpass.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        }else {
            activity.binding.confirmpass.setInputType(InputType.TYPE_CLASS_TEXT)
            activity.binding.eyeConfirm.setBackgroundResource(R.drawable.eye)
        }
    }
}
