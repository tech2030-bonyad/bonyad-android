package fudex.bonyad.viewmodel.user

import android.annotation.SuppressLint
import android.app.Activity
import android.util.Log
import android.view.Gravity
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.adapters.TextViewBindingAdapter
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Ratingdata
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.ui.Fragment.user.RatingdialogFragment
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RatingdialogViewModel(var catogaryFragment: RatingdialogFragment) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: RatingdialogFragment = RatingdialogFragment()
    var activity = Activity()
    val commentObserv = ObservableField<String>()
    @SuppressLint("RestrictedApi")
    fun oncommentChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            commentObserv.set(s.toString())
        }
    }

    init {
        this.context = catogaryFragment
        activity = context.requireActivity()
        if (activity.getString(R.string.lang) == "ar"){
            context.binding.comment.gravity = Gravity.RIGHT
        }
        context.binding.main.setOnClickListener {
            Utilities.closeKeyboard(activity)
        }
    }
    fun dismiss(){
        context.dialogListener?.onDataReceived("rate")
        context.dismiss()
    }

    fun validateInput() {
        var error = false
        if (commentObserv.get() == null || commentObserv.get()!!.isEmpty()) {
            error = true
            context.binding.comment.setError(activity.getString(R.string.required))
        }
        var rating = context.binding.rate.rating.toInt()
        if (rating == 0){
            Dialogs.showToast(context.getString(R.string.must_male_rate) , activity!!)
            return
        }
        if (!error) {
             ratetrip()
        }


    }
    fun ratetrip() {
        Utilities.disabletouch(activity)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java
        )
        var ratedata = Ratingdata( context.arguments?.getInt("id").toString(), context.arguments?.getString("type"), context.binding.rate.rating.toInt().toString(),commentObserv.get())
        val call: Call<ErrorResponse?>? =
           apiService.rateuser(ratedata)
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(
                call: Call<ErrorResponse?>,
                response: Response<ErrorResponse?>
            ) {
                if (response.code() == 200 || response.code() == 201) {
                    context.dialogListener?.onDataReceived("rate")
                    context.dismiss()
//                    Utilities.showSuccessDialog(activity, response.body()!!.message ?: "" ,"" ){
//                        context.dialogListener?.onDataReceived("rate")
//                        context.dismiss()
//                    }
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
                                ratetrip()
                            }
                        })
                }
                isloading.set(false)
                Utilities.enabletouch(activity)

            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection), activity)
                isloading.set(false)
                Utilities.enabletouch(activity)
            }
        })
    }
}