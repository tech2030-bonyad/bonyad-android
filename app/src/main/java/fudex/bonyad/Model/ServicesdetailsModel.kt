package fudex.bonyad.Model

data class ServicesdetailsModel (
    val status: Boolean? = null,
    val message: String? = null,
    val data: ServicesdetailsModelData? = null
)

data class ServicesdetailsModelData (
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val images: ArrayList<Certificate>? = ArrayList()
)

data class AddserviceModel(
    val service_ids: ArrayList<Int>? = ArrayList()
)