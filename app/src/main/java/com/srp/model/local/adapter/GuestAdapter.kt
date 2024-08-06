package com.srp.model.local.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.srp.databinding.ItemFertilizerBinding
import com.srp.model.local.fertilizer.DataDummy
import com.srp.view.activity.auth.login.LoginActivity

class GuestAdapter(private val dataList: List<DataDummy>): RecyclerView.Adapter<GuestAdapter.GuestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GuestViewHolder {
        val binding =
            ItemFertilizerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GuestViewHolder(binding)

    }
    override fun onBindViewHolder(holder: GuestViewHolder, position: Int) {
        val data = dataList[position]
        holder.bind(data)

        holder.itemView.setOnClickListener {
            AlertDialog.Builder(holder.itemView.context).apply {
                setTitle("Wah!")
                setMessage("Kamu Harus Login Terlebih dahulu")
                setPositiveButton("Login Dulu Yuk") { _, _ ->
                    val intent = Intent(holder.itemView.context, LoginActivity::class.java)
                    holder.itemView.context.startActivity(intent)
                }
                show()
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    inner class GuestViewHolder(private val binding: ItemFertilizerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataDummy) {
            binding.tvItemName.text = data.name
            binding.tvItemDescription.text = data.description
            binding.imgItemPhoto.setImageResource(data.image)

        }
    }


}