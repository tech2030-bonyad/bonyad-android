package fudex.bonyad.Model

data class ComplainModel (
    val status: Boolean? = null,
    val message: String? = null,
    val data: ArrayList<ComplainDatum>? = ArrayList()
)

data class ComplainDatum (
    val id: Int? = null,
    var idtxt: String? = null,
    val type: String? = null,
    val message: String? = null,
    val created_at: String? = null,
    var statusTxt: String? = null,
    val reply: String? = null,
    var colorback: Int? = null,
    var color: Int? = null,
    val status: Int? = null
)
