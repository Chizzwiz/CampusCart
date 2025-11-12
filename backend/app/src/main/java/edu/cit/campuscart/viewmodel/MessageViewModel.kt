package edu.cit.campuscart.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.cit.campuscart.model.Conversation
import edu.cit.campuscart.model.Message
import edu.cit.campuscart.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MessageViewModel : ViewModel() {
    private val _conversations = MutableStateFlow<List<Conversation>>(emptyList())
    val conversations: StateFlow<List<Conversation>> = _conversations

    private val _unreadCount = MutableStateFlow(0L)
    val unreadCount: StateFlow<Long> = _unreadCount

    private val apiService: ApiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://your-api-base-url/") // Replace with your actual API base URL
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)
    }

    fun loadConversations(username: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getConversations(username)
                _conversations.value = response
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun loadUnreadCount(username: String) {
        viewModelScope.launch {
            try {
                val count = apiService.getUnreadMessageCount(username)
                _unreadCount.value = count
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun sendMessage(message: Message) {
        viewModelScope.launch {
            try {
                apiService.sendMessage(message)
                // Refresh conversations after sending message
                loadConversations(message.senderUsername)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    fun markMessageAsRead(messageId: Long) {
        viewModelScope.launch {
            try {
                apiService.markAsRead(messageId)
                // Refresh unread count
                loadUnreadCount(_conversations.value.firstOrNull()?.otherUsername ?: return@launch)
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
} 