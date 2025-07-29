package fudex.bonyad.Model

data class MerchantHomeModel (
    val data: MerchantHomeData? = null
)

data class MerchantHomeData (
    val total_orders: Reservations? = null,
    val new_orders: Reservations? = null,
    val completed_orders: Reservations? = null,
    val profit: Reservations? = null,
    val recent_orders: ArrayList<RecentOrder>? = ArrayList(),
    val top_products: ArrayList<ProductsDatum>? = ArrayList()
)

data class RecentOrder (
    var id: Int? = null,
    val code: Int? = null,
    val uuid: String? = null,
    val user_id: Int? = null,
    val username: String? = null,
    val subtotal: String? = null,
    val discount: String? = null,
    val total: String? = null,
    val status: Int? = null,
    val items_count: Int? = null,
    val trade_name: String? = null,
    val business_logo: String? = null,
    val reviews_avg_rating: String? = null,
    val reviews_count: String? = null,
    val status_label: String? = null,
    val payment_method: String? = null,
    val notes: String? = null,
    val shipping_address: String? = null,
    val lat: String? = null,
    val Int: String? = null
)
