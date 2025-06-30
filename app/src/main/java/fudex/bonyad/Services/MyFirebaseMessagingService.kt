package fudex.bonyad.Services

/**
 * Created by PC on 04/10/2016.
 */

import android.annotation.SuppressLint
import android.app.*
import android.app.ActivityManager.RunningAppProcessInfo
import android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
import android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_VISIBLE
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.PowerManager
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.NotificationsActivity
import fudex.bonyad.ui.Activity.technical.TechnicaldetailsapointmentActivity
import fudex.bonyad.ui.Activity.user.DetailsappointmentActivity

import org.greenrobot.eventbus.EventBus
import org.json.JSONObject
import java.util.*


class MyFirebaseMessagingService : FirebaseMessagingService() {
    var operationId: String? = null
    var messageBody: String? = null
    var title: String? = null
    var type: String? = null
    var intent: Intent? = null
    var latitude = 0.0
    var longitude = 0.0
    var obj: JSONObject? = null
    var x = 0
    var storedata: SharedPreferences? = null
    var id: String? = null
    var loginFile: SharedPreferences? = null
    var messageshared: SharedPreferences? = null
    var NOTIFICATION_CHANNEL_ID = "10001"
    var wakeLock: PowerManager.WakeLock? = null

    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @SuppressLint("InvalidWakeLockTag")
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.e("hhbh", remoteMessage.getData().toString())
//        var loginFile = applicationContext.getSharedPreferences("loginFile", Context.MODE_PRIVATE)
//        var isnotifid = loginFile!!.getBoolean("notificationCountKey", true)
//        if (isnotifid == false){
//            return
//        }
        try {
            val jsonObject = JSONObject(remoteMessage.getData() as Map<*, *>?)
            if (jsonObject.getString("type") == "create_reservation" || jsonObject.getString("type") == "update_reservation" || jsonObject.getString("type") == "reservation_change_status") {
                var notificationIntent: Intent? = null
                if (LoginSession.gettype1(applicationContext) == 1){
                    notificationIntent =
                        Intent(getApplicationContext(), DetailsappointmentActivity::class.java)
                    notificationIntent.putExtra("id", jsonObject.getInt("item_id"))
                    notificationIntent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    val random = Random()
                    val notificationID = random.nextInt(9999 - 1000) + 1000
                    show_not(
                        notificationIntent,
                        jsonObject.getString("title"),
                        jsonObject.getString("body"),
                        notificationID
                    )
                }else  if (LoginSession.gettype1(applicationContext) == 3){
                    notificationIntent =
                        Intent(getApplicationContext(), TechnicaldetailsapointmentActivity::class.java)
                    notificationIntent.putExtra("id", jsonObject.getInt("item_id"))
                    notificationIntent.flags =
                        Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    val random = Random()
                    val notificationID = random.nextInt(9999 - 1000) + 1000
                    show_not(
                        notificationIntent,
                        jsonObject.getString("title"),
                        jsonObject.getString("body"),
                        notificationID
                    )
                }


            }else  {
                var notificationIntent: Intent? = null
                notificationIntent =
                    Intent(getApplicationContext(), NotificationsActivity::class.java)
                notificationIntent.flags =
                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                val random = Random()
                val notificationID = random.nextInt(9999 - 1000) + 1000
                show_not(
                    notificationIntent!!,
                    jsonObject.getString("title"),
                    jsonObject.getString("body"),
                    notificationID
                )
            }
            val intent = Intent("message_received")
            intent.putExtra("title",jsonObject.getString("title"))
            intent.putExtra("message", jsonObject.getString("body"))
            intent.putExtra("item_id", jsonObject.getInt("item_id"))
            intent.putExtra("type",jsonObject.getString("type"))
            sendBroadcast(intent)
        }catch (e:Exception){

        }

    }

    private fun show_not(
        notificationIntent: Intent,
        title: String,
        message: String,
        order_id: Int
    ) {
        val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val contentIntent: PendingIntent
        contentIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PendingIntent.getActivity(
                this,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
            )
        } else {
            PendingIntent.getActivity(
                this,
                0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
            )
        }
        var notificationBuilder: NotificationCompat.Builder? = null
        val bitmap1: Bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)
        val notificationManager: NotificationManager = getApplicationContext().getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
        notificationBuilder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(bitmap1)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(contentIntent)
        } else {
            NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(contentIntent)
                .setStyle(
                    NotificationCompat.BigTextStyle()
                        .bigText(message)
                )
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val attributes: AudioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            val importance: Int = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                "NOTIFICATION_CHANNEL_NAME",
                importance
            )
            notificationChannel.enableLights(true)
            notificationChannel.setShowBadge(true)
            notificationChannel.setLightColor(Color.RED)
            notificationChannel.enableVibration(true)
            notificationChannel.setSound(defaultSoundUri, attributes)
            notificationChannel.setVibrationPattern(
                longArrayOf(
                    100,
                    200,
                    300,
                    400,
                    500,
                    400,
                    300,
                    200,
                    400
                )
            )
            assert(notificationManager != null)
            notificationBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        assert(notificationManager != null)
        notificationManager.notify(order_id, notificationBuilder.build())
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    fun foregrounded(): Boolean {
        val appProcessInfo = RunningAppProcessInfo()
        ActivityManager.getMyMemoryState(appProcessInfo)
        return appProcessInfo.importance == IMPORTANCE_FOREGROUND || appProcessInfo.importance == IMPORTANCE_VISIBLE
    }

}