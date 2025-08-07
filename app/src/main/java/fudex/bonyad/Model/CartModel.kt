package fudex.bonyad.Model

data class CartModel (
    val data: CartData? = null
)

data class CartData (
    val id: Int? = null,
    val sub_total: String? = null,
    val discount: String? = null,
    val total: String? = null,
    val tax: String? = null,
    val merchant: UserModel? = null,
    val products: ArrayList<ProductElement>? = ArrayList(),
    val created_at: String? = null
)

data class ProductElement (
    val id: Int? = null,
    val product_id: Int? = null,
    val quantity: String? = null,
    val price: String? = null,
    val sub_total: String? = null,
    val trade_name: String? = null,
    val business_logo: String? = null,
    val discount: String? = null,
    val total: String? = null,
    var rate: String? = "0",
    var comment: String? = "",
    val merchant: UserModel? = null,
    val product: ProductsDatum? = null
)
