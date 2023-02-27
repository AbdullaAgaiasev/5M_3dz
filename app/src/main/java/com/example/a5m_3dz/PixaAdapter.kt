package com.example.a5m_3dz

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.a5m_3dz.databinding.ItemImageBinding

class PixaAdapter(var list: java.util.ArrayList<Hit>) : Adapter<PixaAdapter.PixaViewHolder>() {

    fun addData(list:List<Hit>){
        this.list.addAll(list)
    }

    class PixaViewHolder(var binding: ItemImageBinding) : ViewHolder(binding.root) {
        fun onBind(hit: Hit) {
            binding.ivImage.load(hit.largeImageURL)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PixaViewHolder {
        return PixaViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false))

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: PixaViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}