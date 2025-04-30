package fudex.bonyad.ui.Activity;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001a\u00020\u0004H\u0016J\n\u0010\u0013\u001a\u00020\u000f*\u00020\u0014R\u001b\u0010\u0003\u001a\u00020\u00048BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\r\u00a8\u0006\u0015"}, d2 = {"Lfudex/bonyad/ui/Activity/BaseActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "appCompatDelegate", "Landroidx/appcompat/app/AppCompatDelegate;", "getAppCompatDelegate", "()Landroidx/appcompat/app/AppCompatDelegate;", "appCompatDelegate$delegate", "Lkotlin/Lazy;", "isRunningOnEmulator", "", "()Z", "setRunningOnEmulator", "(Z)V", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "getDelegate", "hideKeyboard", "Landroid/view/View;", "app_debug"})
public abstract class BaseActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy appCompatDelegate$delegate = null;
    private boolean isRunningOnEmulator = true;
    
    public BaseActivity() {
        super();
    }
    
    private final androidx.appcompat.app.AppCompatDelegate getAppCompatDelegate() {
        return null;
    }
    
    public final boolean isRunningOnEmulator() {
        return false;
    }
    
    public final void setRunningOnEmulator(boolean p0) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public androidx.appcompat.app.AppCompatDelegate getDelegate() {
        return null;
    }
    
    @java.lang.Override()
    protected void attachBaseContext(@org.jetbrains.annotations.Nullable()
    android.content.Context newBase) {
    }
    
    public final void hideKeyboard(@org.jetbrains.annotations.NotNull()
    android.view.View $this$hideKeyboard) {
    }
}