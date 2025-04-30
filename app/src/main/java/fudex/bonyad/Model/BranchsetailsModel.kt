package fudex.bonyad.Model

import java.util.ArrayList

data class BranchsetailsModel (
    val status: Boolean? = null,
    val message: String? = null,
    val data: BranchsetailsData? = null
)

data class BranchsetailsData (
    val id: Int? = null,
    val name: String? = null,
    val description: String? = null,
    val home_services_available: Int? = null,
    val address: String? = null,
    val image: String? = null,
    val lat: String? = null,
    val lng: String? = null,
    val rate: String? = null,
    val distance: String? = null,
    val images: ArrayList<Sliders>? = ArrayList(),
    val service_types: ArrayList<ServicetypeDatum>? = ArrayList(),
    val services: ArrayList<Service>? = ArrayList()
)

data class Service (
    val id: Int? = null,
    val name: String? = null,
    var description:String? = null,
    val image: String? = null,
    val service_type: String? = null,
    val price: String? = null,
    val duration: String? = null,
    val rate: String? = null,
    val provider_type: String? = null,
    var ischeck: Boolean? = false,
    var isgift:Int? = 0 ,
    var loctype:Int? = 0 ,
    var location_type: String? = null
)
data class Sliders (
    val id: Int? = null,
    val image: String? = null,
    val description: String? = null,
    val link: String? = null,
    val file: String? = null
)
