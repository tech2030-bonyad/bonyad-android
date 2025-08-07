package fudex.bonyad.Model

data class TransactionsModel (
    val data: ArrayList<TransactionsDatum>? = ArrayList()
)

data class TransactionsDatum (
    val id: Int? = null,
    val amount: String? = null,
    val order_id: Int? = null,
    val status: Int? = null,
    val status_label: String? = null,
    val type: Int? = null,
    val type_label: String? = null,
    val type_of_transaction: Int? = null,
    val type_of_transaction_label: String? = null,
    val formatted_date: String? = null,
    val formatted_date_human: String? = null
)
