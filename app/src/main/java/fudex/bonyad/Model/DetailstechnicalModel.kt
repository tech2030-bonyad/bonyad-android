package fudex.bonyad.Model

data class DetailstechnicalModel (
    val data:  DetailstechnicalData? = null
)

data class  DetailstechnicalData (
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val avatar: String? = null,
    val language: String? = null,
    val lat: String? = null,
    val long: String? = null,
    val address: String? = null,
    val experience_years: Long? = null,
    val zone_id: Long? = null,
    val zone: String? = null,
    val certificates: ArrayList<Certificate>? = ArrayList(),
    val services: ArrayList<StatesDatum>? = ArrayList(),
    val availabilities: ArrayList<Availability>? = ArrayList(),
    val is_subscribed: Boolean? = null
)