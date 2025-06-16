package fudex.bonyad.NetWorkConnction

import fudex.bonyad.Data.Contactdata
import fudex.bonyad.Data.Craetereserve
import fudex.bonyad.Data.Editpass
import fudex.bonyad.Data.Userdata
import fudex.bonyad.Helper.AddaressModel
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Model.AddressesModel
import fudex.bonyad.Model.BookserviceModel
import fudex.bonyad.Model.BrachesModel
import fudex.bonyad.Model.BranchsetailsModel
import fudex.bonyad.Model.ComplainModel
import fudex.bonyad.Model.ContactusModel
import fudex.bonyad.Model.LoginData
import fudex.bonyad.Model.NotsModel
import fudex.bonyad.Model.OrderdetailsModel
import fudex.bonyad.Model.OrdersModel
import fudex.bonyad.Model.RatingModel
import fudex.bonyad.Model.ScheduleResponse
import fudex.bonyad.Model.ServicesdetailsModel
import fudex.bonyad.Model.ServicestypeModel
import fudex.bonyad.Model.SliderModel
import fudex.bonyad.Model.SlotsModel
import fudex.bonyad.Model.StatesModel
import fudex.bonyad.Model.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import fudex.bonyad.Model.AboutModel
import fudex.bonyad.Model.AddserviceModel
import fudex.bonyad.Model.Dateadd
import fudex.bonyad.Model.DetailstechnicalModel
import fudex.bonyad.Model.FaqModel
import fudex.bonyad.Model.HomeModel
import fudex.bonyad.Model.MYSubsribeModel
import fudex.bonyad.Model.PlanModel
import fudex.bonyad.Model.ProfileModel
import fudex.bonyad.Model.SubsribeModel
import fudex.bonyad.Model.TechnicalModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Headers
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiInterface {
    @GET("client/serviceTypes")
    fun getservicestype(): Call<ServicestypeModel?>?

    @GET("zones")
    fun getzones(): Call<StatesModel?>?

    @GET("user-home")
    fun gethome(@Query("name") name: String?): Call<HomeModel?>?

    @GET("technicians")
    fun gettechnicals( @Query("page") page: Int? , @Query("paginate") paginate: Int?, @Query("zones[]") zones: List<Int>?, @Query("services[]") services: List<Int>?, @Query("name") name: String?): Call<TechnicalModel?>?

    @GET("client/cities")
    fun getcities(@Query("state_id") state_id: Int?): Call<StatesModel?>?

    @POST("client/userAddresses")
    fun addaddress(@Body requestBody: RequestBody): Call<AddaressModel?>?

    @POST("client/userAddresses/{addressId}/update")
    fun updateaddress(
        @Path("addressId") addressId: Int,
        @Body requestBody: RequestBody
    ): Call<ErrorResponse?>?

    @POST("client/userAddresses/{addressId}/delete")
    fun deleteaddress(@Path("addressId") addressId: Int): Call<ErrorResponse?>?

    @GET("client/userAddresses")
    fun getaddress(@Query("page") page: Int?): Call<AddressesModel?>?

    @GET("client/branches/{branchId}/rate")
    fun getrating(@Path("branchId") branchId: Int, @Query("page") page: Int?): Call<RatingModel?>?

    @GET("client/branches")
    fun getbranches(
        @Query("page") page: Int?,
        @Query("lat") lat: String?,
        @Query("lng") lng: String?,
        @Query("max_distance") max_distance: String?,
        @Query("service_type") service_type: String?,
        @Query("location_type") location_type: String?,
        @Query("employee_gender") employee_gender: String?,
        @Query("rate") rate: String?,
        @Query("name") name: String?
    ): Call<BrachesModel?>?

    @GET("client/my-orders")
    fun getorders(@Query("page") page: Int?, @Query("status") status: Int?): Call<OrdersModel?>?

    @GET("client/orders/{orderId}")
    fun getorderdetails(@Path("orderId") orderId: Int,  @Query("lat") lat: String?,
    @Query("lng") lng: String?): Call<OrderdetailsModel?>?

    @GET("client/sliders")
    fun getsliders(): Call<SliderModel?>?

    @GET("technicians/{technicalId}")
    fun gettechnical(
        @Path("technicalId") technicalId: Int,
    ): Call<DetailstechnicalModel?>?

    @GET("user-details")
    fun getprofiledata(): Call<ProfileModel?>?

    @POST("login")
    fun login(@Body data: Userdata): Call<LoginData?>?

    @POST("client/checkSocialUser")
    fun sociallogin(@Body requestBody: RequestBody): Call<LoginData?>?

    @POST("checkRegisterValidation")
    fun checkregister(@Body requestBody: RequestBody): Call<ErrorResponse?>?

    @POST("register")
    fun register(@Body requestBody: Userdata): Call<LoginData?>?

    @Multipart
    @POST("register")
    fun technicalregister(
        @Part images: List<MultipartBody.Part>,
        @Part("type") type: RequestBody,
        @Part("name") name: RequestBody,
        @Part("phone") mobile: RequestBody,
        @Part("email") email: RequestBody,
        @Part("address") address: RequestBody,
        @Part("experience_years") experience_years: RequestBody,
        @Part parts: List<MultipartBody.Part>,
        @Part("password") password: RequestBody,
        @Part("password_confirmation") password_confirmation: RequestBody,
        @Part("fcm_token") fcm_token: RequestBody,
        @Part("firebase_token_type") device_type: RequestBody,
        @Part("remember_me") device_id: RequestBody,
        @Part("description") description: RequestBody
        ): Call<LoginData?>?

    @Multipart
    @POST("client/register")
    fun registersocail(
        @Part("name") name: RequestBody,
        @Part("mobile") mobile: RequestBody,
        @Part("email") email: RequestBody,
        @Part("social_id") provider_token: RequestBody,
        @Part("social_type") provider_type: RequestBody,
        @Part("fcm_token") fcm_token: RequestBody,
        @Part("device_type") device_type: RequestBody,
        @Part("device_id") device_id: RequestBody
    ): Call<LoginData?>?

    @POST("verify-account")
    fun verifiyuser(@Body requestBody: Userdata): Call<LoginData?>?

    @POST("password/code")
    fun sendotp(@Body requestBody: Userdata): Call<ProfileModel?>?

    @POST("resend-code")
    fun resendsendotp(@Body requestBody: Userdata): Call<ProfileModel?>?

    @POST("client/check-otp")
    fun checkotp(@Body requestBody: RequestBody): Call<ErrorResponse?>?

    @POST("password/reset")
    fun restpass(@Body requestBody: Userdata): Call<ErrorResponse?>?

    @POST("edit-password")
    fun editpass(@Body requestBody: Editpass): Call<ErrorResponse?>?

    @POST("client/updateEmail")
    fun editemail(@Body requestBody: RequestBody): Call<ProfileModel?>?

    @POST("edit-phone")
    fun editphone(@Body requestBody: Userdata): Call<ProfileModel?>?

    @Headers("Accept: application/json")
    @POST("edit-user")
    fun editprofile(@Body requestBody: RequestBody): Call<ProfileModel?>?

    @POST("verify-new-phone")
    fun editphone1(@Body requestBody: Userdata): Call<ProfileModel?>?

    @Multipart
    @POST("edit-user")
    fun editprofilewithimage(
        @Part imageFile: MultipartBody.Part,
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody?
    ): Call<ProfileModel?>?

    @Multipart
    @POST("edit-user")
    fun edittechnicalprofilewithimage(
        @Part imageFile: MultipartBody.Part?,
        @Part images: List<MultipartBody.Part>?,
        @Part("name") name: RequestBody,
        @Part("email") email: RequestBody,
        @Part("address") address: RequestBody,
        @Part("experience_years") experience_years: RequestBody,
        @Part parts: List<MultipartBody.Part>,
        @Part("description") description: RequestBody
    ): Call<ProfileModel?>?

    @Multipart
    @POST("services/create")
    fun addservice(
        @Part images: List<MultipartBody.Part>?,
        @Part("name") name: RequestBody,
        @Part("description") email: RequestBody,
    ): Call<ErrorResponse?>?
//    @GET("setting")
//    fun getsitting(): Call<SettingModel?>?

    @GET("pages/about-us")
    fun aboutus(): Call<AboutModel?>?

    @GET("settings")
    fun getsetting(): Call<ContactusModel?>?

    @GET("client/pages/cancellation-policy")
    fun cancelpolicy(): Call<AboutModel?>?

    @GET("client/faqs")
    fun faqs(): Call<FaqModel?>?

    @GET("pages/terms-and-conditions")
    fun terms(): Call<AboutModel?>?

    @GET("pages/privacy-policy")
    fun privacy(): Call<AboutModel?>?

    @POST("client/changeLanguage")
    fun changelang(@Body requestBody: RequestBody): Call<ErrorResponse?>?

    @POST("contact-us")
    fun contactus(@Body requestBody: Contactdata): Call<ErrorResponse?>?

    @POST("client/complaintOrSuggest")
    fun sendcomplain(@Body requestBody: RequestBody): Call<ErrorResponse?>?

    @POST("logout")
    fun logout(@Body requestBody: RequestBody): Call<ErrorResponse?>?

    @POST("client/deleteAccount")
    fun deleteaccount(): Call<ErrorResponse?>?

    @GET("technician/availability-dates/{technicalId}") // Append this to the base URL
    fun getSlots(
        @Path("technicalId") technicalId: Int,
        @Query("date") date: String,
    ): Call<ScheduleResponse?>?

    @FormUrlEncoded
    @POST("client/order")
    fun createOrder(
        @Field("location_type") location_type: String?,
        @Field("address_id") address_id: Int?,
        @Field("is_gift") is_gift: Int?,
        @Field("mobile") mobile: String?,
        @Field("address") address: String?,
        @Field("name") name: String?,
        @Field("payment_method") payment_method: String?,
        @Field("gender") gender: String?,
        @Field("price") price: String?,
        @Field("branch_id") branchId: Int?,
        @Field("date") date: String?,
        @FieldMap(encoded = true) services: Map<String, String>?
    ): Call<BookserviceModel?>?

    @POST("client/order/{orderId}/rate")
    fun rateorder(
        @Path("orderId") orderId: Int,
        @Body requestBody: RequestBody
    ): Call<ErrorResponse?>?

    @POST("client/orders/{orderId}/cancel")
    fun cancelorder(@Path("orderId") orderId: Int): Call<ErrorResponse?>?

    @GET("client/my-complaint-suggesstion")
    fun getcomplain(@Query("page") page: Int? , @Query("status") status: Int?): Call<ComplainModel?>?

    @GET("client/notifications")
    fun getnots(@Query("page") page: Int?): Call<NotsModel?>?

    @POST("client/notifications/delete")
    fun deletenots(): Call<ErrorResponse?>?

    @GET("my-services")
    fun getmyservices(): Call<StatesModel?>?

    @GET("services")
    fun getservices(@Query("name") name: String? , @Query("page") page: Int? , @Query("paginate") paginate: Int?): Call<StatesModel?>?

    @GET("services/{serviceId}")
    fun getservicesdetails(@Path("serviceId") serviceId: Int): Call<ServicesdetailsModel?>?

    @GET("availability")
    fun getavailbilty(): Call<ScheduleResponse?>?

    @POST("availability/bulk")
    fun adddates(@Body data: Dateadd): Call<ErrorResponse?>?

    @GET("plans")
    fun getplans(): Call<PlanModel?>?

    @GET("plans/{subsctibeId}/subscribe")
    fun subsribe(@Path("subsctibeId") subsctibeId: Int): Call<SubsribeModel?>?

    @GET("plans/cancel-subscription")
    fun cancelsubseibe(): Call<ErrorResponse?>?

    @GET("plans/my-plan")
    fun getmyplan(): Call<MYSubsribeModel?>?

    @POST("services/add")
    fun addservices(@Body data: AddserviceModel): Call<ErrorResponse?>?

    @HTTP(method = "DELETE", path = "services/delete", hasBody = true)
    fun deleteservices(@Body data: AddserviceModel): Call<ErrorResponse?>?

    @POST("reservations")
    fun createreserve(@Body data: Craetereserve): Call<ErrorResponse?>?
}
