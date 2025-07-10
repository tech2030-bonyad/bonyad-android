package fudex.bonyad.viewmodel

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import dev.b3nedikt.app_locale.AppLocale
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Camera.Companion.activity
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.ChangelangueActivity
import fudex.bonyad.ui.Activity.merchant.MerchanthomeActivity
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale
import kotlin.collections.ArrayList

class ChangelanguageViewModel(var catogaryFragment: ChangelangueActivity) : BaseObservable() {
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var context: ChangelangueActivity = ChangelangueActivity()
    var linearlayout: LinearLayoutManager? = null
    var type = ObservableField<String>("")
    var typeIndex = ObservableField<Int>(2)

    init {
        context = catogaryFragment
    }

    fun back(){
        context.onBackPressed()
    }

    fun english(){
            LoginSession.clearaddress(context!!)
            var loginFile = context.getSharedPreferences("lang", Context.MODE_PRIVATE)
            val editor = loginFile!!.edit()
            editor.putString("lang", "en")
            editor.apply()
            AppLocale.desiredLocale = Locale.ENGLISH
           if (LoginSession.gettype(context) == 1){
            val intent = Intent(context, UserhomeActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.finish()
            context.startActivity(intent)
           }else if (LoginSession.gettype(context) == 2){
               val intent = Intent(context, MerchanthomeActivity::class.java)
                   .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                   .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
               context.finish()
               context.startActivity(intent)
           }else if (LoginSession.gettype(context) == 3){
            val intent = Intent(context, TechnicalHomeActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.finish()
            context.startActivity(intent)
          }
    }
    fun arabic(){
        LoginSession.clearaddress(context!!)
        var loginFile = context.getSharedPreferences("lang", Context.MODE_PRIVATE)
        val editor = loginFile!!.edit()
        editor.putString("lang", "ar")
        editor.apply()
        AppLocale.desiredLocale = Locale("ar")
        if (LoginSession.gettype(context) == 1){
            val intent = Intent(context, UserhomeActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.finish()
            context.startActivity(intent)
        }else if (LoginSession.gettype(context) == 2){
            val intent = Intent(context, MerchanthomeActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.finish()
            context.startActivity(intent)
        }else if (LoginSession.gettype(context) == 3){
            val intent = Intent(context, TechnicalHomeActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.finish()
            context.startActivity(intent)
        }
    }
}