package com.example.campuscart.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.campuscart.model.MessageDTO
import com.example.campuscart.model.ConversationDTO
import com.example.campuscart.repository.MessageRepository
import kotlinx.coroutines.launch

class MessageViewModel(private val repository: MessageRepository) : ViewModel() {
    
    private val _messages = MutableLiveData<List<MessageDTO>>()
    val messages: LiveData<List<MessageDTO>> = _messages

    private val _conversations = MutableLiveData<List<ConversationDTO>>()
    val conversations: LiveData<List<ConversationDTO>> = _conversations

    private val _unreadCount = MutableLiveData<Long>()
    val unreadCount: LiveData<Long> = _unreadCount

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun sendMessage(messageDTO: MessageDTO) {
        viewModelScope.launch {
            repository.sendMessage(messageDTO).fold(
                onSuccess = { /* Message sent successfully */ },
                onFailure = { _error.value = it.message }
            )
        }
    }

    fun loadConversation(username1: String, username2: String) {
        viewModelScope.launch {
            repository.getConversation(username1, username2).fold(
                onSuccess = { _messages.value = it },
                onFailure = { _error.value = it.message }
            )
        }
    }

    fun markMessageAsRead(messageId: Long) {
        viewModelScope.launch {
            repository.markAsRead(messageId).fold(
                onSuccess = { /* Message marked as read */ },
                onFailure = { _error.value = it.message }
            )
        }
    }

    fun loadUnreadMessages(username: String) {
        viewModelScope.launch {
            repository.getUnreadMessages(username).fold(
                onSuccess = { _messages.value = it },
                onFailure = { _error.value = it.message }
            )
        }
    }

    fun loadUnreadMessageCount(username: String) {
        viewModelScope.launch {
            repository.getUnreadMessageCount(username).fold(
                onSuccess = { _unreadCount.value = it },
                onFailure = { _error.value = it.message }
            )
        }
    }

    fun loadProductConversation(username1: String, username2: String, productCode: Int) {
        viewModelScope.launch {
            repository.getProductConversation(username1, username2, productCode).fold(
                onSuccess = { _messages.value = it },
                onFailure = { _error.value = it.message }
            )
        }
    }

    fun loadConversations(username: String) {
        viewModelScope.launch {
            repository.getConversations(username).fold(
                onSuccess = { _conversations.value = it },
                onFailure = { _error.value = it.message }
            )
        }
    }
} 