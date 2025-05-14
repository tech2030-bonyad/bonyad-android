package fudex.bonyad.Model

data class ContactusModel (
    val data: ArrayList<ContactusDatum>? = ArrayList()
)

data class ContactusDatum (
    val key: String? = null,
    val value: String? = null
)