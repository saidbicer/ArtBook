package com.said.androidartbooktesting.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.said.androidartbooktesting.R
import com.said.androidartbooktesting.data.db.entity.Art
import javax.inject.Inject

class ArtRecyclerAdapter @Inject constructor(val glide: RequestManager) :
    RecyclerView.Adapter<ArtRecyclerAdapter.ArtViewHolder>() {

    class ArtViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffUtil = object : DiffUtil.ItemCallback<Art>() {
        override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var arts: List<Art>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.art_row, parent, false)
        return ArtViewHolder(view)
    }

    override fun onBindViewHolder(holder: ArtViewHolder, position: Int) {
        val tvArtName = holder.itemView.findViewById<TextView>(R.id.tvArtName)
        val tvArtistName = holder.itemView.findViewById<TextView>(R.id.tvArtistName)
        val tvYearName = holder.itemView.findViewById<TextView>(R.id.tvYearName)
        val imgArt = holder.itemView.findViewById<ImageView>(R.id.imgArt)

        val art = arts[position]
        holder.itemView.apply {
            tvArtName.text = art.artistName
            tvArtistName.text = art.artistName
            tvYearName.text = art.year.toString()
        }
    }

    override fun getItemCount(): Int {
        return arts.size
    }

}