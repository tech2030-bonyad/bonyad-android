package fudex.bonyad.viewmodel.user

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.location.Geocoder
import android.os.Handler
import android.os.Looper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableField
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import fudex.bonyad.Helper.OpenGps
import fudex.bonyad.R
import fudex.bonyad.ui.Activity.user.LocationmapActivity
import java.io.IOException
import java.util.Locale


class AddplacemapViewModel(var catogaryFragment: LocationmapActivity) : BaseObservable() ,
    OnMapReadyCallback , GoogleMap.OnMapClickListener,
    GoogleMap.OnCameraIdleListener, GoogleMap.OnCameraMoveListener {
    var activity = LocationmapActivity()
    private var mLoading = false
    var mMap: GoogleMap? = null
    private val mMarcadorActual: Marker? = null
    private var mFusedLocationClient: FusedLocationProviderClient? = null
    var lat: String? = ""
    var lon: String? = ""
    private var handler: Handler = Handler(Looper.myLooper()!!)
    val REQUEST_LOCATION = 123
    val locname = ObservableField<String>("")
    var color = ObservableField<Int>(Color.parseColor("#E51D35"))

    init {
        this.activity = catogaryFragment
        val mapFragment =
            activity.supportFragmentManager.findFragmentById(R.id.workerMap) as SupportMapFragment?
        mapFragment!!.getMapAsync(this)
        mFusedLocationClient =
            LocationServices.getFusedLocationProviderClient(activity)
        OpenGps.checkgps(activity)
        setRequestLocation()
    }

    override fun onMapReady(googlemap: GoogleMap?) {
        mMap = googlemap
        if (ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mMap!!.clear()
        mMap!!.setOnMapClickListener(this)
        mMap!!.isMyLocationEnabled = true
        mMap!!.setOnCameraIdleListener(this)
        mMap!!.setOnCameraMoveListener(this)
        mFusedLocationClient!!.lastLocation
            .addOnSuccessListener(
                activity
            ) { location ->
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    mMap!!.clear()
                    if (activity.intent.getDoubleExtra("lat",0.0) != 0.0){
                        lat = activity.intent.getDoubleExtra("lat",0.0).toString()
                        lon = activity.intent.getDoubleExtra("lng",0.0).toString()
                        val latLng = LatLng(lat!!.toDouble(), lon!!.toDouble())
                        var smallMarker : Bitmap? = null
                        smallMarker = activity.getResources().getDrawable(R.drawable.map).toBitmap(60, 60, null)
                        val options = MarkerOptions()
                            .position(latLng).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).flat(true)
                        mMap!!.addMarker(options)
                        mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f))
                        val geocoder = Geocoder(activity, Locale.getDefault())
                        var address = "Unknown location"
                        try {
                            val addresses = geocoder.getFromLocation(lat!!.toDouble() , lon!!.toDouble(), 1)
                            if (addresses!!.isNotEmpty()) {
                                address = addresses[0].getAddressLine(0)
                                locname.set(address)
                            }
                        } catch (e: IOException) {
                            // Handle any errors that occur during the geocoding process
                        }
                    }else {
                        lat = java.lang.String.valueOf(location.latitude)
                        lon = java.lang.String.valueOf(location.longitude)
                        val latLng = LatLng(lat!!.toDouble(), lon!!.toDouble())
                        var smallMarker : Bitmap? = null
                        smallMarker = activity.getResources().getDrawable(R.drawable.map).toBitmap(60, 60, null)
                        val options = MarkerOptions()
                            .position(latLng).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).flat(true)
                        mMap!!.addMarker(options)
                        mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f))
                        val geocoder = Geocoder(activity, Locale.getDefault())
                        var address = "Unknown location"
                        try {
                            val addresses = geocoder.getFromLocation(lat!!.toDouble() , lon!!.toDouble(), 1)
                            if (addresses!!.isNotEmpty()) {
                                address = addresses[0].getAddressLine(0)
                                locname.set(address)
                            }
                        } catch (e: IOException) {
                            // Handle any errors that occur during the geocoding process
                        }
                    }
                }
            }
    }

    fun back(){
        activity.onBackPressed()
    }

    override fun onMapClick(latLng: LatLng?) {
        mMap!!.clear()
        lat = latLng?.latitude.toString()
        lon = latLng?.longitude.toString()
        val latLng = LatLng(lat!!.toDouble(), lon!!.toDouble())
        var smallMarker : Bitmap? = null
        smallMarker = activity.getResources().getDrawable(R.drawable.map).toBitmap(60, 60, null)
        val options = MarkerOptions()
            .position(latLng).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).flat(true)
        mMap!!.addMarker(options)
        mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f))
        val geocoder = Geocoder(activity, Locale.getDefault())
        var address = "Unknown location"
        try {
            val addresses = geocoder.getFromLocation(lat!!.toDouble() , lon!!.toDouble(), 1)
            if (addresses!!.isNotEmpty()) {
                address = addresses[0].getAddressLine(0)
                locname.set(address)
            }
        } catch (e: IOException) {
            // Handle any errors that occur during the geocoding process
        }
    }

    override fun onCameraIdle() {
//        lat = mMap!!.cameraPosition.target.latitude.toString()
//        lon = mMap!!.cameraPosition.target.longitude.toString()
//        val latLng = LatLng(lat!!.toDouble(), lon!!.toDouble())
//        var smallMarker : Bitmap? = null
//        smallMarker = activity.getResources().getDrawable(R.drawable.map).toBitmap(60, 60, null)
//        val options = MarkerOptions()
//            .position(latLng).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).flat(true)
//        mMap!!.addMarker(options)
//        val geocoder = Geocoder(activity, Locale.getDefault())
//        var address = "Unknown location"
//        try {
//            val addresses = geocoder.getFromLocation(lat!!.toDouble() , lon!!.toDouble(), 1)
//            if (addresses!!.isNotEmpty()) {
//                address = addresses[0].getAddressLine(0)
//                locname.set(address)
//            }
//        } catch (e: IOException) {
//            // Handle any errors that occur during the geocoding process
//        }
    }

    override fun onCameraMove() {
    }

    fun addplace(){
        val intent = Intent()
        intent.putExtra("lat", lat)
        intent.putExtra("lng", lon)
        activity.setResult(RESULT_OK, intent)
        activity.onBackPressed()

    }
    private fun setRequestLocation() {
        if (ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                activity,

                arrayOf<String>(
                    Manifest.permission.ACCESS_FINE_LOCATION,

                    Manifest.permission.ACCESS_COARSE_LOCATION
                ), REQUEST_LOCATION
            )
        } else {
//            val gpsTracker = GPSTracker(activity)
//            lat = gpsTracker.getLatitude().toString()
//            lon = gpsTracker.getLongitude().toString()
//            val latLng = LatLng(lat!!.toDouble(), lon!!.toDouble())
//            var smallMarker : Bitmap? = null
//            smallMarker = activity.getResources().getDrawable(R.drawable.map).toBitmap(60, 60, null)
//            val options = MarkerOptions()
//                .position(latLng).icon(BitmapDescriptorFactory.fromBitmap(smallMarker)).flat(true)
//            mMap!!.addMarker(options)
//            mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f))
        }
    }

}