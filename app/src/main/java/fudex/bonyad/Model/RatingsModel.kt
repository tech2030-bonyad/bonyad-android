package fudex.bonyad.Model

data class RatingsModel (
    val data: ArrayList<RatingDatum>? = ArrayList()
)

data class RatingDatum (
    val id: Int? = null,
    val user: User? = null,
    val reviewable: User? = null,
    var reviewable_id: Int? = null,
    val rating: Float? = null,
    val comment: String? = null,
    val created_at: String? = null
)

