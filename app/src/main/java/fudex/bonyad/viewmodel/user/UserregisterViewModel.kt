package fudex.bonyad.viewmodel.user

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.StrictMode
import android.provider.Settings
import android.text.InputFilter
import android.text.InputType
import android.util.Log
import android.view.Gravity
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.databinding.adapters.TextViewBindingAdapter
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.google.gson.Gson
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Data.Userdata
import fudex.bonyad.Helper.Camera
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Helper.Validations
import fudex.bonyad.Model.LoginData
import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.ActiveuserActivity
import fudex.bonyad.ui.Activity.StaticpageActivity
import fudex.bonyad.ui.Activity.user.UserregisterActivity
import okhttp3.MediaType


import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

import kotlin.collections.ArrayList


class UserregisterViewModel(activity: UserregisterActivity) : BaseObservable() {
    var activity: UserregisterActivity = UserregisterActivity()
    val phoneObserv = ObservableField<String>()
    val emailObserv = ObservableField<String>()
    val passwordObserv = ObservableField<String>()
    val confirmpasswordObserv = ObservableField<String>()
    val nameObserv = ObservableField<String>()
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    val phone = ObservableField<String>()
    val code = ObservableField<String>()
    var maxlenght = 0
    var image = ""
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
            activity.binding.confirmpass.gravity = Gravity.RIGHT
        }
        Camera.activity = activity
        maxlenght = 9
        code.set("+966")
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        activity.binding.main.setOnClickListener {
            Utilities.closeKeyboard(activity)
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
        if (emailObserv.get() == null || emailObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.email.setError(activity.getString(R.string.required))
        }
        if (emailObserv.get() != null && emailObserv.get()!!.isNotEmpty()  && !Validations.isValidEmail(emailObserv.get().toString().trim())) {
            error = true
            activity.binding.email.setError(activity.getString(R.string.email_foramt_is_wrong))
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
        }else if (LoginSession.gettype(activity) == 3){
            type = "technician"
        }
        var userdata = Userdata(type,phone1,passwordObserv.get(),"ddd",1,emailObserv.get(),confirmpasswordObserv.get(), name = nameObserv.get())
        val call: Call<LoginData?>? = apiService.register(userdata)
        call?.enqueue(object : Callback<LoginData?> {
            override fun onResponse(call: Call<LoginData?>, response: Response<LoginData?>) {
                if (response.code() == 200 || response.code() == 201) {
                    LoginSession.setUserData(activity, response.body()!!)
                    LoginSession.setdata(activity)
                    var intent: Intent = Intent(activity, ActiveuserActivity::class.java)
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    intent.putExtra("phone",phone1)
                    intent.putExtra("dialcode","+966")
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
//    fun regitersocialclick(){
//        Utilities.disabletouch(activity)
//        isenable.set(false)
//        isloading.set(true)
//        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
//            ApiInterface::class.java)
//        var requestBody: RequestBody? = null
//        var phone1 = phoneObserv.get()
//        if (phone1.toString().substring(0,1) == "0"){
//            phone1 = phoneObserv.get().toString().substring(1 , phoneObserv.get()!!.length)
//        }
//        val imageFile =  File(image)
//        val name = RequestBody.create(MediaType.parse("text/plain"), nameObserv.get())
//        val phone = RequestBody.create(MediaType.parse("text/plain"), phone1)
//        val email = RequestBody.create(MediaType.parse("text/plain"), emailObserv.get())
//        val socailId = RequestBody.create(MediaType.parse("text/plain"), activity.intent.getStringExtra("token"))
//        val socailtype = RequestBody.create(MediaType.parse("text/plain"), activity.intent.getIntExtra("type",0).toString())
//        val fcmtoken = RequestBody.create(MediaType.parse("text/plain"), FirebaseInstanceId.getInstance().getToken())
//        val deciceId = RequestBody.create(MediaType.parse("text/plain"), Utilities.getDeviceId(activity))
//        val devicetype = RequestBody.create(MediaType.parse("text/plain"), "android")
//        val call: Call<LoginData?>? = apiService.registersocail(name,phone,email,socailId,socailtype,fcmtoken,devicetype, deciceId)
//        call?.enqueue(object : Callback<LoginData?> {
//            override fun onResponse(call: Call<LoginData?>, response: Response<LoginData?>) {
//                if (response.code() == 200 || response.code() == 201) {
//                    LoginSession.setUserData(activity, response.body()!!)
//                    LoginSession.setdata(activity)
//                    var intent: Intent = Intent(activity, VerifycodeActivity::class.java)
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
//                    intent.putExtra("phone",phone1)
//                    intent.putExtra("dialcode","+966")
//                    activity.startActivity(intent)
//                    activity.finish()
//                }else {
//                    val errorText = response.errorBody()?.string()
//                    Log.e("data",errorText!!)
//                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
//                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
//                        override fun onRefresh() {
//                            regitersocialclick()
//                        }
//                    })
//                }
//                isloading.set(false)
//                isenable.set(true)
//                Utilities.enabletouch(activity)
//
//            }
//
//            override fun onFailure(call: Call<LoginData?>, t: Throwable) {
//                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
//                isloading.set(false)
//                isenable.set(true)
//                Utilities.enabletouch(activity)
//            }
//        })
//    }

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
                    Camera.cameraOperation()
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
                        Camera.cameraOperation()
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
            Camera.cameraOperation()

        }

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
        activity.startActivity(intent)
    }

}
