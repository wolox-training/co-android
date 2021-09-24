package ar.com.wolox.android.example.ui.home.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.home.viewholder.ItemNewViewHolder
import ar.com.wolox.android.example.utils.abbreviationDayFormat
import ar.com.wolox.android.example.utils.glideImage

class NewsAdapter(val news: List<News>) : RecyclerView.Adapter<ItemNewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ItemNewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemNewViewHolder, position: Int) {
        holder.apply {
            title.text = news[position].title
            description.text = news[position].description
            image.glideImage(news[position].image)
            news[position].date.abbreviationDayFormat().let {
                time.text = it
            }
            liked.apply {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        liked.context,
                        if (news[position].liked) R.drawable.ic_like_on else R.drawable.ic_like_off
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int = news.size
}