package fudex.bonyad.viewmodel.technical

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.UserModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.LoginActivity
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Fragment.technical.TechnicalhomeFragment
import fudex.bonyad.ui.Fragment.technical.TechnicalprofileFragment
import fudex.bonyad.ui.Fragment.technical.TechnicalservicesFragment
import fudex.bonyad.ui.Fragment.user.UserhomeFragment
import fudex.bonyad.ui.Fragment.user.UserprofileFragment
import okhttp3.MultipartBody
import okhttp3.RequestBody
import fudex.bonyad.Model.ProfileModel
import fudex.bonyad.ui.Fragment.technical.TechnicalappointmentFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TechnicalhomeViewModel(var catogaryFragment: TechnicalHomeActivity) : BaseObservable() {
    var activity: TechnicalHomeActivity = TechnicalHomeActivity()
    var fragment: TechnicalhomeFragment = TechnicalhomeFragment()
    var type: ObservableField<Int> = ObservableField(0);
    val draw = ObservableField<String>("0")
    var userdata = ObservableField<UserModel>()
    var img = ObservableField<String>("")
    var not = ObservableField<Boolean>(true)
    var color = ObservableField<Int>(Color.parseColor("#E51D35"))

    init {
        this.activity = catogaryFragment
        replaceFragment(fragment)

        if (LoginSession.isLogin){
            img.set(LoginSession.getUserData(activity).user?.avatar ?: "")
            userdata.set(LoginSession.getUserData(activity).user)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (ContextCompat.checkSelfPermission(activity, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, arrayOf(Manifest.permission.POST_NOTIFICATIONS), 1)
                }
            }
        }

//        if (LoginSession.isLogin){

        if (LoginSession.isLogin){
           // changelang()
        }
    }
    fun replaceFragment(fragment: Fragment) {
        try {
            val backStateName = fragment.javaClass.name
            val manager = activity.supportFragmentManager
            val fragmentPopped = manager.popBackStackImmediate(backStateName, 0)

            if (!fragmentPopped) { //fragment not in back stack, create it.
                val ft = manager.beginTransaction()
                ft.replace(R.id.fram, fragment)
                ft.addToBackStack(backStateName)
                ft.commit()
            }
        } catch (exception: Exception) {

        }


    }

    fun home() {
        if (type.get()!= 0){
            fragment = TechnicalhomeFragment()
            replaceFragment(fragment)
            type.set(0)
        }
    }
    fun appointment() {
        if (type.get()!= 1){
            if (LoginSession.isLogin){
                var fragment = TechnicalappointmentFragment()
                replaceFragment(fragment)
                type.set(1)
            }else {
                var intent: Intent = Intent(activity, LoginActivity::class.java)
                activity.startActivity(intent)
            }
        }
    }
    fun orders() {
        if (type.get()!= 2){
           if (LoginSession.isLogin){
               var fragment = TechnicalservicesFragment()
               replaceFragment(fragment)
               type.set(2)
           }else {
               var intent: Intent = Intent(activity, LoginActivity::class.java)
               activity.startActivity(intent)
           }
        }
    }
    fun proofile() {
        if (type.get()!= 3) {
            var fragment = TechnicalprofileFragment()
            replaceFragment(fragment)
            type.set(3)
        }
    }

    fun onBack(fragment: String) {
        if (fragment.contains("HomeFragment")) {
            type.set(0)
        } else if (fragment.contains("ProfileFragment")) {
            type.set(1)
        } else if (fragment.contains("MoreFragment")) {
            type.set(2)
        }
    }

    fun logout(){
        draw.set("0")
        notifyChange()
        Utilities.disabletouch(activity)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var requestBody: RequestBody? = null
        requestBody =  MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("device_id",Utilities.getDeviceId(activity))
            .build()
        val call: Call<ErrorResponse?>? = apiService.logout(requestBody)
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {
                    LoginSession.clearData(activity)
                }else {
                    val errorText = response.errorBody()?.string() ?:"{}"
                    Log.e("data",errorText!!)
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            logout()
                        }
                    })
                }

                Utilities.enabletouch(activity)

            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                Utilities.enabletouch(activity)
            }
        })
    }

    fun getuserinfo() {
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)

        val call: Call<ProfileModel?>? = apiService.getprofiledata()
        call?.enqueue(object : Callback<ProfileModel?> {
            override fun onResponse(call: Call<ProfileModel?>, response: Response<ProfileModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    if (data?.data?.photo == null){
                        data?.data?.photo = ""
                    }
                    img.set(data?.data?.photo!!)
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
    fun changelang(){
        Utilities.disabletouch(activity)

        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var requestBody: RequestBody? = null
        requestBody =  MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("lang", activity.getString(R.string.lang))
            .addFormDataPart("device_id", Utilities.getDeviceId(activity))
            .build()
        val call: Call<ErrorResponse?>? = apiService.changelang(requestBody)
        call?.enqueue(object : Callback<ErrorResponse?> {
            override fun onResponse(call: Call<ErrorResponse?>, response: Response<ErrorResponse?>) {
                if (response.code() == 200 || response.code() == 201) {

                }else {
                    val errorText = response.errorBody()?.string()
                    Log.e("data",errorText!!)
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            changelang()
                        }
                    })
                }

                Utilities.enabletouch(activity)

            }

            override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)

                Utilities.enabletouch(activity)
            }
        })
    }
}