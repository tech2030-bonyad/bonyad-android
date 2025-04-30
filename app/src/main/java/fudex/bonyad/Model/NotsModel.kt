package fudex.bonyad.Model

data class NotsModel (
    val data: List<NotsDatum>? = null,
    val pagination: Pagination? = null
)

data class NotsDatum (
    val date: String? = null,
    val notifications: ArrayList<Notification>? = ArrayList()
)

data class Notification (
    val id: Int? = null,
    val title: String? = null,
    val body: String? = null,
    val type: String? = null,
    val item_id: Int? = null,
    val sent_at: String? = null
)

data class Pagination (
    val total: String? = null
)
