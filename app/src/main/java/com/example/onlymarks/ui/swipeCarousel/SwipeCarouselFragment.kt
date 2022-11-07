package com.example.onlymarks.ui.swipeCarousel
import com.yuyakaido.android.cardstackview.CardStackView
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.onlymarks.databinding.SwipeCarouselFragmentBinding
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction

class SwipeCarouselFragment: Fragment() {

    private var _binding: SwipeCarouselFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    val cardStackListener = object : CardStackListener {
        override fun onCardDragging(direction: Direction?, ratio: Float) {
        }

        override fun onCardRewound() {
        }

        override fun onCardCanceled() {
        }

        override fun onCardAppeared(view: View?, position: Int) {
        }

        override fun onCardDisappeared(view: View?, position: Int) {
        }

        override fun onCardSwiped(p0: Direction?) {
        }
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

        // set up card stack
        val cardStackView = binding.cardStackView
        val cardStackAdapter = SwipeCarouselAdapter(swipeCarouselViewModel)
        val currentSwipeCards = swipeCarouselViewModel.observeSwipeCards().value!!
        cardStackAdapter.updateSwipeCardsList(currentSwipeCards)

        cardStackView.layoutManager = CardStackLayoutManager(this.context, cardStackListener)
        cardStackView.adapter = cardStackAdapter

        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}