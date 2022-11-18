package com.example.onlymarks.ui.swipeCarousel

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlymarks.dataclasses.SwipeCard
import com.example.onlymarks.databinding.SwipeCarouselItemBinding

class SwipeCarouselAdapter(
    private val viewModel: SwipeCarouselViewModel
): RecyclerView.Adapter<SwipeCarouselAdapter.ViewHolder>() {

    private var swipeCardsList = mutableListOf<SwipeCard>()
    private val defaultImgWidth = 400
    private val defalutImgHeight = 400
    class ViewHolder(
        val swipeCarouselItemBinding: SwipeCarouselItemBinding
    ): RecyclerView.ViewHolder(swipeCarouselItemBinding.root){
        init {

        }
    }

    private fun initImageUrls(imageView: ImageView, urls: List<String>) {
        for (url in urls) {
            setImageUrl(imageView, url)
        }
    }

    private fun setImageUrl(imageView: ImageView, url: String) {
        Glide.with(imageView.context).load(url).override(
            defaultImgWidth, defalutImgHeight
        ).into(imageView)
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

        val imageView = holder.swipeCarouselItemBinding.swipeCardImage

        initImageUrls(imageView, currentSwipeCard.profilePics)

        // make left and right image arrows clickable.
        val totalImages = currentSwipeCard.profilePics.size
        var currentIdx = 0
        var imageUrl = currentSwipeCard.profilePics[currentIdx]
        setImageUrl(imageView, imageUrl)

        holder.swipeCarouselItemBinding.leftImageArrow.setOnClickListener {
            if (currentIdx > 0) {
                currentIdx -= 1
            } else {
                currentIdx = totalImages - 1
            }
            imageUrl = currentSwipeCard.profilePics[currentIdx]
            setImageUrl(imageView, imageUrl)
        }

        holder.swipeCarouselItemBinding.rightImageArrow.setOnClickListener {
            if (currentIdx < totalImages - 1) {
                currentIdx += 1
            } else {
                currentIdx = 0
            }
            imageUrl = currentSwipeCard.profilePics[currentIdx]
            setImageUrl(imageView, imageUrl)
        }

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