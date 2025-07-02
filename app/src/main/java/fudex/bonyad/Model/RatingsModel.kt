package fudex.bonyad.Model

data class RatingsModel (
    val data: ArrayList<RatingDatum>? = ArrayList(),
    val total_count: Int? = null,
    val average_rating: String? = null,
    val star_ratings: StarRatings? = null
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

data class StarRatings (
    val star5: The1__Star? = null,
    val star4: The1__Star? = null,
    val star3: The1__Star? = null,
    val star2: The1__Star? = null,
    val star1: The1__Star? = null
)

data class The1__Star (
    val count: String? = null,
    val ratio: Int? = null
)