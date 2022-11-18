package com.example.onlymarks.ui.messages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlymarks.dataclasses.MessageThread
import com.example.onlymarks.databinding.MessageItemBinding

private const val DEFAULT_TRUNCATE_LENGTH = 50

class MessagesAdapter(): RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    private var messageThreadsList = mutableListOf<MessageThread>()

    class ViewHolder(
        val messageBinding: MessageItemBinding
    ): RecyclerView.ViewHolder(messageBinding.root) {
        init {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val messageBinding = MessageItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(messageBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMessageThread = messageThreadsList[position]

        // TODO: verify that latest message added to thread is at end of message list
        // TODO: think this is enforced in seed data, but not enforced otherwise?
        val totalNumMessages = currentMessageThread.messages.size
        val latestMessage = currentMessageThread.messages[totalNumMessages-1].message
        var latestMessageStr = ""
        if (latestMessage.length > DEFAULT_TRUNCATE_LENGTH) {
            latestMessageStr = (
                    latestMessage.subSequence(0, DEFAULT_TRUNCATE_LENGTH).toString() + "..."
                )
        } else {
            latestMessageStr = latestMessage
        }
        val otherUserName = currentMessageThread.otherUserName

        holder.messageBinding.messagePersonName.text = otherUserName
        holder.messageBinding.messageText.text = latestMessageStr
    }

    override fun getItemCount(): Int {
        return messageThreadsList.size
    }

    fun updateMessageThreadsList(newMessageThreadsList: List<MessageThread>) {
        if (newMessageThreadsList != null) {
            this.messageThreadsList = newMessageThreadsList.toMutableList()
            this.notifyDataSetChanged()
        }
    }

    fun getMessageThreadsList(): MutableList<MessageThread> {
        return messageThreadsList
    }
}