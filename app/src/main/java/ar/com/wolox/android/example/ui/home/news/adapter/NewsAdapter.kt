package ar.com.wolox.android.example.ui.home.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.home.viewholder.ItemNewViewHolder
import ar.com.wolox.android.example.utils.glideImage
import ar.com.wolox.android.example.utils.abbreviationDayFormat

class NewsAdapter(private val listener: NewsListener, private val idUser: Int) : RecyclerView.Adapter<ItemNewViewHolder>() {

    private var news = mutableListOf<News>()

    interface NewsListener {
        fun openDetail(new: News)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ItemNewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemNewViewHolder, position: Int) {
        holder.apply {
            title.text = news[position].commenter
            description.text = news[position].comment
            image.glideImage(news[position].avatar)
            news[position].updatedAt.abbreviationDayFormat().let {
                time.text = it
            }
            liked.apply {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        liked.context,
                        if (news[position].likes.contains(idUser)) R.drawable.ic_like_on else R.drawable.ic_like_off
                    )
                )
            }
            newsItem.setOnClickListener { listener.openDetail(news[position]) }
        }
    }

    override fun getItemCount() = news.size

    fun addNews(items: List<News>) {
        news.addAll(items)
        notifyDataSetChanged()
    }

    fun clear() = news.clear()
}
