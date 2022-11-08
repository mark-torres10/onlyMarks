package com.example.onlymarks.ui.notifications

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onlymarks.api.Notification
import com.example.onlymarks.databinding.NotificationItemBinding

class NotificationsAdapter (
    private val viewModel: NotificationsViewModel
): RecyclerView.Adapter<NotificationsAdapter.ViewHolder>() {

    private var notificationsList = mutableListOf<Notification>()

    class ViewHolder(
        val notificationsBinding: NotificationItemBinding
    ): RecyclerView.ViewHolder(notificationsBinding.root) {
        init {}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val notificationsBinding = NotificationItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(notificationsBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNotification = notificationsList[position]

        // TODO: update what the XML should look like.
        var notificationMessage = ""
        if (currentNotification.notificationType == "swipedOnYourProfile") {
            val userName = currentNotification.sourceUserName
            notificationMessage = "${userName} has liked your profile!"
        }
        holder.notificationsBinding.notificationText.text = notificationMessage
        holder.notificationsBinding.notificationText.setOnClickListener {
            if (!currentNotification.hasBeenViewed) {
                currentNotification.hasBeenViewed = true
                holder.notificationsBinding.notificationText.setBackgroundColor(
                    Color.parseColor("#FFFFFF")
                )
            }
            currentNotification.hasBeenViewed = true
            // TODO: update viewModel
        }


    }

    override fun getItemCount(): Int {
        return notificationsList.size
    }

    fun updateNotificationsList(newNotificationsList: List<Notification>) {
        if (newNotificationsList != null) {
            this.notificationsList = newNotificationsList.toMutableList()
            this.notifyDataSetChanged()
        }
    }

    fun getNotificationsList(): MutableList<Notification> {
        return notificationsList
    }
}