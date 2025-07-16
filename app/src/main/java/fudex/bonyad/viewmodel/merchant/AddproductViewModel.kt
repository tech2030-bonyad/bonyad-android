package fudex.bonyad.viewmodel.merchant

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
import fudex.bonyad.Model.ProductsDatum
import fudex.bonyad.Model.StatesDatum
import fudex.bonyad.Model.StatesModel

import fudex.bonyad.NetWorkConnction.ApiInterface
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import fudex.bonyad.ui.Adapter.technical.Certificatesadapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import fudex.bonyad.Model.ProfileModel
import fudex.bonyad.ui.Activity.merchant.AddproductActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.net.URL
import java.text.SimpleDateFormat

import java.util.*


class AddproductViewModel(activity: AddproductActivity) : BaseObservable() {
    var activity: AddproductActivity = AddproductActivity()
    val nameObserv = ObservableField<String>()
    val priceObserv = ObservableField<String>()
    val quantityObserv = ObservableField<String>()
    val desObserv = ObservableField<String>()
    var isloading: ObservableBoolean = ObservableBoolean(false)
    var isenable: ObservableBoolean = ObservableBoolean(false)
    val dep = ObservableField<String>("")
    val unit = ObservableField<String>("")
    val productdata = ObservableField<ProductsDatum>()
    var unitId = 0
    var depId = 0
    var depList: ArrayList<StatesDatum> = ArrayList()
    var unitList: ArrayList<StatesDatum> = ArrayList()
    var images: ArrayList<Certificate> = ArrayList()
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
    fun onpriceChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            priceObserv.set(s.toString())
        }
    }
    @SuppressLint("RestrictedApi")
    fun onquantityChanged(): TextViewBindingAdapter.OnTextChanged {
        return TextViewBindingAdapter.OnTextChanged { s, start, before, count ->
            quantityObserv.set(s.toString())
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
            activity.binding.price.gravity = Gravity.RIGHT
            activity.binding.quantity.gravity = Gravity.RIGHT
            activity.binding.des.gravity = Gravity.RIGHT
        }
        activity.binding.des.movementMethod = ScrollingMovementMethod()
        var linearlayout = LinearLayoutManager(activity)
        linearlayout!!.orientation = LinearLayoutManager.HORIZONTAL
        activity.binding.photos.layoutManager = linearlayout
        activity.binding.photos.adapter = imagessadapter
      //  getuserinfo()
        Camera.activity = activity
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        getdeps()
        getunits()
        takePictureLauncher = this.activity.registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success && photoUri != null) {
                //this.activity.binding.photo.setImageURI(photoUri)
                images.add(Certificate(0,"",photoUri,photoUri?.path.toString()))
                notifyChange()
            }
        }

        // Gallery launcher
        pickImageLauncher = this.activity.registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            uri?.let {
                images.add(Certificate(0,"",it,it.path.toString()))
                notifyChange()
            }
        }
        if (activity.intent.hasExtra("data")){
            val product = Gson().fromJson(activity.intent.getStringExtra("data"), ProductsDatum::class.java)
            productdata.set(product)
            dep.set(product.category ?: "")
            unit.set(product.unit ?: "")
            unitId = product.unit_id ?: 0
            depId = product.category_id ?: 0
            activity.binding.title.text = activity.getString(R.string.edit_product)
            nameObserv.set(product.name ?: "")
            desObserv.set(product.description ?: "")
            priceObserv.set(product.price ?: "")
            quantityObserv.set(product.quantity ?: "")
            activity.binding.name.setText(product.name ?: "")
            activity.binding.price.setText(product.price ?: "")
            activity.binding.quantity.setText(product.quantity ?: "")
            activity.binding.des.setText(product.description ?: "")
            images.addAll(product.images!!)
            notifyChange()
        }
       // changecolor()
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
        if (priceObserv.get() == null || priceObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.price.setError(activity.getString(R.string.required))
        }
        if (quantityObserv.get() == null || quantityObserv.get()!!.isEmpty()) {
            error = true
            activity.binding.quantity.setError(activity.getString(R.string.required))
        }
        if (images.size == 0){
            error = true
            Dialogs.showToast(activity.getString(R.string.please_add_at_least_one_image),activity)
        }
        if (depId == 0){
            error = true
            Dialogs.showToast(activity.getString(R.string.select) + " " + activity.getString(R.string.department),activity)
        }
        if (unitId == 0){
            error = true
            Dialogs.showToast(activity.getString(R.string.select) + " " + activity.getString(R.string.product_unit),activity)
        }

        if (!error) {
            Utilities.closeKeyboard(activity)
            activity.lifecycleScope.launch {
                addproduct()
            }
        }
    }

    fun back(){
        activity.onBackPressed()
    }

    fun addproduct(){
        activity.lifecycleScope.launch {
            Utilities.disabletouch(activity)
            isenable.set(false)
            isloading.set(true)
            val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
                ApiInterface::class.java
            )
            val imageParts = createPartFromFileList(activity,"images[]", images)
            val name = RequestBody.create(MediaType.parse("text/plain"), nameObserv.get())
            val price = RequestBody.create(MediaType.parse("text/plain"), priceObserv.get() ?: "")
            val quantity = RequestBody.create(MediaType.parse("text/plain"), quantityObserv.get())
            val des = RequestBody.create(MediaType.parse("text/plain"), desObserv.get())
            val dep = RequestBody.create(MediaType.parse("text/plain"), depId.toString())
            val unit = RequestBody.create(MediaType.parse("text/plain"), unitId.toString())

            val call: Call<ErrorResponse?>? = if (activity.intent.hasExtra("data")){apiService.editproduct(productdata.get()?.id.toString() ,imageParts,name,dep, des,price,quantity,unit)}else{apiService.addproduct(imageParts,name,dep, des,price,quantity,unit)}
            call?.enqueue(object : Callback<ErrorResponse?> {
                override fun onResponse(
                    call: Call<ErrorResponse?>,
                    response: Response<ErrorResponse?>
                ) {
                    if (response.code() == 200 || response.code() == 201) {
                        Utilities.showSuccessDialog(activity, response.body()!!.message ?: "" ,
                            activity.getString(R.string.you_can_follow_the_product_details) ){
                            back()
                        }
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
                                    addproduct()
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



    fun getdeps() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var call: Call<StatesModel?>? = null
        call = apiService.getdeps()
        call?.enqueue(object : Callback<StatesModel?> {
            override fun onResponse(call: Call<StatesModel?>, response: Response<StatesModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    depList.clear()
                    depList.addAll(data?.data!!)
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getdeps()
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
    fun getunits() {
        isloading.set(true)
        val apiService: ApiInterface = RetrofitClient.getClient(activity)!!.create(
            ApiInterface::class.java)
        var call: Call<StatesModel?>? = null
        call = apiService.getunits()
        call?.enqueue(object : Callback<StatesModel?> {
            override fun onResponse(call: Call<StatesModel?>, response: Response<StatesModel?>) {
                if (response.code() == 200 || response.code() == 201) {
                    var data = response.body()
                    unitList.clear()
                    unitList.addAll(data?.data!!)
                }else {
                    val errorText = response.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorText, ErrorResponse::class.java)
                    APIModel.handleFailure1(activity, response.code(), errorResponse, object : APIModel.RefreshTokenListener {
                        override fun onRefresh() {
                            getunits()
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
    fun depclick() {
        var secPopUp = PopupMenu(activity, activity.binding.depLin)
        for (i in depList.indices) {
            secPopUp?.menu?.add(i, i, i, depList[i].name)

        }
        secPopUp?.setOnMenuItemClickListener { item ->
            dep.set(depList[item.itemId].name!!)
            depId = depList[item.itemId].id!!
            notifyChange()
            false
        }
        secPopUp.show()
    }
    fun untclick() {
        var secPopUp = PopupMenu(activity, activity.binding.unitLin)
        for (i in unitList.indices) {
            secPopUp?.menu?.add(i, i, i, unitList[i].name)

        }
        secPopUp?.setOnMenuItemClickListener { item ->
            unit.set(unitList[item.itemId].name!!)
            unitId = unitList[item.itemId].id!!
            notifyChange()
            false
        }
        secPopUp.show()
    }

}
