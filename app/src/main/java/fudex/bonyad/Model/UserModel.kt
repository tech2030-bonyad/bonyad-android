package fudex.bonyad.Model

import com.blongho.country_data.Country
import fudex.bonyad.Helper.ErrorResponse

/**
 * Created by hp on 6/3/2018.
 */

class UserModel (
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val mobile: String? = null,
    var photo: String? = null,
    var avatar: String? = null,
    val lat: String? = null,
    val lng: String? = null,
    val location: String? = null,
    val is_social:Int? = null,
    val is_verified: Int? = null,
    val timer: Int? = null,
)
data class UserData (
    var user: UserModel? = null,
    val token: String? = null
)