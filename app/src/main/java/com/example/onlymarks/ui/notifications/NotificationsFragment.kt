package com.example.onlymarks.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onlymarks.databinding.FragmentNotificationsBinding
import com.example.onlymarks.databinding.NotificationItemBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private lateinit var viewModel: NotificationsViewModel
    private lateinit var adapter: NotificationsAdapter
    private lateinit var manager: LinearLayoutManager

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)

        manager = LinearLayoutManager(binding.root.context)

        val currentNotifications = viewModel.observeNotifications().value!!
        adapter = NotificationsAdapter(viewModel)
        adapter.updateNotificationsList(currentNotifications)

        binding.notificationsRecyclerView.adapter = adapter
        binding.notificationsRecyclerView.layoutManager = manager

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}