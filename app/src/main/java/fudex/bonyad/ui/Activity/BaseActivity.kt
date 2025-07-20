package fudex.bonyad.ui.Activity
import android.content.Context
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.ViewPumpAppCompatDelegate
import dev.b3nedikt.app_locale.AppLocale
import fudex.bonyad.Helper.Utilities

abstract class BaseActivity : AppCompatActivity() {
    private val appCompatDelegate: AppCompatDelegate by lazy {
        ViewPumpAppCompatDelegate(
            baseDelegate = super.getDelegate(),
            baseContext = this,
            wrapContext = AppLocale::wrap
        )
    }
    var isRunningOnEmulator: Boolean = true

    override fun getDelegate(): AppCompatDelegate {
        return appCompatDelegate
    }
    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(AppLocale.wrap(newBase!!))
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
    fun View.hideKeyboard() {
        Utilities.closeKeyboard(this@BaseActivity)
    }
}