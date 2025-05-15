package fudex.bonyad.SharedPreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import java.util.Locale;

/**
 * Created by ATIAF on 3/14/2018.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\u00042\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u0011\u001a\u00020\u00102\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0012\u001a\u00020\u0004J\u0016\u0010\u0013\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lfudex/bonyad/SharedPreferences/ConfigurationFile;", "", "()V", "LANGUAGE_KEY", "", "NOTIFICATIONS_KEY", "configFile", "Landroid/content/SharedPreferences;", "getCurrentLanguage", "context", "Landroid/content/Context;", "getNotificationStatus", "", "activity", "Landroid/app/Activity;", "initConfigSharedPreference", "", "setCurrentLanguage", "language", "setNotificationStatus", "isActive", "app_debug"})
public final class ConfigurationFile {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String LANGUAGE_KEY = "languageKey";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String NOTIFICATIONS_KEY = "notificationsKey";
    @org.jetbrains.annotations.Nullable()
    private static android.content.SharedPreferences configFile;
    @org.jetbrains.annotations.NotNull()
    public static final fudex.bonyad.SharedPreferences.ConfigurationFile INSTANCE = null;
    
    private ConfigurationFile() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getCurrentLanguage(@org.jetbrains.annotations.Nullable()
    android.content.Context context) {
        return null;
    }
    
    public final void setCurrentLanguage(@org.jetbrains.annotations.Nullable()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String language) {
    }
    
    public final void setNotificationStatus(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, boolean isActive) {
    }
    
    public final boolean getNotificationStatus(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return false;
    }
    
    private final void initConfigSharedPreference(android.content.Context context) {
    }
}