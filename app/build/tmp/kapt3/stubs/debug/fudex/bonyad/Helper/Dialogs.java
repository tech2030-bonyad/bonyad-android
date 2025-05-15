package fudex.bonyad.Helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;
import android.view.Window;

/**
 * <h1>Implement reusable dialogs</h1>
 * Dialogs class for all dialogs and toasts
 *
 *
 *
 * @author kemo94
 * @version 1.0
 * @since 2017-08-9
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\u0004X\u0086.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\b\u00a8\u0006\u0014"}, d2 = {"Lfudex/bonyad/Helper/Dialogs;", "", "()V", "noInternetDialog", "Landroid/app/Dialog;", "getNoInternetDialog", "()Landroid/app/Dialog;", "setNoInternetDialog", "(Landroid/app/Dialog;)V", "progressDialog", "getProgressDialog", "setProgressDialog", "showToast", "", "message", "", "activity", "Landroid/app/Activity;", "context", "Landroid/content/Context;", "app_debug"})
public final class Dialogs {
    public static android.app.Dialog progressDialog;
    @org.jetbrains.annotations.Nullable()
    private static android.app.Dialog noInternetDialog;
    @org.jetbrains.annotations.NotNull()
    public static final fudex.bonyad.Helper.Dialogs INSTANCE = null;
    
    private Dialogs() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.Dialog getProgressDialog() {
        return null;
    }
    
    public final void setProgressDialog(@org.jetbrains.annotations.NotNull()
    android.app.Dialog p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.app.Dialog getNoInternetDialog() {
        return null;
    }
    
    public final void setNoInternetDialog(@org.jetbrains.annotations.Nullable()
    android.app.Dialog p0) {
    }
    
    public final void showToast(@org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    public final void showToast(@org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
    }
}