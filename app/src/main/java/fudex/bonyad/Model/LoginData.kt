package fudex.bonyad.Model

import java.util.ArrayList


/**
 * Created by BEST BUY on 6/4/2018.
 */

data class LoginData(
    val token_type: String? = null,
    val expires_in: Long? = null,
    val access_token: String? = null,
    val refresh_token: String? = null,
    var data: UserData? = null,
    val message: String? = null
)

data class Loginforsecure(
    val phone: String? = null,
    val countrycode: String? = null,
    var password: String? = null,
)
