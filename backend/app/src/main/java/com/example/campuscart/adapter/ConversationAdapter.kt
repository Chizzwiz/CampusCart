package com.example.campuscart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.campuscart.R
import com.example.campuscart.model.ConversationDTO

class ConversationAdapter(
    private val onConversationClick: (ConversationDTO) -> Unit
) : RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder>() {

    private var conversations: List<ConversationDTO> = emptyList()

    fun updateConversations(newConversations: List<ConversationDTO>) {
        conversations = newConversations
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_conversation, parent, false)
        return ConversationViewHolder(view)
    }

    override fun onBindViewHolder(holder: ConversationViewHolder, position: Int) {
        holder.bind(conversations[position])
    }

    override fun getItemCount() = conversations.size

    inner class ConversationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val usernameTextView: TextView = itemView.findViewById(R.id.usernameTextView)
        private val lastMessageTextView: TextView = itemView.findViewById(R.id.lastMessageTextView)
        private val timestampTextView: TextView = itemView.findViewById(R.id.timestampTextView)
        private val unreadCountTextView: TextView = itemView.findViewById(R.id.unreadCountTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onConversationClick(conversations[position])
                }
            }
        }

        fun bind(conversation: ConversationDTO) {
            usernameTextView.text = conversation.otherUsername
            lastMessageTextView.text = conversation.lastMessage
            timestampTextView.text = conversation.timestamp
            
            if (conversation.unreadCount > 0) {
                unreadCountTextView.visibility = View.VISIBLE
                unreadCountTextView.text = conversation.unreadCount.toString()
            } else {
                unreadCountTextView.visibility = View.GONE
            }
        }
    }
} 