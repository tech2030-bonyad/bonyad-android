package fudex.bonyad.Helper

import android.content.Context
import android.os.Build
import android.provider.Settings.Secure
import com.scottyab.rootbeer.RootBeer
import java.io.File

/**
 * Created by pc on 15/02/2017.
 */
object Config {
    const val IMAGE_DIRECTORY_NAME = "WATER"
    fun isEmulator(context: Context): Boolean {
        val androidId = Secure.getString(context.contentResolver, "android_id")
        return Build.PRODUCT.contains("sdk") || Build.HARDWARE.contains("goldfish") || Build.HARDWARE.contains(
            "ranchu"
        ) || androidId == null
    }

    fun isRooted(context: Context): Boolean {
        val isEmulator = isEmulator(context)
        val buildTags = Build.TAGS
        return if (!isEmulator && buildTags != null && buildTags.contains("test-keys")) {
            true
        } else {
            var file = File("/system/app/Superuser.apk")
            if (file.exists()) {
                true
            } else {
                file = File("/system/xbin/su")
                !isEmulator && file.exists()
            }
        }
    }
    fun isRooted1(context: Context): Boolean {
        val rootBeer = RootBeer(context)
        return (rootBeer.isRooted || CheckDeviceRooted.checkForBusyBoxBinary()
                || CheckDeviceRooted.checkForSuBinary()
                || CheckDeviceRooted.checkSuExists()
                || CheckDeviceRooted.detectTestKeys()
                )
    }

}