package fudex.bonyad.viewmodel.user

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.StrictMode
import android.provider.Settings
import android.util.Log
import android.view.Gravity
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.adapters.TextViewBindingAdapter
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.bumptech.glide.Glide
import com.google.gson.Gson

import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Camera
import fudex.bonyad.Helper.Camera.Companion
import fudex.bonyad.Helper.Camera.Companion.fileUri
import fudex.bonyad.Helper.Camera.Companion.pickImage
import fudex.bonyad.Helper.Camera.Companion.pictureImagePath
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Helper.Validations

import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.user.EdituserdataActivity

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import onnetysolutions.sadded.Model.ProfileModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat

import java.util.*


class EdituserprofileViewModel(activity: EdituserdataActivity) : BaseObservable() {
    var activity: EdituserdataActivity = EdituserdataActivity()
    val nameObserv = ObservableField<String>()
    val emailObserv = ObservableField<String>()
    var img = ObservableField<String>("")
    val locname = ObservableField<String>("")
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    val phone = ObservableField<String>()
    val code = ObservableField<String>()
    var image = ""
    private var month = "-1"
    var birthdate = ""
    var stateId = 0
    var possitionId = 0
    var footId = 0
    var userdata = ObservableField<ProfileModel>()
    var color = ObservableField<Int>(Color.parseColor("#E51D35"))
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>
    private lateinit var pickImageLauncher: ActivityResultLauncher<String>
    private var photoUri: Uri? = null
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
    init {
        this.activity = activity
        if (activity.getString(R.string.lang) == "ar"){
            activity.binding.name.gravity = Gravity.RIGHT
            activity.binding.email.gravity = Gravity.RIGHT
        }
        getuserinfo()
        Camera.activity = activity
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        takePictureLauncher = this.activity.registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success && photoUri != null) {
                //this.activity.binding.photo.setImageURI(photoUri)
                Glide.with(activity)
                    .load(photoUri)
                    .into( this.activity.binding.photo)
                image = photoUri?.path.toString()
            }
        }

        // Gallery launcher
        pickImageLauncher = this.activity.registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                photoUri = it
                image = photoUri?.path.toString()
                Glide.with(activity)
                    .load(photoUri)
                    .into( this.activity.binding.photo)
            }
        }
       // changecolor()
    }


    fun validateInput() {
        var error = false
        if (nameObserv.get() == null || nameObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.name.setError(activity.getString(R.string.required))
        }
        if (emailObserv.get() == null || emailObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.email.setError(activity.getString(R.string.required))
        }
        if (emailObserv.get() != null && emailObserv.get()!!.isNotEmpty()  && !Validations.isValidEmail(emailObserv.get().toString().trim())) {
            error = true
            activity.binding.email.setError(activity.getString(R.string.email_foramt_is_wrong))
        }
        if (!error) {
            Utilities.closeKeyboard(activity)
            if (image == "" ) {
                editparofile()
            }else {
                editprofilewithimage()
            }
        }
    }

    fun back(){
        activity.onBackPressed()
    }
    fun editparofile(){
        Utilities.disabletouch(activity)
        isenable.set(false)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient1(activity)!!.create(
            ApiInterface::class.java)
        var requestBody: RequestBody? = null
        requestBody =  MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("name", nameObserv.get().toString())
            .addFormDataPart("email", emailObserv.get().toString())
            .build()
        Log.e("name",nameObserv.get().toString())
        val call: Call<ProfileModel?>? = apiService.editprofile(requestBody)
        call?.enqueue(object : Callback<ProfileModel?> {
            override fun onResponse(call: Call<ProfileModel?>, response: Response<ProfileModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = LoginSession.getUserData1(activity)
                    data.user == response.body()!!.data
                    LoginSession.setUserData(activity, data)
                    LoginSession.setdata(activity)
                    back()
                }else {
                    val errorText = response.errorBody()?.string()
                    Log.e("data",errorText!!)
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            editparofile()
                        }
                    })
                }
                isloading.set(false)
                isenable.set(true)
                Utilities.enabletouch(activity)

            }

            override fun onFailure(call: Call<ProfileModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                isenable.set(true)
                Utilities.enabletouch(activity)
            }
        })
    }
    fun editprofilewithimage(){
        Utilities.disabletouch(activity)
        isenable.set(false)
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        val imageFile =  File(image)
        val fileSizeInBytes = imageFile.length()
        val fileSizeInKB = fileSizeInBytes / 1024
        val fileSizeInMB = fileSizeInKB / 1024

        // Log the sizes
        Log.d("ImageCapture", "File size: $fileSizeInBytes bytes")
        Log.d("ImageCapture", "File size: $fileSizeInKB KB")
        Log.d("ImageCapture", "File size: $fileSizeInMB MB")

        val requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageFile)
        val imageBody = MultipartBody.Part.createFormData("avatar", imageFile.name, requestFile)
        val name = RequestBody.create(MediaType.parse("text/plain"), nameObserv.get())
        val email = RequestBody.create(MediaType.parse("text/plain"), emailObserv.get())

        val call: Call<ProfileModel?>? = apiService.editprofilewithimage(imageBody,name,email)
        call?.enqueue(object : Callback<ProfileModel?> {
            override fun onResponse(call: Call<ProfileModel?>, response: Response<ProfileModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    Dialogs.showToast(response.body()!!.message!!,activity)
                    var data = LoginSession.getUserData1(activity)
                    data.user == response.body()!!.data
                    LoginSession.setUserData(activity, data)
                    LoginSession.setdata(activity)
                    back()
                }else {
                    val errorText = response.errorBody()?.string()
                    Log.e("data",errorText!!)
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            editprofilewithimage()
                        }
                    })
                }
                isloading.set(false)
                isenable.set(true)
                Utilities.enabletouch(activity)

            }

            override fun onFailure(call: Call<ProfileModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
                isenable.set(true)
                Utilities.enabletouch(activity)
            }
        })
    }

    fun getuserinfo() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)

        val call: Call<ProfileModel?>? = apiService.getprofiledata()
        call?.enqueue(object : Callback<ProfileModel?> {
            override fun onResponse(call: Call<ProfileModel?>, response: Response<ProfileModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    if (data?.data?.avatar == null){
                        data?.data?.avatar = ""
                    }
                    img.set(data?.data?.avatar!!)
                    userdata.set(data)
                    nameObserv.set(data?.data?.name!!)
                    emailObserv.set(data?.data?.email!!)
                    notifyChange()
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getuserinfo()
                        }
                    })
                }

                isloading.set(false)
            }

            override fun onFailure(call: Call<ProfileModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)

            }
        })
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
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss",Locale("en")).format(Date())
        val imageFileName = "$timeStamp.jpg"
        val storageDir = Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS
        )
        var pictureImagePath = storageDir.absolutePath + "/" + imageFileName
        val file = File(pictureImagePath)
        photoUri = Uri.fromFile(file)
        takePictureLauncher.launch(photoUri)
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
