package fudex.bonyad.Helper;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import java.net.URISyntaxException;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\f\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\r"}, d2 = {"Lfudex/bonyad/Helper/PathUtil;", "", "()V", "getPath", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "isDownloadsDocument", "", "isExternalStorageDocument", "isMediaDocument", "app_debug"})
public final class PathUtil {
    @org.jetbrains.annotations.NotNull()
    public static final fudex.bonyad.Helper.PathUtil INSTANCE = null;
    
    private PathUtil() {
        super();
    }
    
    @android.annotation.SuppressLint(value = {"NewApi"})
    @kotlin.jvm.Throws(exceptionClasses = {java.net.URISyntaxException.class})
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPath(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri) throws java.net.URISyntaxException {
        return null;
    }
    
    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public final boolean isExternalStorageDocument(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
        return false;
    }
    
    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public final boolean isDownloadsDocument(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
        return false;
    }
    
    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public final boolean isMediaDocument(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
        return false;
    }
}