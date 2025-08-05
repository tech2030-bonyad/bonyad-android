package fudex.bonyad.NetWorkConnction;

import fudex.bonyad.Data.Cartdata;
import fudex.bonyad.Data.Changestatus;
import fudex.bonyad.Data.Chatdata;
import fudex.bonyad.Data.Contactdata;
import fudex.bonyad.Data.Craetereserve;
import fudex.bonyad.Data.Editpass;
import fudex.bonyad.Data.Orderdata;
import fudex.bonyad.Data.Ratingdata;
import fudex.bonyad.Data.Userdata;
import fudex.bonyad.Helper.AddaressModel;
import fudex.bonyad.Helper.ErrorResponse;
import fudex.bonyad.Model.AddressesModel;
import fudex.bonyad.Model.BookserviceModel;
import fudex.bonyad.Model.BrachesModel;
import fudex.bonyad.Model.ComplainModel;
import fudex.bonyad.Model.ContactusModel;
import fudex.bonyad.Model.LoginData;
import fudex.bonyad.Model.NotsModel;
import fudex.bonyad.Model.OrderdetailsModel;
import fudex.bonyad.Model.OrdersModel;
import fudex.bonyad.Model.RatingModel;
import fudex.bonyad.Model.ScheduleResponse;
import fudex.bonyad.Model.ServicesdetailsModel;
import fudex.bonyad.Model.ServicestypeModel;
import fudex.bonyad.Model.SliderModel;
import fudex.bonyad.Model.StatesModel;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import fudex.bonyad.Model.AboutModel;
import fudex.bonyad.Model.AddserviceModel;
import fudex.bonyad.Model.AppointmentdetailsModel;
import fudex.bonyad.Model.CartModel;
import fudex.bonyad.Model.ChatModel;
import fudex.bonyad.Model.CreateorderModel;
import fudex.bonyad.Model.Dateadd;
import fudex.bonyad.Model.DetailsProductsModel;
import fudex.bonyad.Model.DetailstechnicalModel;
import fudex.bonyad.Model.FaqModel;
import fudex.bonyad.Model.HomeModel;
import fudex.bonyad.Model.MYSubsribeModel;
import fudex.bonyad.Model.MerchantHomeModel;
import fudex.bonyad.Model.MerchantOrdersModel;
import fudex.bonyad.Model.MerchantorderdetailsModel;
import fudex.bonyad.Model.PlanModel;
import fudex.bonyad.Model.ProductsModel;
import fudex.bonyad.Model.ProfileModel;
import fudex.bonyad.Model.RatingsModel;
import fudex.bonyad.Model.SubsribeModel;
import fudex.bonyad.Model.TechnicalHomeModel;
import fudex.bonyad.Model.TechnicalModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u008a\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003H\'J\u001c\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\'J\u001c\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\'J\u001c\u0010\u0010\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u0011H\'J`\u0010\u0012\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\u0010\b\u0001\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00142\b\b\u0001\u0010\u0016\u001a\u00020\f2\b\b\u0001\u0010\u0017\u001a\u00020\f2\b\b\u0001\u0010\u0018\u001a\u00020\f2\b\b\u0001\u0010\u0019\u001a\u00020\f2\b\b\u0001\u0010\u001a\u001a\u00020\f2\b\b\u0001\u0010\u001b\u001a\u00020\fH\'J8\u0010\u001c\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\u0010\b\u0001\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00142\b\b\u0001\u0010\u0016\u001a\u00020\f2\b\b\u0001\u0010\u001d\u001a\u00020\fH\'J\u001c\u0010\u001e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u001fH\'J\u001c\u0010 \u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010!\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\"\u001a\u00020\bH\'J\u0012\u0010#\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003H\'J\u0012\u0010$\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0003H\'J\u001c\u0010%\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\"\u001a\u00020\bH\'J\u001c\u0010&\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\'J&\u0010\'\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\"\u001a\u00020\b2\b\b\u0001\u0010\u000e\u001a\u00020(H\'J\u001c\u0010)\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\'J\u001c\u0010*\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\'J\u001c\u0010+\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010,\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020-H\'J\u00b3\u0001\u0010.\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010/\u0018\u00010\u00032\n\b\u0001\u00100\u001a\u0004\u0018\u0001012\n\b\u0001\u00102\u001a\u0004\u0018\u00010\b2\n\b\u0001\u00103\u001a\u0004\u0018\u00010\b2\n\b\u0001\u00104\u001a\u0004\u0018\u0001012\n\b\u0001\u00105\u001a\u0004\u0018\u0001012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u0001012\n\b\u0001\u00106\u001a\u0004\u0018\u0001012\n\b\u0001\u00107\u001a\u0004\u0018\u0001012\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u0001012\n\b\u0001\u00108\u001a\u0004\u0018\u00010\b2\n\b\u0001\u00109\u001a\u0004\u0018\u0001012\u0016\b\u0001\u0010:\u001a\u0010\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u000201\u0018\u00010;H\'\u00a2\u0006\u0002\u0010<J\u001c\u0010=\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010>\u0018\u00010\u00032\b\b\u0001\u0010\u000e\u001a\u00020?H\'J\u001c\u0010@\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u000e\u001a\u00020AH\'J\u0012\u0010B\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0003H\'J\u001c\u0010C\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010D\u001a\u00020\bH\'J#\u0010E\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\n\b\u0001\u0010F\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0002\u0010GJ\u0012\u0010H\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0003H\'J\u001c\u0010H\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010I\u001a\u000201H\'J\u001c\u0010J\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010F\u001a\u000201H\'J\u001c\u0010K\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u000e\u001a\u00020\u001fH\'J&\u0010L\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010F\u001a\u00020\b2\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\'J\u001c\u0010M\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010N\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\'J\u001c\u0010O\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020PH\'J\u001c\u0010Q\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010N\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020RH\'J\u001c\u0010S\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010N\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020RH\'Jj\u0010T\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010F\u001a\u0002012\u0010\b\u0001\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00142\b\b\u0001\u0010\u0016\u001a\u00020\f2\b\b\u0001\u0010\u0017\u001a\u00020\f2\b\b\u0001\u0010\u0018\u001a\u00020\f2\b\b\u0001\u0010\u0019\u001a\u00020\f2\b\b\u0001\u0010\u001a\u001a\u00020\f2\b\b\u0001\u0010\u001b\u001a\u00020\fH\'J\u001e\u0010U\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010N\u0018\u00010\u00032\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\fH\'JV\u0010V\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010N\u0018\u00010\u00032\b\b\u0001\u0010W\u001a\u00020\u00152\b\b\u0001\u0010\u0016\u001a\u00020\f2\n\b\u0001\u0010\u001d\u001a\u0004\u0018\u00010\f2\n\b\u0001\u00105\u001a\u0004\u0018\u00010\f2\n\b\u0001\u0010X\u001a\u0004\u0018\u00010\f2\n\b\u0001\u0010Y\u001a\u0004\u0018\u00010\fH\'J&\u0010Z\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u000e\u001a\u00020AH\'J|\u0010[\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010N\u0018\u00010\u00032\n\b\u0001\u0010W\u001a\u0004\u0018\u00010\u00152\u0010\b\u0001\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00142\b\b\u0001\u0010\u0016\u001a\u00020\f2\b\b\u0001\u0010\u001d\u001a\u00020\f2\b\b\u0001\u00105\u001a\u00020\f2\b\b\u0001\u0010\\\u001a\u00020\f2\u000e\b\u0001\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\b\b\u0001\u0010\u0018\u001a\u00020\f2\b\b\u0001\u0010^\u001a\u00020\fH\'J\u0012\u0010_\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010`\u0018\u00010\u0003H\'J&\u0010a\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010b\u0018\u00010\u00032\b\b\u0001\u0010c\u001a\u00020\b2\b\b\u0001\u00109\u001a\u000201H\'J#\u0010d\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010e\u0018\u00010\u00032\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0002\u0010GJ\u001c\u0010g\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010h\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u0012\u0010i\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010b\u0018\u00010\u0003H\'J\u0083\u0001\u0010j\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010k\u0018\u00010\u00032\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010l\u001a\u0004\u0018\u0001012\n\b\u0001\u0010m\u001a\u0004\u0018\u0001012\n\b\u0001\u0010n\u001a\u0004\u0018\u0001012\n\b\u0001\u0010o\u001a\u0004\u0018\u0001012\n\b\u0001\u00100\u001a\u0004\u0018\u0001012\n\b\u0001\u0010p\u001a\u0004\u0018\u0001012\n\b\u0001\u0010q\u001a\u0004\u0018\u0001012\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u000101H\'\u00a2\u0006\u0002\u0010rJ\u0012\u0010s\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010t\u0018\u00010\u0003H\'J9\u0010u\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010v\u0018\u00010\u00032\b\b\u0001\u0010w\u001a\u00020\b2\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010x\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0002\u0010yJ#\u0010z\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010{\u0018\u00010\u00032\n\b\u0001\u0010|\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0002\u0010GJ0\u0010}\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010~\u0018\u00010\u00032\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010\u007f\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0003\u0010\u0080\u0001J\u0013\u0010\u0081\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010{\u0018\u00010\u0003H\'J \u0010\u0082\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0083\u0001\u0018\u00010\u00032\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u000101H\'J\u0014\u0010\u0084\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0085\u0001\u0018\u00010\u0003H\'J\u001e\u0010\u0086\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0087\u0001\u0018\u00010\u00032\b\b\u0001\u0010\"\u001a\u00020\bH\'J^\u0010\u0088\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0089\u0001\u0018\u00010\u00032\u0010\b\u0001\u0010\u007f\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00142\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010x\u001a\u0004\u0018\u00010\b2\u000b\b\u0001\u0010\u008a\u0001\u001a\u0004\u0018\u0001012\u000b\b\u0001\u0010\u008b\u0001\u001a\u0004\u0018\u000101H\'\u00a2\u0006\u0003\u0010\u008c\u0001J2\u0010\u008d\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u008e\u0001\u0018\u00010\u00032\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010x\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0003\u0010\u0080\u0001J\u0014\u0010\u008f\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0090\u0001\u0018\u00010\u0003H\'JQ\u0010\u0091\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u008e\u0001\u0018\u00010\u00032\u0011\b\u0001\u0010\u0092\u0001\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00142\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u0001012\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010x\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0003\u0010\u0093\u0001J\u0013\u0010\u0094\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010{\u0018\u00010\u0003H\'J2\u0010\u0095\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0096\u0001\u0018\u00010\u00032\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010x\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0003\u0010\u0080\u0001J\u0014\u0010\u0097\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0096\u0001\u0018\u00010\u0003H\'J6\u0010\u0098\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0099\u0001\u0018\u00010\u00032\b\b\u0001\u0010\"\u001a\u00020\b2\n\b\u0001\u0010l\u001a\u0004\u0018\u0001012\n\b\u0001\u0010m\u001a\u0004\u0018\u000101H\'J2\u0010\u009a\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u009b\u0001\u0018\u00010\u00032\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010\u007f\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0003\u0010\u0080\u0001J\u0014\u0010\u009c\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u009d\u0001\u0018\u00010\u0003H\'J\u001e\u0010\u009e\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u009f\u0001\u0018\u00010\u00032\b\b\u0001\u0010F\u001a\u00020\bH\'JE\u0010\u00a0\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u008e\u0001\u0018\u00010\u00032\u0011\b\u0001\u0010\u0092\u0001\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00142\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010x\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0003\u0010\u00a1\u0001J\u0013\u0010\u00a2\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010N\u0018\u00010\u0003H\'J0\u0010\u00a3\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00a4\u0001\u0018\u00010\u00032\b\b\u0001\u00108\u001a\u00020\b2\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0003\u0010\u00a5\u0001JL\u0010\u00a6\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00a7\u0001\u0018\u00010\u00032\u000b\b\u0001\u0010\u00a8\u0001\u001a\u0004\u0018\u0001012\u000b\b\u0001\u0010\u00a9\u0001\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010x\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0003\u0010\u00aa\u0001J=\u0010\u00ab\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010{\u0018\u00010\u00032\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u0001012\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010x\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0003\u0010\u00ac\u0001J\u001f\u0010\u00ad\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00ae\u0001\u0018\u00010\u00032\t\b\u0001\u0010\u00af\u0001\u001a\u00020\bH\'J\u0014\u0010\u00b0\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00b1\u0001\u0018\u00010\u0003H\'J\u0014\u0010\u00b2\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00b3\u0001\u0018\u00010\u0003H\'J\u0014\u0010\u00b4\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00b5\u0001\u0018\u00010\u0003H\'J\u001e\u0010\u00b6\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00b7\u0001\u0018\u00010\u00032\b\b\u0001\u0010c\u001a\u00020\bH\'J\u001d\u0010\u00b8\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010h\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u0014\u0010\u00b9\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00ba\u0001\u0018\u00010\u0003H\'JD\u0010\u00bb\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u009b\u0001\u0018\u00010\u00032\u0010\b\u0001\u0010\u007f\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00142\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010x\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0003\u0010\u00a1\u0001Jc\u0010\u00bc\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00bd\u0001\u0018\u00010\u00032\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010x\u001a\u0004\u0018\u00010\b2\u0011\b\u0001\u0010\u0092\u0001\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00142\u0010\b\u0001\u0010:\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00142\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u000101H\'\u00a2\u0006\u0003\u0010\u00be\u0001J\u0013\u0010\u00bf\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010{\u0018\u00010\u0003H\'JD\u0010\u00c0\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u009b\u0001\u0018\u00010\u00032\u0010\b\u0001\u0010\u007f\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00142\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010x\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0003\u0010\u00a1\u0001J\u001e\u0010\u00c1\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0087\u0001\u0018\u00010\u00032\b\b\u0001\u0010\"\u001a\u00020\bH\'JD\u0010\u00c2\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0089\u0001\u0018\u00010\u00032\u0010\b\u0001\u0010\u007f\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00142\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\b2\n\b\u0001\u0010x\u001a\u0004\u0018\u00010\bH\'\u00a2\u0006\u0003\u0010\u00a1\u0001J\u001e\u0010\u00c3\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u009f\u0001\u0018\u00010\u00032\b\b\u0001\u0010F\u001a\u00020\bH\'J\u0014\u0010\u00c4\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0096\u0001\u0018\u00010\u0003H\'J\u0013\u0010\u00c5\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010{\u0018\u00010\u0003H\'J\u001e\u0010\u00c6\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00c7\u0001\u0018\u00010\u00032\b\b\u0001\u0010\u000e\u001a\u00020RH\'J\u001d\u0010\u00c8\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\'J\u0013\u0010\u00c9\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u0003H\'J\u009f\u0001\u0010\u00ca\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00c7\u0001\u0018\u00010\u00032\b\b\u0001\u0010W\u001a\u00020\u00152\t\b\u0001\u0010\u00cb\u0001\u001a\u00020\f2\b\b\u0001\u0010\u0016\u001a\u00020\f2\b\b\u0001\u00104\u001a\u00020\f2\n\b\u0001\u0010\u001d\u001a\u0004\u0018\u00010\f2\b\b\u0001\u00105\u001a\u00020\f2\t\b\u0001\u0010\u00cc\u0001\u001a\u00020\f2\t\b\u0001\u0010\u00cd\u0001\u001a\u00020\f2\t\b\u0001\u0010\u00ce\u0001\u001a\u00020\f2\t\b\u0001\u0010\u00cf\u0001\u001a\u00020\f2\t\b\u0001\u0010\u00d0\u0001\u001a\u00020\f2\t\b\u0001\u0010\u00d1\u0001\u001a\u00020\f2\b\b\u0001\u0010\u0018\u001a\u00020\fH\'J\u0013\u0010\u00d2\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003H\'J\u001e\u0010\u00d3\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\t\b\u0001\u0010\u000e\u001a\u00030\u00d4\u0001H\'J\'\u0010\u00d5\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\b2\b\b\u0001\u0010\u000e\u001a\u00020RH\'J\u001e\u0010\u00d6\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00c7\u0001\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020RH\'Ji\u0010\u00d7\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00c7\u0001\u0018\u00010\u00032\b\b\u0001\u0010\u0016\u001a\u00020\f2\b\b\u0001\u00104\u001a\u00020\f2\b\b\u0001\u0010\u001d\u001a\u00020\f2\t\b\u0001\u0010\u00d8\u0001\u001a\u00020\f2\t\b\u0001\u0010\u00d9\u0001\u001a\u00020\f2\t\b\u0001\u0010\u00cf\u0001\u001a\u00020\f2\t\b\u0001\u0010\u00d0\u0001\u001a\u00020\f2\t\b\u0001\u0010\u00d1\u0001\u001a\u00020\fH\'J\u001d\u0010\u00da\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010N\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020RH\'J\u001d\u0010\u00db\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020RH\'J\u001e\u0010\u00dc\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\t\b\u0001\u0010\u000e\u001a\u00030\u00dd\u0001H\'J\u001d\u0010\u00de\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\'J\u001d\u0010\u00df\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010N\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020RH\'J\u001e\u0010\u00e0\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00c7\u0001\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\'J\u001f\u0010\u00e1\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00e2\u0001\u0018\u00010\u00032\t\b\u0001\u0010\u00e3\u0001\u001a\u00020\bH\'J\u00c0\u0001\u0010\u00e4\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00c7\u0001\u0018\u00010\u00032\u0010\b\u0001\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00142\t\b\u0001\u0010\u00cb\u0001\u001a\u00020\f2\b\b\u0001\u0010\u0016\u001a\u00020\f2\b\b\u0001\u00104\u001a\u00020\f2\n\b\u0001\u0010\u001d\u001a\u0004\u0018\u00010\f2\b\b\u0001\u00105\u001a\u00020\f2\b\b\u0001\u0010\\\u001a\u00020\f2\u000e\b\u0001\u0010]\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\t\b\u0001\u0010\u00cd\u0001\u001a\u00020\f2\t\b\u0001\u0010\u00ce\u0001\u001a\u00020\f2\t\b\u0001\u0010\u00cf\u0001\u001a\u00020\f2\t\b\u0001\u0010\u00d0\u0001\u001a\u00020\f2\t\b\u0001\u0010\u00d1\u0001\u001a\u00020\f2\b\b\u0001\u0010\u0018\u001a\u00020\f2\b\b\u0001\u0010^\u001a\u00020\fH\'J\u0013\u0010\u00e5\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003H\'J\'\u0010\u00e6\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010D\u001a\u00020\b2\b\b\u0001\u0010\u000b\u001a\u00020\fH\'J\u001e\u0010\u00e7\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u00c7\u0001\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020RH\'\u00a8\u0006\u00e8\u0001"}, d2 = {"Lfudex/bonyad/NetWorkConnction/ApiInterface;", "", "aboutus", "Lretrofit2/Call;", "Lfudex/bonyad/Model/AboutModel;", "accepttechnicalappointment", "Lfudex/bonyad/Helper/ErrorResponse;", "reservationId", "", "addaddress", "Lfudex/bonyad/Helper/AddaressModel;", "requestBody", "Lokhttp3/RequestBody;", "addcart", "data", "Lfudex/bonyad/Data/Cartdata;", "adddates", "Lfudex/bonyad/Model/Dateadd;", "addproduct", "images", "", "Lokhttp3/MultipartBody$Part;", "name", "category_id", "description", "price", "quantity", "unit_id", "addservice", "email", "addservices", "Lfudex/bonyad/Model/AddserviceModel;", "cancelappointment", "cancelorder", "orderId", "cancelpolicy", "cancelsubseibe", "canceluserorder", "changelang", "changestatus", "Lfudex/bonyad/Data/Changestatus;", "checkotp", "checkregister", "completeappointment", "contactus", "Lfudex/bonyad/Data/Contactdata;", "createOrder", "Lfudex/bonyad/Model/BookserviceModel;", "location_type", "", "address_id", "is_gift", "mobile", "address", "payment_method", "gender", "branchId", "date", "services", "", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/Map;)Lretrofit2/Call;", "createorder", "Lfudex/bonyad/Model/CreateorderModel;", "Lfudex/bonyad/Data/Orderdata;", "createreserve", "Lfudex/bonyad/Data/Craetereserve;", "deleteaccount", "deleteaddress", "addressId", "deleteitemcart", "productId", "(Ljava/lang/Integer;)Lretrofit2/Call;", "deletenots", "notId", "deleteproduct", "deleteservices", "editcart", "editemail", "Lfudex/bonyad/Model/ProfileModel;", "editpass", "Lfudex/bonyad/Data/Editpass;", "editphone", "Lfudex/bonyad/Data/Userdata;", "editphone1", "editproduct", "editprofile", "editprofilewithimage", "imageFile", "tradename", "des", "editreserve", "edittechnicalprofilewithimage", "experience_years", "parts", "zone_id", "faqs", "Lfudex/bonyad/Model/FaqModel;", "getSlots", "Lfudex/bonyad/Model/ScheduleResponse;", "technicalId", "getaddress", "Lfudex/bonyad/Model/AddressesModel;", "page", "getappointmentdetails", "Lfudex/bonyad/Model/AppointmentdetailsModel;", "getavailbilty", "getbranches", "Lfudex/bonyad/Model/BrachesModel;", "lat", "lng", "max_distance", "service_type", "employee_gender", "rate", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lretrofit2/Call;", "getcarts", "Lfudex/bonyad/Model/CartModel;", "getchat", "Lfudex/bonyad/Model/ChatModel;", "messageId", "paginate", "(ILjava/lang/Integer;Ljava/lang/Integer;)Lretrofit2/Call;", "getcities", "Lfudex/bonyad/Model/StatesModel;", "state_id", "getcomplain", "Lfudex/bonyad/Model/ComplainModel;", "status", "(Ljava/lang/Integer;Ljava/lang/Integer;)Lretrofit2/Call;", "getdeps", "gethome", "Lfudex/bonyad/Model/HomeModel;", "getmerchanthome", "Lfudex/bonyad/Model/MerchantHomeModel;", "getmerchantorderdetails", "Lfudex/bonyad/Model/MerchantorderdetailsModel;", "getmerchantorders", "Lfudex/bonyad/Model/MerchantOrdersModel;", "date_from", "date_to", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lretrofit2/Call;", "getmostproducts", "Lfudex/bonyad/Model/ProductsModel;", "getmyplan", "Lfudex/bonyad/Model/MYSubsribeModel;", "getmyproducts", "zones", "(Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lretrofit2/Call;", "getmyservices", "getnots", "Lfudex/bonyad/Model/NotsModel;", "getnotscounts", "getorderdetails", "Lfudex/bonyad/Model/OrderdetailsModel;", "getorders", "Lfudex/bonyad/Model/OrdersModel;", "getplans", "Lfudex/bonyad/Model/PlanModel;", "getproductdetails", "Lfudex/bonyad/Model/DetailsProductsModel;", "getproducts", "(Ljava/util/List;Ljava/lang/Integer;Ljava/lang/Integer;)Lretrofit2/Call;", "getprofiledata", "getrating", "Lfudex/bonyad/Model/RatingModel;", "(ILjava/lang/Integer;)Lretrofit2/Call;", "getratings", "Lfudex/bonyad/Model/RatingsModel;", "reviewable_type", "reviewable_id", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)Lretrofit2/Call;", "getservices", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lretrofit2/Call;", "getservicesdetails", "Lfudex/bonyad/Model/ServicesdetailsModel;", "serviceId", "getservicestype", "Lfudex/bonyad/Model/ServicestypeModel;", "getsetting", "Lfudex/bonyad/Model/ContactusModel;", "getsliders", "Lfudex/bonyad/Model/SliderModel;", "gettechnical", "Lfudex/bonyad/Model/DetailstechnicalModel;", "gettechnicalappointmentdetails", "gettechnicalhome", "Lfudex/bonyad/Model/TechnicalHomeModel;", "gettechnicalmyreservation", "gettechnicals", "Lfudex/bonyad/Model/TechnicalModel;", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)Lretrofit2/Call;", "getunits", "getusermyreservation", "getuserorderdetails", "getuserorders", "getuserproductdetails", "getwalletbalance", "getzones", "login", "Lfudex/bonyad/Model/LoginData;", "logout", "makenotscread", "merchantregister", "type", "trade_name", "password", "password_confirmation", "fcm_token", "device_type", "device_id", "privacy", "rateuser", "Lfudex/bonyad/Data/Ratingdata;", "refusetechnicalappointment", "register", "registersocail", "provider_token", "provider_type", "resendsendotp", "restpass", "sendchat", "Lfudex/bonyad/Data/Chatdata;", "sendcomplain", "sendotp", "sociallogin", "subsribe", "Lfudex/bonyad/Model/SubsribeModel;", "subsctibeId", "technicalregister", "terms", "updateaddress", "verifiyuser", "app_debug"})
public abstract interface ApiInterface {
    
    @retrofit2.http.GET(value = "client/serviceTypes")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ServicestypeModel> getservicestype();
    
    @retrofit2.http.GET(value = "zones")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.StatesModel> getzones();
    
    @retrofit2.http.GET(value = "categories")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.StatesModel> getdeps();
    
    @retrofit2.http.GET(value = "units")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.StatesModel> getunits();
    
    @retrofit2.http.GET(value = "user-home")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.HomeModel> gethome(@retrofit2.http.Query(value = "name")
    @org.jetbrains.annotations.Nullable()
    java.lang.String name);
    
    @retrofit2.http.GET(value = "technician/analytics")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.TechnicalHomeModel> gettechnicalhome();
    
    @retrofit2.http.GET(value = "merchant/home")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.MerchantHomeModel> getmerchanthome();
    
    @retrofit2.http.GET(value = "technicians")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.TechnicalModel> gettechnicals(@retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @retrofit2.http.Query(value = "paginate")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer paginate, @retrofit2.http.Query(value = "zones[]")
    @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Integer> zones, @retrofit2.http.Query(value = "services[]")
    @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Integer> services, @retrofit2.http.Query(value = "name")
    @org.jetbrains.annotations.Nullable()
    java.lang.String name);
    
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
    
    @retrofit2.http.GET(value = "technicians/{technicalId}")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.DetailstechnicalModel> gettechnical(@retrofit2.http.Path(value = "technicalId")
    int technicalId);
    
    @retrofit2.http.GET(value = "user-details")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ProfileModel> getprofiledata();
    
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
    @retrofit2.http.POST(value = "register")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.LoginData> technicalregister(@retrofit2.http.Part()
    @org.jetbrains.annotations.Nullable()
    java.util.List<okhttp3.MultipartBody.Part> images, @retrofit2.http.Part(value = "type")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody type, @retrofit2.http.Part(value = "name")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody name, @retrofit2.http.Part(value = "phone")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody mobile, @retrofit2.http.Part(value = "email")
    @org.jetbrains.annotations.Nullable()
    okhttp3.RequestBody email, @retrofit2.http.Part(value = "address")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody address, @retrofit2.http.Part(value = "experience_years")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody experience_years, @retrofit2.http.Part()
    @org.jetbrains.annotations.NotNull()
    java.util.List<okhttp3.MultipartBody.Part> parts, @retrofit2.http.Part(value = "password")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody password, @retrofit2.http.Part(value = "password_confirmation")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody password_confirmation, @retrofit2.http.Part(value = "fcm_token")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody fcm_token, @retrofit2.http.Part(value = "firebase_token_type")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody device_type, @retrofit2.http.Part(value = "remember_me")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody device_id, @retrofit2.http.Part(value = "description")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody description, @retrofit2.http.Part(value = "zone_id")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody zone_id);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "register")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.LoginData> merchantregister(@retrofit2.http.Part()
    @org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part imageFile, @retrofit2.http.Part(value = "type")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody type, @retrofit2.http.Part(value = "name")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody name, @retrofit2.http.Part(value = "phone")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody mobile, @retrofit2.http.Part(value = "email")
    @org.jetbrains.annotations.Nullable()
    okhttp3.RequestBody email, @retrofit2.http.Part(value = "address")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody address, @retrofit2.http.Part(value = "trade_name")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody trade_name, @retrofit2.http.Part(value = "password")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody password, @retrofit2.http.Part(value = "password_confirmation")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody password_confirmation, @retrofit2.http.Part(value = "fcm_token")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody fcm_token, @retrofit2.http.Part(value = "firebase_token_type")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody device_type, @retrofit2.http.Part(value = "remember_me")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody device_id, @retrofit2.http.Part(value = "description_of_business_activity")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody description);
    
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
    public abstract retrofit2.Call<fudex.bonyad.Model.ProfileModel> sendotp(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Userdata requestBody);
    
    @retrofit2.http.POST(value = "resend-code")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ProfileModel> resendsendotp(@retrofit2.http.Body()
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
    
    @retrofit2.http.POST(value = "edit-password")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> editpass(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Editpass requestBody);
    
    @retrofit2.http.POST(value = "client/updateEmail")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ProfileModel> editemail(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBody);
    
    @retrofit2.http.POST(value = "edit-phone")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ProfileModel> editphone(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Userdata requestBody);
    
    @retrofit2.http.Headers(value = {"Accept: application/json"})
    @retrofit2.http.POST(value = "edit-user")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ProfileModel> editprofile(@retrofit2.http.Body()
    @org.jetbrains.annotations.Nullable()
    okhttp3.RequestBody requestBody);
    
    @retrofit2.http.POST(value = "verify-new-phone")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ProfileModel> editphone1(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Userdata requestBody);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "edit-user")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ProfileModel> editprofilewithimage(@retrofit2.http.Part()
    @org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part imageFile, @retrofit2.http.Part(value = "name")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody name, @retrofit2.http.Part(value = "email")
    @org.jetbrains.annotations.Nullable()
    okhttp3.RequestBody email, @retrofit2.http.Part(value = "address")
    @org.jetbrains.annotations.Nullable()
    okhttp3.RequestBody address, @retrofit2.http.Part(value = "trade_name")
    @org.jetbrains.annotations.Nullable()
    okhttp3.RequestBody tradename, @retrofit2.http.Part(value = "description_of_business_activity")
    @org.jetbrains.annotations.Nullable()
    okhttp3.RequestBody des);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "edit-user")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ProfileModel> edittechnicalprofilewithimage(@retrofit2.http.Part()
    @org.jetbrains.annotations.Nullable()
    okhttp3.MultipartBody.Part imageFile, @retrofit2.http.Part()
    @org.jetbrains.annotations.Nullable()
    java.util.List<okhttp3.MultipartBody.Part> images, @retrofit2.http.Part(value = "name")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody name, @retrofit2.http.Part(value = "email")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody email, @retrofit2.http.Part(value = "address")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody address, @retrofit2.http.Part(value = "experience_years")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody experience_years, @retrofit2.http.Part()
    @org.jetbrains.annotations.NotNull()
    java.util.List<okhttp3.MultipartBody.Part> parts, @retrofit2.http.Part(value = "description")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody description, @retrofit2.http.Part(value = "zone_id")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody zone_id);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "services/create")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> addservice(@retrofit2.http.Part()
    @org.jetbrains.annotations.Nullable()
    java.util.List<okhttp3.MultipartBody.Part> images, @retrofit2.http.Part(value = "name")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody name, @retrofit2.http.Part(value = "description")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody email);
    
    @retrofit2.http.GET(value = "pages/about-us")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.AboutModel> aboutus();
    
    @retrofit2.http.GET(value = "settings")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ContactusModel> getsetting();
    
    @retrofit2.http.GET(value = "client/pages/cancellation-policy")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.AboutModel> cancelpolicy();
    
    @retrofit2.http.GET(value = "client/faqs")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.FaqModel> faqs();
    
    @retrofit2.http.GET(value = "pages/terms-and-conditions")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.AboutModel> terms();
    
    @retrofit2.http.GET(value = "pages/privacy-policy")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.AboutModel> privacy();
    
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
    
    @retrofit2.http.POST(value = "logout")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> logout(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody requestBody);
    
    @retrofit2.http.POST(value = "client/deleteAccount")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> deleteaccount();
    
    @retrofit2.http.GET(value = "technician/availability-dates/{technicalId}")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ScheduleResponse> getSlots(@retrofit2.http.Path(value = "technicalId")
    int technicalId, @retrofit2.http.Query(value = "date")
    @org.jetbrains.annotations.NotNull()
    java.lang.String date);
    
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
    
    @retrofit2.http.POST(value = "reviews")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> rateuser(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Ratingdata data);
    
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
    
    @retrofit2.http.POST(value = "client/notifications/delete")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> deletenots();
    
    @retrofit2.http.GET(value = "my-services")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.StatesModel> getmyservices();
    
    @retrofit2.http.GET(value = "services")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.StatesModel> getservices(@retrofit2.http.Query(value = "name")
    @org.jetbrains.annotations.Nullable()
    java.lang.String name, @retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @retrofit2.http.Query(value = "paginate")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer paginate);
    
    @retrofit2.http.GET(value = "reservations")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.OrdersModel> getusermyreservation(@retrofit2.http.Query(value = "status[]")
    @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Integer> status, @retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @retrofit2.http.Query(value = "paginate")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer paginate);
    
    @retrofit2.http.GET(value = "technician/reservations")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.OrdersModel> gettechnicalmyreservation(@retrofit2.http.Query(value = "status[]")
    @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Integer> status, @retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @retrofit2.http.Query(value = "paginate")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer paginate);
    
    @retrofit2.http.GET(value = "technician/reservations/{reservationId}")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.AppointmentdetailsModel> gettechnicalappointmentdetails(@retrofit2.http.Path(value = "reservationId")
    int reservationId);
    
    @retrofit2.http.GET(value = "reservations/{reservationId}")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.AppointmentdetailsModel> getappointmentdetails(@retrofit2.http.Path(value = "reservationId")
    int reservationId);
    
    @retrofit2.http.POST(value = "technician/reservations/{reservationId}/refuse")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> refusetechnicalappointment(@retrofit2.http.Path(value = "reservationId")
    int reservationId, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Userdata data);
    
    @retrofit2.http.GET(value = "reservations/{reservationId}/cancel")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> cancelappointment(@retrofit2.http.Path(value = "reservationId")
    int reservationId);
    
    @retrofit2.http.GET(value = "reservations/{reservationId}/complete")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> completeappointment(@retrofit2.http.Path(value = "reservationId")
    int reservationId);
    
    @retrofit2.http.GET(value = "technician/reservations/{reservationId}/accept")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> accepttechnicalappointment(@retrofit2.http.Path(value = "reservationId")
    int reservationId);
    
    @retrofit2.http.GET(value = "services/{serviceId}")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ServicesdetailsModel> getservicesdetails(@retrofit2.http.Path(value = "serviceId")
    int serviceId);
    
    @retrofit2.http.GET(value = "availability")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ScheduleResponse> getavailbilty();
    
    @retrofit2.http.POST(value = "availability/bulk")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> adddates(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Model.Dateadd data);
    
    @retrofit2.http.GET(value = "plans")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.PlanModel> getplans();
    
    @retrofit2.http.GET(value = "plans/{subsctibeId}/subscribe")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.SubsribeModel> subsribe(@retrofit2.http.Path(value = "subsctibeId")
    int subsctibeId);
    
    @retrofit2.http.GET(value = "plans/cancel-subscription")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> cancelsubseibe();
    
    @retrofit2.http.GET(value = "plans/my-plan")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.MYSubsribeModel> getmyplan();
    
    @retrofit2.http.POST(value = "services/add")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> addservices(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Model.AddserviceModel data);
    
    @retrofit2.http.HTTP(method = "DELETE", path = "services/delete", hasBody = true)
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> deleteservices(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Model.AddserviceModel data);
    
    @retrofit2.http.POST(value = "reservations")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> createreserve(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Craetereserve data);
    
    @retrofit2.http.PUT(value = "reservations/{reservationId}")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> editreserve(@retrofit2.http.Path(value = "reservationId")
    int reservationId, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Craetereserve data);
    
    @retrofit2.http.POST(value = "chat/send")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> sendchat(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Chatdata data);
    
    @retrofit2.http.GET(value = "chat/messages-by-user/{messageId}")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ChatModel> getchat(@retrofit2.http.Path(value = "messageId")
    int messageId, @retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @retrofit2.http.Query(value = "paginate")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer paginate);
    
    @retrofit2.http.GET(value = "notifications")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.NotsModel> getnots(@retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @retrofit2.http.Query(value = "paginate")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer paginate);
    
    @retrofit2.http.GET(value = "unread-count")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.NotsModel> getnotscounts();
    
    @retrofit2.http.POST(value = "mark-all-read")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> makenotscread();
    
    @retrofit2.http.DELETE(value = "notifications/{notId}")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> deletenots(@retrofit2.http.Path(value = "notId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String notId);
    
    @retrofit2.http.GET(value = "reviews")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.RatingsModel> getratings(@retrofit2.http.Query(value = "reviewable_type")
    @org.jetbrains.annotations.Nullable()
    java.lang.String reviewable_type, @retrofit2.http.Query(value = "reviewable_id")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer reviewable_id, @retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @retrofit2.http.Query(value = "paginate")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer paginate);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "merchant/products")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> addproduct(@retrofit2.http.Part()
    @org.jetbrains.annotations.Nullable()
    java.util.List<okhttp3.MultipartBody.Part> images, @retrofit2.http.Part(value = "name")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody name, @retrofit2.http.Part(value = "category_id")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody category_id, @retrofit2.http.Part(value = "description")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody description, @retrofit2.http.Part(value = "price")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody price, @retrofit2.http.Part(value = "quantity")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody quantity, @retrofit2.http.Part(value = "unit_id")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody unit_id);
    
    @retrofit2.http.Multipart()
    @retrofit2.http.POST(value = "merchant/products/{productId}?_method=put")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> editproduct(@retrofit2.http.Path(value = "productId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String productId, @retrofit2.http.Part()
    @org.jetbrains.annotations.Nullable()
    java.util.List<okhttp3.MultipartBody.Part> images, @retrofit2.http.Part(value = "name")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody name, @retrofit2.http.Part(value = "category_id")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody category_id, @retrofit2.http.Part(value = "description")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody description, @retrofit2.http.Part(value = "price")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody price, @retrofit2.http.Part(value = "quantity")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody quantity, @retrofit2.http.Part(value = "unit_id")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody unit_id);
    
    @retrofit2.http.GET(value = "merchant/products")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ProductsModel> getmyproducts(@retrofit2.http.Query(value = "categories[]")
    @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Integer> zones, @retrofit2.http.Query(value = "name")
    @org.jetbrains.annotations.Nullable()
    java.lang.String name, @retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @retrofit2.http.Query(value = "paginate")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer paginate);
    
    @retrofit2.http.GET(value = "merchant/most-sold-products")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ProductsModel> getmostproducts(@retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @retrofit2.http.Query(value = "paginate")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer paginate);
    
    @retrofit2.http.HTTP(method = "DELETE", path = "merchant/products/{productId}", hasBody = true)
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> deleteproduct(@retrofit2.http.Path(value = "productId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String productId);
    
    @retrofit2.http.GET(value = "merchant/products/{productId}")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.DetailsProductsModel> getproductdetails(@retrofit2.http.Path(value = "productId")
    int productId);
    
    @retrofit2.http.GET(value = "products")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ProductsModel> getproducts(@retrofit2.http.Query(value = "categories[]")
    @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Integer> zones, @retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @retrofit2.http.Query(value = "paginate")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer paginate);
    
    @retrofit2.http.GET(value = "products/{productId}")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.DetailsProductsModel> getuserproductdetails(@retrofit2.http.Path(value = "productId")
    int productId);
    
    @retrofit2.http.POST(value = "carts")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> addcart(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Cartdata data);
    
    @retrofit2.http.PUT(value = "carts/{productId}")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> editcart(@retrofit2.http.Path(value = "productId")
    int productId, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Cartdata data);
    
    @retrofit2.http.HTTP(method = "DELETE", path = "delete-item/{productId}", hasBody = true)
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> deleteitemcart(@retrofit2.http.Path(value = "productId")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer productId);
    
    @retrofit2.http.GET(value = "carts")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.CartModel> getcarts();
    
    @retrofit2.http.GET(value = "wallet/balance")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.NotsModel> getwalletbalance();
    
    @retrofit2.http.POST(value = "orders")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.CreateorderModel> createorder(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Orderdata data);
    
    @retrofit2.http.GET(value = "merchant/orders")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.MerchantOrdersModel> getmerchantorders(@retrofit2.http.Query(value = "status[]")
    @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Integer> status, @retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @retrofit2.http.Query(value = "paginate")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer paginate, @retrofit2.http.Query(value = "date_from")
    @org.jetbrains.annotations.Nullable()
    java.lang.String date_from, @retrofit2.http.Query(value = "date_to")
    @org.jetbrains.annotations.Nullable()
    java.lang.String date_to);
    
    @retrofit2.http.GET(value = "merchant/orders/{orderId}")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.MerchantorderdetailsModel> getmerchantorderdetails(@retrofit2.http.Path(value = "orderId")
    int orderId);
    
    @retrofit2.http.GET(value = "orders")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.MerchantOrdersModel> getuserorders(@retrofit2.http.Query(value = "status[]")
    @org.jetbrains.annotations.Nullable()
    java.util.List<java.lang.Integer> status, @retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer page, @retrofit2.http.Query(value = "paginate")
    @org.jetbrains.annotations.Nullable()
    java.lang.Integer paginate);
    
    @retrofit2.http.GET(value = "orders/{orderId}")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.MerchantorderdetailsModel> getuserorderdetails(@retrofit2.http.Path(value = "orderId")
    int orderId);
    
    @retrofit2.http.PUT(value = "merchant/orders/{orderId}/status")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> changestatus(@retrofit2.http.Path(value = "orderId")
    int orderId, @retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    fudex.bonyad.Data.Changestatus data);
    
    @retrofit2.http.POST(value = "orders/{orderId}/cancel")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Helper.ErrorResponse> canceluserorder(@retrofit2.http.Path(value = "orderId")
    int orderId);
}