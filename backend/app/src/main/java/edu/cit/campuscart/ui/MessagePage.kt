package edu.cit.campuscart.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.cit.campuscart.model.Message
import edu.cit.campuscart.model.Conversation
import edu.cit.campuscart.viewmodel.MessageViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun MessagePage(
    currentUsername: String,
    onNavigateToChat: (String) -> Unit,
    viewModel: MessageViewModel = viewModel()
) {
    var selectedConversation by remember { mutableStateOf<Conversation?>(null) }
    val conversations by viewModel.conversations.collectAsState()
    val unreadCount by viewModel.unreadCount.collectAsState()

    LaunchedEffect(currentUsername) {
        viewModel.loadConversations(currentUsername)
        viewModel.loadUnreadCount(currentUsername)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Header with unread count
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Messages",
                style = MaterialTheme.typography.headlineMedium
            )
            if (unreadCount > 0) {
                Badge(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text(text = unreadCount.toString())
                }
            }
        }

        // Conversations list
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(conversations) { conversation ->
                ConversationItem(
                    conversation = conversation,
                    currentUsername = currentUsername,
                    onClick = { onNavigateToChat(conversation.otherUsername) }
                )
            }
        }
    }
}

@Composable
fun ConversationItem(
    conversation: Conversation,
    currentUsername: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = conversation.otherUsername,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = conversation.lastMessage,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
            }
            Column(
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = formatDate(conversation.lastMessageTime),
                    style = MaterialTheme.typography.bodySmall
                )
                if (conversation.unreadCount > 0) {
                    Badge(
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Text(text = conversation.unreadCount.toString())
                    }
                }
            }
        }
    }
}

private fun formatDate(date: Date): String {
    val sdf = SimpleDateFormat("MMM dd, HH:mm", Locale.getDefault())
    return sdf.format(date)
} 