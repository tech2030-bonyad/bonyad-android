package fudex.bonyad.SharedPreferences

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import fudex.bonyad.Model.AddressesDatum
import fudex.bonyad.Model.LoginData
import fudex.bonyad.Model.UserModel
import fudex.bonyad.ui.Activity.ActiveuserActivity
import fudex.bonyad.ui.Activity.LoginActivity

import fudex.bonyad.Model.LoginModel


/**
 * Created by ATIAF on 3/14/2018.
 */

object LoginSession {
    val USER_DATA_KEY = "userData"
    private val USER_COUNTRY_KEY = "usercountry"
    private val USER_ONBOARDING_KEY = "useronboarding"

    private val IS_LOGIN_KEY = "isLogin"
    private val ACCESS_TOKEN_KEY = "accessTokenKey"
    private val EXPIRE_KEY = "expireKey"
    private val USER_TYPE_KEY = "userTypeKey"
    private val USER_LOCATION_KEY = "userLocationKey"

    private val NOTIFICATION_COUNT_KEY = "notificationCountKey"
    private val NEWS_COUNT_KEY = "newsCountKey"
    private val SELECTED_CITIES_KEY = "selectedCitiesKey"
    private val NOTIFICATION = "notification"

    var isLogin = false
    var lat: Double? = null
    var lng: Double? = null
    var token = ""
    var refresh_token = ""
    var expired: Int = 0

    var loginFile: SharedPreferences? = null
    private var countryFile: SharedPreferences? = null
    private var onboardingFile: SharedPreferences? = null


    private fun initLoginSharedPreference(context: Context) {
        loginFile = context.getSharedPreferences("loginFile", Context.MODE_PRIVATE)
    }
    private fun initcountrySharedPreference(context: Context) {
        countryFile = context.getSharedPreferences("address", Context.MODE_PRIVATE)
    }
    private fun initonboardingSharedPreference(context: Context) {
        countryFile = context.getSharedPreferences("onboardingFile", Context.MODE_PRIVATE)
    }
    fun setUserData(activity: Activity, user: LoginData) {
        initLoginSharedPreference(activity)
        val editor = loginFile!!.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        editor.putString(USER_DATA_KEY, json)
        editor.putBoolean(IS_LOGIN_KEY, true)
        editor.apply()
    }
    fun setUserData1(activity: Context, user: LoginData) {
        initLoginSharedPreference(activity)
        val editor = loginFile!!.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        editor.putString(USER_DATA_KEY, json)
        editor.putBoolean(IS_LOGIN_KEY, true)
        editor.apply()
    }
    fun getUserData1(activity: Context?): LoginData {
        if (activity != null) {
            initLoginSharedPreference(activity)
        }
        val gson = Gson()
        val json = loginFile!!.getString(USER_DATA_KEY, "")
        Log.e("data" , json!!)
        return gson.fromJson<LoginData>(json, LoginData::class.java!!)
    }
    fun setUserData(activity: Activity, user: UserModel) {
        initLoginSharedPreference(activity)
        val editor = loginFile!!.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        editor.putString(USER_DATA_KEY, json)
        editor.apply()
    }

    fun setTokenData(activity: Activity, accessToken: String, expireKey: Long) {
        initLoginSharedPreference(activity)
        val editor = loginFile!!.edit()
        editor.putString(ACCESS_TOKEN_KEY, accessToken)
        editor.putLong(EXPIRE_KEY, expireKey)
        editor.apply()
        initLoginSharedPreference(activity)
    }

    fun getUserData(activity: Context?): LoginModel {
        if (activity != null) {
            initLoginSharedPreference(activity)
        }
        val gson = Gson()
        val json = loginFile!!.getString(USER_DATA_KEY, "")
        Log.e("data" , json!!)
        return gson.fromJson<LoginModel>(json, LoginModel::class.java!!)
    }
    fun setenabletrip(activity: Activity, enable: Int) {
        initLoginSharedPreference(activity)
        val editor = loginFile!!.edit()
        editor.putInt(NOTIFICATION_COUNT_KEY, enable)
        editor.apply()
    }

    fun setNewsCount(activity: Activity, count: Int) {
        initLoginSharedPreference(activity)
        val editor = loginFile!!.edit()
        editor.putInt(NEWS_COUNT_KEY, count)
        editor.apply()
    }

    fun getenabletrip(activity: Activity): Int {
        initLoginSharedPreference(activity)
        return loginFile!!.getInt(NOTIFICATION_COUNT_KEY, 0)
    }

    fun getNewsCount(activity: Activity): Int {
        initLoginSharedPreference(activity)
        return loginFile!!.getInt(NEWS_COUNT_KEY, 0)
    }
    fun setnotification(activity: Activity, enable: Boolean) {
        initLoginSharedPreference(activity)
        val editor = loginFile!!.edit()
        editor.putBoolean(NOTIFICATION_COUNT_KEY, enable)
        editor.apply()
    }
    fun getnotification(activity: Activity): Boolean {
        initLoginSharedPreference(activity)
        return loginFile!!.getBoolean(NOTIFICATION_COUNT_KEY, true)
    }
    fun getUserType(activity: Activity): Int {
        initLoginSharedPreference(activity)
        return loginFile!!.getInt(USER_TYPE_KEY, -1)
    }

    fun isLoggedIn(activity: Activity): Boolean {
        initLoginSharedPreference(activity)
        return loginFile!!.getBoolean(IS_LOGIN_KEY, false)
    }

    fun getAccessToken(activity: Activity): String {
        initLoginSharedPreference(activity)
        return loginFile!!.getString(ACCESS_TOKEN_KEY, "")!!
    }


    fun getAccessToken(activity: Context): String {
        initLoginSharedPreference(activity)
        return "Bearer " +loginFile!!.getString(ACCESS_TOKEN_KEY, "")!!
    }

    fun getExpire(activity: Activity): Long? {
        initLoginSharedPreference(activity)
        return loginFile!!.getLong(EXPIRE_KEY, 0)
    }

    fun clearData(activity: Activity) {
//        FirebaseMessaging.getInstance().unsubscribeFromTopic(BuildConfig.firebasetopic)
//            .addOnCompleteListener { task ->
//                if (task.isSuccessful) {
//                    // Subscription successful
//                    println("Subscribed to topic successfully")
//                } else {
//                    // Subscription failed
//                    println("Failed to subscribe to topic: ${task.exception?.message}")
//                }
//            }
        clearaddress(activity)
        initLoginSharedPreference(activity)
        val editor = loginFile!!.edit()
        editor.clear()
        editor.apply()
        LoginSession.isLogin = false
        val intent = Intent(activity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.startActivity(intent)
        activity.finish()
    }
    fun clearData1(activity: Context) {
        initLoginSharedPreference(activity)
        val editor = loginFile!!.edit()
        editor.clear()
        editor.apply()
        LoginSession.isLogin = false
        val intent = Intent(activity, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.startActivity(intent)
        (activity as Activity).finish()
    }
    fun clearData2(activity: Context) {
        initLoginSharedPreference(activity)
        val editor = loginFile!!.edit()
        editor.clear()
        editor.apply()
        LoginSession.isLogin = false
    }
    fun maintaninece(activity: Context) {
//        val intent = Intent(activity, ForceupdateActivity::class.java)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        activity.startActivity(intent)
//        (activity as Activity).finish()
    }
    fun forceupdate(activity: Context, txt:String) {
//        val intent = Intent(activity, ForceupdateActivity::class.java)
//        intent.putExtra("maintanince",txt)
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
//        activity.startActivity(intent)
//        (activity as Activity).finish()
    }
    fun activeuser(activity: Context) {
        val intent = Intent(activity, ActiveuserActivity::class.java)
        intent.putExtra("phone",LoginSession.getUserData(activity).user?.phone!!)
        intent.putExtra("dialcode","+966")
        intent.putExtra("verify",LoginSession.getUserData(activity).user?.phone!!)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        activity.startActivity(intent)
        (activity as Activity).finish()
    }
    fun clearaddress(activity: Activity) {
        initcountrySharedPreference(activity)
        val editor = countryFile!!.edit()
        editor.clear()
        editor.apply()
    }

    fun setSelectedCitiesForAdmin(activity: Activity, selectedCitiesIds: ArrayList<Int>) {
        initLoginSharedPreference(activity)
        val editor = loginFile!!.edit()
        editor.putString(SELECTED_CITIES_KEY, selectedCitiesIds.toString())
        editor.apply()
    }

    fun getSelectedCitiesForAdmin(activity: Activity): String {
        initLoginSharedPreference(activity)
        return loginFile!!.getString(SELECTED_CITIES_KEY, "[-1]")!!
    }
    fun Addwelcome(activity: Activity, expired: Boolean) {
        onboardingFile = activity.getSharedPreferences("welcome", Context.MODE_PRIVATE)
        val editor = onboardingFile!!.edit()
        editor.putBoolean("welcome", expired)
        editor.apply()
    }
    fun getwelcome(activity: Activity): Boolean {
        onboardingFile =
            activity.getSharedPreferences("welcome", Context.MODE_PRIVATE)
        return onboardingFile!!.getBoolean("welcome", false)
    }
    fun Addtype(activity: Activity, type: Int) {
        var onboardingFile = activity.getSharedPreferences("type", Context.MODE_PRIVATE)
        val editor = onboardingFile!!.edit()
        editor.putInt("type", type)
        editor.apply()
    }
    fun gettype(activity: Activity): Int {
        var onboardingFile =
            activity.getSharedPreferences("type", Context.MODE_PRIVATE)
        return onboardingFile!!.getInt("type", 0)
    }

//    fun Addclub(activity: Context, country: Club) {
//        countryFile = activity.getSharedPreferences("club", Context.MODE_PRIVATE)
//        val editor = countryFile!!.edit()
//        editor.putString("club", Gson().toJson(country))
//        editor.apply()
//    }
//    fun getclub(activity: Context): Club {
//        countryFile =
//            activity.getSharedPreferences("club", Context.MODE_PRIVATE)
//        val gson = Gson()
//        val json = countryFile!!.getString("club", "")
//        Log.e("data" , json!!)
//        return gson.fromJson<Club>(json, Club::class.java!!)
//    }
    fun AddAddress(activity: Context, country: AddressesDatum) {
        countryFile = activity.getSharedPreferences("address", Context.MODE_PRIVATE)
        val editor = countryFile!!.edit()
        editor.putString("address", Gson().toJson(country))
        editor.apply()
    }
    fun getaddress(activity: Context): AddressesDatum {
        countryFile =
            activity.getSharedPreferences("address", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = countryFile!!.getString("address", "{}")
        Log.e("data" , json!!)
        return gson.fromJson<AddressesDatum>(json, AddressesDatum::class.java!!)
    }
    fun setdata(activity: Context): UserModel? {
        return try {
            loginFile = activity.getSharedPreferences("loginFile", 0)
            val x = loginFile!!.getString(USER_DATA_KEY, "")
            var data: UserModel? = null
            if (x != "") {
                val dataType = object : TypeToken<UserModel?>() {}.type
                data = Gson().fromJson(x, dataType)
                Log.e("userdata",x.toString())
                LoginSession.isLogin = true
            } else {
                LoginSession.isLogin = false
            }
            data
        } catch (e: Exception) {
//            loginFile = activity.getSharedPreferences("loginFile", 0)
//            val editor = loginFile!!.edit()
//            editor.clear()
//            editor.commit()
            null
        }
    }
}
