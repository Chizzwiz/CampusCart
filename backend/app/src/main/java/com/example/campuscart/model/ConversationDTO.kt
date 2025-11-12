package com.example.campuscart.model

data class ConversationDTO(
    val otherUsername: String,
    val lastMessage: String,
    val timestamp: String,
    val unreadCount: Int,
    val productCode: Int? = null
) 