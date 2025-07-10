package fudex.bonyad.viewmodel

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.EditpassActivity

import fudex.bonyad.ui.Activity.EditphoneActivity

import fudex.bonyad.ui.Activity.ProfileActivity
import fudex.bonyad.ui.Activity.technical.EdittechnicaldataActivity
import fudex.bonyad.ui.Activity.user.EdituserdataActivity
import fudex.bonyad.Model.ProfileModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileViewModel(var catogaryFragment: ProfileActivity) : BaseObservable(){
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: ProfileActivity = ProfileActivity()
    private var mLoading = false
    var userdata = ObservableField<ProfileModel>()
    var img = ObservableField<String>("")
    var color = ObservableField<Int>(Color.parseColor("#E51D35"))
    var activity = Activity()
    init {
        this.context = catogaryFragment
        activity = catogaryFragment
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
                    img.set(data?.data?.avatar!!)
                    Log.e("data" , img.get()!!)
                    userdata.set(data)
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
                Dialogs.showToast(context.getString(R.string.check_your_connection) , activity)
                isloading.set(false)

            }
        })
    }
    fun deleteaccount() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)

        val call: Call<ErrorResponse?>? = apiService.deleteaccount()
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                    Dialogs.showToast(response.body()!!.message!!,activity)
                    LoginSession.clearData(activity)
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            deleteaccount()
                        }
                    })
                }

                isloading.set(false)
            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(context.getString(R.string.check_your_connection) , activity)
                isloading.set(false)

            }
        })
    }
    fun logout(){
//        var fragment = LogoutFragment()
//        fragment.show(context.supportFragmentManager , "delete")
    }
    fun deletePop(){
        val builder = AlertDialog.Builder(activity)
        builder.setMessage(context.getString(R.string.delete_account))
            .setCancelable(false)
            .setPositiveButton(
                context.getString(R.string.yes),
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, id: Int) {
                        deleteaccount()
                        dialog.cancel()
                    }
                })
            .setNegativeButton(
                context.getString(R.string.no),
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, id: Int) {
                        dialog.cancel()
                    }
                })
        val alert = builder.create()
        alert.show()
    }
    fun editphone(){
        var intent: Intent = Intent(context, EditphoneActivity::class.java)
        context.startActivity(intent)
    }

    fun editpass(){
        var intent: Intent = Intent(context, EditpassActivity::class.java)
        context.startActivity(intent)
    }
    fun editprofile(){
        if (LoginSession.gettype(context) == 1){
            var intent: Intent = Intent(context, EdituserdataActivity::class.java)
            context.startActivity(intent)
        }else if (LoginSession.gettype(context) == 2){
            var intent: Intent = Intent(context, EdituserdataActivity::class.java)
            context.startActivity(intent)
        }else if (LoginSession.gettype(context) == 3){
            var intent: Intent = Intent(context, EdittechnicaldataActivity::class.java)
            context.startActivity(intent)
        }
    }

    fun back() {
        context.onBackPressed()
    }
}