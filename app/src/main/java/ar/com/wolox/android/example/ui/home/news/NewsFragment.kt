package ar.com.wolox.android.example.ui.home.news

import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentNewsBinding
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.home.news.adapter.NewsAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<FragmentNewsBinding, NewsPresenter>(), NewsView {

    lateinit var adapter: NewsAdapter

    override fun layout() = R.layout.fragment_news

    override fun init() {
        with(binding) {
            recyclerview.apply {
                layoutManager = LinearLayoutManager(context)
                hasFixedSize()
            }

            val news: List<News> = listOf(
                News(
                    1,
                    "Ali Connors",
                    "I'll be in your neighborhood doing errands...",
                    "error",
                    "2021-09-23T12:22:10.490Z",
                    false
                ),
                News(
                    2,
                    "Ali Connors",
                    "I'll be in your neighborhood doing errands...",
                    "https://i.pinimg.com/originals/f5/ec/14/f5ec1493f8cf15a2f2d017ac9afe628d.jpg",
                    "2021-09-23T12:22:10.490Z",
                    true
                ),
                News(
                    3,
                    "Ali Connors",
                    "I'll be in your neighborhood doing errands...",
                    "https://i.pinimg.com/originals/42/2b/55/422b5542450bb18908f3ec6cc6004622.png",
                    "2021-09-23T13:22:10.490Z",
                    false
                ),
                News(
                    4,
                    "Ali Connors",
                    "I'll be in your neighborhood doing errands...",
                    "https://emoji.gg/assets/emoji/7017-panda-exicted.png",
                    "2021-09-23T13:28:10.490Z",
                    false
                )
            )

            adapter = NewsAdapter(news)
            recyclerview.adapter = adapter
        }
    }

    override fun setListeners() {
        with(binding) {
            swipe.setOnRefreshListener {
                Handler().postDelayed({
                    swipe.isRefreshing = false
                }, 2000)
            }
        }
    }

    override fun setItemsNews(item: News) {}

    companion object {
        fun newInstance() = NewsFragment()
    }
}

interface NewsView {

    fun setItemsNews(item: News)
}