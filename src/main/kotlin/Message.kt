data class Message(
    val id: Int,
    val senderId: Int,
    val recipientId: Int,
    val text: String,
    val isRead: Boolean = false
)