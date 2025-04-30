package fudex.bonyad.Helper;

/**
 * Created by سيد on 19/12/2016.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 *2\u00020\u0001:\u0001*B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\u001a\u001a\u00020\u0010J\b\u0010\u001b\u001a\u0004\u0018\u00010\u0012J\u0006\u0010\u001c\u001a\u00020\u0010J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!H\u0016J\u0010\u0010\"\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!H\u0016J \u0010#\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\'H\u0016J\u0006\u0010(\u001a\u00020\u001eJ\u0006\u0010)\u001a\u00020\u001eR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\r\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\nR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0084\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006+"}, d2 = {"Lfudex/bonyad/Helper/GPSTracker;", "Landroid/location/LocationListener;", "mContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "canGetLocation", "", "getCanGetLocation", "()Z", "setCanGetLocation", "(Z)V", "isGPSEnabled", "setGPSEnabled", "isNetworkEnabled", "setNetworkEnabled", "latitude", "", "location", "Landroid/location/Location;", "locationManager", "Landroid/location/LocationManager;", "getLocationManager", "()Landroid/location/LocationManager;", "setLocationManager", "(Landroid/location/LocationManager;)V", "longitude", "getLatitude", "getLocation", "getLongitude", "onLocationChanged", "", "onProviderDisabled", "provider", "", "onProviderEnabled", "onStatusChanged", "status", "", "extras", "Landroid/os/Bundle;", "showSettingsAlert", "stopUsingGPS", "Companion", "app_debug"})
public final class GPSTracker implements android.location.LocationListener {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context mContext = null;
    private boolean isGPSEnabled = false;
    private boolean isNetworkEnabled = false;
    private boolean canGetLocation = false;
    @org.jetbrains.annotations.Nullable()
    private android.location.Location location;
    private double latitude = 0.0;
    private double longitude = 0.0;
    @org.jetbrains.annotations.Nullable()
    private android.location.LocationManager locationManager;
    private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 1L;
    private static final long MIN_TIME_BW_UPDATES = 1L;
    @org.jetbrains.annotations.NotNull()
    public static final fudex.bonyad.Helper.GPSTracker.Companion Companion = null;
    
    public GPSTracker(@org.jetbrains.annotations.NotNull()
    android.content.Context mContext) {
        super();
    }
    
    public final boolean isGPSEnabled() {
        return false;
    }
    
    public final void setGPSEnabled(boolean p0) {
    }
    
    public final boolean isNetworkEnabled() {
        return false;
    }
    
    public final void setNetworkEnabled(boolean p0) {
    }
    
    public final boolean getCanGetLocation() {
        return false;
    }
    
    public final void setCanGetLocation(boolean p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    protected final android.location.LocationManager getLocationManager() {
        return null;
    }
    
    protected final void setLocationManager(@org.jetbrains.annotations.Nullable()
    android.location.LocationManager p0) {
    }
    
    /**
     * Function to get the user's current location
     *
     * @return
     */
    @org.jetbrains.annotations.Nullable()
    public final android.location.Location getLocation() {
        return null;
    }
    
    /**
     * Stop using GPS listener Calling this function will stop using GPS in your
     * app
     */
    public final void stopUsingGPS() {
    }
    
    /**
     * Function to get latitude
     */
    public final double getLatitude() {
        return 0.0;
    }
    
    /**
     * Function to get longitude
     */
    public final double getLongitude() {
        return 0.0;
    }
    
    /**
     * Function to check GPS/wifi enabled
     *
     * @return boolean
     */
    public final boolean canGetLocation() {
        return false;
    }
    
    /**
     * Function to show settings alert dialog On pressing Settings button will
     * lauch Settings Options
     */
    public final void showSettingsAlert() {
    }
    
    @java.lang.Override()
    public void onLocationChanged(@org.jetbrains.annotations.NotNull()
    android.location.Location location) {
    }
    
    @java.lang.Override()
    public void onProviderDisabled(@org.jetbrains.annotations.NotNull()
    java.lang.String provider) {
    }
    
    @java.lang.Override()
    public void onProviderEnabled(@org.jetbrains.annotations.NotNull()
    java.lang.String provider) {
    }
    
    @java.lang.Override()
    public void onStatusChanged(@org.jetbrains.annotations.NotNull()
    java.lang.String provider, int status, @org.jetbrains.annotations.NotNull()
    android.os.Bundle extras) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lfudex/bonyad/Helper/GPSTracker$Companion;", "", "()V", "MIN_DISTANCE_CHANGE_FOR_UPDATES", "", "MIN_TIME_BW_UPDATES", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}