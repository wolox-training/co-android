package ar.com.wolox.android.example.ui.home.news.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.NewsItemBinding
import ar.com.wolox.android.example.ui.home.news.NewsPresenter
import ar.com.wolox.android.example.ui.home.viewholder.ItemNewViewHolder

class NewsAdapter(private var presenter: NewsPresenter) : RecyclerView.Adapter<ItemNewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemNewViewHolder {
        val binding: NewsItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.news_item, parent, false)
        return ItemNewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemNewViewHolder, position: Int) {
        presenter.onBindNewsView(holder, position)
    }

    override fun getItemCount(): Int = presenter.getNewsRows()
}