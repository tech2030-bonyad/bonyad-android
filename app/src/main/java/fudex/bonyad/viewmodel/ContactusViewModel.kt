package fudex.bonyad.viewmodel

import android.annotation.SuppressLint
import android.text.Html
import android.text.InputFilter
import android.util.Log
import android.view.Gravity
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.adapters.TextViewBindingAdapter
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson

import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Contactdata
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.IntentClass
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Helper.Validations
import fudex.bonyad.Model.ContactusModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.ContactusActivity
import fudex.bonyad.Model.AboutModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.*


class ContactusViewModel(activity: ContactusActivity) : BaseObservable() {

    // observable fields on the
    var activity: ContactusActivity = ContactusActivity()
    val emailObserv = ObservableField<String>()
    val messageObserv = ObservableField<String>()
    val phoneObserv = ObservableField<String>()
    val nameObserv = ObservableField<String>()
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    var maxlenght = 0
    var insta = ""
    var whats = ""
    var twitter = ""
    var linkidin = ""

    @SuppressLint("RestrictedApi")
    fun onmobileChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            if (activity.binding.phone.text.length > 0 && activity.binding.phone.text.substring(0,1) == "0"){
                activity.binding.phone.filters = arrayOf(InputFilter.LengthFilter(maxlenght + 1))
            }else {
                activity.binding.phone.filters = arrayOf(InputFilter.LengthFilter(maxlenght))
            }
            phoneObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun onnameChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            nameObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun onemailChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            emailObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun onmessageChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            messageObserv.set(s.toString())
        }
    }


    init {
        this.activity = activity
        maxlenght = 9
        if (activity.getString(R.string.lang) == "ar"){
            activity.binding.email.gravity = Gravity.RIGHT
            activity.binding.message.gravity = Gravity.RIGHT
            activity.binding.name.gravity = Gravity.RIGHT
            activity.binding.phone.gravity = Gravity.RIGHT

        }
        if (LoginSession.isLogin){
            activity.binding.email.setText(LoginSession.getUserData(activity).user?.email)
            activity.binding.name.setText(LoginSession.getUserData(activity).user?.name)
            activity.binding.phone.setText(LoginSession.getUserData(activity).user?.phone)
            emailObserv.set(LoginSession.getUserData(activity).user?.email)
            phoneObserv.set(LoginSession.getUserData(activity).user?.phone)
            nameObserv.set(LoginSession.getUserData(activity).user?.name)

        }
        activity.binding.main.setOnClickListener {
            Utilities.closeKeyboard(activity)
        }
        getsetting()

    }


    fun validateInput() {
        var error = false
        if (emailObserv.get() == null || emailObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.email.setError(activity.getString(R.string.required))
        }
        if (emailObserv.get() != null && emailObserv.get()!!.isNotEmpty()  && !Validations.isValidEmail(emailObserv.get().toString().trim())) {
            error = true
            activity.binding.email.setError(activity.getString(R.string.email_foramt_is_wrong))
        }
        if (messageObserv.get() == null || messageObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.message.setError(activity.getString(R.string.required))
        }
        if (nameObserv.get() == null || nameObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.name.setError(activity.getString(R.string.required))
        }

        if (phoneObserv.get() == null || phoneObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.phone.setError(activity.getString(R.string.required))
        }
        var phone1 = phoneObserv.get()
        if (phone1?.length ?: 0 > 0) {
            if (phone1.toString().substring(0,1) == "0"){
                if (phone1?.length != maxlenght + 1){
                    error = true
                    activity.binding.phone.setError(activity.getString(R.string.length_must_be) + " " + (maxlenght + 1).toString() )
                }
            }else {
                if (phone1?.length != maxlenght){
                    error = true
                    activity.binding.phone.setError(activity.getString(R.string.length_must_be) + " " + maxlenght.toString() )
                }
            }
        }
        if (!error) {
            Utilities.closeKeyboard(activity)
            contactusclick()
        }
    }

    fun back(){
        activity.onBackPressed()
    }
    fun contactusclick(){
        Utilities.disabletouch(activity)
        isenable.set(false)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var phone1 = phoneObserv.get()
        if (phone1.toString().substring(0,1) == "0"){
            phone1 = phoneObserv.get().toString().substring(1 , phoneObserv.get()!!.length)
        }
        var userdata = Contactdata(nameObserv.get(),emailObserv.get(),phoneObserv.get(),"ddd",messageObserv.get())
        val call: Call<ErrorResponse?>? = apiService.contactus(userdata)
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                    Dialogs.showToast(response.body()!!.message!!,activity)
                    back()
                }else {
                    val errorText = response.errorBody()?.string()
                    Log.e("data",errorText!!)
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            contactusclick()
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
    fun getsetting() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(ApiInterface::class.java)
        val call: Call<ContactusModel?>? = apiService.getsetting()
        call?.enqueue(object : Callback<ContactusModel?> {
            override fun onResponse(call: Call<ContactusModel?>, response: Response<ContactusModel?>) {
                if (response!!.code() == 200 || response!!.code() == 201) {
                    var data = response.body()
                    for (item in data?.data!!){
                        if (item.key == "facebook"){
                            insta = item.value ?: ""
                        }
                        if (item.key == "linkedIn"){
                            linkidin = item.value ?: ""
                        }
                        if (item.key == "twitter"){
                            twitter = item.value ?: ""
                        }
                        if (item.key == "whatsapp"){
                            whats = item.value ?: ""
                        }
                    }
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getsetting()
                        }
                    })
                }
                isloading.set(false)

            }

            override fun onFailure(call: Call<ContactusModel?>?, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
            }
        })
    }
    fun instaclisk(){
        if (insta == ""){
            return
        }
        IntentClass.goToLink(activity,insta)
    }
    fun twitterclisk(){
        if (twitter == ""){
            return
        }
        IntentClass.goToLink(activity,twitter)
    }
    fun linkinclisk(){
        if (linkidin == ""){
            return
        }
        IntentClass.goToLink(activity,linkidin)
    }
    fun whatsclisk(){
        if (whats == ""){
            return
        }
        IntentClass.goToLink(activity,"https://wa.me/" + whats)
    }
}
