package fudex.bonyad.Model

data class OrdersModel (
    val status: Boolean? = null,
    val message: Any? = null,
    val data: ArrayList<OrderDatum>? = ArrayList()
)

data class OrderDatum (
    val id: Int? = null,
    val status: String? = null,
    var servicename: String? = null,
    val service_types: ArrayList<String>? = ArrayList(),
    val is_gift: Int? = null ,
    val service : ArrayList<Service>? = ArrayList(),
    val price: String? = null,
    val date: String? = null,
    val time: String? = null
)
