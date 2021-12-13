package com.gmail.eamosse.imdb.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gmail.eamosse.idbdata.data.MovieOfACategory
import com.gmail.eamosse.imdb.R
import com.gmail.eamosse.imdb.databinding.MocItemListBinding
import com.squareup.picasso.Picasso


class MovieOfACategoryAdapter(private val items: List<MovieOfACategory>, val handler: (movieOfACategory:MovieOfACategory) -> Unit) :
    RecyclerView.Adapter<MovieOfACategoryAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: MocItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MovieOfACategory) {
            binding.item = item
            loadImageWithUri(binding.categoryImg,"https://image.tmdb.org/t/p/w500${item.poster_path}")
            binding.root.setOnClickListener {
                handler(item)
            }
        }
    }
    @BindingAdapter("app:imageUri")
    fun loadImageWithUri(imageView: ImageView, imageUri: String){
        Glide.with(imageView.context).load(imageUri).into(imageView)
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