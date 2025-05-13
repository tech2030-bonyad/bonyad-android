package fudex.bonyad.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent

import androidx.databinding.BaseObservable
import dev.b3nedikt.app_locale.AppLocale
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession

import fudex.bonyad.ui.Activity.LoginActivity
import fudex.bonyad.ui.Activity.SelecttypeActivity
import fudex.bonyad.ui.Activity.SplashActivity
import java.util.*
class SelecttypeViewModel(activity: SelecttypeActivity) : BaseObservable() {
    var activity: SelecttypeActivity = SelecttypeActivity()

    init {
        this.activity = activity
        LoginSession.clearData2(activity)

    }

    fun settype(type : Int){
        LoginSession.Addtype(activity,type)
        var intent: Intent = Intent(activity, LoginActivity::class.java)
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        activity.startActivity(intent)
        activity.finish()
    }
    fun lang(){
        if (activity.getString(R.string.lang) == "ar"){
            var loginFile = activity.getSharedPreferences("lang", Context.MODE_PRIVATE)
            val editor = loginFile!!.edit()
            editor.putString("lang", "en")
            editor.apply()
            AppLocale.desiredLocale = Locale.ENGLISH
            val intent = Intent(activity, SplashActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.finish()
            activity.startActivity(intent)
        }else{
            var loginFile = activity.getSharedPreferences("lang", Context.MODE_PRIVATE)
            val editor = loginFile!!.edit()
            editor.putString("lang", "ar")
            editor.apply()
            AppLocale.desiredLocale = Locale("ar")
            val intent = Intent(activity, SplashActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.finish()
            activity.startActivity(intent)
        }
    }

}
