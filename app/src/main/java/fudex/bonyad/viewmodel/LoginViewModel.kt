package fudex.bonyad.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.text.InputType
import android.view.Gravity
import androidx.annotation.RequiresApi
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.adapters.TextViewBindingAdapter
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson

import dev.b3nedikt.app_locale.AppLocale
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Userdata
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.LoginData
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.ActiveuserActivity
import fudex.bonyad.ui.Activity.ForgetpassActivity
import fudex.bonyad.ui.Activity.LoginActivity
import fudex.bonyad.ui.Activity.SplashActivity
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Activity.technical.TechnicalregisterActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.ui.Activity.user.UserregisterActivity
import okhttp3.RequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
class LoginViewModel(activity: LoginActivity) : BaseObservable() {

    // observable fields on the
    var activity: LoginActivity = LoginActivity()
    val phoneObserv = ObservableField<String>()
    val passwordObserv = ObservableField<String>()
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    val phone = ObservableField<String>()

    var value: String? =
        null
    var type:Int? = null
    @SuppressLint("RestrictedApi")
    fun onpasswordChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            passwordObserv.set(s.toString())
        }
    }

    @SuppressLint("RestrictedApi")
    fun onmobileChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            phoneObserv.set(s.toString())
        }
    }
    init {
        this.activity = activity
        if (activity.getString(R.string.lang) == "ar"){
            (activity as LoginActivity).binding.pass.gravity = Gravity.RIGHT
            (activity as LoginActivity).binding.phone.gravity = Gravity.RIGHT
        }
//        code.set(LoginSession.getcountry(activity).code)
//        codelogin = LoginSession.getcountry(activity).code!!

    }


    fun validateInput() {

        var error = false
        if (passwordObserv.get() == null || passwordObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.pass.setError(activity.getString(R.string.required))
        }
        if (phoneObserv.get() == null || phoneObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.phone.setError(activity.getString(R.string.required))
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
        var phone1 = phoneObserv.get()
        if (phone1.toString().substring(0,1) == "0"){
            phone1 = phoneObserv.get().toString().substring(1 , phoneObserv.get()!!.length)
        }
        var type = ""
        if (LoginSession.gettype(activity) == 1){
            type = "user"
        }else if (LoginSession.gettype(activity) == 3){
            type = "technician"
        }
        var userdata = Userdata(type,phone1,passwordObserv.get(),"ddd",1)
        val call: Call<LoginData?>? = apiService.login(userdata)
        call?.enqueue(object : Callback<LoginData?> {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onResponse(call: Call<LoginData?>, response: Response<LoginData?>) {
                if (response.code() == 200 || response.code() == 201) {
                    LoginSession.setUserData(activity, response.body()!!)
                    LoginSession.setdata(activity)
                    if (LoginSession.gettype(activity) == 1) {
                        var intent: Intent = Intent(activity, UserhomeActivity::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        activity.startActivity(intent)
                        activity.finish()
                    }else if (LoginSession.gettype(activity) == 3) {
                        var intent: Intent = Intent(activity, TechnicalHomeActivity::class.java)
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                        activity.startActivity(intent)
                        activity.finish()
                    }
                }else if (response.code() == 409) {
                    var intent: Intent = Intent(activity, ActiveuserActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.putExtra("phone",phone1)
                    intent.putExtra("verify",phone1)
                    activity.startActivity(intent)
                    activity.finish()
                } else{
                    val errorText = response.errorBody()?.string() ?: "{}"
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

            override fun onFailure(call: Call<LoginData?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                isenable.set(true)
                Utilities.enabletouch(activity)
            }
        })
    }

    fun forgetpass(){
        var intent: Intent = Intent(activity, ForgetpassActivity::class.java)
        activity.startActivity(intent)
    }

    fun registerpass(){
        if (LoginSession.gettype(activity) == 1){
            var intent: Intent = Intent(activity, UserregisterActivity::class.java)
            activity.startActivity(intent)
        }else  if (LoginSession.gettype(activity) == 3){
            var intent: Intent = Intent(activity, TechnicalregisterActivity::class.java)
            activity.startActivity(intent)
        }


    }
    fun passclick(){
        if ((activity as LoginActivity).binding.pass.inputType == InputType.TYPE_CLASS_TEXT){
            (activity as LoginActivity).binding.eye.setBackgroundResource(R.drawable.ic_mdi_eye_off_outline)
            (activity as LoginActivity).binding.pass.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        }else {
            (activity as LoginActivity).binding.pass.setInputType(InputType.TYPE_CLASS_TEXT)
            (activity as LoginActivity).binding.eye.setBackgroundResource(R.drawable.eye)
        }
    }


    fun skip(){
//        var intent: Intent = Intent(activity, HomeActivity::class.java)
//        activity.startActivity(intent)
    }
    fun lang(){
        if (activity.getString(R.string.lang) == "ar"){
            var loginFile = activity.getSharedPreferences("lang", Context.MODE_PRIVATE)
            val editor = loginFile!!.edit()
            editor.putString("lang", "en")
            editor.apply()
            AppLocale.desiredLocale = Locale.ENGLISH
            val intent = Intent(activity, SplashActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.finish()
            activity.startActivity(intent)
        }else{
            var loginFile = activity.getSharedPreferences("lang", Context.MODE_PRIVATE)
            val editor = loginFile!!.edit()
            editor.putString("lang", "ar")
            editor.apply()
            AppLocale.desiredLocale = Locale("ar")
            val intent = Intent(activity, SplashActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.finish()
            activity.startActivity(intent)
        }
    }


}
