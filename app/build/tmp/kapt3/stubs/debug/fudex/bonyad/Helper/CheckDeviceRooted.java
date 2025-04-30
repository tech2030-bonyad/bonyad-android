package fudex.bonyad.Helper;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0005H\u0002J\u0006\u0010\n\u001a\u00020\bJ\u0006\u0010\u000b\u001a\u00020\bJ\u0006\u0010\f\u001a\u00020\bJ\u0006\u0010\r\u001a\u00020\bR\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006\u00a8\u0006\u000e"}, d2 = {"Lfudex/bonyad/Helper/CheckDeviceRooted;", "", "()V", "binaryPaths", "", "", "[Ljava/lang/String;", "checkForBinary", "", "filename", "checkForBusyBoxBinary", "checkForSuBinary", "checkSuExists", "detectTestKeys", "app_debug"})
public final class CheckDeviceRooted {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String[] binaryPaths = {"/data/local/", "/data/local/bin/", "/data/local/xbin/", "/sbin/", "/su/bin/", "/system/bin/", "/system/bin/.ext/", "/system/bin/failsafe/", "/system/sd/xbin/", "/system/usr/we-need-root/", "/system/xbin/", "/system/app/Superuser.apk", "/cache", "/data", "/dev"};
    @org.jetbrains.annotations.NotNull()
    public static final fudex.bonyad.Helper.CheckDeviceRooted INSTANCE = null;
    
    private CheckDeviceRooted() {
        super();
    }
    
    public final boolean detectTestKeys() {
        return false;
    }
    
    public final boolean checkForSuBinary() {
        return false;
    }
    
    public final boolean checkForBusyBoxBinary() {
        return false;
    }
    
    /**
     * @param filename - check for this existence of this
     * file("su","busybox")
     * @return true if exists
     */
    private final boolean checkForBinary(java.lang.String filename) {
        return false;
    }
    
    /**
     * A variation on the checking for SU, this attempts a 'which su'
     * different file system check for the su binary
     * @return true if su exists
     */
    public final boolean checkSuExists() {
        return false;
    }
}