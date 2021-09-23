package ar.com.wolox.android.example.ui.home.viewholder

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.NewsItemBinding
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.home.news.NewsView
import ar.com.wolox.android.example.utils.glideImage
import ar.com.wolox.android.example.utils.abbreviationDayFormat

class ItemNewViewHolder constructor(private var binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root), NewsView {

    override fun setItemsNews(item: News) {
        with(binding) {
            title.text = item.title
            description.text = item.description
            image.glideImage(item.image)
            item.date.abbreviationDayFormat()?.let {
                date.text = it
            }
            liked.apply {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        liked.context,
                        if (item.liked) R.drawable.ic_like_on else R.drawable.ic_like_off
                    )
                )
            }
        }
    }
}