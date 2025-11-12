package edu.cit.campuscart.model

import java.util.Date

data class Conversation(
    val otherUsername: String,
    val lastMessage: String,
    val lastMessageTime: Date,
    val unreadCount: Int = 0,
    val productCode: Int? = null
) 