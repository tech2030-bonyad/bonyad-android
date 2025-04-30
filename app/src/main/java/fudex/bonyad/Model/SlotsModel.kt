package fudex.bonyad.Model

import android.graphics.Color

data class SlotsModel (
    val status: Boolean? = null,
    val message: String? = null,
    val sub_total: String? = null,
    val vat_rate: String? = null,
    val vat_amount: String? = null,
    val total_price: String? = null,
    val data: ArrayList<SlotsDatum>? = ArrayList()
)

data class SlotsDatum (
    val slots: ArrayList<Slot>? = ArrayList(),
    val service_id: Int? = null,
    var isselect:Boolean? = false,
    val price: String? = null,
    var duration: String? = null,
    val provider_type: String? = null,
    val service_name: String? = null
)

data class Slot (
    val from: String? = null,
    val to: String? = null,
    var isselect:Boolean?  = false,
    var service_id: Int? = null,
    var duration: String? = null,
    var color: Int? = null,
    val active: Boolean? = null
)
