package fudex.bonyad.NetWorkConnction

import fudex.bonyad.Helper.AddaressModel
import fudex.bonyad.Helper.ErrorResponse
import fudex.bonyad.Model.AddressesModel
import fudex.bonyad.Model.BookserviceModel
import fudex.bonyad.Model.BrachesModel
import fudex.bonyad.Model.BranchsetailsModel
import fudex.bonyad.Model.ComplainModel
import fudex.bonyad.Model.LoginData
import fudex.bonyad.Model.NotsModel
import fudex.bonyad.Model.OrderdetailsModel
import fudex.bonyad.Model.OrdersModel
import fudex.bonyad.Model.RatingModel
import fudex.bonyad.Model.ServicestypeModel
import fudex.bonyad.Model.SliderModel
import fudex.bonyad.Model.SlotsModel
import fudex.bonyad.Model.StatesModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import onnetysolutions.sadded.Model.AboutModel
import onnetysolutions.sadded.Model.FaqModel
import onnetysolutions.sadded.Model.ProfileModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiInterface {
    @GET("client/serviceTypes")
    fun getservicestype(): Call<ServicestypeModel?>?

    @GET("client/states")
    fun getstates(): Call<StatesModel?>?

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

    @GET("client/branches/{branchId}")
    fun getbranchdetails(
        @Path("branchId") tripId: Int,
        @Query("lat") lat: String?,
        @Query("lng") lng: String?,
        @Query("service_type") service_type: String?,
        @Query("location_type") location_type: String?
    ): Call<BranchsetailsModel?>?

    @GET("client/profile")
    fun getprofiledata(): Call<ProfileModel?>?

    @POST("client/login")
    fun login(@Body requestBody: RequestBody): Call<LoginData?>?

    @POST("client/checkSocialUser")
    fun sociallogin(@Body requestBody: RequestBody): Call<LoginData?>?

    @POST("checkRegisterValidation")
    fun checkregister(@Body requestBody: RequestBody): Call<ErrorResponse?>?

    @POST("client/register")
    fun register(@Body requestBody: RequestBody): Call<LoginData?>?

    @Multipart
    @POST("client/register")
    fun registerimage(
        @Part("name") name: RequestBody,
        @Part("mobile") mobile: RequestBody,
        @Part("password") password: RequestBody,
        @Part("password_confirmation") password_confirmation: RequestBody,
        @Part("fcm_token") fcm_token: RequestBody,
        @Part("device_type") device_type: RequestBody,
        @Part("device_id") device_id: RequestBody
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

    @POST("client/confirm-otp-register")
    fun verifiyuser(@Body requestBody: RequestBody): Call<LoginData?>?

    @POST("client/send-otp")
    fun sendotp(@Body requestBody: RequestBody): Call<ProfileModel?>?

    @POST("client/check-otp")
    fun checkotp(@Body requestBody: RequestBody): Call<ErrorResponse?>?

    @POST("client/forgotPassword")
    fun restpass(@Body requestBody: RequestBody): Call<ErrorResponse?>?

    @POST("client/updatePassword")
    fun editpass(@Body requestBody: RequestBody): Call<ErrorResponse?>?

    @POST("client/updateEmail")
    fun editemail(@Body requestBody: RequestBody): Call<ProfileModel?>?

    @POST("client/send-otp")
    fun editphone(@Body requestBody: RequestBody): Call<ProfileModel?>?

    @POST("client/updateProfile")
    fun editprofile(@Body requestBody: RequestBody): Call<ProfileModel?>?

    @POST("client/updateMobile")
    fun editphone1(@Body requestBody: RequestBody): Call<ProfileModel?>?

    @Multipart
    @POST("client/updateProfile")
    fun editprofilewithimage(
        @Part imageFile: MultipartBody.Part,
        @Part("name") name: RequestBody
    ): Call<ProfileModel?>?


//    @GET("setting")
//    fun getsitting(): Call<SettingModel?>?

    @GET("client/pages/about-us")
    fun aboutus(): Call<AboutModel?>?

    @GET("client/pages/cancellation-policy")
    fun cancelpolicy(): Call<AboutModel?>?

    @GET("client/faqs")
    fun faqs(): Call<FaqModel?>?

    @GET("client/pages/terms-and-conditions")
    fun terms(): Call<AboutModel?>?

    @GET("client/pages/privacy-policy")
    fun privacy(): Call<AboutModel?>?

    @POST("client/changeLanguage")
    fun changelang(@Body requestBody: RequestBody): Call<ErrorResponse?>?

    @POST("client/contact-us")
    fun contactus(@Body requestBody: RequestBody): Call<ErrorResponse?>?

    @POST("client/complaintOrSuggest")
    fun sendcomplain(@Body requestBody: RequestBody): Call<ErrorResponse?>?

    @POST("client/logout")
    fun logout(@Body requestBody: RequestBody): Call<ErrorResponse?>?

    @POST("client/deleteAccount")
    fun deleteaccount(): Call<ErrorResponse?>?

    @GET("client/slots") // Append this to the base URL
    fun getSlots(
        @Query("date") date: String,
        @Query("branch_id") branchId: Int,
        @Query("service_ids[]") serviceIds: List<Int>
    ): Call<SlotsModel?>?

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
}
