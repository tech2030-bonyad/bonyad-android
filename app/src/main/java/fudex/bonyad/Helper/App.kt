package fudex.bonyad.Helper

import android.app.Activity
import android.app.Application
import android.content.ComponentCallbacks2
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Build.VERSION.SDK
import android.os.Bundle
import android.os.Handler
import android.os.StrictMode
import android.provider.UserDictionary.Words
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.blongho.country_data.World
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.microsoft.applicationinsights.library.ApplicationInsights
import fudex.bonyad.Apimodel.APIModel
import dev.b3nedikt.app_locale.AppLocale
import dev.b3nedikt.app_locale.SharedPrefsAppLocaleRepository
import dev.b3nedikt.reword.RewordInterceptor
import dev.b3nedikt.viewpump.ViewPump
import java.util.*

import kotlin.text.Typography.dagger


class App : Application() {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
//        FirebaseCrashlytics.getInstance().setCrashlyticsCollectionEnabled(true)
        AppLocale.supportedLocales = listOf(Locale.ENGLISH, LOCALE_ARABIC)
        AppLocale.appLocaleRepository = SharedPrefsAppLocaleRepository(this)
        ViewPump.init(RewordInterceptor)
        World.init(applicationContext)
        ApplicationInsights.setup(this.getApplicationContext(), this);
        ApplicationInsights.start();
    }


    override fun getResources(): Resources {
        return AppLocale.wrap(baseContext).resources
    }

    companion object {
        val LOCALE_ARABIC = Locale("ar")


    }

}