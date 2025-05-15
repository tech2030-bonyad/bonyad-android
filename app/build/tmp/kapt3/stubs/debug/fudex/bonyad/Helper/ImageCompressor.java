package fudex.bonyad.Helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0016\b\u0002\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u0004\u0018\u00010\nJ\u0018\u0010\u0003\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u000e\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000bJ\u0010\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\bJ\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u000bH\u0002J\u001c\u0010\u0015\u001a\u00020\u000b*\u00020\u00062\u0006\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u0017\u00a8\u0006\u0019"}, d2 = {"Lfudex/bonyad/Helper/ImageCompressor;", "", "()V", "compressBitmap", "", "context", "Landroid/content/Context;", "bitmap1", "Landroid/graphics/Bitmap;", "cb", "Lkotlin/Function1;", "Ljava/io/File;", "bitmap", "quality", "", "compressCurrentBitmapFile", "originalImageFile", "compressImage", "image", "updateDecodeBounds", "imageFile", "getPicturesFile", "fileName", "", "subDirectory", "app_debug"})
public final class ImageCompressor {
    @org.jetbrains.annotations.NotNull()
    public static final fudex.bonyad.Helper.ImageCompressor INSTANCE = null;
    
    private ImageCompressor() {
        super();
    }
    
    /**
     * This doesn't compress the original image file.
     * It compresses the bitmap and updates it to the new file and returns from app cache
     */
    @kotlin.jvm.Throws(exceptionClasses = {java.lang.Exception.class})
    public final void compressBitmap(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap1, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.io.File, kotlin.Unit> cb) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.Bitmap compressImage(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap image) {
        return null;
    }
    
    private final android.graphics.Bitmap compressBitmap(android.graphics.Bitmap bitmap, int quality) {
        return null;
    }
    
    /**
     * This compress the original file.
     */
    @kotlin.jvm.Throws(exceptionClasses = {java.lang.Exception.class})
    public final void compressCurrentBitmapFile(@org.jetbrains.annotations.NotNull()
    java.io.File originalImageFile) throws java.lang.Exception {
    }
    
    /**
     * Measure decodeBounds of the bitmap from given File.
     */
    private final android.graphics.Bitmap updateDecodeBounds(java.io.File imageFile) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.io.File getPicturesFile(@org.jetbrains.annotations.NotNull()
    android.content.Context $this$getPicturesFile, @org.jetbrains.annotations.NotNull()
    java.lang.String fileName, @org.jetbrains.annotations.NotNull()
    java.lang.String subDirectory) {
        return null;
    }
}