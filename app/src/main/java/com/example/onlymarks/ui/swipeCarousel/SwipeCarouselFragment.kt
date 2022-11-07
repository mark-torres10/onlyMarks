package com.example.onlymarks.ui.swipeCarousel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.onlymarks.databinding.SwipeCarouselFragmentBinding

class SwipeCarouselFragment: Fragment() {

    private var _binding: SwipeCarouselFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val swipeCarouselViewModel =
            ViewModelProvider(this).get(SwipeCarouselViewModel::class.java)

        _binding = SwipeCarouselFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        /*
        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
         */
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}