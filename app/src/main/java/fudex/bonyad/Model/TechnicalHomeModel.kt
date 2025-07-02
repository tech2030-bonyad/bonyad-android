package fudex.bonyad.Model

data class TechnicalHomeModel (
    val data: TechnicalHomeMData? = null
)

data class TechnicalHomeMData (
    val total_reservations: Reservations? = null,
    val new_reservations: Reservations? = null,
    val completed_reservations: Reservations? = null,
    val upcoming_reservations: ArrayList<OrdersDatum>? = ArrayList(),
    val previous_reservations: ArrayList<OrdersDatum>? = ArrayList()
)
data class Reservations (
    val total_count: String? = null,
    val current_month: String? = null,
    val previous_month: String? = null,
    val ratio: String? = null,
    var trend: String? = null
)