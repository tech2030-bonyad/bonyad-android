package fudex.bonyad.Model

import fudex.bonyad.Model.UserModel


/**
 * Created by hp on 6/3/2018.
 */

class ProfileModel{
    val token_type: String? = null
    val expires_in: Int? = null
    var access_token: String? = null
    var refresh_token: String? = null
    val message: String? = null
    val data= UserModel()
}

