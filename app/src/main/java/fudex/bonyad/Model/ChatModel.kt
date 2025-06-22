package fudex.bonyad.Model


data class ChatModel(
    val data: ArrayList<MessageItem>? = ArrayList()
)

data class MessageItem(
    val id: Int? = null,
    val conversation_id: Int? = null,
    val message: String? = null,
    val sender: UserInfo? = null,
    val receiver: UserInfo? = null,
    val is_read: Boolean? = null,
    var is_me: Boolean? = false,
    val attachment: String? = null,
    val read_at: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null,
    val time_ago: String? = null
)

data class UserInfo(
    val id: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val type: String? = null,
    val avatar: String? = null
)
