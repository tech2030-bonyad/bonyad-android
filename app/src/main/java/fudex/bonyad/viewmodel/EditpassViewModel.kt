package fudex.bonyad.viewmodel

import android.annotation.SuppressLint
import android.graphics.Color
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
import fudex.bonyad.Data.Editpass
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.EditpassActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EditpassViewModel(activity: EditpassActivity) : BaseObservable() {
    // observable fields on the
    var activity: EditpassActivity = EditpassActivity()
    val oldpasswordObserv = ObservableField<String>()
    val passwordObserv = ObservableField<String>()
    val confirmpasswordObserv = ObservableField<String>()
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    var color = ObservableField<Int>(Color.parseColor("#E51D35"))
    @SuppressLint("RestrictedApi")
    fun onoldpasswordChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            oldpasswordObserv.set(s.toString())
        }
    }
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
            activity.binding.confirmPass.gravity = Gravity.RIGHT
            activity.binding.oldpass.gravity = Gravity.RIGHT
        }
        //changecolor()
    }


    fun validateInput() {
        var error = false
        if (oldpasswordObserv.get() == null || oldpasswordObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.oldpass.setError(activity.getString(R.string.required))
        }
        if (passwordObserv.get() == null || passwordObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.pass.setError(activity.getString(R.string.required))
        }
        if (confirmpasswordObserv.get() == null || confirmpasswordObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.confirmPass.setError(activity.getString(R.string.required))
        }
        if (confirmpasswordObserv.get() != null && confirmpasswordObserv.get()!!.isNotEmpty() && passwordObserv.get() != null && passwordObserv.get()!!.isNotEmpty()) {
            if (confirmpasswordObserv.get() != passwordObserv.get()) {
                error = true
                activity.binding.confirmPass.setError(activity.getString(R.string.confirm_password_must_same_password))
            }
        }
        if (!error) {
            Utilities.closeKeyboard(activity)
            editpassclick()
        }
    }

    fun back(){
        activity.onBackPressed()
    }
    fun editpassclick(){
        Utilities.disabletouch(activity)
        isenable.set(false)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var editpass = Editpass(oldpasswordObserv.get(),passwordObserv.get(),passwordObserv.get())

        val call: Call<ErrorResponse?>? = apiService.editpass(editpass)
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
                            editpassclick()
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
    fun oldpassclick(){
        if (activity.binding.oldpass.inputType == InputType.TYPE_CLASS_TEXT){
            activity.binding.oldeyePass.setBackgroundResource(R.drawable.ic_mdi_eye_off_outline)
            activity.binding.oldpass.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        }else {
            activity.binding.oldpass.setInputType(InputType.TYPE_CLASS_TEXT)
            activity.binding.oldeyePass.setBackgroundResource(R.drawable.eye)
        }
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
        if (activity.binding.confirmPass.inputType == InputType.TYPE_CLASS_TEXT){
            activity.binding.eyeConfirm.setBackgroundResource(R.drawable.ic_mdi_eye_off_outline)
            activity.binding.confirmPass.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        }else {
            activity.binding.confirmPass.setInputType(InputType.TYPE_CLASS_TEXT)
            activity.binding.eyeConfirm.setBackgroundResource(R.drawable.eye)
        }
    }

}
