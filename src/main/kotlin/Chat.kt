data class Chat(
    val id: Int,
    var messages: MutableList<Message> = mutableListOf()
)