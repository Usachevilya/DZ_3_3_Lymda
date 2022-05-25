import org.junit.Test

import org.junit.Assert.*

class ChatServiceTest {

    @Test
    fun getChats() {
        ChatService.clearUsers()
        ChatService.createUser()
        ChatService.createUser()
        ChatService.createMessage(0,1,"")

        val chats = ChatService.getChats(0)

        assertEquals(1, chats.size)
    }

    @Test
    fun getChats_noChats() {
        ChatService.clearUsers()
        ChatService.createUser()
        ChatService.createUser()
        ChatService.createUser()
        ChatService.createMessage(0,1,"")

        val chats = ChatService.getChats(2)

        assertEquals(0, chats.size)
    }

    @Test
    fun getUnreadChatsCount() {
        ChatService.clearUsers()
        ChatService.createUser()
        ChatService.createUser()
        ChatService.createUser()
        ChatService.createMessage(0,1,"")
        ChatService.createMessage(2,1,"")

        val unreadChatsCount = ChatService.getUnreadChatsCount(1)

        assertEquals(2, unreadChatsCount)
    }

    @Test
    fun getMessage() {
        ChatService.clearUsers()
        ChatService.createUser()
        ChatService.createUser()
        ChatService.createUser()
        ChatService.createMessage(0,1,"")
        ChatService.createMessage(0,1,"")
        ChatService.createMessage(0,1,"")
        ChatService.createMessage(0,1,"")
        ChatService.createMessage(0,1,"")

        val messages = ChatService.getMessages(0, 0, 2, 3)

        assertEquals(3, messages.size)
    }

    @Test
    fun createMessage() {
        ChatService.clearUsers()
        ChatService.createUser()
        ChatService.createUser()

        val chatCreated = ChatService.createMessage(0,1,"")

        assertEquals(1, chatCreated)
    }

    @Test
    fun createMessage_noCreateChat() {
        ChatService.clearUsers()
        ChatService.createUser()
        ChatService.createUser()
        ChatService.createMessage(0,1,"")

        val chatCreated = ChatService.createMessage(0,1,"")

        assertEquals(0, chatCreated)
    }

    @Test
    fun deleteMessage() {
        ChatService.clearUsers()
        ChatService.createUser()
        ChatService.createUser()
        ChatService.createMessage(0,1,"")
        ChatService.createMessage(0,1,"")

        val deleteMessage = ChatService.deleteMessage(0,0,0)

        assertEquals(1, deleteMessage)
    }

    @Test
    fun deleteChat() {
        ChatService.clearUsers()
        ChatService.createUser()
        ChatService.createUser()
        ChatService.createMessage(0,1,"")

        val deleteChat = ChatService.deleteChat(0, 0)

        assertEquals(1, deleteChat)
    }
}