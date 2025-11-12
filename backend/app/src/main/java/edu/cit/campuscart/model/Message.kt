package edu.cit.campuscart.model

import java.util.Date

data class Message(
    val id: Long = 0,
    val senderUsername: String,
    val receiverUsername: String,
    val content: String,
    val timestamp: Date = Date(),
    val isRead: Boolean = false,
    val productCode: Int? = null
) 