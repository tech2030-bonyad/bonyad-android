package fudex.bonyad.Helper

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.min
object ImageCompressor {
    /**
     * This doesn't compress the original image file.
     * It compresses the bitmap and updates it to the new file and returns from app cache
     */
    @Throws(Exception::class)
    fun compressBitmap(context: Context, bitmap1: Bitmap, cb: ((File) -> Unit)? = null) {
        val bitmap = bitmap1
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss" , Locale("en")).format(Date())
        val name = "$timeStamp.jpg"
        val file1 = File(Environment.getExternalStorageDirectory(), "Download")
        if (!file1.exists()) {
            file1.mkdirs()
        }
        val result = File(file1.absolutePath + File.separator.toString() + name)
        val fOut = FileOutputStream(result)
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut)
        fOut.flush() // Not really required
        fOut.close() // do not forget to close the stream
        bitmap.recycle() //recycle the bitmap
        cb?.invoke(result)
    }
    fun compressImage(image: Bitmap): Bitmap? {
        val outputStream = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.PNG, 80, outputStream)
        val compressedByteArray = outputStream.toByteArray()
        val compressedBitmap = BitmapFactory.decodeByteArray(compressedByteArray, 0, compressedByteArray.size)
        outputStream.close()
        return compressedBitmap
//        val baos = ByteArrayOutputStream()
//        image.compress(Bitmap.CompressFormat.JPEG, 80, baos) // 100baos
//        var options = 80
//        while (baos.toByteArray().size / 1024 > (5 * 1024)) { // 100kb,
//            baos.reset() // baosbaos
//            image.compress(Bitmap.CompressFormat.JPEG, options, baos) // options%baos
//            options -= 10 // 10
//        }
//        val isBm = ByteArrayInputStream(
//            baos.toByteArray()
//        ) // baosByteArrayInputStream
//        return BitmapFactory.decodeStream(isBm, null, null)
//        return image
    }
    private fun compressBitmap(bitmap: Bitmap, quality: Int): Bitmap {

        val stream = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.WEBP,quality,stream)

        val byteArray = stream.toByteArray()


        return BitmapFactory.decodeByteArray(byteArray,0,byteArray.size)


    }
    /**
     * This compress the original file.
     */
    @Throws(Exception::class)
    fun compressCurrentBitmapFile(originalImageFile: File) {
        val bitmap = updateDecodeBounds(originalImageFile)
        val fOut = FileOutputStream(originalImageFile)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fOut)
        fOut.flush() // Not really required
        fOut.close() // do not forget to close the stream
        bitmap.recycle() //recycle the bitmap
    }

    /**
     * Measure decodeBounds of the bitmap from given File.
     */
    private fun updateDecodeBounds(imageFile: File): Bitmap {
        return BitmapFactory.Options().run {
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(imageFile.absolutePath, this)
            val sampleHeight = if (outWidth > outHeight) 900 else 1100
            val sampleWidth = if (outWidth > outHeight) 1100 else 900
            /**
             * You can tweak the sizes 900, 1100.
             * The bigger the number is, the more details you can keep.
             * The lesser, the lesser quality of details.
             */
            inSampleSize = min(outWidth / sampleWidth, outHeight / sampleHeight)
            inJustDecodeBounds = false
            BitmapFactory.decodeFile(imageFile.absolutePath, this)
        }
    }
    fun Context.getPicturesFile(fileName: String, subDirectory: String = ""): File {
        return File(this.cacheDir.absolutePath.plus("/$subDirectory"), fileName)
    }
}