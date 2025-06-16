package fudex.bonyad.NetWorkConnction;

import fudex.bonyad.Data.Contactdata;
import fudex.bonyad.Data.Craetereserve;
import fudex.bonyad.Data.Editpass;
import fudex.bonyad.Data.Userdata;
import fudex.bonyad.Helper.AddaressModel;
import fudex.bonyad.Helper.ErrorResponse;
import fudex.bonyad.Model.AddressesModel;
import fudex.bonyad.Model.BookserviceModel;
import fudex.bonyad.Model.BrachesModel;
import fudex.bonyad.Model.BranchsetailsModel;
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
import fudex.bonyad.Model.SlotsModel;
import fudex.bonyad.Model.StatesModel;
import fudex.bonyad.Model.User;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import fudex.bonyad.Model.AboutModel;
import fudex.bonyad.Model.AddserviceModel;
import fudex.bonyad.Model.Dateadd;
import fudex.bonyad.Model.DetailstechnicalModel;
import fudex.bonyad.Model.FaqModel;
import fudex.bonyad.Model.HomeModel;
import fudex.bonyad.Model.MYSubsribeModel;
import fudex.bonyad.Model.PlanModel;
import fudex.bonyad.Model.ProfileModel;
import fudex.bonyad.Model.SubsribeModel;
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
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0098\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\t\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003H\'J\u001c\u0010\u0005\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010\t\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020\fH\'J8\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\u0010\b\u0001\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\b\b\u0001\u0010\u0011\u001a\u00020\b2\b\b\u0001\u0010\u0012\u001a\u00020\bH\'J\u001c\u0010\u0013\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020\u0014H\'J\u001c\u0010\u0015\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0016\u001a\u00020\u0017H\'J\u0012\u0010\u0018\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003H\'J\u0012\u0010\u0019\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0003H\'J\u001c\u0010\u001a\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010\u001b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010\u001c\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010\u001d\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\u001eH\'J\u00b3\u0001\u0010\u001f\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010 \u0018\u00010\u00032\n\b\u0001\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010#\u001a\u0004\u0018\u00010\u00172\n\b\u0001\u0010$\u001a\u0004\u0018\u00010\u00172\n\b\u0001\u0010%\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010&\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010\'\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010(\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010)\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010*\u001a\u0004\u0018\u00010\u00172\n\b\u0001\u0010+\u001a\u0004\u0018\u00010\"2\u0016\b\u0001\u0010,\u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\"\u0018\u00010-H\'\u00a2\u0006\u0002\u0010.J\u001c\u0010/\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u000200H\'J\u0012\u00101\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0003H\'J\u001c\u00102\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u00103\u001a\u00020\u0017H\'J\u0012\u00104\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u0003H\'J\u001c\u00105\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020\u0014H\'J\u001c\u00106\u001a\f\u0012\u0006\u0012\u0004\u0018\u000107\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001c\u00108\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u000209H\'J\u001c\u0010:\u001a\f\u0012\u0006\u0012\u0004\u0018\u000107\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020;H\'J\u001c\u0010<\u001a\f\u0012\u0006\u0012\u0004\u0018\u000107\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020;H\'J\u001c\u0010=\u001a\f\u0012\u0006\u0012\u0004\u0018\u000107\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J2\u0010>\u001a\f\u0012\u0006\u0012\u0004\u0018\u000107\u0018\u00010\u00032\b\b\u0001\u0010?\u001a\u00020\u00102\b\b\u0001\u0010\u0011\u001a\u00020\b2\n\b\u0001\u0010\u0012\u001a\u0004\u0018\u00010\bH\'Jr\u0010@\u001a\f\u0012\u0006\u0012\u0004\u0018\u000107\u0018\u00010\u00032\n\b\u0001\u0010?\u001a\u0004\u0018\u00010\u00102\u0010\b\u0001\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f2\b\b\u0001\u0010\u0011\u001a\u00020\b2\b\b\u0001\u0010\u0012\u001a\u00020\b2\b\b\u0001\u0010&\u001a\u00020\b2\b\b\u0001\u0010A\u001a\u00020\b2\u000e\b\u0001\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\b\b\u0001\u0010C\u001a\u00020\bH\'J\u0012\u0010D\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010E\u0018\u00010\u0003H\'J&\u0010F\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010G\u0018\u00010\u00032\b\b\u0001\u0010H\u001a\u00020\u00172\b\b\u0001\u0010+\u001a\u00020\"H\'J#\u0010I\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010J\u0018\u00010\u00032\n\b\u0001\u0010K\u001a\u0004\u0018\u00010\u0017H\'\u00a2\u0006\u0002\u0010LJ\u0012\u0010M\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010G\u0018\u00010\u0003H\'J\u0083\u0001\u0010N\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010O\u0018\u00010\u00032\n\b\u0001\u0010K\u001a\u0004\u0018\u00010\u00172\n\b\u0001\u0010P\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010Q\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010R\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010S\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010T\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010U\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\"H\'\u00a2\u0006\u0002\u0010VJ#\u0010W\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010X\u0018\u00010\u00032\n\b\u0001\u0010Y\u001a\u0004\u0018\u00010\u0017H\'\u00a2\u0006\u0002\u0010LJ/\u0010Z\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010[\u0018\u00010\u00032\n\b\u0001\u0010K\u001a\u0004\u0018\u00010\u00172\n\b\u0001\u0010\\\u001a\u0004\u0018\u00010\u0017H\'\u00a2\u0006\u0002\u0010]J\u001e\u0010^\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010_\u0018\u00010\u00032\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\"H\'J\u0012\u0010`\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010a\u0018\u00010\u0003H\'J\u0012\u0010b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010X\u0018\u00010\u0003H\'J#\u0010c\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010d\u0018\u00010\u00032\n\b\u0001\u0010K\u001a\u0004\u0018\u00010\u0017H\'\u00a2\u0006\u0002\u0010LJ4\u0010e\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010f\u0018\u00010\u00032\b\b\u0001\u0010\u0016\u001a\u00020\u00172\n\b\u0001\u0010P\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010Q\u001a\u0004\u0018\u00010\"H\'J/\u0010g\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010h\u0018\u00010\u00032\n\b\u0001\u0010K\u001a\u0004\u0018\u00010\u00172\n\b\u0001\u0010\\\u001a\u0004\u0018\u00010\u0017H\'\u00a2\u0006\u0002\u0010]J\u0012\u0010i\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010j\u0018\u00010\u0003H\'J\u0012\u0010k\u001a\f\u0012\u0006\u0012\u0004\u0018\u000107\u0018\u00010\u0003H\'J-\u0010l\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010m\u0018\u00010\u00032\b\b\u0001\u0010*\u001a\u00020\u00172\n\b\u0001\u0010K\u001a\u0004\u0018\u00010\u0017H\'\u00a2\u0006\u0002\u0010nJ;\u0010o\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010X\u0018\u00010\u00032\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\"2\n\b\u0001\u0010K\u001a\u0004\u0018\u00010\u00172\n\b\u0001\u0010p\u001a\u0004\u0018\u00010\u0017H\'\u00a2\u0006\u0002\u0010qJ\u001c\u0010r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010s\u0018\u00010\u00032\b\b\u0001\u0010t\u001a\u00020\u0017H\'J\u0012\u0010u\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010v\u0018\u00010\u0003H\'J\u0012\u0010w\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010x\u0018\u00010\u0003H\'J\u0012\u0010y\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010z\u0018\u00010\u0003H\'J\u001c\u0010{\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010|\u0018\u00010\u00032\b\b\u0001\u0010H\u001a\u00020\u0017H\'J`\u0010}\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010~\u0018\u00010\u00032\n\b\u0001\u0010K\u001a\u0004\u0018\u00010\u00172\n\b\u0001\u0010p\u001a\u0004\u0018\u00010\u00172\u0010\b\u0001\u0010\u007f\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u000f2\u0010\b\u0001\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u000f2\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\"H\'\u00a2\u0006\u0003\u0010\u0080\u0001J\u0013\u0010\u0081\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010X\u0018\u00010\u0003H\'J\u001e\u0010\u0082\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0083\u0001\u0018\u00010\u00032\b\b\u0001\u0010\u000b\u001a\u00020;H\'J\u001d\u0010\u0084\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u0013\u0010\u0085\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003H\'J\'\u0010\u0086\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0016\u001a\u00020\u00172\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001e\u0010\u0087\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0083\u0001\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020;H\'Ji\u0010\u0088\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0083\u0001\u0018\u00010\u00032\b\b\u0001\u0010\u0011\u001a\u00020\b2\b\b\u0001\u0010%\u001a\u00020\b2\b\b\u0001\u0010\u0012\u001a\u00020\b2\t\b\u0001\u0010\u0089\u0001\u001a\u00020\b2\t\b\u0001\u0010\u008a\u0001\u001a\u00020\b2\t\b\u0001\u0010\u008b\u0001\u001a\u00020\b2\t\b\u0001\u0010\u008c\u0001\u001a\u00020\b2\t\b\u0001\u0010\u008d\u0001\u001a\u00020\bH\'J\u001d\u0010\u008e\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u000107\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020;H\'J\u001d\u0010\u008f\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020;H\'J\u001d\u0010\u0090\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001d\u0010\u0091\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u000107\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020;H\'J\u001e\u0010\u0092\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0083\u0001\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001f\u0010\u0093\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0094\u0001\u0018\u00010\u00032\t\b\u0001\u0010\u0095\u0001\u001a\u00020\u0017H\'J\u00b2\u0001\u0010\u0096\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0083\u0001\u0018\u00010\u00032\u000e\b\u0001\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\t\b\u0001\u0010\u0097\u0001\u001a\u00020\b2\b\b\u0001\u0010\u0011\u001a\u00020\b2\b\b\u0001\u0010%\u001a\u00020\b2\b\b\u0001\u0010\u0012\u001a\u00020\b2\b\b\u0001\u0010&\u001a\u00020\b2\b\b\u0001\u0010A\u001a\u00020\b2\u000e\b\u0001\u0010B\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\t\b\u0001\u0010\u0098\u0001\u001a\u00020\b2\t\b\u0001\u0010\u0099\u0001\u001a\u00020\b2\t\b\u0001\u0010\u008b\u0001\u001a\u00020\b2\t\b\u0001\u0010\u008c\u0001\u001a\u00020\b2\t\b\u0001\u0010\u008d\u0001\u001a\u00020\b2\b\b\u0001\u0010C\u001a\u00020\bH\'J\u0013\u0010\u009a\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003H\'J\'\u0010\u009b\u0001\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\u00032\b\b\u0001\u00103\u001a\u00020\u00172\b\b\u0001\u0010\u0007\u001a\u00020\bH\'J\u001e\u0010\u009c\u0001\u001a\r\u0012\u0007\u0012\u0005\u0018\u00010\u0083\u0001\u0018\u00010\u00032\b\b\u0001\u0010\u0007\u001a\u00020;H\'\u00a8\u0006\u009d\u0001"}, d2 = {"Lfudex/bonyad/NetWorkConnction/ApiInterface;", "", "aboutus", "Lretrofit2/Call;", "Lfudex/bonyad/Model/AboutModel;", "addaddress", "Lfudex/bonyad/Helper/AddaressModel;", "requestBody", "Lokhttp3/RequestBody;", "adddates", "Lfudex/bonyad/Helper/ErrorResponse;", "data", "Lfudex/bonyad/Model/Dateadd;", "addservice", "images", "", "Lokhttp3/MultipartBody$Part;", "name", "email", "addservices", "Lfudex/bonyad/Model/AddserviceModel;", "cancelorder", "orderId", "", "cancelpolicy", "cancelsubseibe", "changelang", "checkotp", "checkregister", "contactus", "Lfudex/bonyad/Data/Contactdata;", "createOrder", "Lfudex/bonyad/Model/BookserviceModel;", "location_type", "", "address_id", "is_gift", "mobile", "address", "payment_method", "gender", "price", "branchId", "date", "services", "", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;Ljava/util/Map;)Lretrofit2/Call;", "createreserve", "Lfudex/bonyad/Data/Craetereserve;", "deleteaccount", "deleteaddress", "addressId", "deletenots", "deleteservices", "editemail", "Lfudex/bonyad/Model/ProfileModel;", "editpass", "Lfudex/bonyad/Data/Editpass;", "editphone", "Lfudex/bonyad/Data/Userdata;", "editphone1", "editprofile", "editprofilewithimage", "imageFile", "edittechnicalprofilewithimage", "experience_years", "parts", "description", "faqs", "Lfudex/bonyad/Model/FaqModel;", "getSlots", "Lfudex/bonyad/Model/ScheduleResponse;", "technicalId", "getaddress", "Lfudex/bonyad/Model/AddressesModel;", "page", "(Ljava/lang/Integer;)Lretrofit2/Call;", "getavailbilty", "getbranches", "Lfudex/bonyad/Model/BrachesModel;", "lat", "lng", "max_distance", "service_type", "employee_gender", "rate", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lretrofit2/Call;", "getcities", "Lfudex/bonyad/Model/StatesModel;", "state_id", "getcomplain", "Lfudex/bonyad/Model/ComplainModel;", "status", "(Ljava/lang/Integer;Ljava/lang/Integer;)Lretrofit2/Call;", "gethome", "Lfudex/bonyad/Model/HomeModel;", "getmyplan", "Lfudex/bonyad/Model/MYSubsribeModel;", "getmyservices", "getnots", "Lfudex/bonyad/Model/NotsModel;", "getorderdetails", "Lfudex/bonyad/Model/OrderdetailsModel;", "getorders", "Lfudex/bonyad/Model/OrdersModel;", "getplans", "Lfudex/bonyad/Model/PlanModel;", "getprofiledata", "getrating", "Lfudex/bonyad/Model/RatingModel;", "(ILjava/lang/Integer;)Lretrofit2/Call;", "getservices", "paginate", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lretrofit2/Call;", "getservicesdetails", "Lfudex/bonyad/Model/ServicesdetailsModel;", "serviceId", "getservicestype", "Lfudex/bonyad/Model/ServicestypeModel;", "getsetting", "Lfudex/bonyad/Model/ContactusModel;", "getsliders", "Lfudex/bonyad/Model/SliderModel;", "gettechnical", "Lfudex/bonyad/Model/DetailstechnicalModel;", "gettechnicals", "Lfudex/bonyad/Model/TechnicalModel;", "zones", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/util/List;Ljava/util/List;Ljava/lang/String;)Lretrofit2/Call;", "getzones", "login", "Lfudex/bonyad/Model/LoginData;", "logout", "privacy", "rateorder", "register", "registersocail", "provider_token", "provider_type", "fcm_token", "device_type", "device_id", "resendsendotp", "restpass", "sendcomplain", "sendotp", "sociallogin", "subsribe", "Lfudex/bonyad/Model/SubsribeModel;", "subsctibeId", "technicalregister", "type", "password", "password_confirmation", "terms", "updateaddress", "verifiyuser", "app_debug"})
public abstract interface ApiInterface {
    
    @retrofit2.http.GET(value = "client/serviceTypes")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.ServicestypeModel> getservicestype();
    
    @retrofit2.http.GET(value = "zones")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.StatesModel> getzones();
    
    @retrofit2.http.GET(value = "user-home")
    @org.jetbrains.annotations.Nullable()
    public abstract retrofit2.Call<fudex.bonyad.Model.HomeModel> gethome(@retrofit2.http.Query(value = "name")
    @org.jetbrains.annotations.Nullable()
    java.lang.String name);
    
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
    @org.jetbrains.annotations.NotNull()
    java.util.List<okhttp3.MultipartBody.Part> images, @retrofit2.http.Part(value = "type")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody type, @retrofit2.http.Part(value = "name")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody name, @retrofit2.http.Part(value = "phone")
    @org.jetbrains.annotations.NotNull()
    okhttp3.RequestBody mobile, @retrofit2.http.Part(value = "email")
    @org.jetbrains.annotations.NotNull()
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
    @org.jetbrains.annotations.NotNull()
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
    okhttp3.RequestBody email);
    
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
    okhttp3.RequestBody description);
    
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
}