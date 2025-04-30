package fudex.bonyad.Model

data class AddressesModel (
    val data: ArrayList<AddressesDatum>? = ArrayList(),
    val status: Boolean? = null,
    val message: String? = null
)

data class AddressesDatum (
    val id: Int? = null,
    val lat: String? = null,
    var addressTxt: String? = null,
    val lng: String? = null,
    val is_default: Int? = null,
    val state_id: CityModel? = null,
    val city_id: CityModel? = null,
    val district: String? = null
)

data class CityModel (
    val id: Int? = null,
    val name: String? = null,
    val state_id: Int? = null
)

