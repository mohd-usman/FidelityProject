package com.example.fideltyproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fideltyproject.R
import com.example.fideltyproject.model.SearchAnimeResponse
import com.squareup.picasso.Picasso

class AnimeAdapter(private var animeList: MutableList<SearchAnimeResponse.SearchAnimeItem?>?, private val context: Context): RecyclerView.Adapter<AnimeAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.item_name)
        val role=itemView.findViewById<TextView>(R.id.item_type)
        val score=itemView.findViewById<TextView>(R.id.item_score)
        val episodes=itemView.findViewById<TextView>(R.id.item_episodes)
        val rank=itemView.findViewById<TextView>(R.id.item_rank)
        val date=itemView.findViewById<TextView>(R.id.item_date)
        val imageView = itemView.findViewById<ImageView>(R.id.item_imageView)

        fun bindTo(data: SearchAnimeResponse.SearchAnimeItem, context: Context) {

            name.text = data.title
            score.text= data.score.toString()
            episodes.text=data.episodes.toString()
            rank.text=data.malId.toString()
            var endDate=data.endDate
            if (endDate == "null" || endDate == null)
                endDate = "Unknown"
            else endDate=endDate.take(7)

            date.text=data.startDate.take(7) +"  -  "+ endDate
            role.text=data.type
            Picasso.get().load(data.imageUrl).placeholder(android.R.drawable.stat_sys_download).into(imageView)
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnimeAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.anime_layout_recyclerview, parent, false)
        return ViewHolder(view)    }

    override fun getItemCount(): Int {
        return animeList?.size ?: 0
    }

    override fun onBindViewHolder(holder: AnimeAdapter.ViewHolder, position: Int) {
        var anime = animeList?.get(position)
        if (anime != null) {
            holder.bindTo(anime,context)
        }
    }
    fun setData(animeList: MutableList<SearchAnimeResponse.SearchAnimeItem?>?){
        this.animeList=animeList
        notifyDataSetChanged()
    }

}