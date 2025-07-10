package fudex.bonyad.viewmodel

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.CountDownTimer
import android.util.Log
import android.view.WindowManager
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
import fudex.bonyad.Model.LoginData
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.ActiveuserActivity
import fudex.bonyad.ui.Activity.LoginActivity
import fudex.bonyad.ui.Activity.ResetpasswordActivity
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import okhttp3.MultipartBody
import okhttp3.RequestBody
import fudex.bonyad.Model.ProfileModel
import fudex.bonyad.ui.Activity.merchant.MerchanthomeActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale
import java.util.concurrent.TimeUnit


class ActivecodeViewModel(activity: ActiveuserActivity) : BaseObservable() {
    // observable fields on the
    var activity: ActiveuserActivity = ActiveuserActivity()
    val num1Observ = ObservableField<String>()
    val num2Observ = ObservableField<String>()
    val num3Observ = ObservableField<String>()
    val num4Observ = ObservableField<String>()
    val title = ObservableField<String>("")
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var canverifiy: ObservableBoolean = ObservableBoolean(false)
    var isresend: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    val phone = ObservableField<String>()
    val dialcode = ObservableField<String>()
    val timer = ObservableField<String>("")
    var color = ObservableField<Int>(Color.parseColor("#E51D35"))
    var colortrans = ObservableField<Int>(Color.parseColor("#F7B9C0"))
    var time = 120
    @SuppressLint("RestrictedApi")
    fun onnum1Changed(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            num1Observ.set(s.toString())
            if (s.toString().count() == 1){
                activity.binding.code2.requestFocus()
                activity.binding.code1.setBackgroundResource(R.drawable.rec_lightblue)
            }else {
                activity.binding.code2.setBackgroundResource(R.drawable.rec_strockdarkblue)
            }
        }
        canverify()
    }
    @SuppressLint("RestrictedApi")
    fun onnum2Changed(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            num2Observ.set(s.toString())
            if (s.toString().count() == 1){
                activity.binding.code3.requestFocus()
                activity.binding.code2.setBackgroundResource(R.drawable.rec_lightblue)
            }else if (s.toString().count() == 0){
                activity.binding.code1.requestFocus()
                activity.binding.code2.setBackgroundResource(R.drawable.rec_strockdarkblue)
            }
        }
        canverify()
    }
    @SuppressLint("RestrictedApi")
    fun onnum3Changed(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            num3Observ.set(s.toString())
            if (s.toString().count() == 1){
                activity.binding.code4.requestFocus()
                activity.binding.code3.setBackgroundResource(R.drawable.rec_lightblue)
            }else if (s.toString().count() == 0){
                activity.binding.code2.requestFocus()
                activity.binding.code3.setBackgroundResource(R.drawable.rec_strockdarkblue)
            }
        }
        canverify()
    }
    @SuppressLint("RestrictedApi")
    fun onnum4Changed(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            num4Observ.set(s.toString())
            if (s.toString().count() == 0){
                activity.binding.code3.requestFocus()
                activity.binding.code4.setBackgroundResource(R.drawable.rec_strockdarkblue)
            }else {
                activity.binding.code4.setBackgroundResource(R.drawable.rec_lightblue)
            }
            canverify()
        }
    }
    
    init {
        this.activity = activity
        activity.binding.code1.requestFocus()
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        if (LoginSession.isLogin && activity.intent.hasExtra("editphone")){
            phone.set(activity.intent.getStringExtra("phone"))
          //  title.set( activity.getString(R.string.to_activate_your_account_please_enter_the_code_sent_to_the_number) + " " + activity.intent.getStringExtra("phone"))
        }else if (LoginSession.isLogin && activity.intent.hasExtra("editemail")){
            phone.set(activity.intent.getStringExtra("phone"))
           // title.set( activity.getString(R.string.to_activate_your_account_please_enter_the_code_sent_to_the_email) + " " + activity.intent.getStringExtra("phone"))
        }else {
            phone.set(activity.intent.getStringExtra("phone"))
           // title.set( activity.getString(R.string.to_activate_your_account_please_enter_the_code_sent_to_the_number) + " " + activity.intent.getStringExtra("phone"))
        }
       // time = activity.intent.getIntExtra("time",0)
        timershow()
        //changecolor()
    }
    fun timershow(){
        object : CountDownTimer((time * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) -
                        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished))
                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))

                val formattedTime = String.format(Locale(activity.getString(R.string.lang)), "%02d:%02d", minutes, seconds)
                val localizedText = formattedTime
                timer.set(localizedText)
            }

            override fun onFinish() {
                timer.set("")
                isresend.set(true)
            }
        }.start()
    }
    fun canverify(){
        var isgood = true
        if (num1Observ.get() == null || num1Observ.get()!!.isEmpty()) {
            isgood = false
        }
        if (num2Observ.get() == null || num2Observ.get()!!.isEmpty()) {
            isgood = false
        }
        if (num3Observ.get() == null || num3Observ.get()!!.isEmpty()) {
            isgood = false
        }
        if (num4Observ.get() == null || num4Observ.get()!!.isEmpty()) {
            isgood = false
        }
        if (isgood){
            canverifiy.set(true)
            verifycode()
        }else {
            canverifiy.set(false)
        }
    }
    fun verifycode(){
        var isgood = true
        if (num1Observ.get() == null || num1Observ.get()!!.isEmpty()) {
            isgood = false
            activity.binding.code1.setError(activity.getString(R.string.required))
        }
        if (num2Observ.get() == null || num2Observ.get()!!.isEmpty()) {
            isgood = false
            activity.binding.code2.setError(activity.getString(R.string.required))
        }
        if (num3Observ.get() == null || num3Observ.get()!!.isEmpty()) {
            isgood = false
            activity.binding.code3.setError(activity.getString(R.string.required))
        }
        if (num4Observ.get() == null || num4Observ.get()!!.isEmpty()) {
            isgood = false
            activity.binding.code4.setError(activity.getString(R.string.required))
        }
        if (isgood){
           validateInput()
        }
    }


    fun validateInput() {
            if (activity.intent.hasExtra("editphone")){
                Utilities.disabletouch(activity)
                isenable.set(false)
                isloading.set(true)
                val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
                    ApiInterface::class.java)

                var type = ""
                if (LoginSession.gettype(activity) == 1){
                    type = "user"
                }else if (LoginSession.gettype(activity) == 2){
                    type = "merchant"
                }else if (LoginSession.gettype(activity) == 3){
                    type = "technician"
                }
                var userdata = Userdata(type, phone.get(), otp =  num1Observ.get() + num2Observ.get() + num3Observ.get() + num4Observ.get())
                val call: Call<ProfileModel?>? = apiService.editphone1(userdata)
                call?.enqueue(object : Callback<ProfileModel?> {
                    override fun onResponse(call: Call<ProfileModel?>, response: Response<ProfileModel?>) {
                        if (response.code() == 200 || response.code() == 201) {
//                        Utilities.showSuccessDialog(activity, response.body()!!.message ?: "" ,
//                            "" ){
//
//                        }
                            if (LoginSession.gettype(activity) == 1){
                                var data = LoginSession.getUserData1(activity)
                                data.user == response.body()!!.data
                                LoginSession.setUserData(activity, data)
                                LoginSession.setdata(activity)
                                var intent: Intent = Intent(activity, UserhomeActivity::class.java)
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                activity.startActivity(intent)
                                activity.finish()
                            }else if (LoginSession.gettype(activity) == 2){
                                var data = LoginSession.getUserData1(activity)
                                data.user == response.body()!!.data
                                LoginSession.setUserData(activity, data)
                                LoginSession.setdata(activity)
                                var intent: Intent = Intent(activity, MerchanthomeActivity::class.java)
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                activity.startActivity(intent)
                                activity.finish()
                            }else if (LoginSession.gettype(activity) == 3){
                                var data = LoginSession.getUserData1(activity)
                                data.user == response.body()!!.data
                                LoginSession.setUserData(activity, data)
                                LoginSession.setdata(activity)
                                var intent: Intent = Intent(activity, TechnicalHomeActivity::class.java)
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                activity.startActivity(intent)
                                activity.finish()
                            }

                        }else {
                            val errorText = response.errorBody()?.string()
                            Log.e("data",errorText!!)
                            val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                            APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                                override fun onRefresh() {
                                    validateInput()
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
            }else if (activity.intent.hasExtra("verify")){
                Utilities.disabletouch(activity)
                isenable.set(false)
                isloading.set(true)
                val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
                    ApiInterface::class.java)
                var type = ""
                if (LoginSession.gettype(activity) == 1){
                    type = "user"
                }else if (LoginSession.gettype(activity) == 2){
                    type = "merchant"
                }else if (LoginSession.gettype(activity) == 3){
                    type = "technician"
                }
                var userdata = Userdata(type, phone.get(), otp = num1Observ.get() + num2Observ.get() + num3Observ.get() + num4Observ.get())
                val call: Call<LoginData?>? = apiService.verifiyuser(userdata)
                call?.enqueue(object : Callback<LoginData?> {
                    override fun onResponse(call: Call<LoginData?>, response: Response<LoginData?>) {
                        if (response.code() == 200 || response.code() == 201) {
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
                                    validateInput()
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
        }else {
            var intent: Intent = Intent(activity, ResetpasswordActivity::class.java)
            intent.putExtra("phone",phone.get())
            intent.putExtra("otp",num1Observ.get() + num2Observ.get() + num3Observ.get() + num4Observ.get())
            activity.startActivity(intent)
        }
    }

    fun sendsms() {
        isresend.set(false)
            if (activity.intent.hasExtra("editphone")){
                Utilities.disabletouch(activity)
                isenable.set(false)
                isloading.set(true)
                val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
                    ApiInterface::class.java
                )
                var type = ""
                if (LoginSession.gettype(activity) == 1){
                    type = "user"
                }else if (LoginSession.gettype(activity) == 2){
                    type = "merchant"
                }else if (LoginSession.gettype(activity) == 3){
                    type = "technician"
                }
                var userdata = Userdata(type, phone.get())
                val call: Call<ProfileModel?>? = apiService.editphone(userdata)
                call?.enqueue(object : Callback<ProfileModel?> {
                    override fun onResponse(
                        call: Call<ProfileModel?>,
                        response: Response<ProfileModel?>
                    ) {
                        if (response.code() == 200 || response.code() == 201) {
                            Dialogs.showToast(response.body()!!.message!!, activity)
                            timershow()
                        } else {
                            val errorText = response.errorBody()?.string()
                            Log.e("data", errorText!!)
                            val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                            APIModel.handleFailure1(
                                activity,
                                response.code(),
                                errorResponse,
                                object : APIModel.RefreshTokenListener {
                                    override fun onRefresh() {
                                        validateInput()
                                    }
                                })
                        }
                        isloading.set(false)
                        isenable.set(true)
                        Utilities.enabletouch(activity)

                    }

                    override fun onFailure(call: Call<ProfileModel?>, t: Throwable) {
                        Dialogs.showToast(activity.getString(R.string.check_your_connection), activity)
                        isloading.set(false)
                        isenable.set(true)
                        Utilities.enabletouch(activity)
                    }
                })
            }else if (activity.intent.hasExtra("verify")){
                Utilities.disabletouch(activity)
                isenable.set(false)
                isloading.set(true)
                val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
                    ApiInterface::class.java
                )
                var type = ""
                if (LoginSession.gettype(activity) == 1){
                    type = "user"
                }else if (LoginSession.gettype(activity) == 2){
                    type = "merchant"
                }else if (LoginSession.gettype(activity) == 3){
                    type = "technician"
                }
                var userdata = Userdata(type, phone.get())
                val call: Call<ProfileModel?>? = apiService.resendsendotp(userdata)
                call?.enqueue(object : Callback<ProfileModel?> {
                    override fun onResponse(
                        call: Call<ProfileModel?>,
                        response: Response<ProfileModel?>
                    ) {
                        if (response.code() == 200 || response.code() == 201) {
                            Dialogs.showToast(response.body()!!.message!!, activity)
                            timershow()
                        } else {
                            val errorText = response.errorBody()?.string()
                            Log.e("data", errorText!!)
                            val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                            APIModel.handleFailure1(
                                activity,
                                response.code(),
                                errorResponse,
                                object : APIModel.RefreshTokenListener {
                                    override fun onRefresh() {
                                        validateInput()
                                    }
                                })
                        }
                        isloading.set(false)
                        isenable.set(true)
                        Utilities.enabletouch(activity)

                    }

                    override fun onFailure(call: Call<ProfileModel?>, t: Throwable) {
                        Dialogs.showToast(activity.getString(R.string.check_your_connection), activity)
                        isloading.set(false)
                        isenable.set(true)
                        Utilities.enabletouch(activity)
                    }
                })
        }else {
            val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
                ApiInterface::class.java)
            var type = ""
            if (LoginSession.gettype(activity) == 1){
                type = "user"
            }else if (LoginSession.gettype(activity) == 2){
                type = "merchant"
            }else if (LoginSession.gettype(activity) == 3){
                type = "technician"
            }
            var userdata = Userdata(type,phone.get())
            val call: Call<ProfileModel?>? = apiService.sendotp(userdata)
            call?.enqueue(object : Callback<ProfileModel?> {
                override fun onResponse(call: Call<ProfileModel?>, response: Response<ProfileModel?>) {
                    if (response.code() == 200 || response.code() == 201) {
                        Dialogs.showToast(response.body()!!.message!! , activity)

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
    }
    fun back(){
        if (activity.intent.hasExtra("editphone")){
            activity.onBackPressed()
        }else if (activity.intent.hasExtra("editemail")){
            activity.onBackPressed()
        }else {
            if (LoginSession.isLogin){
                LoginSession.clearData(activity)
            }else {
                activity.onBackPressed()
            }
        }

    }



}
