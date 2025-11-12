package com.example.campuscart.repository

import com.example.campuscart.api.MessageService
import com.example.campuscart.model.MessageDTO
import com.example.campuscart.model.ConversationDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MessageRepository(private val messageService: MessageService) {
    
    suspend fun sendMessage(messageDTO: MessageDTO): Result<MessageDTO> = withContext(Dispatchers.IO) {
        try {
            val response = messageService.sendMessage(messageDTO)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to send message"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getConversation(username1: String, username2: String): Result<List<MessageDTO>> = withContext(Dispatchers.IO) {
        try {
            val response = messageService.getConversation(username1, username2)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to get conversation"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun markAsRead(messageId: Long): Result<Unit> = withContext(Dispatchers.IO) {
        try {
            val response = messageService.markAsRead(messageId)
            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("Failed to mark message as read"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUnreadMessages(username: String): Result<List<MessageDTO>> = withContext(Dispatchers.IO) {
        try {
            val response = messageService.getUnreadMessages(username)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to get unread messages"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUnreadMessageCount(username: String): Result<Long> = withContext(Dispatchers.IO) {
        try {
            val response = messageService.getUnreadMessageCount(username)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to get unread message count"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductConversation(username1: String, username2: String, productCode: Int): Result<List<MessageDTO>> = withContext(Dispatchers.IO) {
        try {
            val response = messageService.getProductConversation(username1, username2, productCode)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to get product conversation"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getConversations(username: String): Result<List<ConversationDTO>> = withContext(Dispatchers.IO) {
        try {
            val response = messageService.getConversations(username)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to get conversations"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
} 