package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.example.model.News

interface NewsView {
    fun loadNews(news: List<News>)
    fun clearNews()
    fun showLoader(visible: Boolean)
    fun responseFailed()
    fun responseFailure()
    fun finishRefreshing()
}
