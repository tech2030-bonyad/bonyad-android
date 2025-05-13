package atiaf.redstone.NetWorkConnction

import android.content.Context
import fudex.bonyad.Apimodel.APIModel
import fudex.bonyad.BuildConfig
import fudex.bonyad.R
import fudex.bonyad.SharedPreferences.LoginSession
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetrofitClient {
    private var retrofit: Retrofit? = null
    private var retrofit1: Retrofit? = null

    fun getClient(context: Context): Retrofit? {
        var httpClient = OkHttpClient().newBuilder()
        httpClient.networkInterceptors().add(Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.header("version", APIModel.version)
            requestBuilder.header("Lang", context.getString(R.string.lang))
            requestBuilder.header("Accept", "application/json")
            requestBuilder.header("Content-Type", "application/json")
            requestBuilder.header("Accept-Language", context.getString(R.string.lang))
            requestBuilder.header("x-device-type", "android")
            requestBuilder.header("x-app-version", BuildConfig.VERSION_CODE.toString())
            try {
                requestBuilder.header(
                    "Authorization",
                    "Bearer " + LoginSession.getUserData(context).token
                )
            } catch (e: Exception) {

            }
            chain.proceed(requestBuilder.build())
        })
        httpClient.apply {
            connectTimeout(1, TimeUnit.MINUTES)
            writeTimeout(2, TimeUnit.MINUTES) // write timeout
            readTimeout(2, TimeUnit.MINUTES) // read timeout
        }
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(APIModel.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
        return retrofit
    }
    fun getClientintegration(context: Context): Retrofit? {
        var httpClient = OkHttpClient().newBuilder()
        httpClient.networkInterceptors().add(Interceptor { chain ->
            val requestBuilder = chain.request().newBuilder()
            requestBuilder.header("x-rapidapi-host", "api-football-v1.p.rapidapi.com")
            requestBuilder.header("Lang", context.getString(R.string.lang))
            requestBuilder.header("x-rapidapi-key", "a81f0c8fbfmshe382542c405b534p1a4f2fjsnab63334d3305")
            requestBuilder.header("Accept-Language", context.getString(R.string.lang))
            requestBuilder.header("x-device-type", "android")
            requestBuilder.header("x-app-version", BuildConfig.VERSION_CODE.toString())
            try {
                requestBuilder.header(
                    "Authorization",
                    "Bearer " + LoginSession.getUserData(context).token
                )
            } catch (e: Exception) {

            }
            chain.proceed(requestBuilder.build())
        })
        httpClient.apply {
            connectTimeout(1, TimeUnit.MINUTES)
            writeTimeout(2, TimeUnit.MINUTES) // write timeout
            readTimeout(2, TimeUnit.MINUTES) // read timeout
        }
        if (retrofit1 == null) {
            retrofit1 = Retrofit.Builder()
                .baseUrl(APIModel.INTEGRATION_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
        return retrofit1
    }
}