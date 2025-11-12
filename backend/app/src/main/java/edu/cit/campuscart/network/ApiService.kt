package edu.cit.campuscart.network

import edu.cit.campuscart.model.Conversation
import edu.cit.campuscart.model.Message
import retrofit2.http.*

interface ApiService {
    @GET("api/messages/conversations/{username}")
    suspend fun getConversations(@Path("username") username: String): List<Conversation>

    @GET("api/messages/unread/count/{username}")
    suspend fun getUnreadMessageCount(@Path("username") username: String): Long

    @POST("api/messages")
    suspend fun sendMessage(@Body message: Message): Message

    @PUT("api/messages/{messageId}/read")
    suspend fun markAsRead(@Path("messageId") messageId: Long)

    @GET("api/messages/conversation/{username1}/{username2}")
    suspend fun getConversation(
        @Path("username1") username1: String,
        @Path("username2") username2: String
    ): List<Message>

    @GET("api/messages/unread/{username}")
    suspend fun getUnreadMessages(@Path("username") username: String): List<Message>

    @GET("api/messages/conversation/{username1}/{username2}/product/{productCode}")
    suspend fun getProductConversation(
        @Path("username1") username1: String,
        @Path("username2") username2: String,
        @Path("productCode") productCode: Int
    ): List<Message>
} 