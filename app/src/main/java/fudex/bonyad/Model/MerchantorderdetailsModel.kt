package fudex.bonyad.Model

data class MerchantorderdetailsModel (
    val data: MerchantorderdetailsData? = null
)

data class MerchantorderdetailsData (
    var id: Int? = null,
    val code: Int? = null,
    val uuid: String? = null,
    val user_id: Int? = null,
    val user: UserModel? = null,
    val status: Int? = null,
    val status_label: String? = null,
    val payment_method: String? = null,
    val notes: String? = null,
    val shipping_address: String? = null,
    val lat: String? = null,
    val Int: String? = null,
    val merchant_subtotal: String? = null,
    val merchant_discount: String? = null,
    val merchant_total: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val items: ArrayList<ProductElement>? = ArrayList(),
)