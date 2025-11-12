package com.example.campuscart.api

import com.example.campuscart.model.MessageDTO
import com.example.campuscart.model.ConversationDTO
import retrofit2.Response
import retrofit2.http.*

interface MessageService {
    @POST("api/messages")
    suspend fun sendMessage(@Body messageDTO: MessageDTO): Response<MessageDTO>

    @GET("api/messages/conversation/{username1}/{username2}")
    suspend fun getConversation(
        @Path("username1") username1: String,
        @Path("username2") username2: String
    ): Response<List<MessageDTO>>

    @PUT("api/messages/{messageId}/read")
    suspend fun markAsRead(@Path("messageId") messageId: Long): Response<Unit>

    @GET("api/messages/unread/{username}")
    suspend fun getUnreadMessages(@Path("username") username: String): Response<List<MessageDTO>>

    @GET("api/messages/unread/count/{username}")
    suspend fun getUnreadMessageCount(@Path("username") username: String): Response<Long>

    @GET("api/messages/conversation/{username1}/{username2}/product/{productCode}")
    suspend fun getProductConversation(
        @Path("username1") username1: String,
        @Path("username2") username2: String,
        @Path("productCode") productCode: Int
    ): Response<List<MessageDTO>>

    @GET("api/messages/conversations/{username}")
    suspend fun getConversations(@Path("username") username: String): Response<List<ConversationDTO>>
} 