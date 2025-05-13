package fudex.bonyad.viewmodel.user

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Handler
import android.util.Log
import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.UserModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.ChangelangueActivity

import fudex.bonyad.ui.Activity.ContactusActivity

import fudex.bonyad.ui.Activity.LoginActivity
import fudex.bonyad.ui.Activity.ProfileActivity
import fudex.bonyad.ui.Activity.StaticpageActivity
import fudex.bonyad.ui.Fragment.user.UserprofileFragment

import onnetysolutions.sadded.Model.ProfileModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserprofileViewModel(var catogaryFragment: UserprofileFragment) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: UserprofileFragment = UserprofileFragment()
    var activity = Activity()
    var img = ObservableField<String>("")
    var islogin = ObservableField<Boolean>(false)
    var userdata = ObservableField<UserModel>()

    init {
        this.context = catogaryFragment
        activity = context.requireActivity()
        islogin.set(LoginSession.isLogin)
    }
    fun about(){
        var intent: Intent = Intent(activity, StaticpageActivity::class.java)
        intent.putExtra("type",1)
        activity.startActivity(intent)
    }
    fun terms(){
        var intent: Intent = Intent(activity, StaticpageActivity::class.java)
        intent.putExtra("type",2)
        activity.startActivity(intent)
    }
    fun privacy(){
        var intent: Intent = Intent(activity, StaticpageActivity::class.java)
        intent.putExtra("type",3)
        activity.startActivity(intent)
    }

    fun contactusus(){
        var intent: Intent = Intent(activity, ContactusActivity::class.java)
        activity.startActivity(intent)
    }

    fun settings(){
        var intent: Intent = Intent(activity, ChangelangueActivity::class.java)
        activity.startActivity(intent)
    }
    fun profile(){
        var intent: Intent = Intent(activity, ProfileActivity::class.java)
        activity.startActivity(intent)
    }
    fun logout(){
//        var fragment = LogoutFragment()
//        fragment.show((activity as HomeActivity).supportFragmentManager , "logout")
    }
    fun getuserinfo() {
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
                    userdata.set(data?.data)
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getuserinfo()
                        }
                    })
                }

            }

            override fun onFailure(call: Call<ProfileModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
            }
        })
    }
}