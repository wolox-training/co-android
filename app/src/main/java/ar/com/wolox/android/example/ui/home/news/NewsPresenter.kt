package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.home.viewholder.ItemNewViewHolder
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class NewsPresenter @Inject constructor() : BasePresenter<NewsView>() {

    private var news: MutableList<News> = mutableListOf()

    fun onAddItemsNews(items: List<News>) = this.news.addAll(items)

    fun onBindNewsView(holder: ItemNewViewHolder, position: Int) = holder.setItemsNews(news[position])

    fun getNewsRows(): Int = news.size
}