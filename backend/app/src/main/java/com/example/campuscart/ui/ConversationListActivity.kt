package com.example.campuscart.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campuscart.R
import com.example.campuscart.adapter.ConversationAdapter
import com.example.campuscart.model.ConversationDTO
import com.example.campuscart.viewmodel.MessageViewModel

class ConversationListActivity : AppCompatActivity() {

    private lateinit var viewModel: MessageViewModel
    private lateinit var adapter: ConversationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversation_list)

        // Initialize ViewModel
        viewModel = ViewModelProvider(this)[MessageViewModel::class.java]

        // Setup RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.conversationsRecyclerView)
        adapter = ConversationAdapter { conversation ->
            // Handle conversation click
            openConversation(conversation)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Observe ViewModel data
        viewModel.conversations.observe(this) { conversations ->
            adapter.updateConversations(conversations)
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }

        // Load conversations for the current user
        // TODO: Replace with actual username
        val currentUsername = "current_user"
        viewModel.loadConversations(currentUsername)
    }

    private fun openConversation(conversation: ConversationDTO) {
        // TODO: Launch conversation detail activity
        // val intent = Intent(this, ConversationDetailActivity::class.java).apply {
        //     putExtra("otherUsername", conversation.otherUsername)
        //     putExtra("productCode", conversation.productCode)
        // }
        // startActivity(intent)
    }
} 