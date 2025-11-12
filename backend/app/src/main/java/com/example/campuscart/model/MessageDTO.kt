package com.example.campuscart.model

data class MessageDTO(
    val id: Long? = null,
    val senderUsername: String,
    val receiverUsername: String,
    val content: String,
    val timestamp: String,
    val isRead: Boolean = false,
    val productCode: Int? = null
) 