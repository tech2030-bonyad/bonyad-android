package fudex.bonyad.Model

data class HomeModel (
    val data: HomeMData? = null
)

data class HomeMData (
    val sliders: ArrayList<Slider>? = ArrayList(),
    val categories: List<Any?>? = null,
    val technicians: ArrayList<Technician>? = ArrayList(),
    val products: List<Any?>? = null
)

data class Technician (
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val avatar: String? = null,
    val type_label: String? = null
)

data class TechnicalModel (
    val data: ArrayList<Technician>? = ArrayList()
)
data class Slider (
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val image: String? = null
)