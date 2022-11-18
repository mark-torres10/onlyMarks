package com.example.onlymarks.ui.swipeCarousel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlymarks.R
import com.example.onlymarks.api.SwipeCard
import com.example.onlymarks.databinding.SwipeCarouselFragmentBinding
import com.example.onlymarks.databinding.SwipeCarouselItemBinding

class SwipeCarouselAdapter(
    private val viewModel: SwipeCarouselViewModel
): RecyclerView.Adapter<SwipeCarouselAdapter.ViewHolder>() {

    private var swipeCardsList = mutableListOf<SwipeCard>()
    class ViewHolder(
        val swipeCarouselItemBinding: SwipeCarouselItemBinding
    ): RecyclerView.ViewHolder(swipeCarouselItemBinding.root){
        init {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val swipeCarouselBinding = SwipeCarouselItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(swipeCarouselBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentSwipeCard = swipeCardsList[position]
        //holder.swipeCarouselItemBinding.swipeCardImage.setImageDrawable()
        holder.swipeCarouselItemBinding.swipeCardName.text = currentSwipeCard.name
        holder.swipeCarouselItemBinding.swipeCardAge.text = currentSwipeCard.age.toString()
        holder.swipeCarouselItemBinding.swipeCardBio.text = currentSwipeCard.bio
        holder.swipeCarouselItemBinding.swipeCardDistance.text = currentSwipeCard.distance.toString()

        val url = currentSwipeCard.profilePicStr.toString()
        Glide.with(
            holder.swipeCarouselItemBinding.swipeCardImage.context
        ).load(url).into(holder.swipeCarouselItemBinding.swipeCardImage)
    }

    override fun getItemCount(): Int {
        return swipeCardsList.size
    }

    fun updateSwipeCardsList(newSwipeCardsList: List<SwipeCard>) {
        if (newSwipeCardsList != null) {
            this.swipeCardsList = newSwipeCardsList.toMutableList()
            this.notifyDataSetChanged()
        }
    }

    fun getSwipeCardsList(): MutableList<SwipeCard> {
        return swipeCardsList
    }
}