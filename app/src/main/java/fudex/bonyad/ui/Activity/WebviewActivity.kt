package fudex.bonyad.ui.Activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.technical.TechnicalHomeActivity
import fudex.bonyad.ui.Activity.user.UserhomeActivity


class WebviewActivity : BaseActivity() {
    private var webview: WebView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        initView()
    }
    @RequiresApi(Build.VERSION_CODES.M)
    private fun initView() {
        webview = findViewById<WebView>(R.id.webView)
        val settings = webview?.getSettings()
        settings?.javaScriptEnabled = true
        webview?.settings?.javaScriptEnabled = true
        webview?.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY)
        val alertDialog: AlertDialog = AlertDialog.Builder(this).create()
//        val progressBar =
//            ProgressDialog.show(this@WebViewActivity, getString(R.string.app_name), "Loading...")
        webview?.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                Log.e("url", url)
                if (url.contains("?status=true")){
                    webview?.visibility = View.GONE
                   if (intent.hasExtra("order")){
                       var intent: Intent = Intent(this@WebviewActivity, UserhomeActivity::class.java)
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                       startActivity(intent)
                       finish()
                   }else {
                       var intent: Intent = Intent(this@WebviewActivity, TechnicalHomeActivity::class.java)
                       intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                       startActivity(intent)
                       finish()
                   }
                }else  if (url.contains("?status=false")){
                    Dialogs.showToast(getString(R.string.payment_failer),this@WebviewActivity)
                    onBackPressed()
                }
                view.loadUrl(url)
                return true
            }


            override fun onPageFinished(view: WebView, url: String) {
//                if (progressBar.isShowing) {
//                    progressBar.dismiss()
//                }
            }

            override fun onReceivedError(
                view: WebView,
                errorCode: Int,
                description: String,
                failingUrl: String
            ) {
                alertDialog.setTitle("Error")
                alertDialog.setMessage(description)
                alertDialog.setButton("OK",
                    DialogInterface.OnClickListener { dialog, which -> return@OnClickListener })
                alertDialog.show()
            }
        })
        webview?.loadUrl(intent.getStringExtra("url")!!)
        webview?.webChromeClient = object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
                Log.e("message" , consoleMessage.message())
                if (consoleMessage.message() == "your message") {
                    // Handle message here
                    return true
                }
                if (consoleMessage.sourceId().toString().contains("?status=true")){
                    webview?.visibility = View.GONE
                    var intent: Intent = Intent(this@WebviewActivity, TechnicalHomeActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    startActivity(intent)
                    finish()
                }else  if (consoleMessage.sourceId().toString().contains("?status=false")){
                    Dialogs.showToast(getString(R.string.payment_failer),this@WebviewActivity)
                    onBackPressed()
                }
                return super.onConsoleMessage(consoleMessage)
            }
        }

    }
}