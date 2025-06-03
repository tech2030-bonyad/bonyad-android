package fudex.bonyad.Model

data class OrdersModel (
    val data: ArrayList<OrdersDatum>? = ArrayList()
)

data class OrdersDatum (
    val id: Int? = null,
    val username: String? = null,
    val user_avatar: String? = null,
    val technician_name: String? = null,
    val technician_avatar: String? = null,
    var address: String? = null,
    val day: Day? = null,
    val start_time: String? = null,
    val end_time: String? = null,
    val status: Day? = null,
    val notes: String? = null,
    val lat: String? = null,
    val long: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null
)

data class Day (
    val value: Int? = null,
    val label: String? = null
)