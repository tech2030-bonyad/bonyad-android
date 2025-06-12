package fudex.bonyad.SharedPreferences;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import fudex.bonyad.Model.AddressesDatum;
import fudex.bonyad.Model.LoginData;
import fudex.bonyad.Model.UserModel;
import fudex.bonyad.ui.Activity.ActiveuserActivity;
import fudex.bonyad.ui.Activity.LoginActivity;
import fudex.bonyad.Model.LoginModel;

/**
 * Created by ATIAF on 3/14/2018.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0016\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u00106\u001a\u0002072\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;J\u0016\u0010<\u001a\u0002072\u0006\u00108\u001a\u00020=2\u0006\u0010>\u001a\u00020\u0004J\u0016\u0010?\u001a\u0002072\u0006\u00108\u001a\u00020=2\u0006\u0010@\u001a\u00020\u0015J\u0016\u0010A\u001a\u0002072\u0006\u00108\u001a\u00020=2\u0006\u0010\u0014\u001a\u00020\u001bJ\u000e\u0010B\u001a\u0002072\u0006\u00108\u001a\u000209J\u000e\u0010C\u001a\u0002072\u0006\u00108\u001a\u00020=J\u000e\u0010D\u001a\u0002072\u0006\u00108\u001a\u000209J\u000e\u0010E\u001a\u0002072\u0006\u00108\u001a\u000209J\u000e\u0010F\u001a\u0002072\u0006\u00108\u001a\u00020=J\u0016\u0010G\u001a\u0002072\u0006\u00108\u001a\u0002092\u0006\u0010H\u001a\u00020\u0004J\u000e\u0010I\u001a\u00020\u00042\u0006\u00108\u001a\u00020=J\u000e\u0010I\u001a\u00020\u00042\u0006\u00108\u001a\u000209J\u0015\u0010J\u001a\u0004\u0018\u00010K2\u0006\u00108\u001a\u00020=\u00a2\u0006\u0002\u0010LJ\u000e\u0010M\u001a\u00020\u00152\u0006\u00108\u001a\u00020=J\u000e\u0010N\u001a\u00020\u00042\u0006\u00108\u001a\u00020=J\u0010\u0010O\u001a\u00020P2\b\u00108\u001a\u0004\u0018\u000109J\u0010\u0010Q\u001a\u00020R2\b\u00108\u001a\u0004\u0018\u000109J\u000e\u0010S\u001a\u00020\u00152\u0006\u00108\u001a\u00020=J\u000e\u0010T\u001a\u00020;2\u0006\u00108\u001a\u000209J\u000e\u0010U\u001a\u00020\u00152\u0006\u00108\u001a\u00020=J\u000e\u0010V\u001a\u00020\u001b2\u0006\u00108\u001a\u00020=J\u000e\u0010W\u001a\u00020\u00042\u0006\u00108\u001a\u00020=J\u000e\u0010X\u001a\u00020\u00152\u0006\u00108\u001a\u00020=J\u000e\u0010Y\u001a\u00020\u001b2\u0006\u00108\u001a\u00020=J\u0010\u0010Z\u001a\u0002072\u0006\u0010[\u001a\u000209H\u0002J\u0010\u0010\\\u001a\u0002072\u0006\u0010[\u001a\u000209H\u0002J\u0010\u0010]\u001a\u0002072\u0006\u0010[\u001a\u000209H\u0002J\u000e\u0010^\u001a\u00020\u001b2\u0006\u00108\u001a\u00020=J\u000e\u0010_\u001a\u0002072\u0006\u00108\u001a\u000209J\u0016\u0010`\u001a\u0002072\u0006\u00108\u001a\u00020=2\u0006\u0010a\u001a\u00020\u0015J&\u0010b\u001a\u0002072\u0006\u00108\u001a\u00020=2\u0016\u0010c\u001a\u0012\u0012\u0004\u0012\u00020\u00150dj\b\u0012\u0004\u0012\u00020\u0015`eJ\u001e\u0010f\u001a\u0002072\u0006\u00108\u001a\u00020=2\u0006\u0010g\u001a\u00020\u00042\u0006\u0010h\u001a\u00020KJ\u0016\u0010i\u001a\u0002072\u0006\u00108\u001a\u00020=2\u0006\u0010j\u001a\u00020RJ\u0016\u0010i\u001a\u0002072\u0006\u00108\u001a\u00020=2\u0006\u0010j\u001a\u00020kJ\u0016\u0010l\u001a\u0002072\u0006\u00108\u001a\u0002092\u0006\u0010j\u001a\u00020RJ\u0010\u0010m\u001a\u0004\u0018\u00010k2\u0006\u00108\u001a\u000209J\u0016\u0010n\u001a\u0002072\u0006\u00108\u001a\u00020=2\u0006\u0010o\u001a\u00020\u0015J\u0016\u0010p\u001a\u0002072\u0006\u00108\u001a\u00020=2\u0006\u0010o\u001a\u00020\u001bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u0004X\u0086D\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u00020\u0015X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001e\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010%\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001e\u0010&\u001a\u0004\u0018\u00010 X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010%\u001a\u0004\b\'\u0010\"\"\u0004\b(\u0010$R\u001c\u0010)\u001a\u0004\u0018\u00010\u0013X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R\u0010\u0010.\u001a\u0004\u0018\u00010\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\u000e\"\u0004\b1\u00102R\u001a\u00103\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u0010\u000e\"\u0004\b5\u00102\u00a8\u0006q"}, d2 = {"Lfudex/bonyad/SharedPreferences/LoginSession;", "", "()V", "ACCESS_TOKEN_KEY", "", "EXPIRE_KEY", "IS_LOGIN_KEY", "NEWS_COUNT_KEY", "NOTIFICATION", "NOTIFICATION_COUNT_KEY", "SELECTED_CITIES_KEY", "USER_COUNTRY_KEY", "USER_DATA_KEY", "getUSER_DATA_KEY", "()Ljava/lang/String;", "USER_LOCATION_KEY", "USER_ONBOARDING_KEY", "USER_TYPE_KEY", "countryFile", "Landroid/content/SharedPreferences;", "expired", "", "getExpired", "()I", "setExpired", "(I)V", "isLogin", "", "()Z", "setLogin", "(Z)V", "lat", "", "getLat", "()Ljava/lang/Double;", "setLat", "(Ljava/lang/Double;)V", "Ljava/lang/Double;", "lng", "getLng", "setLng", "loginFile", "getLoginFile", "()Landroid/content/SharedPreferences;", "setLoginFile", "(Landroid/content/SharedPreferences;)V", "onboardingFile", "refresh_token", "getRefresh_token", "setRefresh_token", "(Ljava/lang/String;)V", "token", "getToken", "setToken", "AddAddress", "", "activity", "Landroid/content/Context;", "country", "Lfudex/bonyad/Model/AddressesDatum;", "Addservice", "Landroid/app/Activity;", "data", "Addtype", "type", "Addwelcome", "activeuser", "clearData", "clearData1", "clearData2", "clearaddress", "forceupdate", "txt", "getAccessToken", "getExpire", "", "(Landroid/app/Activity;)Ljava/lang/Long;", "getNewsCount", "getSelectedCitiesForAdmin", "getUserData", "Lfudex/bonyad/Model/LoginModel;", "getUserData1", "Lfudex/bonyad/Model/LoginData;", "getUserType", "getaddress", "getenabletrip", "getnotification", "getservice", "gettype", "getwelcome", "initLoginSharedPreference", "context", "initcountrySharedPreference", "initonboardingSharedPreference", "isLoggedIn", "maintaninece", "setNewsCount", "count", "setSelectedCitiesForAdmin", "selectedCitiesIds", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "setTokenData", "accessToken", "expireKey", "setUserData", "user", "Lfudex/bonyad/Model/UserModel;", "setUserData1", "setdata", "setenabletrip", "enable", "setnotification", "app_debug"})
public final class LoginSession {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String USER_DATA_KEY = "userData";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String USER_COUNTRY_KEY = "usercountry";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String USER_ONBOARDING_KEY = "useronboarding";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String IS_LOGIN_KEY = "isLogin";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ACCESS_TOKEN_KEY = "accessTokenKey";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String EXPIRE_KEY = "expireKey";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String USER_TYPE_KEY = "userTypeKey";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String USER_LOCATION_KEY = "userLocationKey";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String NOTIFICATION_COUNT_KEY = "notificationCountKey";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String NEWS_COUNT_KEY = "newsCountKey";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String SELECTED_CITIES_KEY = "selectedCitiesKey";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String NOTIFICATION = "notification";
    private static boolean isLogin = false;
    @org.jetbrains.annotations.Nullable()
    private static java.lang.Double lat;
    @org.jetbrains.annotations.Nullable()
    private static java.lang.Double lng;
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String token = "";
    @org.jetbrains.annotations.NotNull()
    private static java.lang.String refresh_token = "";
    private static int expired = 0;
    @org.jetbrains.annotations.Nullable()
    private static android.content.SharedPreferences loginFile;
    @org.jetbrains.annotations.Nullable()
    private static android.content.SharedPreferences countryFile;
    @org.jetbrains.annotations.Nullable()
    private static android.content.SharedPreferences onboardingFile;
    @org.jetbrains.annotations.NotNull()
    public static final fudex.bonyad.SharedPreferences.LoginSession INSTANCE = null;
    
    private LoginSession() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getUSER_DATA_KEY() {
        return null;
    }
    
    public final boolean isLogin() {
        return false;
    }
    
    public final void setLogin(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getLat() {
        return null;
    }
    
    public final void setLat(@org.jetbrains.annotations.Nullable()
    java.lang.Double p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Double getLng() {
        return null;
    }
    
    public final void setLng(@org.jetbrains.annotations.Nullable()
    java.lang.Double p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getToken() {
        return null;
    }
    
    public final void setToken(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRefresh_token() {
        return null;
    }
    
    public final void setRefresh_token(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getExpired() {
        return 0;
    }
    
    public final void setExpired(int p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.content.SharedPreferences getLoginFile() {
        return null;
    }
    
    public final void setLoginFile(@org.jetbrains.annotations.Nullable()
    android.content.SharedPreferences p0) {
    }
    
    private final void initLoginSharedPreference(android.content.Context context) {
    }
    
    private final void initcountrySharedPreference(android.content.Context context) {
    }
    
    private final void initonboardingSharedPreference(android.content.Context context) {
    }
    
    public final void setUserData(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Model.LoginData user) {
    }
    
    public final void setUserData1(@org.jetbrains.annotations.NotNull()
    android.content.Context activity, @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Model.LoginData user) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final fudex.bonyad.Model.LoginData getUserData1(@org.jetbrains.annotations.Nullable()
    android.content.Context activity) {
        return null;
    }
    
    public final void setUserData(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Model.UserModel user) {
    }
    
    public final void setTokenData(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String accessToken, long expireKey) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final fudex.bonyad.Model.LoginModel getUserData(@org.jetbrains.annotations.Nullable()
    android.content.Context activity) {
        return null;
    }
    
    public final void setenabletrip(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, int enable) {
    }
    
    public final void setNewsCount(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, int count) {
    }
    
    public final int getenabletrip(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return 0;
    }
    
    public final int getNewsCount(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return 0;
    }
    
    public final void setnotification(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, boolean enable) {
    }
    
    public final boolean getnotification(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return false;
    }
    
    public final int getUserType(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return 0;
    }
    
    public final boolean isLoggedIn(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return false;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAccessToken(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAccessToken(@org.jetbrains.annotations.NotNull()
    android.content.Context activity) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getExpire(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return null;
    }
    
    public final void clearData(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    public final void clearData1(@org.jetbrains.annotations.NotNull()
    android.content.Context activity) {
    }
    
    public final void clearData2(@org.jetbrains.annotations.NotNull()
    android.content.Context activity) {
    }
    
    public final void maintaninece(@org.jetbrains.annotations.NotNull()
    android.content.Context activity) {
    }
    
    public final void forceupdate(@org.jetbrains.annotations.NotNull()
    android.content.Context activity, @org.jetbrains.annotations.NotNull()
    java.lang.String txt) {
    }
    
    public final void activeuser(@org.jetbrains.annotations.NotNull()
    android.content.Context activity) {
    }
    
    public final void clearaddress(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    public final void setSelectedCitiesForAdmin(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.Integer> selectedCitiesIds) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSelectedCitiesForAdmin(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return null;
    }
    
    public final void Addservice(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, @org.jetbrains.annotations.NotNull()
    java.lang.String data) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getservice(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return null;
    }
    
    public final void Addwelcome(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, boolean expired) {
    }
    
    public final boolean getwelcome(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return false;
    }
    
    public final void Addtype(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity, int type) {
    }
    
    public final int gettype(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
        return 0;
    }
    
    public final void AddAddress(@org.jetbrains.annotations.NotNull()
    android.content.Context activity, @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Model.AddressesDatum country) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final fudex.bonyad.Model.AddressesDatum getaddress(@org.jetbrains.annotations.NotNull()
    android.content.Context activity) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final fudex.bonyad.Model.UserModel setdata(@org.jetbrains.annotations.NotNull()
    android.content.Context activity) {
        return null;
    }
}