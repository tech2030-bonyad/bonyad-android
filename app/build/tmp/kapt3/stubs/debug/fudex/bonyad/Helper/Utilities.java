package fudex.bonyad.Helper;

import android.Manifest;
import fudex.bonyad.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.google.android.gms.maps.model.LatLng;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import androidx.recyclerview.widget.RecyclerView;
import fudex.bonyad.Model.Availability;
import java.io.File;
import java.io.IOException;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00a0\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u0018\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0018\u0010\u0012\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0006J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0007J\u000e\u0010\u001a\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010\u001d\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u0004J\u0016\u0010\u001f\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0006J\u0016\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&J\u000e\u0010\'\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u001cJ\u000e\u0010(\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u001cJ\u0016\u0010)\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\u0006J\u0016\u0010*\u001a\u00020$2\u0006\u0010+\u001a\u00020\u00042\u0006\u0010%\u001a\u00020&J\u000e\u0010,\u001a\u00020\r2\u0006\u0010-\u001a\u00020.J\u0018\u0010/\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u0002002\u0006\u0010\u0019\u001a\u00020\u0004H\u0007J\u001e\u00101\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u0004J\u001e\u00101\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u0004J\u001e\u00104\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u00042\u0006\u00103\u001a\u00020\u0004J\u0016\u00105\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u00102\u001a\u00020\u0004J\u000e\u00106\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fJ,\u00107\u001a\u0002082\u0006\u0010\u0018\u001a\u00020\u00042\f\u00109\u001a\b\u0012\u0004\u0012\u00020;0:2\u0006\u0010<\u001a\u00020\u00062\u0006\u0010=\u001a\u00020\u0004J&\u0010>\u001a\u0002082\u0006\u0010?\u001a\u00020\u00142\u0006\u0010@\u001a\u00020\u00142\u0006\u0010A\u001a\u00020\u00142\u0006\u0010B\u001a\u00020\u0014J\u000e\u0010C\u001a\u00020\u00042\u0006\u0010D\u001a\u00020\u0006J\u0018\u0010E\u001a\u00020\r2\u0006\u0010\u001b\u001a\u00020\u001c2\b\u0010F\u001a\u0004\u0018\u00010GJ\u000e\u0010H\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u0004J&\u0010I\u001a\u00020J2\u0006\u0010K\u001a\u00020J2\u0006\u0010!\u001a\u00020\u00062\u0006\u0010L\u001a\u00020J2\u0006\u0010M\u001a\u00020JJ.\u0010N\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u001c2\u0006\u0010O\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\u00042\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\r0RH\u0007J,\u0010S\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010T\u001a\u00020\u00042\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\r0VJ.\u0010W\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u001c2\u0006\u0010O\u001a\u00020\u00042\u0006\u0010P\u001a\u00020\u00042\f\u0010Q\u001a\b\u0012\u0004\u0012\u00020\r0RH\u0007J\u0018\u0010X\u001a\u0004\u0018\u00010Y2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010Z\u001a\u00020[R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\\"}, d2 = {"Lfudex/bonyad/Helper/Utilities;", "", "()V", "KEY_ALIAS", "", "LOCATION_REQUEST_PERMISSION", "", "LOCATION_SETTINGS_RESULT_CODE", "CalculationByDistance", "StartP", "Lcom/google/android/gms/maps/model/LatLng;", "EndP", "animImage", "", "context", "Landroid/content/Context;", "textViewObject", "Landroidx/cardview/widget/CardView;", "animImage1", "aroundUp", "", "number", "canDecimal", "changeformate", "date", "lang", "closeKeyboard", "activity", "Landroid/app/Activity;", "copyToClipboard", "text", "decrypt", "input", "shift", "decryptData", "encryptedData", "", "secretKey", "Ljavax/crypto/SecretKey;", "disabletouch", "enabletouch", "encrypt", "encryptData", "data", "expandRecyclerViewHeight", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "formatDateToArabic", "Lorg/joda/time/LocalDate;", "formatMessageDateTime", "pattern", "local", "formatMessageDateTime1", "formatMessageDateTimefromdate", "getDeviceId", "isOverlapping", "", "existingRanges", "", "Lfudex/bonyad/Model/Availability;", "index1", "type", "movetodistance", "orginlat", "orginlng", "destlat", "destlng", "pad", "time", "requestPermissions", "fragment", "Landroidx/fragment/app/Fragment;", "setDate", "shiftChar", "", "char", "start", "end", "showSuccessDialog", "title", "body", "onDismissAction", "Lkotlin/Function0;", "showTimePickerDialog", "initialTime", "onTimeSelected", "Lkotlin/Function1;", "showWithdrwasDialog", "uriToFile", "Ljava/io/File;", "uri", "Landroid/net/Uri;", "app_debug"})
public final class Utilities {
    public static final int LOCATION_REQUEST_PERMISSION = 1083;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String KEY_ALIAS = "tifoforsecuredata";
    public static final int LOCATION_SETTINGS_RESULT_CODE = 2913;
    @org.jetbrains.annotations.NotNull()
    public static final fudex.bonyad.Helper.Utilities INSTANCE = null;
    
    private Utilities() {
        super();
    }
    
    public final void requestPermissions(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.Nullable()
    androidx.fragment.app.Fragment fragment) {
    }
    
    public final void closeKeyboard(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDeviceId(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatMessageDateTime(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String pattern) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatMessageDateTimefromdate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String pattern) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatMessageDateTime(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String pattern, @org.jetbrains.annotations.NotNull()
    java.lang.String local) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatMessageDateTime1(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String pattern, @org.jetbrains.annotations.NotNull()
    java.lang.String local) {
        return null;
    }
    
    @android.annotation.SuppressLint(value = {"NewApi"})
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String changeformate(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.lang.String lang) {
        return null;
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String formatDateToArabic(@org.jetbrains.annotations.NotNull()
    org.joda.time.LocalDate date, @org.jetbrains.annotations.NotNull()
    java.lang.String lang) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String setDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String pad(int time) {
        return null;
    }
    
    public final void animImage(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.cardview.widget.CardView textViewObject) {
    }
    
    public final void animImage1(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.cardview.widget.CardView textViewObject) {
    }
    
    public final boolean movetodistance(double orginlat, double orginlng, double destlat, double destlng) {
        return false;
    }
    
    public final double aroundUp(double number, int canDecimal) {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String CalculationByDistance(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.maps.model.LatLng StartP, @org.jetbrains.annotations.NotNull()
    com.google.android.gms.maps.model.LatLng EndP) {
        return null;
    }
    
    public final void disabletouch(@org.jetbrains.annotations.NotNull()
    android.app.Activity context) {
    }
    
    public final void enabletouch(@org.jetbrains.annotations.NotNull()
    android.app.Activity context) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] encryptData(@org.jetbrains.annotations.NotNull()
    java.lang.String data, @org.jetbrains.annotations.NotNull()
    javax.crypto.SecretKey secretKey) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String decryptData(@org.jetbrains.annotations.NotNull()
    byte[] encryptedData, @org.jetbrains.annotations.NotNull()
    javax.crypto.SecretKey secretKey) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String encrypt(@org.jetbrains.annotations.NotNull()
    java.lang.String input, int shift) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String decrypt(@org.jetbrains.annotations.NotNull()
    java.lang.String input, int shift) {
        return null;
    }
    
    public final char shiftChar(char p0_1526187, int shift, char start, char end) {
        return '\u0000';
    }
    
    @android.annotation.SuppressLint(value = {"CutPasteId"})
    public final void showSuccessDialog(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String body, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismissAction) {
    }
    
    @android.annotation.SuppressLint(value = {"CutPasteId"})
    public final void showWithdrwasDialog(@org.jetbrains.annotations.NotNull()
    android.app.Activity context, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String body, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDismissAction) {
    }
    
    public final void copyToClipboard(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String text) {
    }
    
    public final void expandRecyclerViewHeight(@org.jetbrains.annotations.NotNull()
    androidx.recyclerview.widget.RecyclerView recyclerView) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.io.File uriToFile(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
        return null;
    }
    
    public final void showTimePickerDialog(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String initialTime, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onTimeSelected) {
    }
    
    public final boolean isOverlapping(@org.jetbrains.annotations.NotNull()
    java.lang.String date, @org.jetbrains.annotations.NotNull()
    java.util.List<fudex.bonyad.Model.Availability> existingRanges, int index1, @org.jetbrains.annotations.NotNull()
    java.lang.String type) {
        return false;
    }
}