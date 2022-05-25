data class User(
    val id: Int,
    var chats: MutableList<Chat> = mutableListOf()
)