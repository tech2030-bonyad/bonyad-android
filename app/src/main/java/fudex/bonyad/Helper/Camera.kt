package fudex.bonyad.Helper

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.*
import android.media.ExifInterface
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import fudex.bonyad.R
import java.io.*
import java.text.SimpleDateFormat
import java.util.*


class Camera() : AppCompatActivity() {
    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        // save file url in bundle as it will be null on screen orientation
        // changes
        outState.putParcelable("file_uri", fileUri)
    }

    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri")
    }

    var selectedImage: Bitmap? = null
    var inputStream: InputStream? = null
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            permessionConstant -> {
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    openCamera()
                } else {
                    Toast.makeText(activity, R.string.please_activate_permission, Toast.LENGTH_LONG)
                }
                return
            }
        }
    }

    companion object {
        //    private static final String TAG = activity.class.getSimpleName();
        val CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100
        private val CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200
        private val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 100
        val PICK_PHOTO_FOR_AVATAR = 1
        var fileUri: Uri? = null
        val MEDIA_TYPE_IMAGE = 1
        val MEDIA_TYPE_VIDEO = 2
        var myBitmap: Bitmap? = null
        var activity: Activity? = null
        var pictureImagePath: String? = ""

        //-----------------------------------------------------Camera Started----------------------------------------------------------------------------------------------------------------------------------
        fun cameraOperation() {
            val alert = AlertDialog.Builder(
                activity
            )
            //		alert.setCancelable(false);
            alert.setTitle(activity!!.getString(R.string.plz_img))
            alert.setMessage(R.string.plz_camera_gallery)
            alert.setPositiveButton(
                activity!!.getString(R.string.camera)
            ) { dialog, which ->
                val currentapiVersion = Build.VERSION.SDK_INT
                if (currentapiVersion >= Build.VERSION_CODES.M) {
                    val permissionCheck = ContextCompat.checkSelfPermission(
                        (activity)!!,
                        Manifest.permission.CAMERA
                    )
                    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                        openCamera()
                    } else {  //  PERMISSION_DENIED
                        ActivityCompat.requestPermissions(
                            (activity)!!,
                            arrayOf(Manifest.permission.CAMERA),
                            permessionConstant
                        )
                    }
                } else {
                    openCamera()
                }
            }
            alert.setNegativeButton(
                activity!!.getString(R.string.images)
            ) { dialog, which ->
                pickImage()
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

        var sourceFile: File? = null
        fun pickImage() {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            activity!!.startActivityForResult(intent, PICK_PHOTO_FOR_AVATAR)
        }

        /**
         * Receiving activity result method will be called after closing the camera
         */
        var selectedImagePath = ""
        @SuppressLint("NewApi")
        fun getPath(uri: Uri?): String? {
            if (uri == null) {
                return null
            }
            val projection = arrayOf(MediaStore.Images.Media.DATA)
            var cursor: Cursor? = null
            if (Build.VERSION.SDK_INT > 19) {
                try {
                    var `is`: InputStream? = null
                    if (uri.authority != null) {
                        try {
                            `is` = activity!!.contentResolver.openInputStream(uri)
                            val bmp = BitmapFactory.decodeStream(`is`)
                            return writeToTempImageAndGetPathUri(activity, bmp).toString()
                        } catch (e: FileNotFoundException) {
                            e.printStackTrace()
                        } finally {
                            try {
                                `is`!!.close()
                            } catch (e: IOException) {
                                e.printStackTrace()
                            }
                        }
                    }
                } catch (e: IllegalArgumentException) {
                    e.printStackTrace()
                }
            } else {
                cursor = activity!!.contentResolver.query(uri, projection, null, null, null)
            }
            var path: String? = null
            try {
                val column_index = cursor!!
                    .getColumnIndex(MediaStore.Images.Media.DATA)
                cursor!!.moveToFirst()
                path = cursor.getString(column_index).toString()
                cursor.close()
            } catch (e: NullPointerException) {
                e.printStackTrace()
            } catch (e: ArrayIndexOutOfBoundsException) {
                e.printStackTrace()
            }
            return path
        }

        private fun getOutputMediaFile(type: Int): File? {

            // External sdcard location
            val mediaStorageDir = File(
                Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                Config.IMAGE_DIRECTORY_NAME
            )

            // Create the storage directory if it does not exist
            if (!mediaStorageDir.exists()) {
                if (!mediaStorageDir.mkdirs()) {
                    Log.d(
                        ContentValues.TAG, ("Oops! Failed create "
                                + Config.IMAGE_DIRECTORY_NAME) + " directory"
                    )
                    return null
                }
            }

            // Create a media file dialog_name
            val timeStamp = SimpleDateFormat(
                "yyyyMMdd_HHmmss",
                Locale.getDefault()
            ).format(Date())
            val mediaFile: File
            if (type == MEDIA_TYPE_IMAGE) {
                mediaFile = File(
                    mediaStorageDir.path + File.separator
                            + "IMG_" + timeStamp + ".jpg"
                )
            } else {
                return null
            }
            return mediaFile
        }

        fun getOutputMediaFileUri(type: Int): Uri {
            return Uri.fromFile(getOutputMediaFile(type))
        }

        fun startInstalledAppDetailsActivity(context: Activity?) { // da bygt7ly saf7t l setting mn abl l permessions
            if (context == null) {
                return
            }
            val i = Intent()
            i.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            i.addCategory(Intent.CATEGORY_DEFAULT)
            i.data = Uri.parse("package:" + context.packageName)
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
            i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS)
            context.startActivity(i)
        }

        fun openCamera() {
            val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss",Locale("en")).format(Date())
            val imageFileName = "$timeStamp.jpg"
            val storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
            )
            pictureImagePath = storageDir.absolutePath + "/" + imageFileName
            val file = File(pictureImagePath)
            fileUri = Uri.fromFile(file)
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            activity!!.startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE)
        }

        private val permessionConstant = 0
        fun saveBitmapToFile(file: File): File? {  // n2alel size l sooora
            try {

                // BitmapFactory options to downsize the image
                val o = BitmapFactory.Options()
                o.inJustDecodeBounds = true
                o.inSampleSize = 6
                // factor of downsizing the image
                var inputStream = FileInputStream(file)
                //Bitmap selectedBitmap = null;
                BitmapFactory.decodeStream(inputStream, null, o)
                inputStream.close()

                // The new size we want to scale to
                val REQUIRED_SIZE = 75

                // Find the correct scale value. It should be the power of 2.
                var scale = 1
                while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                    o.outHeight / scale / 2 >= REQUIRED_SIZE
                ) {
                    scale *= 2
                }
                val o2 = BitmapFactory.Options()
                o2.inSampleSize = scale
                inputStream = FileInputStream(file)
                val selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2)
                inputStream.close()

                // here i override the original image file
                file.createNewFile()
                val outputStream = FileOutputStream(file)
                selectedBitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                return file
            } catch (e: Exception) {
                return null
            }
        }

        fun decodeFile(f: File?): Bitmap? {
            try {
                //Decode image size
                val o = BitmapFactory.Options()
                o.inJustDecodeBounds = true
                BitmapFactory.decodeStream(FileInputStream(f), null, o)

                //The new size we want to scale to
                val REQUIRED_SIZE = 70

                //Find the correct scale value. It should be the power of 2.
                var scale = 1
                while (o.outWidth / scale / 2 >= REQUIRED_SIZE && o.outHeight / scale / 2 >= REQUIRED_SIZE) scale *= 2

                //Decode with inSampleSize
                val o2 = BitmapFactory.Options()
                o2.inSampleSize = scale
                return BitmapFactory.decodeStream(FileInputStream(f), null, o2)
            } catch (e: FileNotFoundException) {
            }
            return null
        }

        fun writeToTempImageAndGetPathUri(inContext: Context?, inImage: Bitmap): Uri {
            val bytes = ByteArrayOutputStream()
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path = MediaStore.Images.Media.insertImage(
                inContext!!.contentResolver,
                inImage,
                "Title",
                null
            )
            return Uri.parse(path)
        }

        fun compressImage(imageUri: String): String {
            val filePath = getRealPathFromURI(imageUri)
            val bitmapImage = BitmapFactory.decodeFile(filePath)

//            val nh = (bitmapImage.height * (512.0 / bitmapImage.width)).toInt()
//            val scaledBitmap = Bitmap.createScaledBitmap(bitmapImage, 512, nh, true)
//            var scaledBitmap: Bitmap? = null
//            val options = BitmapFactory.Options()
//
////      by setting this field as true, the actual bitmap pixels are not loaded in the memory. Just the bounds are loaded. If
////      you try the use the bitmap here, you will get null.
//            options.inJustDecodeBounds = true
//            var bmp = BitmapFactory.decodeFile(filePath, options)
//            var actualHeight = options.outHeight
//            var actualWidth = options.outWidth
//
////      max Height and width values of the compressed image is taken as 816x612
//            val maxHeight = 816.0f
//            val maxWidth = 612.0f
//            var imgRatio = (actualWidth / actualHeight).toFloat()
//            val maxRatio = maxWidth / maxHeight
//
////      width and height values are set maintaining the aspect ratio of the image
//            if (actualHeight > maxHeight || actualWidth > maxWidth) {
//                if (imgRatio < maxRatio) {
//                    imgRatio = maxHeight / actualHeight
//                    actualWidth = (imgRatio * actualWidth).toInt()
//                    actualHeight = maxHeight.toInt()
//                } else if (imgRatio > maxRatio) {
//                    imgRatio = maxWidth / actualWidth
//                    actualHeight = (imgRatio * actualHeight).toInt()
//                    actualWidth = maxWidth.toInt()
//                } else {
//                    actualHeight = maxHeight.toInt()
//                    actualWidth = maxWidth.toInt()
//                }
//            }
//
////      setting inSampleSize value allows to load a scaled down version of the original image
//            options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight)
//
////      inJustDecodeBounds set to false to load the actual bitmap
//            options.inJustDecodeBounds = false
//
////      this options allow android to claim the bitmap memory if it runs low on memory
//            options.inPurgeable = true
//            options.inInputShareable = true
//            options.inTempStorage = ByteArray(16 * 1024)
//            try {
////          load the bitmap from its path
//                bmp = BitmapFactory.decodeFile(filePath, options)
//            } catch (exception: OutOfMemoryError) {
//                exception.printStackTrace()
//            }
//            try {
//                scaledBitmap =
//                    Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888)
//            } catch (exception: OutOfMemoryError) {
//                exception.printStackTrace()
//            }
//            val ratioX = actualWidth / options.outWidth.toFloat()
//            val ratioY = actualHeight / options.outHeight.toFloat()
//            val middleX = actualWidth / 2.0f
//            val middleY = actualHeight / 2.0f
//            val scaleMatrix = Matrix()
//            scaleMatrix.setScale(ratioX, ratioY, middleX, middleY)
//            val canvas = Canvas((scaledBitmap)!!)
//            canvas.setMatrix(scaleMatrix)
//            canvas.drawBitmap(
//                bmp,
//                middleX - bmp.width / 2,
//                middleY - bmp.height / 2,
//                Paint(Paint.FILTER_BITMAP_FLAG)
//            )
//
////      check the rotation of the image and display it properly
//            val exif: ExifInterface
//            try {
//                exif = ExifInterface((filePath)!!)
//                val orientation = exif.getAttributeInt(
//                    ExifInterface.TAG_ORIENTATION, 0
//                )
//                Log.d("EXIF", "Exif: $orientation")
//                val matrix = Matrix()
//                if (orientation == 6) {
//                    matrix.postRotate(90f)
//                    Log.d("EXIF", "Exif: $orientation")
//                } else if (orientation == 3) {
//                    matrix.postRotate(180f)
//                    Log.d("EXIF", "Exif: $orientation")
//                } else if (orientation == 8) {
//                    matrix.postRotate(270f)
//                    Log.d("EXIF", "Exif: $orientation")
//                }
//                scaledBitmap = Bitmap.createBitmap(
//                    (scaledBitmap), 0, 0,
//                    scaledBitmap!!.width, scaledBitmap.height, matrix,
//                    true
//                )
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
            var out: FileOutputStream? = null
            val filename = filename
            try {
                out = FileOutputStream(filename)

//          write the compressed bitmap at the destination specified by filename.
                bitmapImage!!.compress(Bitmap.CompressFormat.JPEG, 50, out)
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            return filename
        }

        val filename: String
            get() {
                val file = File(
                    Environment.getExternalStorageDirectory().path,
                    "MyFolder/Images"
                )
                if (!file.exists()) {
                    file.mkdirs()
                }
                return (file.absolutePath + "/" + System.currentTimeMillis() + ".jpg")
            }

        private fun getRealPathFromURI(contentURI: String): String? {
            val contentUri = Uri.parse(contentURI)
            val cursor = activity!!.contentResolver.query(contentUri, null, null, null, null)
            if (cursor == null) {
                return contentUri.path
            } else {
                cursor.moveToFirst()
                val index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
                return cursor.getString(index)
            }
        }

        fun calculateInSampleSize(
            options: BitmapFactory.Options,
            reqWidth: Int,
            reqHeight: Int
        ): Int {
            val height = options.outHeight
            val width = options.outWidth
            var inSampleSize = 1
            if (height > reqHeight || width > reqWidth) {
                val heightRatio = Math.round(height.toFloat() / reqHeight.toFloat())
                val widthRatio = Math.round(width.toFloat() / reqWidth.toFloat())
                inSampleSize = if (heightRatio < widthRatio) heightRatio else widthRatio
            }
            val totalPixels = (width * height).toFloat()
            val totalReqPixelsCap = (reqWidth * reqHeight * 2).toFloat()
            while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
                inSampleSize++
            }
            return inSampleSize
        }

        //------------------------------------------------------------------------------------------------------------------------------
        // omar camera
        fun getImageUri(inContext: Context, inImage: Bitmap): Uri {
            val bytes = ByteArrayOutputStream()
            inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
            val path = MediaStore.Images.Media.insertImage(
                inContext.contentResolver,
                inImage,
                "Title",
                null
            )
            fileUri = Uri.parse(path)
            return Uri.parse(path)
        }

        fun getRealPathFromURI(uri: Uri?): String {
            fileUri = uri
            val cursor = activity!!.contentResolver.query(
                (uri)!!, null, null, null, null
            )
            cursor!!.moveToFirst()
            val idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA)
            return cursor.getString(idx)
        }
    }
}
