package com.organeco.model.local.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.organeco.databinding.ItemBookmarkBinding
import com.organeco.model.local.entity.Recommendation

class BookmarkAdapter (private val listBookmark : List<Recommendation>) :
    RecyclerView.Adapter<BookmarkAdapter.UserViewHolder>()
{
    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    class UserViewHolder(var binding: ItemBookmarkBinding) : RecyclerView.ViewHolder(binding.root) {  //inner class UserViewHolder
        fun binding(recommendation: Recommendation) {
            with(binding){
                tvItemName.text = recommendation.result
                tvItemTemparature.text = recommendation.temperature.toString()
                tvItemHumidity.text = recommendation.humidity.toString()
                tvItemMoisture.text = recommendation.moisture.toString()
                tvItemSoilType.text = recommendation.soil_type
                tvItemCropType.text = recommendation.crop_type
                tvItemNitrogen.text = recommendation.nitrogen.toString()
                tvItemPotassium.text = recommendation.potassium.toString()
                tvItemPhosphorous.text = recommendation.phosphorous.toString()
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            ItemBookmarkBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding(listBookmark[position])
        holder.itemView.setOnClickListener {
            onItemClickCallback?.onItemClicked(
                listBookmark[holder.adapterPosition]
            )
        }
    }

    override fun getItemCount(): Int = listBookmark.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Recommendation)
    }
}