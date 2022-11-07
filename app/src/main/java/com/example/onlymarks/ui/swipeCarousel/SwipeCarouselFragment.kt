package com.example.onlymarks.ui.swipeCarousel
import com.yuyakaido.android.cardstackview.CardStackView
import android.os.Bundle
import android.util.Log
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

    private fun initCardListeners(binding: SwipeCarouselFragmentBinding) {

        // on click, flip card
        binding.swipeCardLayout.setOnClickListener {
            Log.d("XXX", "Flipping card")
        }

        // on left swipe,
        // have card follow left and disappear off screen
        // and show a red "X" popup


        // on right swipe,
        // have card follow right and disappear off screen
        // and show a green "checkmark" popup
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val swipeCarouselViewModel =
            ViewModelProvider(this).get(SwipeCarouselViewModel::class.java)

        _binding = SwipeCarouselFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initCardListeners(binding)

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