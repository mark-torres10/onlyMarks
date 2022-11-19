package com.example.onlymarks.ui.messages

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlymarks.databinding.MessageItemBinding
import com.example.onlymarks.dataclasses.Message
import com.example.onlymarks.dataclasses.MessageThread

class MessageAdapter(): RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    private var messageThread: MessageThread? = null
    private var messageList = mutableListOf<Message>()
    private var messageThreadOwnerId: Int? = null
    private var messageThreadReceiverId: Int? = null

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
        val currentMessage = messageList[position]
        val currentMessageText = currentMessage.message
        val leftView = holder.messageBinding.messageTextLeft
        val rightView = holder.messageBinding.messageTextRight

        // if message sent from someone else, display on left side.
        if (currentMessage.sentFromId == messageThreadReceiverId) {
            rightView.visibility = View.INVISIBLE
            leftView.visibility = View.VISIBLE
            leftView.text = currentMessageText
        }

        // if message sent from current user, display on right side.
        else if (currentMessage.sentFromId == messageThreadOwnerId) {
            leftView.visibility = View.INVISIBLE
            rightView.visibility = View.VISIBLE
            rightView.text = currentMessageText
        }
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun updateMessageThread(newMessageThread: MessageThread) {
        if (newMessageThread != null) {
            this.messageThread = newMessageThread
            this.messageList = newMessageThread.messages.toMutableList()
            this.messageThreadOwnerId = newMessageThread.threadOwnerId
            this.messageThreadReceiverId = newMessageThread.threadReceiverId
            this.notifyDataSetChanged()
        }
    }
}