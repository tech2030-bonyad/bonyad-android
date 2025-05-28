package fudex.bonyad.ui.Activity.user

import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.DataBindingUtil
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import fudex.bonyad.Helper.GPSTracker
import fudex.bonyad.R
import fudex.bonyad.databinding.LocateonmapviewModelBinding
import fudex.bonyad.viewmodel.user.AddplacemapViewModel

class LocationmapActivity : AppCompatActivity() {
    lateinit var addplacemapViewModel: AddplacemapViewModel
    lateinit var binding : LocateonmapviewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_locationmap)
        addplacemapViewModel = AddplacemapViewModel(this@LocationmapActivity)
        binding.model = addplacemapViewModel
    }
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == addplacemapViewModel.REQUEST_LOCATION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                val gpsTracker = GPSTracker(this@LocationmapActivity)
                addplacemapViewModel.lat = gpsTracker.getLatitude().toString()
                addplacemapViewModel.lon = gpsTracker.getLongitude().toString()
                val latLng: LatLng = LatLng(gpsTracker.getLatitude(), gpsTracker.getLongitude())
                var smallMarker : Bitmap? = null
                smallMarker = getResources().getDrawable(R.drawable.map).toBitmap(60, 60, null)
                val options = MarkerOptions()
                    .position(latLng).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).flat(true)
                addplacemapViewModel.mMap!!.addMarker(options)
                addplacemapViewModel.mMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f))
            } else {
                // Permission denied, handle the case

            }
        }
    }
}