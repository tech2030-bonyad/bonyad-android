package fudex.bonyad.viewmodel

import android.annotation.SuppressLint
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
import fudex.bonyad.Data.Visadata
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.IntentClass
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Helper.Validations
import fudex.bonyad.Model.ContactusModel
import fudex.bonyad.Model.ProfileModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.AddaccountbankActivity
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.*


class AddaccountbankViewModel(activity: AddaccountbankActivity) : BaseObservable() {

    // observable fields on the
    var activity: AddaccountbankActivity = AddaccountbankActivity()
    val holdnameObserv = ObservableField<String>()
    val banknameObserv = ObservableField<String>()
    val ibanObserv = ObservableField<String>()
    val accountnumObserv = ObservableField<String>()
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)

    @SuppressLint("RestrictedApi")
    fun onholdnameChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->

            holdnameObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun onbanknameChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            banknameObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun onibanhanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            ibanObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun onaccountnumhanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            accountnumObserv.set(s.toString())
        }
    }


    init {
        this.activity = activity
        if (activity.getString(R.string.lang) == "ar"){
            activity.binding.holdname.gravity = Gravity.RIGHT
            activity.binding.iban.gravity = Gravity.RIGHT
            activity.binding.accountnum.gravity = Gravity.RIGHT
            activity.binding.bankame.gravity = Gravity.RIGHT

        }
        if (LoginSession.isLogin){
           getuserinfo()
        }
        activity.binding.main.setOnClickListener {
            Utilities.closeKeyboard(activity)
        }

    }


    fun validateInput() {
        var error = false
        if (accountnumObserv.get() == null || accountnumObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.accountnum.setError(activity.getString(R.string.required))
        }
        if (banknameObserv.get() == null || banknameObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.bankame.setError(activity.getString(R.string.required))
        }
        if (ibanObserv.get() == null || ibanObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.iban.setError(activity.getString(R.string.required))
        }

        if (holdnameObserv.get() == null || holdnameObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.holdname.setError(activity.getString(R.string.required))
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
        val call: Call<ProfileModel?>? = apiService.editvisa(Visadata(holdnameObserv.get() ?: "", banknameObserv.get() ?: "",ibanObserv.get() ?: "" , accountnumObserv.get() ?: ""))
        call?.enqueue(object : Callback<ProfileModel?> {
            override fun onResponse(call: Call<ProfileModel?>, response: Response<ProfileModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = LoginSession.getUserData1(activity)
                    data.user == response.body()!!.data
                    LoginSession.setUserData(activity, data)
                    LoginSession.setdata(activity)
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

            override fun onFailure(call: Call<ProfileModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                isenable.set(true)
                Utilities.enabletouch(activity)
            }
        })
    }
    fun getuserinfo() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)

        val call: Call<ProfileModel?>? = apiService.getprofiledata()
        call?.enqueue(object : Callback<ProfileModel?> {
            override fun onResponse(call: Call<ProfileModel?>, response: Response<ProfileModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    if (data?.data?.avatar == null){
                        data?.data?.avatar = ""
                    }
                    activity.binding.accountnum.setText(data?.data?.account_number)
                    activity.binding.bankame.setText(data?.data?.bank_name)
                    activity.binding.iban.setText(data?.data?.iban)
                    activity.binding.holdname.setText(data?.data?.account_name)
                    accountnumObserv.set(data?.data?.account_number)
                    banknameObserv.set(data?.data?.bank_name)
                    ibanObserv.set(data?.data?.iban)
                    holdnameObserv.set(data?.data?.account_name)
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getuserinfo()
                        }
                    })
                }

                isloading.set(false)
            }

            override fun onFailure(call: Call<ProfileModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)

            }
        })
    }
}
