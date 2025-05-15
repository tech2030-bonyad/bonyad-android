package fudex.bonyad.Model

import android.net.Uri
import com.blongho.country_data.Country
import fudex.bonyad.Helper.ErrorResponse
import kotlin.collections.ArrayList

/**
 * Created by hp on 6/3/2018.
 */

class UserModel (
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null,
    var photo: String? = null,
    var avatar: String? = null,
    val address: String? = null,
    val experience_years: String? = null,
    val zone: String? = null,
    val is_from_social:Boolean? = null,
    val zone_id: Int? = null,
    val certificates: ArrayList<Certificate>? = ArrayList(),
    val timer: Int? = null,
)
data class UserData (
    var user: UserModel? = null,
    val token: String? = null
)
data class Certificate (
    var id: Int? = null,
    val url: String? = null,
    val uri: Uri? = null,
    val path: String? = null
)