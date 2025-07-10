package fudex.bonyad.Model;

import android.net.Uri;
import com.blongho.country_data.Country;
import fudex.bonyad.Helper.ErrorResponse;
import kotlin.collections.ArrayList;

/**
 * Created by hp on 6/3/2018.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001d\u0018\u00002\u00020\u0001B\u0081\u0002\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u0012\u001c\b\u0002\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015j\n\u0012\u0004\u0012\u00020\u0016\u0018\u0001`\u0017\u0012\u001c\b\u0002\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0015j\n\u0012\u0004\u0012\u00020\u0019\u0018\u0001`\u0017\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\u0002\u0010\u001bR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u001c\u0010\t\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001d\"\u0004\b\u001f\u0010 R\u0013\u0010\u0013\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001dR%\u0010\u0018\u001a\u0016\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0015j\n\u0012\u0004\u0012\u00020\u0019\u0018\u0001`\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u001dR\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u001dR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001dR\u0013\u0010\f\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001dR\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010*\u001a\u0004\b(\u0010)R\u0015\u0010\u000e\u001a\u0004\u0018\u00010\u000f\u00a2\u0006\n\n\u0002\u0010,\u001a\u0004\b\u000e\u0010+R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001dR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b.\u0010\u001dR\u001c\u0010\b\u001a\u0004\u0018\u00010\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u001d\"\u0004\b0\u0010 R\u0015\u0010\u001a\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010*\u001a\u0004\b1\u0010)R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010\u001dR\u0013\u0010\r\u001a\u0004\u0018\u00010\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b3\u0010\u001dR\u0015\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u00a2\u0006\n\n\u0002\u0010*\u001a\u0004\b4\u0010)R%\u0010\u0014\u001a\u0016\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015j\n\u0012\u0004\u0012\u00020\u0016\u0018\u0001`\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u0010#\u00a8\u00066"}, d2 = {"Lfudex/bonyad/Model/UserModel;", "", "id", "", "name", "", "email", "phone", "photo", "avatar", "address", "description", "experience_years", "zone", "is_from_social", "", "zone_id", "trade_name", "description_of_business_activity", "business_logo", "zones", "Ljava/util/ArrayList;", "Lfudex/bonyad/Model/StatesDatum;", "Lkotlin/collections/ArrayList;", "certificates", "Lfudex/bonyad/Model/Certificate;", "timer", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/lang/Integer;)V", "getAddress", "()Ljava/lang/String;", "getAvatar", "setAvatar", "(Ljava/lang/String;)V", "getBusiness_logo", "getCertificates", "()Ljava/util/ArrayList;", "getDescription", "getDescription_of_business_activity", "getEmail", "getExperience_years", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "getName", "getPhone", "getPhoto", "setPhoto", "getTimer", "getTrade_name", "getZone", "getZone_id", "getZones", "app_debug"})
public final class UserModel {
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer id = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String name = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String email = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String phone = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String photo;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String avatar;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String address = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String description = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String experience_years = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String zone = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Boolean is_from_social = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer zone_id = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String trade_name = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String description_of_business_activity = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.String business_logo = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.ArrayList<fudex.bonyad.Model.StatesDatum> zones = null;
    @org.jetbrains.annotations.Nullable()
    private final java.util.ArrayList<fudex.bonyad.Model.Certificate> certificates = null;
    @org.jetbrains.annotations.Nullable()
    private final java.lang.Integer timer = null;
    
    public UserModel(@org.jetbrains.annotations.Nullable()
    java.lang.Integer id, @org.jetbrains.annotations.Nullable()
    java.lang.String name, @org.jetbrains.annotations.Nullable()
    java.lang.String email, @org.jetbrains.annotations.Nullable()
    java.lang.String phone, @org.jetbrains.annotations.Nullable()
    java.lang.String photo, @org.jetbrains.annotations.Nullable()
    java.lang.String avatar, @org.jetbrains.annotations.Nullable()
    java.lang.String address, @org.jetbrains.annotations.Nullable()
    java.lang.String description, @org.jetbrains.annotations.Nullable()
    java.lang.String experience_years, @org.jetbrains.annotations.Nullable()
    java.lang.String zone, @org.jetbrains.annotations.Nullable()
    java.lang.Boolean is_from_social, @org.jetbrains.annotations.Nullable()
    java.lang.Integer zone_id, @org.jetbrains.annotations.Nullable()
    java.lang.String trade_name, @org.jetbrains.annotations.Nullable()
    java.lang.String description_of_business_activity, @org.jetbrains.annotations.Nullable()
    java.lang.String business_logo, @org.jetbrains.annotations.Nullable()
    java.util.ArrayList<fudex.bonyad.Model.StatesDatum> zones, @org.jetbrains.annotations.Nullable()
    java.util.ArrayList<fudex.bonyad.Model.Certificate> certificates, @org.jetbrains.annotations.Nullable()
    java.lang.Integer timer) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getId() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getName() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getEmail() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPhone() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getPhoto() {
        return null;
    }
    
    public final void setPhoto(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAvatar() {
        return null;
    }
    
    public final void setAvatar(@org.jetbrains.annotations.Nullable()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getExperience_years() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getZone() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Boolean is_from_social() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getZone_id() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getTrade_name() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getDescription_of_business_activity() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getBusiness_logo() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.ArrayList<fudex.bonyad.Model.StatesDatum> getZones() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.util.ArrayList<fudex.bonyad.Model.Certificate> getCertificates() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Integer getTimer() {
        return null;
    }
    
    public UserModel() {
        super();
    }
}