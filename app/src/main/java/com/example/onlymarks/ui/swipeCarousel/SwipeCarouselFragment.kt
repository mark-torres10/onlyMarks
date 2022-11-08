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
import com.example.onlymarks.api.SwipeCard
import com.example.onlymarks.databinding.SwipeCarouselFragmentBinding
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import kotlin.properties.Delegates

class SwipeCarouselFragment: Fragment() {

    private var _binding: SwipeCarouselFragmentBinding? = null
    private lateinit var adapter: SwipeCarouselAdapter
    private lateinit var viewModel: SwipeCarouselViewModel

    private lateinit var currentCard: SwipeCard
    private var latestIndex by Delegates.notNull<Int>()

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
            Log.d("XXX", "Switching to new card.")
            currentCard = adapter.getSwipeCardsList()[position]
            latestIndex = position
        }

        override fun onCardSwiped(direction: Direction?) {
            if (direction == Direction.Right) {
                // update to liked profile. Update in adapter and view model
                currentCard.youLikeThemBool = true
                adapter.getSwipeCardsList()[latestIndex] = currentCard
                // TODO: will have to update viewModel at some point
                Log.d("XXX", "Liked? ${adapter.getSwipeCardsList()[latestIndex]}")
                viewModel.setSwipeCards(adapter.getSwipeCardsList())
                // TODO: show X popup

            } else if (direction == Direction.Left) {
                // update to disliked profile
                currentCard.youLikeThemBool = false
                adapter.getSwipeCardsList()[latestIndex] = currentCard
                // TODO: will have to update viewModel at some point
                Log.d("XXX", "Liked? ${adapter.getSwipeCardsList()[latestIndex]}")
                viewModel.setSwipeCards(adapter.getSwipeCardsList())
                // TODO: show green checkmark
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(SwipeCarouselViewModel::class.java)

        _binding = SwipeCarouselFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // set up card stack
        val cardStackView = binding.cardStackView
        adapter = SwipeCarouselAdapter(viewModel)
        val currentSwipeCards = viewModel.observeSwipeCards().value!!
        adapter.updateSwipeCardsList(currentSwipeCards)

        cardStackView.layoutManager = CardStackLayoutManager(this.context, cardStackListener)
        cardStackView.adapter = adapter

        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}