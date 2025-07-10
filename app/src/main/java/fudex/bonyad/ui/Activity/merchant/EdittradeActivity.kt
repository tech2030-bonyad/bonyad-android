package fudex.bonyad.ui.Activity.merchant

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import fudex.bonyad.Helper.Camera
import fudex.bonyad.R
import fudex.bonyad.databinding.EdittradeViewModelBinding
import fudex.bonyad.ui.Activity.BaseActivity
import fudex.bonyad.ui.Activity.merchant.MerchanthomeActivity
import fudex.bonyad.ui.Activity.user.EdituserdataActivity
import fudex.bonyad.viewmodel.merchant.EdittradeViewModel

class EdittradeActivity : BaseActivity() {
    lateinit var edittradeViewModel: EdittradeViewModel
    lateinit var binding : EdittradeViewModelBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edittrade)
        edittradeViewModel = EdittradeViewModel(this@EdittradeActivity)
        binding.model = edittradeViewModel
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
                        this@EdittradeActivity,
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
                        edittradeViewModel.cameraOperation()
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
}