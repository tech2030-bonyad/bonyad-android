package fudex.bonyad.Data

data class Userdata (
    val type: String? = null,
    val phone: String? = null,
    val password: String? = null,
    val fcm_token: String? = null,
    val remember_me: Int? = null,
    val email: String? = null,
    val password_confirmation: String? = null,
    val otp: String? = null,
    val name: String? = null,
    )

data class Contactdata (
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val subject: String? = null,
    val message: String? = null
)