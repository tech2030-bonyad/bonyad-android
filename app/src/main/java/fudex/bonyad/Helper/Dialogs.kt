package fudex.bonyad.Helper
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.widget.Toast
import android.view.Window


/**
 * <h1>Implement reusable dialogs</h1>
 * Dialogs class for all dialogs and toasts
 *
 *
 *
 * @author kemo94
 * @version 1.0
 * @since 2017-08-9
 */

object Dialogs {

    lateinit var progressDialog: Dialog

    var noInternetDialog: Dialog? = null


    fun showToast(message: String, activity: Activity) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    fun showToast(message: String, context: Context) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }


}
