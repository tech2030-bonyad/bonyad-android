package fudex.bonyad.Model

import kotlin.collections.ArrayList

data class PlanModel(
    val data: ArrayList<PlanData>? = ArrayList()
)

data class PlanData(
    val id: Int? = null,
    val name: String? = null,
    val duration: Int? = null,      // in minutes
    val price: String? = null,      // keep as String if using it for display or currency formatting
    val image: String? = null,
    val expired_date: String? = null,
    val created_at: String? = null
)
data class SubsribeModel(
    val data: SubsribeData? = null,
)

data class SubsribeData(
    val id: Int? = null,
    val redirectUrl: String? = null,
)

data class MYSubsribeModel(
    val data: PlanData? = null,
)