package fudex.bonyad.viewmodel.merchant

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.StrictMode
import android.provider.Settings
import android.text.InputFilter
import android.text.InputType
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.Gravity
import android.widget.PopupMenu
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.adapters.TextViewBindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.bumptech.glide.Glide
import com.google.firebase.messaging.FirebaseMessaging
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Userdata
import fudex.bonyad.Helper.Camera
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Helper.Validations
import fudex.bonyad.Model.LoginData
import fudex.bonyad.Model.StatesDatum
import fudex.bonyad.Model.StatesModel
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.ActiveuserActivity
import fudex.bonyad.ui.Activity.StaticpageActivity
import fudex.bonyad.ui.Activity.technical.TechnicalregisterActivity
import fudex.bonyad.ui.Adapter.technical.Imagessadapter
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody


import fudex.bonyad.Model.DistanceModel
import fudex.bonyad.Model.RegisterimageModel
import fudex.bonyad.ui.Activity.merchant.MerchantregisterActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

import kotlin.collections.ArrayList


class MerchantregisterViewModel(activity: MerchantregisterActivity) : BaseObservable() {
    var activity: MerchantregisterActivity = MerchantregisterActivity()
    val phoneObserv = ObservableField<String>()
    val emailObserv = ObservableField<String>()
    val desObserv = ObservableField<String>()
    val tradenameObserv = ObservableField<String>()
    val addressObserv = ObservableField<String>()
    val passwordObserv = ObservableField<String>()
    val confirmpasswordObserv = ObservableField<String>()
    val nameObserv = ObservableField<String>()
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    val phone = ObservableField<String>()
    val code = ObservableField<String>()
    var maxlenght = 0
    var image = ""
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>
    private lateinit var pickImageLauncher: ActivityResultLauncher<String>
    private var photoUri: Uri? = null
    var linearlayout: LinearLayoutManager? = null
    private val imagessadapter = Imagessadapter()
    var token = "hh"

    @SuppressLint("RestrictedApi")
    fun onnameChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            nameObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun onemailChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            emailObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun onaddressChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            addressObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun ondesChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            desObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun ontradenameChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            tradenameObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun onmobileChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            if (activity.binding.phone.text.length > 0 && activity.binding.phone.text.substring(0,1) == "0"){
                activity.binding.phone.filters = arrayOf(InputFilter.LengthFilter(maxlenght + 1))
            }else {
                activity.binding.phone.filters = arrayOf(InputFilter.LengthFilter(maxlenght))
            }
            phoneObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun onpasswordChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            passwordObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun onconfirmpasswordhanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            confirmpasswordObserv.set(s.toString())
        }
    }
    init {
        this.activity = activity
        if (activity.getString(R.string.lang) == "ar"){
            activity.binding.name.gravity = Gravity.RIGHT
            activity.binding.email.gravity = Gravity.RIGHT
            activity.binding.phone.gravity = Gravity.RIGHT
            activity.binding.pass.gravity = Gravity.RIGHT
            activity.binding.address.gravity = Gravity.RIGHT
            activity.binding.tradedes.gravity = Gravity.RIGHT
            activity.binding.tradename.gravity = Gravity.RIGHT
            activity.binding.confirmpass.gravity = Gravity.RIGHT
        }
        activity.binding.tradedes.movementMethod = ScrollingMovementMethod()
        Camera.activity = activity
        maxlenght = 9
        code.set("+966")
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        activity.binding.main.setOnClickListener {
            Utilities.closeKeyboard(activity)
        }
        takePictureLauncher = this.activity.registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success && photoUri != null) {
                //this.activity.binding.photo.setImageURI(photoUri)
                Glide.with(activity)
                    .load(photoUri)
                    .into( this.activity.binding.img)
                image = photoUri?.path.toString()
                notifyChange()
            }
        }

        // Gallery launcher
        pickImageLauncher = this.activity.registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                photoUri = it
                Glide.with(activity)
                    .load(it)
                    .into( this.activity.binding.img)
                image = it?.path.toString()
                notifyChange()
            }
        }
        gettoken()
    }

    fun gettoken(){
        FirebaseMessaging.getInstance().token
            .addOnCompleteListener { task ->
                if (!task.isSuccessful) {
                    Log.w("FCM", "Fetching FCM registration token failed", task.exception)
                    return@addOnCompleteListener
                }

                // Get new FCM registration token
                token = task.result ?: "hhhh"
            }

    }
    fun validateInput() {
        var error = false
        if (nameObserv.get() == null || nameObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.name.setError(activity.getString(R.string.required))
        }

        if (phoneObserv.get() == null || phoneObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.phone.setError(activity.getString(R.string.required))
        }
        if (addressObserv.get() == null || addressObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.address.setError(activity.getString(R.string.required))
        }
        if (desObserv.get() == null || desObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.tradedes.setError(activity.getString(R.string.required))
        }
        if (tradenameObserv.get() == null || tradenameObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.tradename.setError(activity.getString(R.string.required))
        }
//        if (emailObserv.get() == null || emailObserv.get()!!.isEmpty()) {
//            error = true
//            activity.binding.email.setError(activity.getString(R.string.required))
//        }
        if ((emailObserv.get() ?: "") != "") {
            if (emailObserv.get() != null && emailObserv.get()!!
                    .isNotEmpty() && !Validations.isValidEmail(emailObserv.get().toString().trim())
            ) {
                error = true
                activity.binding.email.setError(activity.getString(R.string.email_foramt_is_wrong))
            }
        }
        if (activity.intent.hasExtra("type") == false) {
            if (passwordObserv.get() == null || passwordObserv.get()!!.isEmpty()) {
                error = true
                activity.binding.pass.setError(activity.getString(R.string.required))
            }
            if (confirmpasswordObserv.get() == null || confirmpasswordObserv.get()!!.isEmpty()) {
                error = true
                activity.binding.confirmpass.setError(activity.getString(R.string.required))
            }
            if (confirmpasswordObserv.get() != null && confirmpasswordObserv.get()!!
                    .isNotEmpty() && passwordObserv.get() != null && passwordObserv.get()!!
                    .isNotEmpty()
            ) {
                if (confirmpasswordObserv.get() != passwordObserv.get()) {
                    error = true
                    activity.binding.confirmpass.setError(activity.getString(R.string.confirm_password_must_same_password))
                }
            }
        }
        if (activity.binding.agreeCheck.isChecked == false){
            error = true
            Dialogs.showToast(activity.getString(R.string.i_agree_to) + " "  + activity.getString(R.string.terms_and_conditions),activity)
        }

        if (image == ""){
            error = true
            Dialogs.showToast(activity.getString(R.string.select) + " "  + activity.getString(R.string.business_activity_logo),activity)
        }
        var phone1 = phoneObserv.get()
        if (phone1?.length ?: 0 > 0) {
            if (phone1.toString().substring(0,1) == "0"){
                if (phone1?.length != maxlenght + 1){
                    error = true
                    activity.binding.phone.setError(activity.getString(R.string.length_must_be) + " " + (maxlenght + 1).toString() )
                }
            }else {
                if (phone1?.length != maxlenght){
                    error = true
                    activity.binding.phone.setError(activity.getString(R.string.length_must_be) + " " + maxlenght.toString() )
                }
            }
        }

        if (!error) {
            Utilities.closeKeyboard(activity)
            if (activity.intent.hasExtra("type") == false) {
                regiterclick()
            }else {
                //regitersocialclick()
            }
        }
    }

    fun back(){
        activity.onBackPressed()
    }
    fun regiterclick(){
        Utilities.disabletouch(activity)
        isenable.set(false)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var phone1 = phoneObserv.get()
        if (phone1.toString().substring(0,1) == "0"){
            phone1 = phoneObserv.get().toString().substring(1 , phoneObserv.get()!!.length)
        }
        var type = ""
        if (LoginSession.gettype(activity) == 1){
            type = "user"
        }else if (LoginSession.gettype(activity) == 2){
            type = "merchant"
        }else if (LoginSession.gettype(activity) == 3){
            type = "technician"
        }
        val imageFile =  Utilities.uriToFile(activity,photoUri!!)
        val fileSizeInBytes = imageFile!!.length()
        val fileSizeInKB = fileSizeInBytes / 1024
        val fileSizeInMB = fileSizeInKB / 1024

        // Log the sizes
        Log.d("ImageCapture", "File size: $fileSizeInBytes bytes")
        Log.d("ImageCapture", "File size: $fileSizeInKB KB")
        Log.d("ImageCapture", "File size: $fileSizeInMB MB")

        val requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageFile)
        val imageBody = MultipartBody.Part.createFormData("business_logo", imageFile!!.name, requestFile)
        val name = RequestBody.create(MediaType.parse("text/plain"), nameObserv.get())
        val email = RequestBody.create(MediaType.parse("text/plain"), emailObserv.get() ?: "")
        val phone = RequestBody.create(MediaType.parse("text/plain"), phoneObserv.get())
        val address = RequestBody.create(MediaType.parse("text/plain"), addressObserv.get())
        val password = RequestBody.create(MediaType.parse("text/plain"), passwordObserv.get())
        val typeRequest = RequestBody.create(MediaType.parse("text/plain"), type)
        val token = RequestBody.create(MediaType.parse("text/plain"), token)
        val tokentype = RequestBody.create(MediaType.parse("text/plain"), "android")
        val remeber = RequestBody.create(MediaType.parse("text/plain"), "1")
        val tradename = RequestBody.create(MediaType.parse("text/plain"), tradenameObserv.get())
        val des = RequestBody.create(MediaType.parse("text/plain"), desObserv.get())

        val call: Call<LoginData?>? = apiService.merchantregister(imageBody,typeRequest,name,phone,email,address,tradename,password,password,token,tokentype,remeber,des)
        call?.enqueue(object : Callback<LoginData?> {
            override fun onResponse(call: Call<LoginData?>, response: Response<LoginData?>) {
                if (response.code() == 200 || response.code() == 201) {
                    LoginSession.setUserData(activity, response.body()!!)
                    LoginSession.setdata(activity)
                    var intent: Intent = Intent(activity, ActiveuserActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.putExtra("phone",phone1)
                    intent.putExtra("dialcode","+966")
                    intent.putExtra("verify",phone1)
                    activity.startActivity(intent)
                    activity.finish()
                }else {
                    val errorText = response.errorBody()?.string()
                    Log.e("data",errorText!!)
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            regiterclick()
                        }
                    })
                }
                isloading.set(false)
                isenable.set(true)
                Utilities.enabletouch(activity)

            }

            override fun onFailure(call: Call<LoginData?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                isenable.set(true)
                Utilities.enabletouch(activity)
            }
        })
    }
    fun createPartFromFileList(fieldName: String, imageFiles: List<RegisterimageModel>): List<MultipartBody.Part> {
        val parts = mutableListOf<MultipartBody.Part>()
        for (file in imageFiles) {
            val requestBody = RequestBody.create(MediaType.parse("image/jpeg"), Utilities.uriToFile(activity,file.data!!))
            parts.add(MultipartBody.Part.createFormData(fieldName, Utilities.uriToFile(activity,file.data!!)!!.name, requestBody))
        }
        return parts

    }


    fun passclick(){
        if (activity.binding.pass.inputType == InputType.TYPE_CLASS_TEXT){
            activity.binding.eyePass.setBackgroundResource(R.drawable.ic_mdi_eye_off_outline)
            activity.binding.pass.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        }else {
            activity.binding.pass.setInputType(InputType.TYPE_CLASS_TEXT)
            activity.binding.eyePass.setBackgroundResource(R.drawable.eye)
        }
    }
    fun passconfirmclick(){
        if (activity.binding.confirmpass.inputType == InputType.TYPE_CLASS_TEXT){
            activity.binding.eyeConfirm.setBackgroundResource(R.drawable.ic_mdi_eye_off_outline)
            activity.binding.confirmpass.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        }else {
            activity.binding.confirmpass.setInputType(InputType.TYPE_CLASS_TEXT)
            activity.binding.eyeConfirm.setBackgroundResource(R.drawable.eye)
        }
    }

    fun terms(){
        var intent: Intent = Intent(activity, StaticpageActivity::class.java)
        intent.putExtra("type",2)
        activity.startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    fun checkpermission(){
        val currentapiVersion = Build.VERSION.SDK_INT
        if (currentapiVersion >= Build.VERSION_CODES.R) {
//            if (Environment.isExternalStorageManager()) {
            val permissionCheck = ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.CAMERA
            )
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.CAMERA
                )
            ) {
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri: Uri = Uri.fromParts(
                    "package", activity.getPackageName(),
                    null
                )
                intent.data = uri
                activity.startActivity(intent)
            }else {
                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    cameraOperation()
                } else {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(
                            Manifest.permission.CAMERA
                            , Manifest.permission.READ_MEDIA_VIDEO,
                            Manifest.permission.READ_MEDIA_IMAGES,
                            Manifest.permission.READ_MEDIA_AUDIO,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE),
                        2
                    )
                }
            }
//            } else {
//                val intent = Intent()
//                intent.action = Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION
//                val uri: Uri = Uri.fromParts("package", activity.getPackageName(), null)
//                intent.data = uri
//                activity.startActivity(intent)
//            }
        } else if (currentapiVersion >= Build.VERSION_CODES.M) {
            val permissionCheck = ContextCompat.checkSelfPermission(
                activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        Manifest.permission.CAMERA
                    )
                ) {
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri: Uri = Uri.fromParts(
                        "package", activity.getPackageName(),
                        null
                    )
                    intent.data = uri
                    activity.startActivity(intent)
                }else {
                    val permissionCheck = ContextCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.CAMERA
                    )
                    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                        cameraOperation()
                    } else {
                        ActivityCompat.requestPermissions(
                            activity,
                            arrayOf(Manifest.permission.CAMERA),
                            2
                        )
                    }
                }
            } else {  //  PERMISSION_DENIED
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                ) {
                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    val uri: Uri = Uri.fromParts(
                        "package", activity.getPackageName(),
                        null
                    )
                    intent.data = uri
                    activity.startActivity(intent)
                }else {
                    ActivityCompat.requestPermissions(
                        activity,
                        arrayOf(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.MANAGE_EXTERNAL_STORAGE
                        ),
                        1
                    )
                }
            }
        } else {
            cameraOperation()

        }

    }
    fun isYouTubeLink(url: String): Boolean {
        // Define the regex pattern for YouTube URLs
        val youtubePattern = Regex("(https?://)?(www\\.)?(youtube\\.com|youtu\\.?be)/.+")

        // Check if the URL matches the pattern
        return youtubePattern.matches(url)
    }
    private fun openCamera() {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale("en")).format(Date())
        val imageFileName = "$timeStamp.jpg"
        val storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val pictureImagePath = "${storageDir.absolutePath}/$imageFileName"
        val file = File(pictureImagePath)
        val uri = Uri.fromFile(file) // local val, safe for smart cast
        photoUri = uri // assign to your class-level property if needed

        takePictureLauncher.launch(uri) // use the local `uri` here

    }

    private fun pickImageFromGallery() {
        pickImageLauncher.launch("image/*")
    }
    fun cameraOperation() {
        val alert = AlertDialog.Builder(
            Camera.activity
        )
        //		alert.setCancelable(false);
        alert.setTitle(Camera.activity!!.getString(R.string.plz_img))
        alert.setMessage(R.string.plz_camera_gallery)
        alert.setPositiveButton(
            Camera.activity!!.getString(R.string.camera)
        ) { dialog, which ->
            openCamera()
        }
        alert.setNegativeButton(
            activity!!.getString(R.string.images)
        ) { dialog, which ->
            pickImageFromGallery()
//                val currentapiVersion = Build.VERSION.SDK_INT
//                if (currentapiVersion >= Build.VERSION_CODES.M) {
//                    val permissionCheck = ContextCompat.checkSelfPermission(
//                        (activity)!!,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//                    )
//                    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
//                        pickImage()
//                    } else {  //  PERMISSION_DENIED
//                        ActivityCompat.requestPermissions(
//                            (activity)!!,
//                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
//                            permessionConstant
//                        )
//                    }
//                } else {
//                    pickImage()
//                }
        }
        alert.setNeutralButton(
            activity!!.getString(R.string.cancel)
        ) { dialog, which -> dialog.cancel() }
        alert.show()
    }

}
