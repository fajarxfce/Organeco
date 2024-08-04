package com.organeco.view.viewpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.organeco.databinding.ItemSliderBinding
import kotlin.math.min

class ImageSliderAdapter(private val imageList: List<ImageData>) :
    RecyclerView.Adapter<ImageSliderAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: ItemSliderBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        val binding = itemView
        fun bind(data: ImageData) {
            with(binding) {
                ivSlider.setImageResource(data.imageUrl)
//                Glide.with(itemView)
//                    .load(data.imageUrl)
//                    .into(ivSlider)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemSliderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(imageList[position])
    }

    override fun getItemCount(): Int = min(imageList.size, 3)


}