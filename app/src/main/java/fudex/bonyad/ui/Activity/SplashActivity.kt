package fudex.bonyad.ui.Activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        LoginSession.setdata(this)
        object : Thread() {
            override fun run() {
                try {
                    sleep(1500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    if (LoginSession.isLogin){
                        if (LoginSession.gettype(this@SplashActivity) == 1){
                            var intent: Intent = Intent(this@SplashActivity, UserhomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }else if (LoginSession.gettype(this@SplashActivity) == 3){
                            var intent: Intent = Intent(this@SplashActivity, TechnicalHomeActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }else {
                        var intent: Intent = Intent(this@SplashActivity, SelecttypeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }

                }
            }
        }.start()
    }
    fun showWhenLockedAndTurnScreenOn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        } else {
            window.addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            )
        }
    }
}

