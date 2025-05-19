package fudex.bonyad.viewmodel.technical

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
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
import android.widget.PopupMenu
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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
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
import fudex.bonyad.Model.Certificate
import fudex.bonyad.Model.StatesDatum
import fudex.bonyad.Model.StatesModel

import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Activity.technical.EdittechnicaldataActivity
import fudex.bonyad.ui.Activity.user.EdituserdataActivity
import fudex.bonyad.ui.Adapter.technical.Certificatesadapter
import fudex.bonyad.ui.Adapter.technical.Imagessadapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import onnetysolutions.sadded.Model.DistanceModel
import onnetysolutions.sadded.Model.ProfileModel
import onnetysolutions.sadded.Model.RegisterimageModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.net.URL
import java.text.SimpleDateFormat

import java.util.*


class EdittechnicalprofileViewModel(activity: EdittechnicaldataActivity) : BaseObservable() {
    var activity: EdittechnicaldataActivity = EdittechnicaldataActivity()
    val nameObserv = ObservableField<String>()
    val emailObserv = ObservableField<String>()
    val addressObserv = ObservableField<String>()
    var img = ObservableField<String>("")
    val locname = ObservableField<String>("")
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    val phone = ObservableField<String>()
    val code = ObservableField<String>()
    val zonename = ObservableField<String>("")
    var zoneId = 0
    var image = ""
    private var month = "-1"
    var birthdate = ""
    var stateId = 0
    var possitionId = 0
    var footId = 0
    val expertname = ObservableField<String>("")
    var exeperienceList : ArrayList<DistanceModel> = ArrayList()
    var zoneList: ArrayList<StatesDatum> = ArrayList()
    var images: ArrayList<Certificate> = ArrayList()
    var userdata = ObservableField<ProfileModel>()
    var color = ObservableField<Int>(Color.parseColor("#E51D35"))
    private lateinit var takePictureLauncher: ActivityResultLauncher<Uri>
    private lateinit var pickImageLauncher: ActivityResultLauncher<String>
    private var photoUri: Uri? = null
    var type = 0
    private val imagessadapter = Certificatesadapter()
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
    init {
        this.activity = activity
        if (activity.getString(R.string.lang) == "ar"){
            activity.binding.name.gravity = Gravity.RIGHT
            activity.binding.email.gravity = Gravity.RIGHT
            activity.binding.address.gravity = Gravity.RIGHT
        }
        var linearlayout = LinearLayoutManager(activity)
        linearlayout!!.orientation = LinearLayoutManager.HORIZONTAL
        activity.binding.photos.layoutManager = linearlayout
        activity.binding.photos.adapter = imagessadapter
        getuserinfo()
        Camera.activity = activity
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        exeperienceList.add(DistanceModel(1, "1"))
        exeperienceList.add(DistanceModel(2, "2"))
        exeperienceList.add(DistanceModel(3,"3"))
        exeperienceList.add(DistanceModel(4, "4"))
        exeperienceList.add(DistanceModel(5, "5"))
        exeperienceList.add(DistanceModel(6, "6"))
        exeperienceList.add(DistanceModel(7, "7"))
        exeperienceList.add(DistanceModel(8, "8"))
        exeperienceList.add(DistanceModel(9, "9"))
        exeperienceList.add(DistanceModel(10, "+10"))
        getzones()
        takePictureLauncher = this.activity.registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success && photoUri != null) {
                //this.activity.binding.photo.setImageURI(photoUri)
               if (type == 1){
                   Glide.with(activity)
                       .load(photoUri)
                       .into( this.activity.binding.photo)
                   image = photoUri?.path.toString()
               }else if (type == 2){
                   images.add(Certificate(0,"",photoUri,photoUri?.path.toString()))
                   notifyChange()
               }
            }
        }

        // Gallery launcher
        pickImageLauncher = this.activity.registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                if (type == 1){
                    photoUri = it
                    Glide.with(activity)
                        .load(it)
                        .into( this.activity.binding.photo)
                    image = it.path.toString()
                }else if (type == 2){
                    images.add(Certificate(0,"",it,it.path.toString()))
                    notifyChange()
                }
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
            activity.lifecycleScope.launch {
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
        activity.lifecycleScope.launch {
            Utilities.disabletouch(activity)
            isenable.set(false)
            isloading.set(true)
            val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
                ApiInterface::class.java
            )
            var requestFile: RequestBody? = null
            var imageBody: MultipartBody.Part? = null
            if (image != "") {
                val imageFile = Utilities.uriToFile(activity, photoUri!!)
                requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imageFile)
                imageBody =
                    MultipartBody.Part.createFormData("avatar", imageFile!!.name, requestFile)
            }
            val imageParts = createPartFromFileList(activity,"certificates[]", images)
            val name = RequestBody.create(MediaType.parse("text/plain"), nameObserv.get())
            val email = RequestBody.create(MediaType.parse("text/plain"), emailObserv.get())
            val address = RequestBody.create(MediaType.parse("text/plain"), addressObserv.get())
            val zone = RequestBody.create(MediaType.parse("text/plain"), zoneId.toString())
            val expereinece = RequestBody.create(MediaType.parse("text/plain"), expertname.get())

            val call: Call<ProfileModel?>? = apiService.edittechnicalprofilewithimage(
                if (image == "") {
                    null
                } else {
                    imageBody
                }, if (imageParts.size == 0) {
                    null
                } else {
                    imageParts
                }, name, email, address, expereinece, zone
            )
            call?.enqueue(object : Callback<ProfileModel?> {
                override fun onResponse(
                    call: Call<ProfileModel?>,
                    response: Response<ProfileModel?>
                ) {
                    if (response.code() == 200 || response.code() == 201) {
                        Dialogs.showToast(response.body()!!.message!!, activity)
                        var data = LoginSession.getUserData1(activity)
                        data.user == response.body()!!.data
                        LoginSession.setUserData(activity, data)
                        LoginSession.setdata(activity)
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
                                    editprofilewithimage()
                                }
                            })
                    }
                    isloading.set(false)
                    isenable.set(true)
                    Utilities.enabletouch(activity)

                }

                override fun onFailure(call: Call<ProfileModel?>, t: Throwable) {
                    Dialogs.showToast(activity.getString(R.string.check_your_connection), activity)
                    isloading.set(false)
                    isenable.set(true)
                    Utilities.enabletouch(activity)
                }
            })
        }
    }
    suspend fun createPartFromFileList(
        context: Context,
        fieldName: String,
        imageFiles: List<Certificate>
    ): List<MultipartBody.Part> = withContext(Dispatchers.IO) {
        val parts = mutableListOf<MultipartBody.Part>()

        for (file in imageFiles) {
            if (file.id == 0) {
                val requestBody = RequestBody.create(MediaType.parse("image/jpeg"), Utilities.uriToFile(activity,file.uri!!))
                parts.add(MultipartBody.Part.createFormData(fieldName, Utilities.uriToFile(activity,file.uri!!)!!.name, requestBody))
            } else {
                val url = file.url ?: continue
                try {
                    val downloadedFile = Glide.with(context)
                        .asFile()
                        .load(url)
                        .submit()
                        .get()

                    val requestBody = RequestBody.create(MediaType.parse("image/jpeg"), downloadedFile)
                    parts.add(MultipartBody.Part.createFormData(fieldName, downloadedFile.name, requestBody))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        return@withContext parts
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
                    addressObserv.set(data?.data?.address!!)
                    zoneId = data?.data?.zone_id!!
                    zonename.set(data?.data?.zone!!)
                    expertname.set(data?.data?.experience_years!!)
                    images.clear()
                    images.addAll(data?.data?.certificates!!)
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
    fun getzones() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var call: Call<StatesModel?>? = null
        call = apiService.getzones()
        call?.enqueue(object : Callback<StatesModel?> {
            override fun onResponse(call: Call<StatesModel?>, response: Response<StatesModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    zoneList.clear()
                    zoneList.addAll(data?.data!!)
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getzones()
                        }
                    })
                }

                isloading.set(false)
            }

            override fun onFailure(call: Call<StatesModel?>, t: Throwable) {
                Dialogs.showToast(activity.getString(R.string.check_your_connection) , activity)
                isloading.set(false)
            }
        })
    }
    @RequiresApi(Build.VERSION_CODES.R)
    fun checkpermission(type : Int){
        this.type = type
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
    fun expertclick() {
        var secPopUp = PopupMenu(activity, activity.binding.expertLin)
        for (i in exeperienceList.indices) {
            secPopUp?.menu?.add(i, i, i, exeperienceList[i].title)

        }
        secPopUp?.setOnMenuItemClickListener { item ->
            expertname.set(exeperienceList[item.itemId].title!!)
            false
        }
        secPopUp.show()
    }
    fun zoneclick() {
        var secPopUp = PopupMenu(activity, activity.binding.zoneLin)
        for (i in zoneList.indices) {
            secPopUp?.menu?.add(i, i, i, zoneList[i].name)

        }
        secPopUp?.setOnMenuItemClickListener { item ->
            zonename.set(zoneList[item.itemId].name!!)
            zoneId = zoneList[item.itemId].id!!
            false
        }
        secPopUp.show()
    }
}
