package fudex.bonyad.Apimodel

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.RequestParams
import com.loopj.android.http.TextHttpResponseHandler
import fudex.bonyad.Apimodel.APIModel.FORCE_UPDATE
import fudex.bonyad.R
import cz.msebera.android.httpclient.Header
import fudex.bonyad.BuildConfig
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Model.LoginData
import fudex.bonyad.SharedPreferences.LoginSession
import java.io.IOException
import java.lang.reflect.Type

object APIModel {
    const val take = 10
    var BASE_URL = BuildConfig.BASE_URL
    var INTEGRATION_URL = "https://api-football-v1.p.rapidapi.com/v3/"
    var isproduction = false
//    val BASE_URL = "http://whereapp.kixedo.com/api/"
//    val BASE_URL = "https://apis.whereappco.com/"

    //    public final static String BASE_URL = "https://onnety-solutions.com/laeq/api/";
    // when app version is old
    const val FORCE_UPDATE = 451

    // when user blocked
    const val BLOCK = 456

    // when token expired
    const val REFRESH_TOKEN = 401
    const val SUCCESS = 200
    const val CREATED = 201
    const val Failer = 422
    const val BAD_REQUEST = 400
    const val UNAUTHORIZE = 401
    const val SERVER_ERROR = 500
    const val USER_DELETE = 403
    const val Not_FOUND = 404
    const val Not_RESPONSE = 0

    const val Error = 409
    var version = "v1"
    var device_type = "1"
    var count = 0
    fun handleFailure(
        activity: Context,
        statusCode: Int,
        errorResponse: String,
        listener: RefreshTokenListener
    ) {
        Log.e("fail", "$statusCode--$errorResponse")
        val dataType: Type = object : TypeToken<ErrorResponse?>() {}.getType()
        var responseBody = ErrorResponse()
        try {
            if (statusCode != SERVER_ERROR) responseBody = Gson().fromJson(errorResponse, dataType)
        } catch (e: Exception) {
        }
        when (statusCode) {
            BAD_REQUEST ->  try {
                Log.e("data" , responseBody.errors.toString())
                Dialogs.showToast(
                    if (responseBody.message != null) responseBody.message!! else "",
                    activity
                )
            }catch (e:Exception){
                Dialogs.showToast(
                    if (responseBody.message != null) responseBody.message!! else "",
                    activity
                )
            }
            Not_FOUND -> {
                try {
                    if (responseBody.errors.toString().isNullOrEmpty()) {
                        Dialogs.showToast(
                            if (responseBody.message != null) responseBody.message!! else "",
                            activity
                        )
                    } else {
                        Dialogs.showToast(responseBody.getMsg(), activity)
                    }
                }catch (e:Exception){
                    Dialogs.showToast(
                        if (responseBody.message != null) responseBody.message!! else "",
                        activity
                    )
                }
                (activity as Activity).finish()
            }
            Failer -> try {
                if (responseBody.errors.toString().isNullOrEmpty()) {
                    Dialogs.showToast(
                        if (responseBody.message != null) responseBody.message!! else "",
                        activity
                    )
                } else {
                    Dialogs.showToast(responseBody.getMsg(), activity)
                }
            }catch (e:Exception){
                Dialogs.showToast(
                    if (responseBody.message != null) responseBody.message!! else "",
                    activity
                )
            }
            Error -> try {
                if (responseBody.errors.toString().isNullOrEmpty()) {
                    Dialogs.showToast(
                        if (responseBody.message != null) responseBody.message!! else "",
                        activity
                    )
                } else {
                    Dialogs.showToast(responseBody.getMsg(), activity)
                }
            }catch (e:Exception){
                Dialogs.showToast(
                    if (responseBody.message != null) responseBody.message!! else "",
                    activity
                )
            }
            USER_DELETE -> LoginSession.clearData1(activity)
            409 -> LoginSession.activeuser(activity)

            REFRESH_TOKEN -> try {
                Log.e("data" , responseBody.errors.toString())
                if (LoginSession.isLogin){
                    LoginSession.clearData1(activity)
                }else {
                    Dialogs.showToast(
                        if (responseBody.message != null) responseBody.message!! else "",
                        activity
                    )
                }
            }catch (e:Exception){
                if (LoginSession.isLogin){
                    LoginSession.clearData1(activity)
                }else {
                    Dialogs.showToast(
                        if (responseBody.message != null) responseBody.message!! else "",
                        activity
                    )
                }

            }

            Not_RESPONSE -> {
//                if (count >= 2){
                    Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
//                    count  = 0
//                }else {
//                    listener.onRefresh();
//                    count = count + 1
//                }

            }
            else -> Toast.makeText(activity, activity.getString(R.string.failed_to_connect_to_server_please_repeat_your_requesr), Toast.LENGTH_SHORT).show()
        }
    }
    fun handleFailure1(
        activity: Context,
        statusCode: Int,
        errorResponse: ErrorResponse,
        listener: RefreshTokenListener
    ) {
        Log.e("fail", "$statusCode--$errorResponse")
        val dataType: Type = object : TypeToken<ErrorResponse?>() {}.getType()
        var responseBody = errorResponse
        when (statusCode) {
            BAD_REQUEST ->  try {
                Log.e("data" , responseBody.errors.toString())
                Dialogs.showToast(
                    if (responseBody.message != null) responseBody.message!! else "",
                    activity
                )
            }catch (e:Exception){
                Dialogs.showToast(
                    if (responseBody.message != null) responseBody.message!! else "",
                    activity
                )
            }
            429 ->  try {
                Log.e("data" , responseBody.errors.toString())
                Dialogs.showToast(
                    if (responseBody.message != null) responseBody.message!! else "",
                    activity
                )
            }catch (e:Exception){
                Dialogs.showToast(
                    if (responseBody.message != null) responseBody.message!! else "",
                    activity
                )
            }
            426 ->  try {
                Log.e("data" , responseBody.errors.toString())
               LoginSession.forceupdate(activity, if (responseBody.message != null) responseBody.message!! else "")
            }catch (e:Exception){
                Dialogs.showToast(
                    if (responseBody.message != null) responseBody.message!! else "",
                    activity
                )
            }
            503 ->  LoginSession.maintaninece(activity)
            Not_FOUND -> {
                try {
                    if (responseBody.errors.toString().isNullOrEmpty()) {
                        Dialogs.showToast(
                            if (responseBody.message != null) responseBody.message!! else "",
                            activity
                        )
                    } else {
                        Dialogs.showToast(responseBody.getMsg(), activity)
                    }
                }catch (e:Exception){
                    Dialogs.showToast(
                        if (responseBody.message != null) responseBody.message!! else "",
                        activity
                    )
                }
                (activity as Activity).finish()
            }
            Failer -> try {
                if (responseBody.errors.toString().isNullOrEmpty() || responseBody.errors == null) {
                    Dialogs.showToast(
                        if (responseBody.message != null) responseBody.message!! else "",
                        activity
                    )
                } else {
                    Dialogs.showToast(responseBody.getMsg(), activity)
                }
            }catch (e:Exception){
                Dialogs.showToast(
                    if (responseBody.message != null) responseBody.message!! else "",
                    activity
                )
            }
            Error -> try {
                if (responseBody.errors.toString().isNullOrEmpty()) {
                    Dialogs.showToast(
                        if (responseBody.message != null) responseBody.message!! else "",
                        activity
                    )
                } else {
                    Dialogs.showToast(responseBody.getMsg(), activity)
                }
            }catch (e:Exception){
                Dialogs.showToast(
                    if (responseBody.message != null) responseBody.message!! else "",
                    activity
                )
            }
            USER_DELETE -> LoginSession.clearData1(activity)
            409 -> LoginSession.activeuser(activity)

            REFRESH_TOKEN -> try {
                Log.e("data" , responseBody.errors.toString())
                if (LoginSession.isLogin){
                    LoginSession.clearData1(activity)
                }else {
                    Dialogs.showToast(
                        if (responseBody.message != null) responseBody.message!! else "",
                        activity
                    )
                }
            }catch (e:Exception){
                if (LoginSession.isLogin){
                    LoginSession.clearData1(activity)
                }else {
                    Dialogs.showToast(
                        if (responseBody.message != null) responseBody.message!! else "",
                        activity
                    )
                }

            }
            Not_RESPONSE -> {
                if (count >= 2){
                    Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                    count  = 0
                }else {
                    listener.onRefresh();
                    count = count + 1
                }

            }
            else -> Toast.makeText(activity, activity.getString(R.string.failed_to_connect_to_server_please_repeat_your_requesr), Toast.LENGTH_SHORT).show()
        }
    }
    fun userResponses(activity: Activity?, code: Int) {
        when (code) {
            FORCE_UPDATE -> {}
            BLOCK -> {}
            REFRESH_TOKEN -> //                SharedPref sharedPref = new SharedPref(activity);
//  sharedPref.setToken(response.body().getTokenResponse().getToken());
                try {
//                    func.notify();
                } catch (e: Exception) {
                }
        }
    }
    fun refreshtoken(currentActivity: Context , listener : RefreshTokenListener){
        var params = RequestParams()
        try {
            params.put("refresh_token", LoginSession.getUserData(currentActivity!!).refresh_token)
        }catch (e:Exception){

        }
        APIModel.postMethod2(currentActivity, "driver/token/refresh", params, object : TextHttpResponseHandler() {
            override fun onFailure(statusCode: Int, headers: Array<Header>?, responseString: String?, throwable: Throwable) {
                LoginSession.clearData1(currentActivity)
            }

            override fun onSuccess(statusCode: Int, headers: Array<Header>, responseString: String) {
                Log.e("url", responseString)

//                    try {
                val dataType = object : TypeToken<LoginData>() {

                }.type
                val data = Gson().fromJson<LoginData>(responseString, dataType)
                data.user = LoginSession.setdata(currentActivity)
                LoginSession.setUserData1(currentActivity, data)
                LoginSession.isLogin = true
                listener.onRefresh();
//                  }catch (e:Exception){
//
//                  }

            }
        })

//        }catch (e: Exception){
////            LoginSession.clearData1(currentActivity)
//        }

    }
    fun getMethod(
        currentActivity: Activity,
        url: String,
        textHttpResponseHandler: TextHttpResponseHandler?
    ): AsyncHttpClient {
        val client = AsyncHttpClient()
        if (isConnected()){
            client.setTimeout(30000)
            client.maxConnections = 1
            client.addHeader("version", version + "")
            client.addHeader("Lang", currentActivity.getString(R.string.lang))
            client.addHeader("Accept", "application/json")
            client.addHeader("Accept-Language", currentActivity?.getString(R.string.lang))
            client.addHeader("Device-Platform", "android")
            try {
                client.addHeader(
                    "Authorization",
                    "Bearer " + LoginSession.getUserData(currentActivity!!).token
                )
            } catch (e: Exception) {

            }
            client.get(BASE_URL + url, textHttpResponseHandler)
            Log.e("url", BASE_URL + url)
        }else {

        }
        return client

    }
    fun getMethod1(
        currentActivity: Activity,
        url: String,
        textHttpResponseHandler: TextHttpResponseHandler?
    ): AsyncHttpClient {
        val client = AsyncHttpClient()
        if (isConnected()) {
            client.setTimeout(30000)
            client.maxConnections = 1
            client.addHeader("version", version + "")
            client.addHeader("Lang", currentActivity.getString(R.string.lang))
            client.addHeader("Accept", "application/json")
            client.addHeader("Accept-Language", currentActivity?.getString(R.string.lang))
            client.addHeader("Device-Platform", "android")
            try {
                client.addHeader(
                    "Authorization",
                    "Bearer " + LoginSession.getUserData(currentActivity!!).token
                )
            } catch (e: Exception) {

            }
            client.get(url, textHttpResponseHandler)
            Log.e("url", url)
        }else {

        }
        return client
    }
    fun postMethod(
        currentActivity: Activity,
        url: String,
        params: RequestParams,
        textHttpResponseHandler: TextHttpResponseHandler?
    ): AsyncHttpClient {
        val client = AsyncHttpClient()
        if (isConnected()) {
            client.setTimeout(500000)
            client.maxConnections = 1
            client.addHeader("version", version + "")
            client.addHeader("Lang", currentActivity.getString(R.string.lang))
            client.addHeader("Device-Platform", "android")

            if (params.toString().contains("cv") || params.toString().contains("image")) {
//            client.addHeader("Connection", "Keep-Alive");
//
//            try {
//                StringEntity entity = new StringEntity(params.toString());
//                client.addHeader("Content-Length", String.valueOf(entity.getContentLength()));
//
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
            } else {
            }
            client.addHeader("Accept", "application/json")
            client.addHeader("Accept-Language", currentActivity?.getString(R.string.lang))
            try {
                client.addHeader(
                    "Authorization",
                    "Bearer " + LoginSession.getUserData(currentActivity!!).token
                )
            } catch (e: Exception) {

            }
            client.post(BASE_URL + url, params, textHttpResponseHandler)
            Log.e("url", BASE_URL + url)
        }else {

        }
        return client
    }
    fun postMethod1(
        currentActivity: Context,
        url: String,
        params: RequestParams?,
        textHttpResponseHandler: TextHttpResponseHandler?
    ): AsyncHttpClient? {
        val client = AsyncHttpClient()
        if (isConnected()) {
            client.setTimeout(50000000)
            client.maxConnections = 1
            client.addHeader("version", version + "")
            client.addHeader("Lang", currentActivity.getString(R.string.lang))
            client.addHeader("Accept", "application/json")
            client.addHeader("Accept-Language", currentActivity?.getString(R.string.lang))
            client.addHeader("Device-Platform", "android")
            try {
                client.addHeader(
                    "Authorization",
                    "Bearer " + LoginSession.getUserData(currentActivity!!).token
                )
            } catch (e: Exception) {

            }
            client.post(BASE_URL + url, params, textHttpResponseHandler)
            Log.e("url", BASE_URL + url)
        }else {

        }
        return client
    }

    fun postMethod2(
        currentActivity: Context,
        url: String,
        params: RequestParams?,
        textHttpResponseHandler: TextHttpResponseHandler?
    ): AsyncHttpClient? {
        val client = AsyncHttpClient()
        if (isConnected()) {
            client.setTimeout(50000000)
            client.maxConnections = 1
            client.addHeader("version", version + "")
            client.addHeader("Lang", currentActivity.getString(R.string.lang))
            client.addHeader("Accept", "application/json")
            client.addHeader("Accept-Language", currentActivity?.getString(R.string.lang))
            client.addHeader("Device-Platform", "android")

            client.post(BASE_URL + url, params, textHttpResponseHandler)
            Log.e("url", BASE_URL + url)

        }else {

        }
        return client
    }
    fun putMethod(
        currentActivity: Activity,
        url: String,
        params: RequestParams?,
        textHttpResponseHandler: TextHttpResponseHandler?
    ): AsyncHttpClient {
        val client = AsyncHttpClient()
        if (isConnected()) {
            client.setTimeout(30000)
            client.maxConnections = 1
            client.addHeader("version", version + "")
            client.addHeader("Lang", currentActivity.getString(R.string.lang))
            client.addHeader("Content-Type", "application/x-www-form-urlencoded")
            client.addHeader("Accept", "application/json")
            client.addHeader("Accept-Language", currentActivity?.getString(R.string.lang))
            client.addHeader("Device-Platform", "android")
            try {
                client.addHeader(
                    "Authorization",
                    "Bearer " + LoginSession.getUserData(currentActivity!!).token
                )
            } catch (e: Exception) {

            }
            var loginFile: SharedPreferences
            client.put(BASE_URL + url, params, textHttpResponseHandler)
            Log.e("url", BASE_URL + url)

        }else {

        }
        return client
    }

    fun deleteMethod(
        currentActivity: Activity,
        url: String,
        textHttpResponseHandler: TextHttpResponseHandler?
    ): AsyncHttpClient {
        val client = AsyncHttpClient()
        if (isConnected()) {
            client.setTimeout(30000)
            client.maxConnections = 1
            client.maxConnections = 1
            client.addHeader("version", version + "")
            client.addHeader("Content-Type", "application/x-www-form-urlencoded")
            client.addHeader("Device-Platform", "android")
            try {
                client.addHeader(
                    "Authorization",
                    "Bearer " + LoginSession.getUserData(currentActivity!!).token
                )
            } catch (e: Exception) {

            }
            client.addHeader("Accept", "application/json")
            client.addHeader("Accept-Language", currentActivity?.getString(R.string.lang))
            client.addHeader("Lang", currentActivity.getString(R.string.lang))
            client.delete(BASE_URL + url, textHttpResponseHandler)
        }else {

        }
        return client
    }

    fun tokenPost(
        currentActivity: Activity,
        url: String,
        params: RequestParams?,
        textHttpResponseHandler: TextHttpResponseHandler?
    ): AsyncHttpClient {
        val client = AsyncHttpClient()
        if (isConnected()) {
            client.setTimeout(30000)
            client.maxConnections = 1
            client.addHeader("version", version + "")
            client.addHeader("Content-Type", "application/x-www-form-urlencoded")
            client.addHeader("Device-Platform", "android")
            try {
                client.addHeader(
                    "Authorization",
                    "Bearer " + LoginSession.getUserData(currentActivity!!).token
                )
            } catch (e: Exception) {

            }
            client.addHeader("Accept", "application/json")
            client.addHeader("Accept-Language", currentActivity?.getString(R.string.lang))
            client.addHeader("Lang", currentActivity.getString(R.string.lang))
            client.post(BASE_URL + url, params, textHttpResponseHandler)
            Log.e("url", BASE_URL + url)

        }else {

        }
        return client
    }

    interface RefreshTokenListener {
        fun onRefresh()
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    fun foregrounded(): Boolean {
        val appProcessInfo = ActivityManager.RunningAppProcessInfo()
        ActivityManager.getMyMemoryState(appProcessInfo)
        return appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND || appProcessInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE
    }
    @Throws(InterruptedException::class, IOException::class)
    fun isConnected(): Boolean {
        val command = "ping -c 1 google.com"
//        return Runtime.getRuntime().exec(command).waitFor() == 0
        return  true
    }
    @Throws(InterruptedException::class, IOException::class)
    fun isConnected1(): Boolean {
        val command = "ping -c 1 google.com"
        return Runtime.getRuntime().exec(command).waitFor() == 0
    }
}