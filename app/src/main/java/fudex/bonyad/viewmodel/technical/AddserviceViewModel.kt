package fudex.bonyad.viewmodel.technical

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.StrictMode
import android.provider.Settings
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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import atiaf.redstone.NetWorkConnction.RetrofitClient
import com.bumptech.glide.Glide
import com.google.gson.Gson

import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.Helper.Camera
import fudex.bonyad.Helper.Dialogs
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Helper.Utilities
import fudex.bonyad.Model.Certificate
import fudex.bonyad.Model.StatesModel

import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.technical.AddserviceActivity
import fudex.bonyad.ui.Adapter.technical.Certificatesadapter
import fudex.bonyad.ui.Adapter.technical.Imagessadapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import onnetysolutions.sadded.Model.ProfileModel
import onnetysolutions.sadded.Model.RegisterimageModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.net.URL
import java.text.SimpleDateFormat

import java.util.*


class AddserviceViewModel(activity: AddserviceActivity) : BaseObservable() {
    var activity: AddserviceActivity = AddserviceActivity()
    val nameObserv = ObservableField<String>()
    val desObserv = ObservableField<String>()
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    var images: ArrayList<RegisterimageModel> = ArrayList()
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>
    private lateinit var pickImageLauncher: ActivityResultLauncher<String>
    private var photoUri: Uri? = null
    var type = 0
    private val imagessadapter = Imagessadapter()
    @SuppressLint("RestrictedApi")
    fun onnameChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            nameObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun ondesChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            desObserv.set(s.toString())
        }
    }

    init {
        this.activity = activity
        if (activity.getString(R.string.lang) == "ar"){
            activity.binding.name.gravity = Gravity.RIGHT
            activity.binding.des.gravity = Gravity.RIGHT
        }
        var linearlayout = LinearLayoutManager(activity)
        linearlayout!!.orientation = LinearLayoutManager.HORIZONTAL
        activity.binding.photos.layoutManager = linearlayout
        activity.binding.photos.adapter = imagessadapter
        Camera.activity = activity
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        takePictureLauncher = this.activity.registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success && photoUri != null) {
                //this.activity.binding.photo.setImageURI(photoUri)
//                Glide.with(activity)
//                    .load(photoUri)
//                    .into( this.activity.binding.photo)
//                image = photoUri?.path.toString()
                images.add(RegisterimageModel(photoUri,photoUri?.path.toString()))
                notifyChange()
            }
        }

        // Gallery launcher
        pickImageLauncher = this.activity.registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                images.add(RegisterimageModel(it,it?.path.toString()))
                notifyChange()
            }
        }
    }


    fun validateInput() {
        var error = false
        if (nameObserv.get() == null || nameObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.name.setError(activity.getString(R.string.required))
        }
        if (desObserv.get() == null || desObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.des.setError(activity.getString(R.string.required))
        }
        if (images.size == 0){
            error = true
            Dialogs.showToast(activity.getString(R.string.select) + " "  + activity.getString(R.string.service_images),activity)
        }
        if (!error) {
            Utilities.closeKeyboard(activity)
            activity.lifecycleScope.launch {
                addservice()
            }
        }
    }

    fun back(){
        activity.onBackPressed()
    }

    fun addservice(){
        activity.lifecycleScope.launch {
            Utilities.disabletouch(activity)
            isenable.set(false)
            isloading.set(true)
            val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
                ApiInterface::class.java
            )
            val imageParts = createPartFromFileList("images[]", images)
            val name = RequestBody.create(MediaType.parse("text/plain"), nameObserv.get())
            val description = RequestBody.create(MediaType.parse("text/plain"), desObserv.get())

            val call: Call<ErrorResponse?>? = apiService.addservice(imageParts,name,description)
            call?.enqueue(object : Callback<ErrorResponse?> {
                override fun onResponse(
                    call: Call<ErrorResponse?>,
                    response: Response<ErrorResponse?>
                ) {
                    if (response.code() == 200 || response.code() == 201) {
                        Dialogs.showToast(response.body()!!.message!!, activity)
                        back()
                    } else {
                        val errorText = response.errorBody()?.string()
                        Log.e("data", errorText!!)
                        val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                        APIModel.handleFailure1(
                            activity,
                            response.code(),
                            errorResponse,
                            object : APIModel.RefreshTokenListener {
                                override fun onRefresh() {
                                    addservice()
                                }
                            })
                    }
                    isloading.set(false)
                    isenable.set(true)
                    Utilities.enabletouch(activity)

                }

                override fun onFailure(call: Call<ErrorResponse?>, t: Throwable) {
                    Dialogs.showToast(activity.getString(R.string.check_your_connection), activity)
                    isloading.set(false)
                    isenable.set(true)
                    Utilities.enabletouch(activity)
                }
            })
        }
    }
    fun createPartFromFileList(fieldName: String, imageFiles: List<RegisterimageModel>): List<MultipartBody.Part> {
        val parts = mutableListOf<MultipartBody.Part>()
        for (file in imageFiles) {
            val requestBody = RequestBody.create(MediaType.parse("image/jpeg"), Utilities.uriToFile(activity,file.data!!))
            parts.add(MultipartBody.Part.createFormData(fieldName, Utilities.uriToFile(activity,file.data!!)!!.name, requestBody))
        }
        return parts

    }

    suspend fun glideUrlToUri(context: Context, imageUrl: String): File {
        return withContext(Dispatchers.IO) {
            val futureTarget = Glide.with(context)
                .asFile()
                .load(imageUrl)
                .submit()

            val file = futureTarget.get() // This blocks, so keep it in IO thread
            file // Return the cached file
        }
    }

    suspend fun downloadUrlToFile(context: Context, urlString: String): File {
        val url = URL(urlString)
        val connection = url.openConnection()
        connection.connect()

        val inputStream = connection.getInputStream()
        val file = File.createTempFile("downloaded_", ".jpg", context.cacheDir)

        file.outputStream().use { output ->
            inputStream.copyTo(output)
        }

        return file
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
