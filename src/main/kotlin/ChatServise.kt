object ChatService {
    private var users = mutableListOf<User>()
    private var nextUserId = 0
    private var nextChatId = 0
    private var nextMessageId = 0

    fun getUnreadChatsCount(userId: Int): Int =
        users.last { it.id == userId }
            .chats.filter { it.messages
                .any { !it.isRead && it.recipientId == userId }
            }
            .size


    fun getChats(userId: Int): MutableList<Chat> =
        users
            .last { it.id == userId }
            .chats

    fun getMessages(
        userId: Int,
        chatId: Int,
        messageId: Int,
        messageCount: Int
    ): List<Message> =
        users
            .last { it.id == userId }.chats
            .last { it.id == chatId }.messages
            .filter { it.id >= messageId }
            .take(messageCount)

    fun createMessage(
        senderId: Int,
        recipientId: Int,
        text: String
    ): Int {
        val sender = users.last { it.id == senderId }
        val recipient = users.last { it.id == recipientId }
        val chats = sender
            .chats
            .filter { it ->
                it.messages
                    .any { it.senderId == recipientId || it.recipientId == recipientId }
            }
        val chat: Chat
        val chatCreated: Boolean
        if (chats.size == 1) {
            chat = chats[0]
            chatCreated = false
        } else {
            chat = Chat(nextChatId)
            sender.chats.add(chat)
            recipient.chats.add((chat))
            nextChatId++
            chatCreated = true
        }
        val message = Message(
            nextMessageId,
            senderId,
            recipientId,
            text
        )
        nextMessageId++
        chat.messages.add(message)
        return if (chatCreated) 1 else 0
    }

    fun deleteMessage(
        userId: Int,
        chatId: Int,
        messageId: Int
    ): Int {
        users
            .last { it.id == userId }.chats
            .last { it.id == chatId }.messages
            .removeIf { it.id == messageId }
        users
            .last { it.id == userId }.chats
            .removeIf { it.messages.size == 0 }
        return 1
    }

    fun deleteChat(
        userId: Int,
        chatId: Int
    ):Int {
        users
            .last { it.id == userId }.chats
            .removeIf { it.id == chatId }
        return 1
    }

    fun createUser() {
        val user = User(nextUserId)
        users.add(user)
        nextUserId++
    }

    fun clearUsers() {
        users = mutableListOf()
        nextUserId = 0
        nextChatId = 0
        nextMessageId = 0
    }
}