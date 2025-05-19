package fudex.bonyad.Model

data class StatesModel (
    val status: Boolean? = null,
    val message: String? = null,
    val data: ArrayList<StatesDatum>? = ArrayList()
)

data class StatesDatum (
    val id: Int? = null,
    val name: String? = null,
    val image: String? = null
    )

data class ServicetypeDatum (
    val key: String? = null,
    val value: String? = null
)
data class ServicestypeModel (
    val status: Boolean? = null,
    val message: String? = null,
    val data: ArrayList<ServicetypeDatum>? = ArrayList()
)