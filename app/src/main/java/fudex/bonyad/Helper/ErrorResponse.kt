package fudex.bonyad.Helper
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import fudex.bonyad.Model.AddressesDatum

/**
 * Created by BEST BUY on 5/7/2018.
 */
class ErrorResponse {
    val status: Boolean? = null
    val message: String? = null
    val payment_url: String? = null
    val payment_token: String? = null
    val subscription_id: Int? = null
    val iframe_redirection_url:String? = null

    val errors: Map<String, List<String>>? = null

    fun getMsg(): String {
        val errorsString = errors?.map { (key, values) ->
             values.joinToString("\n") { "$it" }
        }!!.joinToString("\n\n")
        return errorsString
    }

    data class Errors (
        val token: List<String>? = ArrayList(),
        val email: List<String>? = ArrayList(),
        var password : List<String>? = ArrayList(),
        var phone: List<String>? = ArrayList(),
        var mobile: List<String>? = ArrayList(),
        var old_password : List<String>? = ArrayList(),
        var social_type : List<String>? = ArrayList(),
        var social_id : List<String>? = ArrayList(),
        var account : List<String>? = ArrayList(),
        var credentials : List<String>? = ArrayList(),
        var otp : List<String>? = ArrayList()
    )

}

class AddaressModel {
    val message: String? = null
    val data: AddressesDatum? = null
}