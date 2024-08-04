package com.organeco.model.local.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.organeco.databinding.ItemFertilizerBinding
import com.organeco.model.local.fertilizer.DataDummy
import com.organeco.view.activity.detail.DetailActivity

class DummyAdapter(
    private val dataList: List<DataDummy>
) : RecyclerView.Adapter<DummyAdapter.DummyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyViewHolder {
        val binding =
            ItemFertilizerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DummyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("data_dummy", dataList[holder.adapterPosition])
            Toast.makeText(
                holder.itemView.context,
                "Kamu memilih " + dataList[holder.adapterPosition].name,
                Toast.LENGTH_SHORT
            ).show()
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = dataList.size


    inner class DummyViewHolder(private val binding: ItemFertilizerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataDummy) {
            binding.tvItemName.text = data.name
            binding.tvItemDescription.text = data.description
            binding.imgItemPhoto.setImageResource(data.image)

        }
    }

}