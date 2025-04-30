package fudex.bonyad.Helper

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.location.LocationManager
import android.provider.Settings
import fudex.bonyad.R

/**
 * Created by tarek on 13/08/18.
 */
object OpenGps {
    fun checkgps(activity: Activity) {
        val locationManager: LocationManager =
            activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            val builder = AlertDialog.Builder(activity)
            builder.setMessage(activity.getString(R.string.open1))
                .setCancelable(false)
                .setPositiveButton(
                    activity.getString(R.string.yes),
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface, id: Int) {
                            activity.startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
                        }
                    })
                .setNegativeButton(
                    activity.getString(R.string.no),
                    object : DialogInterface.OnClickListener {
                        override fun onClick(dialog: DialogInterface, id: Int) {
                            dialog.cancel()
                        }
                    })
            val alert = builder.create()
            alert.show()
        }
    }
}