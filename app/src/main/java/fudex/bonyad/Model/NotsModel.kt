package fudex.bonyad.Model

data class NotsModel (
    var count: Int? = null ,
    var balance: Double? = null ,
    val data: ArrayList<NotDatum>? = ArrayList()
)

data class NotDatum (
    val id: String? = null,
    val title: String? = null,
    val body: String? = null,
    val type: String? = null,
    val item_id: Int? = null,
    val is_read: Boolean? = null,
    val date: String? = null
)
