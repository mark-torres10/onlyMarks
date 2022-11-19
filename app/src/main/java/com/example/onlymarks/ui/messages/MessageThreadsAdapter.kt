package com.example.onlymarks.ui.messages

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.onlymarks.dataclasses.MessageThread
import com.example.onlymarks.databinding.MessageThreadsItemBinding
import com.example.onlymarks.seedData.MARK_TO_IMAGE

private const val DEFAULT_TRUNCATE_LENGTH = 40

class MessageThreadsAdapter(
    resultLauncher: ActivityResultLauncher<Intent>
): RecyclerView.Adapter<MessageThreadsAdapter.ViewHolder>() {

    private var messageThreadsList = mutableListOf<MessageThread>()
    private var messageResultLauncher = resultLauncher

    class ViewHolder(
        val messageBinding: MessageThreadsItemBinding
    ): RecyclerView.ViewHolder(messageBinding.root) {
        init {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val messageBinding = MessageThreadsItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(messageBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentMessageThread = messageThreadsList[position]

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

        holder.messageBinding.root.setOnClickListener {
            val oneMessageIntent = Intent(
                holder.messageBinding.root.context, OneMessageThread::class.java
            )
            val extras = Bundle()
            val messagePersonName = holder.messageBinding.messagePersonName.text.toString()
            extras.putString("messagePersonName", messagePersonName)
            extras.putParcelable("messageThread", currentMessageThread)
            oneMessageIntent.putExtras(extras)
            messageResultLauncher.launch(oneMessageIntent)
        }

        val thumbnailImageView = holder.messageBinding.messagePersonThumbnail
        val url = MARK_TO_IMAGE[currentMessageThread.otherUserName]?.get(0)

        Glide.with(thumbnailImageView.context)
            .load(url)
            .circleCrop()
            .override(120, 120)
            .into(thumbnailImageView)

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