package onnetysolutions.sadded.Model

import fudex.bonyad.Model.UserData
import fudex.bonyad.Model.UserModel


/**
 * Created by hp on 6/3/2018.
 */

class LoginModel{
    val token_type: String? = null
    val expires_in: Int? = null
    var token: String? = null
    var refresh_token: String? = null
    val message: String? = null
    val user= UserModel()
}

