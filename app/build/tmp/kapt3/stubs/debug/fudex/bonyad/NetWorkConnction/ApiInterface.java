package fudex.bonyad.NetWorkConnction;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00cc\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010$\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003H\'J\u001c\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\'J\u0012\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003H\'J\u001c\u0010\u000e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010\u000f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010\u0011\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\u0012H\'J\u00b3\u0001\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0014\u0018\u00010\u00032\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\f2\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\f2\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010\u001b\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010\u001c\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010\u001d\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010\u001e\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010\u001f\u001a\u0004\u0018\u00010\f2\n\b\u0001\u0010 \u001a\u0004\u0018\u00010\u00162\u0016\b\u0001\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u0016\u0018\u00010\"H\'\u00a2\u0006\u0002\u0010#J\u0012\u0010$\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0003H\'J\u001c\u0010%\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010&\u001a\u00020\fH\'J\u0012\u0010\'\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0003H\'J\u001c\u0010(\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010)\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010*\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010+\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010)\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020,H\'J\u001c\u0010-\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010)\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020,H\'J\u001c\u0010.\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010)\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J&\u0010/\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010)\u0018\u00010\u00032\b\b\u0001\u00100\u001a\u0002012\b\b\u0001\u0010\u001b\u001a\u00020\bH\'J\u0012\u00102\u001a\f\u0012\u0006\u0012\u0004\u0018\u000103\u0018\u00010\u0003H\'J6\u00104\u001a\f\u0012\u0006\u0012\u0004\u0018\u000105\u0018\u00010\u00032\b\b\u0001\u0010 \u001a\u00020\u00162\b\b\u0001\u0010\u001f\u001a\u00020\f2\u000e\b\u0001\u00106\u001a\b\u0012\u0004\u0012\u00020\f07H\'J#\u00108\u001a\f\u0012\u0006\u0012\u0004\u0018\u000109\u0018\u00010\u00032\n\b\u0001\u0010:\u001a\u0004\u0018\u00010\fH\'\u00a2\u0006\u0002\u0010;JL\u0010<\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010=\u0018\u00010\u00032\b\b\u0001\u0010>\u001a\u00020\f2\n\b\u0001\u0010?\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010@\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010A\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\'J\u0083\u0001\u0010B\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010C\u0018\u00010\u00032\n\b\u0001\u0010:\u001a\u0004\u0018\u00010\f2\n\b\u0001\u0010?\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010@\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010D\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010A\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010E\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010F\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010\u001b\u001a\u0004\u0018\u00010\u0016H\'\u00a2\u0006\u0002\u0010GJ#\u0010H\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010I\u0018\u00010\u00032\n\b\u0001\u0010J\u001a\u0004\u0018\u00010\fH\'\u00a2\u0006\u0002\u0010;J/\u0010K\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010L\u0018\u00010\u00032\n\b\u0001\u0010:\u001a\u0004\u0018\u00010\f2\n\b\u0001\u0010M\u001a\u0004\u0018\u00010\fH\'\u00a2\u0006\u0002\u0010NJ#\u0010O\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010P\u0018\u00010\u00032\n\b\u0001\u0010:\u001a\u0004\u0018\u00010\fH\'\u00a2\u0006\u0002\u0010;J4\u0010Q\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010R\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020\f2\n\b\u0001\u0010?\u001a\u0004\u0018\u00010\u00162\n\b\u0001\u0010@\u001a\u0004\u0018\u00010\u0016H\'J/\u0010S\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010T\u0018\u00010\u00032\n\b\u0001\u0010:\u001a\u0004\u0018\u00010\f2\n\b\u0001\u0010M\u001a\u0004\u0018\u00010\fH\'\u00a2\u0006\u0002\u0010NJ\u0012\u0010U\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010)\u0018\u00010\u0003H\'J-\u0010V\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010W\u0018\u00010\u00032\b\b\u0001\u0010\u001f\u001a\u00020\f2\n\b\u0001\u0010:\u001a\u0004\u0018\u00010\fH\'\u00a2\u0006\u0002\u0010XJ\u0012\u0010Y\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010Z\u0018\u00010\u0003H\'J\u0012\u0010[\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\\\u0018\u00010\u0003H\'J\u0012\u0010]\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010I\u0018\u00010\u0003H\'J\u001c\u0010^\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010_\u0018\u00010\u00032\b\b\u0001\u0010`\u001a\u00020,H\'J\u001c\u0010a\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u0012\u0010b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003H\'J&\u0010c\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020\f2\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010d\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010_\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020,H\'JX\u0010e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010_\u0018\u00010\u00032\b\b\u0001\u0010\u001b\u001a\u00020\b2\b\b\u0001\u0010\u0019\u001a\u00020\b2\b\b\u0001\u0010f\u001a\u00020\b2\b\b\u0001\u0010g\u001a\u00020\b2\b\b\u0001\u0010h\u001a\u00020\b2\b\b\u0001\u0010i\u001a\u00020\b2\b\b\u0001\u0010j\u001a\u00020\bH\'Jb\u0010k\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010_\u0018\u00010\u00032\b\b\u0001\u0010\u001b\u001a\u00020\b2\b\b\u0001\u0010\u0019\u001a\u00020\b2\b\b\u0001\u0010l\u001a\u00020\b2\b\b\u0001\u0010m\u001a\u00020\b2\b\b\u0001\u0010n\u001a\u00020\b2\b\b\u0001\u0010h\u001a\u00020\b2\b\b\u0001\u0010i\u001a\u00020\b2\b\b\u0001\u0010j\u001a\u00020\bH\'J\u001c\u0010o\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010)\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020,H\'J\u001c\u0010p\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020,H\'J\u001c\u0010q\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010)\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020,H\'J\u001c\u0010s\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010_\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u0012\u0010t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003H\'J&\u0010u\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010&\u001a\u00020\f2\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010v\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010_\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020,H\'\u00a8\u0006w"}, d2 = {"Lfudex/bonyad/NetWorkConnction/ApiInterface;", "", "aboutus", "Lretrofit2/Call;", "Lonnetysolutions/sadded/Model/AboutModel;", "addaddress", "Lfudex/bonyad/Helper/AddaressModel;", "requestBody", "Lokhttp3/RequestBody;", "cancelorder", "Lfudex/bonyad/Helper/ErrorResponse;", "orderId", "", "cancelpolicy", "changelang", "checkotp", "checkregister", "contactus", "Lfudex/bonyad/Data/Contactdata;", "createOrder", "Lfudex/bonyad/Model/BookserviceModel;", "location_type", "", "address_id", "is_gift", "mobile", "address", "name", "payment_method", "gender", "price", "branchId", "date", "services", "", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/Map;)Lretrofit2/Call;", "deleteaccount", "deleteaddress", "addressId", "deletenots", "editemail", "Lonnetysolutions/sadded/Model/ProfileModel;", "editpass", "editphone", "Lfudex/bonyad/Data/Userdata;", "editphone1", "editprofile", "editprofilewithimage", "imageFile", "Lokhttp3/MultipartBody$Part;", "faqs", "Lonnetysolutions/sadded/Model/FaqModel;", "getSlots", "Lfudex/bonyad/Model/SlotsModel;", "serviceIds", "", "getaddress", "Lfudex/bonyad/Model/AddressesModel;", "page", "(Ljava/lang/Integer;)Lretrofit2/Call;", "getbranchdetails", "Lfudex/bonyad/Model/BranchsetailsModel;", "tripId", "lat", "lng", "service_type", "getbranches", "Lfudex/bonyad/Model/BrachesModel;", "max_distance", "employee_gender", "rate", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lretrofit2/Call;", "getcities", "Lfudex/bonyad/Model/StatesModel;", "state_id", "getcomplain", "Lfudex/bonyad/Model/ComplainModel;", "status", "(Ljava/lang/Integer;Ljava/lang/Integer;)Lretrofit2/Call;", "getnots", "Lfudex/bonyad/Model/NotsModel;", "getorderdetails", "Lfudex/bonyad/Model/OrderdetailsModel;", "getorders", "Lfudex/bonyad/Model/OrdersModel;", "getprofiledata", "getrating", "Lfudex/bonyad/Model/RatingModel;", "(ILjava/lang/Integer;)Lretrofit2/Call;", "getservicestype", "Lfudex/bonyad/Model/ServicestypeModel;", "getsliders", "Lfudex/bonyad/Model/SliderModel;", "getstates", "login", "Lfudex/bonyad/Model/LoginData;", "data", "logout", "privacy", "rateorder", "register", "registerimage", "password", "password_confirmation", "fcm_token", "device_type", "device_id", "registersocail", "email", "provider_token", "provider_type", "resendsendotp", "restpass", "sendcomplain", "sendotp", "sociallogin", "terms", "updateaddress", "verifiyuser", "app_debug"})
public abstract interface ApiInterface {
    
    @retrofit2.http.GET(value = "client/serviceTypes")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ServicestypeModel> getservicestype();
    
    @retrofit2.http.GET(value = "client/states")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.StatesModel> getstates();
    
    @retrofit2.http.GET(value = "client/cities")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.StatesModel> getcities(@retrofit2.http.Query(value = "state_id")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer state_id);
    
    @retrofit2.http.POST(value = "client/userAddresses")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.AddaressModel> addaddress(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBody);
    
    @retrofit2.http.POST(value = "client/userAddresses/{addressId}/update")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> updateaddress(@retrofit2.http.Path(value = "addressId")
    int addressId, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBody);
    
    @retrofit2.http.POST(value = "client/userAddresses/{addressId}/delete")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> deleteaddress(@retrofit2.http.Path(value = "addressId")
    int addressId);
    
    @retrofit2.http.GET(value = "client/userAddresses")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.AddressesModel> getaddress(@retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page);
    
    @retrofit2.http.GET(value = "client/branches/{branchId}/rate")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.RatingModel> getrating(@retrofit2.http.Path(value = "branchId")
    int branchId, @retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page);
    
    @retrofit2.http.GET(value = "client/branches")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.BrachesModel> getbranches(@retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @retrofit2.http.Query(value = "lat")
    @org.jetbrains.annotations.Nullable()
    java.lang.String lat, @retrofit2.http.Query(value = "lng")
    @org.jetbrains.annotations.Nullable()
    java.lang.String lng, @retrofit2.http.Query(value = "max_distance")
    @org.jetbrains.annotations.Nullable()
    java.lang.String max_distance, @retrofit2.http.Query(value = "service_type")
    @org.jetbrains.annotations.Nullable()
    java.lang.String service_type, @retrofit2.http.Query(value = "location_type")
    @org.jetbrains.annotations.Nullable()
    java.lang.String location_type, @retrofit2.http.Query(value = "employee_gender")
    @org.jetbrains.annotations.Nullable()
    java.lang.String employee_gender, @retrofit2.http.Query(value = "rate")
    @org.jetbrains.annotations.Nullable()
    java.lang.String rate, @retrofit2.http.Query(value = "name")
    @org.jetbrains.annotations.Nullable()
    java.lang.String name);
    
    @retrofit2.http.GET(value = "client/my-orders")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.OrdersModel> getorders(@retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @retrofit2.http.Query(value = "status")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer status);
    
    @retrofit2.http.GET(value = "client/orders/{orderId}")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.OrderdetailsModel> getorderdetails(@retrofit2.http.Path(value = "orderId")
    int orderId, @retrofit2.http.Query(value = "lat")
    @org.jetbrains.annotations.Nullable()
    java.lang.String lat, @retrofit2.http.Query(value = "lng")
    @org.jetbrains.annotations.Nullable()
    java.lang.String lng);
    
    @retrofit2.http.GET(value = "client/sliders")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.SliderModel> getsliders();
    
    @retrofit2.http.GET(value = "client/branches/{branchId}")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.BranchsetailsModel> getbranchdetails(@retrofit2.http.Path(value = "branchId")
    int tripId, @retrofit2.http.Query(value = "lat")
    @org.jetbrains.annotations.Nullable()
    java.lang.String lat, @retrofit2.http.Query(value = "lng")
    @org.jetbrains.annotations.Nullable()
    java.lang.String lng, @retrofit2.http.Query(value = "service_type")
    @org.jetbrains.annotations.Nullable()
    java.lang.String service_type, @retrofit2.http.Query(value = "location_type")
    @org.jetbrains.annotations.Nullable()
    java.lang.String location_type);
    
    @retrofit2.http.GET(value = "user-details")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<onnetysolutions.sadded.Model.ProfileModel> getprofiledata();
    
    @retrofit2.http.POST(value = "login")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.LoginData> login(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Userdata data);
    
    @retrofit2.http.POST(value = "client/checkSocialUser")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.LoginData> sociallogin(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBody);
    
    @retrofit2.http.POST(value = "checkRegisterValidation")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> checkregister(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBody);
    
    @retrofit2.http.POST(value = "register")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.LoginData> register(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Userdata requestBody);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "client/register")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.LoginData> registerimage(@retrofit2.http.Part(value = "name")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody name, @retrofit2.http.Part(value = "mobile")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody mobile, @retrofit2.http.Part(value = "password")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody password, @retrofit2.http.Part(value = "password_confirmation")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody password_confirmation, @retrofit2.http.Part(value = "fcm_token")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody fcm_token, @retrofit2.http.Part(value = "device_type")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody device_type, @retrofit2.http.Part(value = "device_id")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody device_id);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "client/register")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.LoginData> registersocail(@retrofit2.http.Part(value = "name")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody name, @retrofit2.http.Part(value = "mobile")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody mobile, @retrofit2.http.Part(value = "email")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody email, @retrofit2.http.Part(value = "social_id")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody provider_token, @retrofit2.http.Part(value = "social_type")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody provider_type, @retrofit2.http.Part(value = "fcm_token")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody fcm_token, @retrofit2.http.Part(value = "device_type")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody device_type, @retrofit2.http.Part(value = "device_id")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody device_id);
    
    @retrofit2.http.POST(value = "verify-account")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.LoginData> verifiyuser(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Userdata requestBody);
    
    @retrofit2.http.POST(value = "password/code")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<onnetysolutions.sadded.Model.ProfileModel> sendotp(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Userdata requestBody);
    
    @retrofit2.http.POST(value = "resend-code")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<onnetysolutions.sadded.Model.ProfileModel> resendsendotp(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Userdata requestBody);
    
    @retrofit2.http.POST(value = "client/check-otp")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> checkotp(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBody);
    
    @retrofit2.http.POST(value = "password/reset")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> restpass(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Userdata requestBody);
    
    @retrofit2.http.POST(value = "client/updatePassword")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> editpass(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBody);
    
    @retrofit2.http.POST(value = "client/updateEmail")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<onnetysolutions.sadded.Model.ProfileModel> editemail(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBody);
    
    @retrofit2.http.POST(value = "password/code")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<onnetysolutions.sadded.Model.ProfileModel> editphone(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Userdata requestBody);
    
    @retrofit2.http.POST(value = "client/updateProfile")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<onnetysolutions.sadded.Model.ProfileModel> editprofile(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBody);
    
    @retrofit2.http.POST(value = "edit-phone")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<onnetysolutions.sadded.Model.ProfileModel> editphone1(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Userdata requestBody);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "client/updateProfile")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<onnetysolutions.sadded.Model.ProfileModel> editprofilewithimage(@retrofit2.http.Part()
    @org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part imageFile, @retrofit2.http.Part(value = "name")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody name);
    
    @retrofit2.http.GET(value = "pages/about-us")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<onnetysolutions.sadded.Model.AboutModel> aboutus();
    
    @retrofit2.http.GET(value = "client/pages/cancellation-policy")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<onnetysolutions.sadded.Model.AboutModel> cancelpolicy();
    
    @retrofit2.http.GET(value = "client/faqs")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<onnetysolutions.sadded.Model.FaqModel> faqs();
    
    @retrofit2.http.GET(value = "pages/terms-and-conditions")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<onnetysolutions.sadded.Model.AboutModel> terms();
    
    @retrofit2.http.GET(value = "pages/privacy-policy")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<onnetysolutions.sadded.Model.AboutModel> privacy();
    
    @retrofit2.http.POST(value = "client/changeLanguage")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> changelang(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBody);
    
    @retrofit2.http.POST(value = "contact-us")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> contactus(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Contactdata requestBody);
    
    @retrofit2.http.POST(value = "client/complaintOrSuggest")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> sendcomplain(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBody);
    
    @retrofit2.http.POST(value = "client/logout")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> logout(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBody);
    
    @retrofit2.http.POST(value = "client/deleteAccount")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> deleteaccount();
    
    @retrofit2.http.GET(value = "client/slots")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.SlotsModel> getSlots(@retrofit2.http.Query(value = "date")
    @org.jetbrains.annotations.NotNull()
    java.lang.String date, @retrofit2.http.Query(value = "branch_id")
    int branchId, @retrofit2.http.Query(value = "service_ids[]")
    @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Integer> serviceIds);
    
    @retrofit2.http.FormUrlEncoded()
    @retrofit2.http.POST(value = "client/order")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.BookserviceModel> createOrder(@retrofit2.http.Field(value = "location_type")
    @org.jetbrains.annotations.Nullable()
    java.lang.String location_type, @retrofit2.http.Field(value = "address_id")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer address_id, @retrofit2.http.Field(value = "is_gift")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer is_gift, @retrofit2.http.Field(value = "mobile")
    @org.jetbrains.annotations.Nullable()
    java.lang.String mobile, @retrofit2.http.Field(value = "address")
    @org.jetbrains.annotations.Nullable()
    java.lang.String address, @retrofit2.http.Field(value = "name")
    @org.jetbrains.annotations.Nullable()
    java.lang.String name, @retrofit2.http.Field(value = "payment_method")
    @org.jetbrains.annotations.Nullable()
    java.lang.String payment_method, @retrofit2.http.Field(value = "gender")
    @org.jetbrains.annotations.Nullable()
    java.lang.String gender, @retrofit2.http.Field(value = "price")
    @org.jetbrains.annotations.Nullable()
    java.lang.String price, @retrofit2.http.Field(value = "branch_id")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer branchId, @retrofit2.http.Field(value = "date")
    @org.jetbrains.annotations.Nullable()
    java.lang.String date, @retrofit2.http.FieldMap(encoded = true)
    @org.jetbrains.annotations.Nullable()
    java.util.Map<java.lang.String, java.lang.String> services);
    
    @retrofit2.http.POST(value = "client/order/{orderId}/rate")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> rateorder(@retrofit2.http.Path(value = "orderId")
    int orderId, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBody);
    
    @retrofit2.http.POST(value = "client/orders/{orderId}/cancel")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> cancelorder(@retrofit2.http.Path(value = "orderId")
    int orderId);
    
    @retrofit2.http.GET(value = "client/my-complaint-suggesstion")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ComplainModel> getcomplain(@retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @retrofit2.http.Query(value = "status")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer status);
    
    @retrofit2.http.GET(value = "client/notifications")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.NotsModel> getnots(@retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page);
    
    @retrofit2.http.POST(value = "client/notifications/delete")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> deletenots();
}