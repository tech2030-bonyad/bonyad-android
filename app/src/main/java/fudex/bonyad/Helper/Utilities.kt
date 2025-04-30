package fudex.bonyad.Helper
import android.Manifest
import fudex.bonyad.R
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.model.LatLng
import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat
import java.nio.charset.Charset
import java.text.DecimalFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView

object Utilities {
    const val LOCATION_REQUEST_PERMISSION = 1083
    const val KEY_ALIAS = "tifoforsecuredata"

    //    public static final int CAMERA_REQUEST_PERMISSION = 8210;
    //
    //    // RESULT CODES //
    const val LOCATION_SETTINGS_RESULT_CODE = 2913
    fun requestPermissions(activity: Activity, fragment: Fragment?) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.ACCESS_FINE_LOCATION)) {
                val builder = AlertDialog.Builder(activity)
                builder
                    .setTitle("Location permission")
                    .setMessage("Location permission is needed to detect nearby places")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton("Ok", object : DialogInterface.OnClickListener {
                        @RequiresApi(api = Build.VERSION_CODES.M)
                        override fun onClick(dialog: DialogInterface, which: Int) {
                            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                                    Manifest.permission.ACCESS_COARSE_LOCATION)) {
                                val intent = Intent()
                                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                                val uri: Uri = Uri.fromParts(
                                    "package", activity.getPackageName(),
                                    null
                                )
                                intent.data = uri
                                activity.startActivity(intent)
                            }else {
                                if (fragment != null) fragment.requestPermissions(
                                    arrayOf(
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.ACCESS_COARSE_LOCATION
                                    ), Utilities.LOCATION_REQUEST_PERMISSION
                                ) else ActivityCompat.requestPermissions(activity,
                                    arrayOf(
                                        Manifest.permission.ACCESS_FINE_LOCATION,
                                        Manifest.permission.ACCESS_COARSE_LOCATION
                                    ), Utilities.LOCATION_REQUEST_PERMISSION
                                )

                            }

                        }
                    }) /*
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    activity.finish();
                                }
                            })*/
                    .show()
                //            } else {
//
//                Toast.makeText(activity, "taany2", Toast.LENGTH_SHORT).show();
//
//                if (fragment != null)
//                    fragment.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
//                            Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_PERMISSION);
//                else
//                    activity.requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
//                            Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_PERMISSION);
//            }
            }

        }catch (e:Exception){

        }
    }

    fun closeKeyboard(activity: Activity) {
        try {
            val inputManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                activity.getCurrentFocus()!!.getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        } catch (a: Exception) {
        }
    }

    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID)
    }

    fun formatMessageDateTime(activity: Activity , date: String , pattern : String): String {
        try {
            var lang = activity.getString(R.string.lang)
            var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale(lang))
            var date1 = simpleDateFormat.parse(date)
            simpleDateFormat = SimpleDateFormat(pattern, Locale(lang))
            return simpleDateFormat.format(date1)
        }catch (e:Exception){
            return  ""
        }
    }
    fun formatMessageDateTimefromdate(date: String , pattern : String): String {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date.toLong()
        var simpleDateFormat = SimpleDateFormat(pattern)
        return simpleDateFormat.format(calendar.time)
    }
    fun formatMessageDateTime(date: String , pattern : String , local : String): String {
        var simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale("en"))
        var date1 = simpleDateFormat.parse(date)
        simpleDateFormat = SimpleDateFormat(pattern, Locale(local))
        return simpleDateFormat.format(date1)
    }
    @SuppressLint("NewApi")
    fun changeformate(date: String , lang: String) : String {
        val utcDateTime = ZonedDateTime.parse(date)
        val gmtDateTime = utcDateTime.withZoneSameInstant(ZoneId.of("GMT"))
        val formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale(lang))
        val formattedTime = gmtDateTime.format(formatter)
        return formattedTime
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDateToArabic(date: LocalDate, lang: String): String {
        val formatter: org.joda.time.format.DateTimeFormatter? = DateTimeFormat.forPattern("MMMM yyyy").withLocale(Locale(lang))
        return formatter!!.print(date)
    }
    fun setDate(date: String): String {

        val patternTarget = "yyyy-MM-dd"
        val sdfTarget = SimpleDateFormat(patternTarget)
        var date1: Date? = null
        try {
            date1 = sdfTarget.parse(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val dateTime = DateTime(date1)

        val cal = Calendar.getInstance()
        cal.time = date1
        val monthName = SimpleDateFormat("MMMM").format(cal.time)
        val dayName = SimpleDateFormat("EEEE").format(cal.time)


        return dayName + " " + pad(dateTime.getDayOfMonth()) + " " + monthName + " " + dateTime.getYear()
    }

    fun pad(time: Int): String {

        return if (time < 10) "0$time" else "" + time

    }
    fun animImage(context: Context? , textViewObject : CardView) {
//        // Load the animation like this
//        val animRightToLeft: Animation =
//            AnimationUtils.loadAnimation(context, fudex.bonyad.R.anim.anim_move_right_to_left)
//        textViewObject.setLayerType(View.LAYER_TYPE_HARDWARE, null)
//        animRightToLeft.fillAfter = false
//        // Start the animation like this
//        textViewObject.startAnimation(animRightToLeft)
    }
    fun animImage1(context: Context? , textViewObject : CardView) {
        // Load the animation like this
//        val animRightToLeft: Animation =
//            AnimationUtils.loadAnimation(context, fudex.bonyad.R.anim.anim_move_left_to_right)
//        textViewObject.setLayerType(View.LAYER_TYPE_HARDWARE, null)
//        animRightToLeft.fillAfter = false
//        // Start the animation like this
//        textViewObject.startAnimation(animRightToLeft)
    }
    fun movetodistance(orginlat : Double , orginlng : Double , destlat : Double , destlng : Double) : Boolean{
        var isgood = false
        var latDistance : Double = Math.toRadians(destlat - orginlat);
        var  lngDistance : Double = Math.toRadians(destlng - orginlng);
        var a : Double = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
                (Math.cos(Math.toRadians(destlat))) *
                (Math.cos(Math.toRadians(orginlat))) *
                (Math.sin(lngDistance / 2)) *
                (Math.sin(lngDistance / 2));

        var c : Double = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        var  dist : Double = 6371 * c;
        if (dist <= 0.5){
            isgood = true
        }
        return isgood
    }
    fun aroundUp(number: Double, canDecimal: Int): Double {
        val cifras = Math.pow(10.0, canDecimal.toDouble()).toInt()
        return Math.ceil(number * cifras) / cifras
    }
    fun CalculationByDistance(StartP: LatLng, EndP: LatLng): String {
        val Radius = 6371 // radius of earth in Km
        val lat1: Double = StartP.latitude
        val lat2: Double = EndP.latitude
        val lon1: Double = StartP.longitude
        val lon2: Double = EndP.longitude
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = (Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + (Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2)))
        val c = 2 * Math.asin(Math.sqrt(a))
        val valueResult = Radius * c
        val km = valueResult / 1
        val newFormat = DecimalFormat("####")
        val kmInDec: Int = Integer.valueOf(newFormat.format(km))
        val meter = valueResult % 1000
        val meterInDec: Int = Integer.valueOf(newFormat.format(meter))
        Log.i(
            "Radius Value", "" + valueResult + "   KM  " + kmInDec
                    + " Meter   " + meterInDec
        )
        return kmInDec.toString() + "," + meterInDec.toString() + " KM"
    }
    fun disabletouch(context: Activity){
        context.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)

    }
    fun enabletouch(context: Activity){
        context.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
    }
//    fun generateSecretKey(): SecretKey {
//        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
//        val keyGenParameterSpec = KeyGenParameterSpec.Builder(
//            KEY_ALIAS,
//            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
//        ).apply {
//            setBlockModes(KeyProperties.BLOCK_MODE_CBC)
//            setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_PKCS7)
//            setUserAuthenticationRequired(true)
//        }.build()
//
//        keyGenerator.init(keyGenParameterSpec)
//        return keyGenerator.generateKey()
//    }
    fun encryptData(data: String, secretKey: SecretKey): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        val ivBytes = cipher.iv
        val encryptedData = cipher.doFinal(data.toByteArray(Charset.defaultCharset()))

        // Concatenate IV and encrypted data
        val combined = ByteArray(ivBytes.size + encryptedData.size)
        System.arraycopy(ivBytes, 0, combined, 0, ivBytes.size)
        System.arraycopy(encryptedData, 0, combined, ivBytes.size, encryptedData.size)

        return combined
    }
    fun decryptData(encryptedData: ByteArray, secretKey: SecretKey): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding")

        // Extract IV from the combined encrypted data
        val ivBytes = encryptedData.copyOfRange(0, cipher.blockSize)

        val encryptedBytes = encryptedData.copyOfRange(cipher.blockSize, encryptedData.size)
        val ivSpec = IvParameterSpec(ivBytes)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec)

        val decryptedBytes = cipher.doFinal(encryptedBytes)
        return String(decryptedBytes)
    }
    fun encrypt(input: String, shift: Int): String {
        val shiftedChars = input.map { char ->
            when {
                char.isLowerCase() -> shiftChar(char, shift, 'a', 'z')
                char.isUpperCase() -> shiftChar(char, shift, 'A', 'Z')
                else -> char
            }
        }
        return shiftedChars.joinToString("")
    }

    fun decrypt(input: String, shift: Int): String {
        return encrypt(input, -shift)
    }

    fun shiftChar(char: Char, shift: Int, start: Char, end: Char): Char {
        val range = end.toInt() - start.toInt() + 1
        val shifted = (char.toInt() - start.toInt() + shift) % range
        return (start.toInt() + if (shifted >= 0) shifted else shifted + range).toChar()
    }
//    @SuppressLint("CutPasteId")
//    fun showSuccessDialog(context: Activity, title : String, body: String, onDismissAction: () -> Unit) {
//        val dialog = Dialog(context)
//        dialog.setContentView(R.layout.dialog_success_messag)
//        dialog.setCancelable(false)
//        dialog.window?.setGravity(Gravity.BOTTOM)
//        dialog.window?.setBackgroundDrawable(Color.TRANSPARENT.toDrawable())
//        val width = ViewGroup.LayoutParams.MATCH_PARENT
//        val height = ViewGroup.LayoutParams.WRAP_CONTENT
//        dialog.window?.setLayout(width, height)
//        dialog.findViewById<TextView>(R.id.title).text = title
//        dialog.findViewById<TextView>(R.id.body).text = body
//        if (body == "") {
//            dialog.findViewById<TextView>(R.id.body).visibility = View.GONE
//        }
//        dialog.show()
//        Handler(Looper.getMainLooper()).postDelayed({
//            dialog.dismiss()
//            onDismissAction()
//        }, 3000)
//    }
    fun copyToClipboard(context: Context, text: String) {
        val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("Booking Details", text)
        clipboard.setPrimaryClip(clip)
        Dialogs.showToast(context.getString(R.string.copied), context)
    }
    fun expandRecyclerViewHeight(recyclerView: RecyclerView) {
        val adapter = recyclerView.adapter ?: return
        var totalHeight = 0

        for (i in 0 until adapter.itemCount) {
            val holder = adapter.createViewHolder(recyclerView, adapter.getItemViewType(i))
            adapter.onBindViewHolder(holder, i)

            holder.itemView.measure(
                View.MeasureSpec.makeMeasureSpec(recyclerView.width, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.UNSPECIFIED
            )

            totalHeight += holder.itemView.measuredHeight
        }

        val params = recyclerView.layoutParams
        params.height = totalHeight
        recyclerView.layoutParams = params
    }


}