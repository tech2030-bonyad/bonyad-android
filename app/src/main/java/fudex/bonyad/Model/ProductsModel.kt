package fudex.bonyad.Model

data class ProductsModel (
    val data: ArrayList<ProductsDatum>? = ArrayList()
)

data class ProductsDatum (
    val id: Int? = null,
    val name: String? = null,
    val price: String? = null,
    val quantity: String? = null,
    val unit_id: Int? = null,
    val unit: String? = null,
    val discount: String? = null,
    val category_id: Int? = null,
    val description: String? = null,
    val category: String? = null,
    val image: String? = null,
    val average_rating: String? = null,
    val review_count: String? = null,
    var images: ArrayList<Certificate> = ArrayList()
)

data class DetailsProductsModel (
    val data: ProductsDatum? = null
)