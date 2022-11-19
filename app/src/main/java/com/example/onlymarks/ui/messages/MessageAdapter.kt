package com.example.onlymarks.ui.messages

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlymarks.databinding.MessageItemBinding
import com.example.onlymarks.dataclasses.Message

class MessageAdapter(): RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    private var messageList = mutableListOf<Message>()

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
        holder.messageBinding.messageText.text = currentMessage.message
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    fun updateMessageList(newMessageList: List<Message>) {
        if (newMessageList != null) {
            this.messageList = newMessageList.toMutableList()
            this.notifyDataSetChanged()
        }
    }

    fun getMessageList(): MutableList<Message> {
        return messageList
    }

}