package fudex.bonyad.Model

data class BookserviceModel (
    val status: Boolean? = null,
    val message: String? = null,
    val data: Data? = null
)

data class Data (
    val payment_url: String? = null,
    val order_id: Int? = null,
    val success_url: String? = null,
    val fail_url: String? = null
)