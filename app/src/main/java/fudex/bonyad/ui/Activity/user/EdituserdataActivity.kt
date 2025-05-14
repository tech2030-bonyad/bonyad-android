package fudex.bonyad.ui.Activity.user

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.location.Geocoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import fudex.bonyad.Helper.Camera
import fudex.bonyad.Helper.ImageCompressor
import fudex.bonyad.R
import fudex.bonyad.databinding.EdituserViewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.viewmodel.user.EdituserprofileViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.Locale

class EdituserdataActivity : BaseActivity() {
    lateinit var edituserprofileViewModel: EdituserprofileViewModel
    lateinit var binding : EdituserViewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edituserdata)
        edituserprofileViewModel = EdituserprofileViewModel(this@EdituserdataActivity)
        binding.model = edituserprofileViewModel
        binding.main.setOnClickListener {
            it.hideKeyboard()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this@EdituserdataActivity,
                        Manifest.permission.CAMERA
                    )
                ) {
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri: Uri = Uri.fromParts(
                        "package", getPackageName(),
                        null
                    )
                    intent.data = uri
                    startActivity(intent)
                }else {
                    val permissionCheck = ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.CAMERA
                    )
                    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                       edituserprofileViewModel.cameraOperation()
                    } else {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.CAMERA),
                            2
                        )
                    }
                }
            } else {
            }
        }else if (requestCode == 2) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Camera.cameraOperation()
            } else {
            }
        }
    }
//    @SuppressLint("MissingSuperCall")
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        if (requestCode == Camera.CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
//            if (resultCode == RESULT_OK) {
////                Camera.myBitmap = data?.getExtras()?.get("data") as Bitmap?
//                try {
//                    Camera.myBitmap = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
//                        ImageDecoder.decodeBitmap(
//                            ImageDecoder.createSource(contentResolver,
//                                Camera.fileUri!!
//                            ))
//                    } else {
//                        MediaStore.Images.Media.getBitmap(contentResolver, Camera.fileUri)
//                    }
//////                    Camera.myBitmap =
////                        MediaStore.Images.Media.getBitmap(contentResolver, Camera.fileUri)
//                } catch (e: IOException) {
//                    e.printStackTrace()
//                }
//                lifecycleScope.launch {
//                   // Camera.myBitmap = ImageCompressor.compressImage(Camera.myBitmap!!)
//                    edituserprofileViewModel.image = Camera.fileUri?.path!!
//                    binding.photo.setImageBitmap(Camera.myBitmap)
//                }
//            } else if (resultCode == RESULT_CANCELED) {
//
//                // user cancelled Image capture
//
//            } else {
//                // failed to capture image
//
//            }
//        } else if (requestCode == Camera.PICK_PHOTO_FOR_AVATAR && resultCode == RESULT_OK) {
//            if (data == null) {
//
//                return
//            }
//            val selectedImageUri: Uri? = data.data
//            try {
//                Camera.myBitmap =
//                    MediaStore.Images.Media.getBitmap(contentResolver, selectedImageUri)
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//            lifecycleScope.launch {
//                ImageCompressor.compressBitmap(this@EdituserdataActivity, Camera.myBitmap!! , { file ->
//                    Camera.fileUri = Uri.fromFile(file)
//                    Camera.myBitmap = BitmapFactory.decodeFile(file!!.getAbsolutePath());
//                    binding.photo.setImageBitmap(Camera.myBitmap)
//                    edituserprofileViewModel.image = file!!.getAbsolutePath()
//
//                })
//            }
//
//            try {
//
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//            //Setting the Bitmap to ImageView
//        }
//
//    }
}