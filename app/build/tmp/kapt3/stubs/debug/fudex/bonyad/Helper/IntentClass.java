package fudex.bonyad.Helper;

import android.app.Activity;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.provider.ContactsContract;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import fudex.bonyad.R;

/**
 * <h1>Implement reusable methods of all intent actions</h1>
 * IntentClass class for all actions of intent
 *
 *
 *
 * @author  kemo94
 * @version 1.0
 * @since   2017-08-9
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\'\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rJ&\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00062\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0014J$\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00062\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014J.\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001aJ\u000e\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001e\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\r2\u0006\u0010 \u001a\u00020\rJ\u0016\u0010!\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\"\u001a\u00020\rJ\u001e\u0010#\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\r2\u0006\u0010%\u001a\u00020\rJ\u0016\u0010&\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\'\u001a\u00020\rJ\'\u0010(\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\nJ\u000e\u0010)\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\"\u0010*\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00062\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u00122\u0006\u0010\u0013\u001a\u00020\u0014J\u0016\u0010+\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010,\u001a\u00020\rJ1\u0010-\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\r0/2\u0006\u0010 \u001a\u00020\r2\u0006\u00100\u001a\u000201\u00a2\u0006\u0002\u00102J\u0016\u00103\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u001e2\u0006\u00104\u001a\u00020\rJ\u000e\u00105\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u00106\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u001e\u00107\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00108\u001a\u00020\r2\u0006\u00109\u001a\u00020\rJ\u0016\u0010:\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\rJ\u000e\u0010<\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0006J\u0016\u0010=\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u00104\u001a\u00020\rJ\u000e\u0010>\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0006J\u0016\u0010?\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\'\u001a\u00020\rJ&\u0010@\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010A\u001a\u00020B2\u0006\u0010\f\u001a\u00020C2\u0006\u0010D\u001a\u00020\r\u00a8\u0006E"}, d2 = {"Lfudex/bonyad/Helper/IntentClass;", "", "()V", "goMap", "", "activity", "Landroid/app/Activity;", "lat", "", "lng", "(Landroid/app/Activity;Ljava/lang/Double;Ljava/lang/Double;)V", "goSharedata", "text", "", "sendTo", "goToActivity", "currentActivity", "targetClass", "Ljava/lang/Class;", "value", "Landroid/os/Bundle;", "goToActivityAndClear", "goToAddEvent", "title", "location", "begin", "", "end", "goToBluetooth", "goToEmail", "Landroid/content/Context;", "address", "subject", "goToFacebook", "id", "goToInsertContact", "name", "email", "goToLink", "url", "goToNavigate", "goToOpenWifiSettings", "goToResultActivity", "goToSearchWeb", "query", "goTocomposeEmail", "addresses", "", "attachment", "Landroid/net/Uri;", "(Landroid/app/Activity;[Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;)V", "goTodialPhoneNumber", "phoneNumber", "goTogoogleplay", "goTogoogleplay1", "goTowhatsApp", "smsNumber", "smsText", "openwhatsapp", "number", "rateApp", "sendSms", "shareapp", "shareurl", "toolBarSet", "back_image", "Landroid/widget/ImageView;", "Landroid/widget/TextView;", "string", "app_debug"})
public final class IntentClass {
    @org.jetbrains.annotations.NotNull()
    public static final fudex.bonyad.Helper.IntentClass INSTANCE = null;
    
    private IntentClass() {
        super();
    }
    
    public final void goToActivity(@org.jetbrains.annotations.NotNull()
    android.app.Activity currentActivity, @org.jetbrains.annotations.NotNull()
    java.lang.Class<?> targetClass, @org.jetbrains.annotations.Nullable()
    android.os.Bundle value) {
    }
    
    public final void toolBarSet(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    android.widget.ImageView back_image, @org.jetbrains.annotations.NotNull()
    android.widget.TextView text, @org.jetbrains.annotations.NotNull()
    java.lang.String string) {
    }
    
    public final void goToResultActivity(@org.jetbrains.annotations.NotNull()
    android.app.Activity currentActivity, @org.jetbrains.annotations.NotNull()
    java.lang.Class<?> targetClass, @org.jetbrains.annotations.NotNull()
    android.os.Bundle value) {
    }
    
    public final void rateApp(@org.jetbrains.annotations.NotNull()
    android.app.Activity currentActivity) {
    }
    
    public final void shareapp(@org.jetbrains.annotations.NotNull()
    android.app.Activity currentActivity) {
    }
    
    public final void shareurl(@org.jetbrains.annotations.NotNull()
    android.app.Activity currentActivity, @org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    public final void goToActivityAndClear(@org.jetbrains.annotations.NotNull()
    android.app.Activity currentActivity, @org.jetbrains.annotations.NotNull()
    java.lang.Class<?> targetClass, @org.jetbrains.annotations.Nullable()
    android.os.Bundle value) {
    }
    
    public final void goTodialPhoneNumber(@org.jetbrains.annotations.NotNull()
    android.content.Context currentActivity, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber) {
    }
    
    public final void goToFacebook(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String id) {
    }
    
    public final void goToLink(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    public final void goTogoogleplay(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    public final void goTogoogleplay1(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    public final void goSharedata(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    java.lang.String sendTo) {
    }
    
    public final void goMap(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.Nullable()
    java.lang.Double lat, @org.jetbrains.annotations.Nullable()
    java.lang.Double lng) {
    }
    
    public final void goTowhatsApp(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String smsNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String smsText) {
    }
    
    public final void openwhatsapp(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String number) {
    }
    
    public final void goToOpenWifiSettings(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    public final void goToNavigate(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.Nullable()
    java.lang.Double lat, @org.jetbrains.annotations.Nullable()
    java.lang.Double lng) {
    }
    
    public final void goToBluetooth(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    public final void sendSms(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber) {
    }
    
    public final void goToAddEvent(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String location, long begin, long end) {
    }
    
    public final void goToInsertContact(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String email) {
    }
    
    public final void goTocomposeEmail(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String[] addresses, @org.jetbrains.annotations.NotNull()
    java.lang.String subject, @org.jetbrains.annotations.NotNull()
    android.net.Uri attachment) {
    }
    
    public final void goToEmail(@org.jetbrains.annotations.NotNull()
    android.content.Context activity, @org.jetbrains.annotations.NotNull()
    java.lang.String address, @org.jetbrains.annotations.NotNull()
    java.lang.String subject) {
    }
    
    public final void goToSearchWeb(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
}