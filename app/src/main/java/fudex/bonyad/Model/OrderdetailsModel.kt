package fudex.bonyad.Model

data class OrderdetailsModel (
    val status: Boolean? = null,
    val message: String? = null,
    val data: OrderdetailsData? = null
)

data class OrderdetailsData (
    val id: Int? = null,
    val status_key: Int? = null,
    val status: String? = null,
    val service: ArrayList<Service>? = ArrayList(),
    val service_types: ArrayList<String>? = ArrayList(),
    val price: String? = null,
    val is_gift: Int? = null,
    val location_type: String? = null,
    val date: String? = null,
    val time: String? = null,
    val branch: Branch? = null,
    val rating: Rating? = null,
    val gift: Gift? = null
)

data class Branch (
    val name: String? = null,
    val image: String? = null,
    val rate: String? = null,
    val distance: String? = null
)

data class Gift (
    val name: String? = null,
    val mobile: String? = null,
    val gender: String? = null,
    val address: String? = null
)
data class Rating (
    val rating: String? = null,
    val comment: String? = null,
    val created_at: String? = null,
    val user: User? = null
)

data class User (
    val id: Int? = null,
    val name: String? = null,
    val avatar: String? = null,
    val avatar_url: String? = null
)