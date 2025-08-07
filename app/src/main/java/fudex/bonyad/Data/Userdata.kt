package fudex.bonyad.Data

data class Userdata (
    val type: String? = null,
    val phone: String? = null,
    val password: String? = null,
    val fcm_token: String? = null,
    val remember_me: Int? = null,
    val firebase_token_type:String? = null,
    val email: String? = null,
    val password_confirmation: String? = null,
    val otp: String? = null,
    val name: String? = null,
    val notes: String? = null,
    )

data class Contactdata (
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val subject: String? = null,
    val message: String? = null
)

data class Editpass (
    val password: String? = null,
    val new_password: String? = null,
    val new_password_confirmation: String? = null,
)

data class Craetereserve (
    val technician_id: Int? = null,
    val day: Int? = null,
    val date_of_reservation: String? = null,
    val date_id: Int? = null,
    val lat: String? = null,
    val long: String? = null
)


data class Ratingdata (
    val reviewable_id: String? = null,
    val reviewable_type: String? = null,
    val rating: String? = null,
    val comment: String? = null,
)
data class Chatdata (
    val receiver_id: Int? = null,
    val message: String? = null,
)

data class Cartdata (
    val product_id: Int? = null,
    val quantity: Int? = null,
)

data class Orderdata (
    val payment_method: String? = null,
    val lat: String? = null,
    val long: String? = null,
    val shipping_address: String? = null,
    val wallet_amount: String? = null,
    )

data class Changestatus (
    var status: Int? = null,
)

data class Visadata (
    var account_name: String? = null,
    var bank_name: String? = null,
    var iban: String? = null,
    var account_number: String? = null
    )

data class Chargestatus (
    var amount: String? = null,
)