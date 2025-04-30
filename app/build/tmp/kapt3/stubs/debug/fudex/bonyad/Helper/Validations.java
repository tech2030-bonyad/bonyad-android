package fudex.bonyad.Helper;

/**
 * <h1>Implement all Validations in data</h1>
 * Validations class for validate data and animate errors
 *
 *
 *
 * @author  kemo94
 * @version 1.0
 * @since   2017-08-9
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0010\b\n\u0002\b\u0006\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006J\u000e\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u0006J\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0006J\u000e\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0006J\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0006J\u000e\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0006J\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0017J\u000e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0006J\u000e\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0006J\u000e\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001b\u001a\u00020\u0006\u00a8\u0006\u001d"}, d2 = {"Lfudex/bonyad/Helper/Validations;", "", "()V", "isMatchPassword", "", "password", "", "confPassword", "isValidCode", "code", "isValidDate", "date", "isValidEmail", "email", "isValidGender", "gender", "isValidMobile", "mobile", "isValidName", "name", "isValidPassword", "isValidSpinnerItem", "pos", "", "isValidStr", "str", "isValidnumber", "text", "isValidspecial", "app_debug"})
public final class Validations {
    @org.jetbrains.annotations.NotNull()
    public static final fudex.bonyad.Helper.Validations INSTANCE = null;
    
    private Validations() {
        super();
    }
    
    /**
     * This method is used to validate mobile.
     * called when handle validation of user mobile.
     * @param name of mobile user
     */
    public final boolean isValidName(@org.jetbrains.annotations.NotNull()
    java.lang.String name) {
        return false;
    }
    
    /**
     * This method is used to validate mobile.
     * called when handle validation of user mobile.
     * @param mobile of mobile user
     */
    public final boolean isValidMobile(@org.jetbrains.annotations.NotNull()
    java.lang.String mobile) {
        return false;
    }
    
    public final boolean isValidCode(@org.jetbrains.annotations.NotNull()
    java.lang.String code) {
        return false;
    }
    
    /**
     * This method is used to validate password.
     * called when handle validation of user password.
     * @param password of password user
     */
    public final boolean isValidPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String password) {
        return false;
    }
    
    public final boolean isMatchPassword(@org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String confPassword) {
        return false;
    }
    
    public final boolean isValidSpinnerItem(int pos) {
        return false;
    }
    
    public final boolean isValidGender(@org.jetbrains.annotations.NotNull()
    java.lang.String gender) {
        return false;
    }
    
    public final boolean isValidDate(@org.jetbrains.annotations.NotNull()
    java.lang.String date) {
        return false;
    }
    
    public final boolean isValidStr(@org.jetbrains.annotations.NotNull()
    java.lang.String str) {
        return false;
    }
    
    /**
     * This method is used to validate email.
     * called when handle validation of user email.
     * @param email of email user
     */
    public final boolean isValidEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
        return false;
    }
    
    public final boolean isValidnumber(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return false;
    }
    
    public final boolean isValidspecial(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return false;
    }
}