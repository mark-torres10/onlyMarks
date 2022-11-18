package com.example.onlymarks.ui.swipeCarousel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.onlymarks.dataclasses.SwipeCard
import com.example.onlymarks.databinding.SwipeCarouselFragmentBinding
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import java.lang.Thread.sleep
import kotlin.properties.Delegates

class SwipeCarouselFragment: Fragment() {

    private var _binding: SwipeCarouselFragmentBinding? = null
    private lateinit var adapter: SwipeCarouselAdapter
    private lateinit var viewModel: SwipeCarouselViewModel
    private lateinit var layoutManager: CardStackLayoutManager

    private lateinit var currentCard: SwipeCard
    private var latestIndex by Delegates.notNull<Int>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private fun setOverlayVisibility(
        direction: Direction?, ratio: Float
    ) {
        // set overlay visibility on card drag depending on direction + ratio.
        if (direction?.name == "Left") {
            //val opacity = (ratio * 100).toInt()
            binding.cardRedX.visibility = View.VISIBLE
            binding.cardRedX.alpha = ratio
            binding.cardGreenCheckMark.visibility = View.INVISIBLE
        } else if (direction?.name == "Right") {
            val opacity = (ratio * 100).toInt()
            binding.cardGreenCheckMark.visibility = View.VISIBLE
            binding.cardGreenCheckMark.alpha = ratio
            binding.cardRedX.visibility = View.INVISIBLE
        }
    }

    private fun clearOverlays() {
        binding.cardRedX.visibility = View.INVISIBLE
        binding.cardGreenCheckMark.visibility = View.INVISIBLE
    }

    val cardStackListener = object : CardStackListener {
        override fun onCardDragging(direction: Direction?, ratio: Float) {
            setOverlayVisibility(direction, ratio)
        }

        override fun onCardRewound() {
        }

        override fun onCardCanceled() {
        }

        override fun onCardAppeared(view: View?, position: Int) {
            // TODO: work out the kinks of this delay.
            sleep(500)
            clearOverlays()
        }

        override fun onCardDisappeared(view: View?, position: Int) {
            Log.d("XXX", "Switching to new card.")
            currentCard = adapter.getSwipeCardsList()[position]
            latestIndex = position
        }

        override fun onCardSwiped(direction: Direction?) {
            if (layoutManager.topPosition == adapter.itemCount) {
                Log.d("XXX", "Reached end of stack.")
                sleep(500)
                clearOverlays()
            }
            if (direction == Direction.Right) {
                // update to liked profile. Update in adapter and view model
                currentCard.youLikeThemBool = true
                adapter.getSwipeCardsList()[latestIndex] = currentCard
                viewModel.setSwipeCards(adapter.getSwipeCardsList())

            } else if (direction == Direction.Left) {
                // update to disliked profile
                currentCard.youLikeThemBool = false
                adapter.getSwipeCardsList()[latestIndex] = currentCard
                viewModel.setSwipeCards(adapter.getSwipeCardsList())
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

        layoutManager = CardStackLayoutManager(this.context, cardStackListener)
        cardStackView.layoutManager = layoutManager
        cardStackView.adapter = adapter

        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}