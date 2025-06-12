package fudex.bonyad.Model

data class TechnicalHomeModel (
    val data: TechnicalHomeMData? = null
)

data class TechnicalHomeMData (
    val total_reservations: String? = null,
    val new_reservations: String? = null,
    val completed_reservations: String? = null,
    val upcoming_reservations: ArrayList<OrdersDatum>? = ArrayList(),
    val previous_reservations: ArrayList<OrdersDatum>? = ArrayList()
)