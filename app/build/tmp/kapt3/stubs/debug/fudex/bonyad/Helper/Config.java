package fudex.bonyad.Helper;

import android.content.Context;
import android.os.Build;
import android.provider.Settings.Secure;
import com.scottyab.rootbeer.RootBeer;
import java.io.File;

/**
 * Created by pc on 15/02/2017.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u000e\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lfudex/bonyad/Helper/Config;", "", "()V", "IMAGE_DIRECTORY_NAME", "", "isEmulator", "", "context", "Landroid/content/Context;", "isRooted", "isRooted1", "app_debug"})
public final class Config {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String IMAGE_DIRECTORY_NAME = "WATER";
    @org.jetbrains.annotations.NotNull()
    public static final fudex.bonyad.Helper.Config INSTANCE = null;
    
    private Config() {
        super();
    }
    
    public final boolean isEmulator(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final boolean isRooted(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
    
    public final boolean isRooted1(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return false;
    }
}