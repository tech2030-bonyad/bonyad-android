package fudex.bonyad.Helper
import android.app.Activity
import android.app.SearchManager
import android.content.ActivityNotFoundException
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.ContactsContract
import android.provider.Settings
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import fudex.bonyad.R


/**
 * <h1>Implement reusable methods of all intent actions</h1>
 * IntentClass class for all actions of intent
 *
 *
 *
 * @author  kemo94
 * @version 1.0
 * @since   2017-08-9
 */
object IntentClass {

    // go to another activity

    fun goToActivity(currentActivity: Activity, targetClass: Class<*>, value: Bundle? = null) {
        val intent = Intent(currentActivity, targetClass)
        intent.putExtra("data", value)
        /*if (targetClass == SplashActivity.class) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    }*/
        currentActivity.startActivity(intent)
    }

    fun toolBarSet(activity: Activity, back_image: ImageView, text: TextView, string: String) {
        back_image.setOnClickListener(View.OnClickListener { activity.finish() })
        text.text = string

    }



    fun goToResultActivity(currentActivity: Activity, targetClass: Class<*>, value: Bundle) {

        val intent = Intent(currentActivity, targetClass)
        intent.putExtra("data", value)
        currentActivity.startActivityForResult(intent, 111)
    }


    fun rateApp(currentActivity: Activity) {
        val uri = Uri.parse("market://details?id=" + currentActivity.packageName)
        val goToMarket = Intent(Intent.ACTION_VIEW, uri)
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY or
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK)
        try {
            currentActivity.startActivity(goToMarket)
        } catch (e: ActivityNotFoundException) {
            currentActivity.startActivity(Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + currentActivity.packageName)))
        }

    }
    fun shareapp(currentActivity: Activity) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
        share.putExtra(Intent.EXTRA_SUBJECT, currentActivity!!.getString(R.string.app_name))
        share.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id="+ currentActivity.packageName)
        currentActivity!!.startActivity(Intent.createChooser(share, "Share link!"))
    }
    fun shareurl(currentActivity: Activity , url:String) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET)
        share.putExtra(Intent.EXTRA_SUBJECT, currentActivity!!.getString(R.string.app_name))
        share.putExtra(Intent.EXTRA_TEXT, url)
        currentActivity!!.startActivity(Intent.createChooser(share, "Share link!"))
    }
    fun goToActivityAndClear(currentActivity: Activity, targetClass: Class<*>, value: Bundle?) {

        val intent = Intent(currentActivity, targetClass)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.putExtra("data", value)
        currentActivity.startActivity(intent)
    }
    // to open dial phone number

    fun goTodialPhoneNumber(currentActivity: Context, phoneNumber: String) {
        try {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:" + phoneNumber)
            currentActivity.startActivity(intent)
        }catch (e:Exception){

        }
    }

    fun goToFacebook(activity: Activity, id: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/" + id))
            activity.startActivity(intent)
        } catch (e: Exception) {
            activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/" + id)))
        }

    }

    //go to fb ,twitter ,google plus ....etc

    fun goToLink(activity: Activity, url: String) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        activity.startActivity(intent)


    }

    fun goTogoogleplay(activity: Activity) {
        val uri = Uri.parse("https://play.google.com/store/apps/details?id="+ activity.packageName)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        activity.startActivity(intent)


    }
    fun goTogoogleplay1(activity: Activity) {
        val uri = Uri.parse("https://play.google.com/store/apps/details?id=com.engaz.whereapp")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        activity.startActivity(intent)


    }
    // go to other app with data
    fun goSharedata(activity: Activity, text: String, sendTo: String) {

        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(Intent.EXTRA_TEXT, text)
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, activity.getString(R.string.app_name))
        sendIntent.type = "text/plain"
        activity.startActivity(Intent.createChooser(sendIntent, sendTo))
    }

    // show marker on map
    fun goMap(activity: Activity, lat: Double?, lng: Double?) {

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:<lat>,<long>?q=$lat,$lng"))
        activity.startActivity(intent)
    }


    // go to whatsapp
    fun goTowhatsApp(activity: Activity, smsNumber: String, smsText: String) {

        val uri = Uri.parse("smsto:" + smsNumber)
        val i = Intent(Intent.ACTION_SENDTO, uri)
        i.putExtra("sms_body", smsText)
        i.`package` = "com.whatsapp"
        activity.startActivity(i)
    }
    fun openwhatsapp(activity: Activity, number: String) {
        val url = "https://api.whatsapp.com/send?phone=$number"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        activity.startActivity(i)
    }
    // to open wifi settings and can change any action setting
    fun goToOpenWifiSettings(activity: Activity) {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        if (intent.resolveActivity(activity.packageManager) != null) {
            activity.startActivity(intent)
        }
    }

    // to navigate
    fun goToNavigate(activity: Activity, lat: Double?, lng: Double?) {
        val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?q=" + lat + "," + lng))
        activity.startActivity(intent)
    }


    //to open bluetooth
    fun goToBluetooth(activity: Activity) {

        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val cn = ComponentName("com.android.settings", "com.android.settings.bluetooth.BluetoothSettings")
        intent.component = cn
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        activity.startActivity(intent)

    }

    fun sendSms(activity: Activity, phoneNumber: String) {

        activity.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber)))

    }


    // to create event on mob
    fun goToAddEvent(activity: Activity, title: String, location: String, begin: Long, end: Long) {
        val intent = Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, title)
                .putExtra(CalendarContract.Events.EVENT_LOCATION, location)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, begin)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, end)
        if (intent.resolveActivity(activity.packageManager) != null) {
            activity.startActivity(intent)
        }
    }


    //add new contact

    fun goToInsertContact(activity: Activity, name: String, email: String) {
        val intent = Intent(Intent.ACTION_INSERT)
        intent.type = ContactsContract.Contacts.CONTENT_TYPE
        intent.putExtra(ContactsContract.Intents.Insert.NAME, name)
        intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email)
        if (intent.resolveActivity(activity.packageManager) != null) {
            activity.startActivity(intent)
        }
    }


    // send email to more than one with attachment

    fun goTocomposeEmail(activity: Activity, addresses: Array<String>, subject: String, attachment: Uri) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "*/*"
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        intent.putExtra(Intent.EXTRA_STREAM, attachment)
        if (intent.resolveActivity(activity.packageManager) != null) {
            activity.startActivity(intent)
        }
    }
    //send email to one

    fun goToEmail(activity: Context, address: String, subject: String) {
        val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", address, null))
        activity.startActivity(Intent.createChooser(intent, "Send email..."))
    }


    //search any thing in any app on your mob

    fun goToSearchWeb(activity: Activity, query: String) {
        val intent = Intent(Intent.ACTION_SEARCH)
        intent.putExtra(SearchManager.QUERY, query)
        if (intent.resolveActivity(activity.packageManager) != null) {
            activity.startActivity(intent)
        }
    }


}