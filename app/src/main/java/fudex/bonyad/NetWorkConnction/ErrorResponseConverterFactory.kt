package fudex.bonyad.NetWorkConnction

import com.google.gson.Gson
import fudex.bonyad.Helper.ErrorResponse
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class ErrorResponseConverterFactory : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return if (type == ErrorResponse::class.java) {
            Converter<ResponseBody, ErrorResponse> { responseBody ->
                val responseString = responseBody.string()
                val errorResponse = Gson().fromJson(responseString, ErrorResponse::class.java)
                errorResponse
            }
        } else {
            null
        }
    }
}