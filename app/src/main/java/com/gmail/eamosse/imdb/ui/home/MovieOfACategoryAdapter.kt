package com.gmail.eamosse.imdb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.eamosse.idbdata.data.MovieOfACategory
import com.gmail.eamosse.imdb.databinding.MocItemListBinding



class MovieOfACategoryAdapter(private val items: List<MovieOfACategory>, val handler: (movieOfACategory:MovieOfACategory) -> Unit) :
    RecyclerView.Adapter<MovieOfACategoryAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MocItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieOfACategory) {
            binding.item = item
            //   BidingAdapters.changeImage(binding.categoryImg, item.poster_path);

            binding.root.setOnClickListener {
                handler(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(MocItemListBinding.inflate(inflater, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }
}