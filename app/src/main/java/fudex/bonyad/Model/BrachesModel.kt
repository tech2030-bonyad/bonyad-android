package fudex.bonyad.Model

data class BrachesModel (
    val data: ArrayList<BranchDatum>? = ArrayList(),
    val status: Boolean? = null,
    val message: String? = null
)

data class BranchDatum (
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val address: String? = null,
    val image: String? = null,
    val rate: String? = null,
    val distance: String? = null,
    val service_types: ArrayList<String>? = ArrayList()
)


data class SliderModel (
    val data: ArrayList<Sliders>? = ArrayList(),
    val status: Boolean? = null,
    val message: String? = null
)