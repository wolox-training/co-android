package ar.com.wolox.android.example.ui.home.news

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentNewsBinding
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.home.news.adapter.EndlessRecyclerOnScrollListener
import ar.com.wolox.android.example.ui.home.news.adapter.NewsAdapter
import ar.com.wolox.android.example.utils.toggleVisibility
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<FragmentNewsBinding, NewsPresenter>(), NewsView, SwipeRefreshLayout.OnRefreshListener {
    @Inject internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_news

    lateinit var scrollListener: EndlessRecyclerOnScrollListener

    override fun init() {
        binding.swipe.setOnRefreshListener(this)
        binding.recyclerView.adapter = NewsAdapter()

        scrollListener = object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMoreNews() {
                presenter.loadNews()
            }
        }

        binding.recyclerView.addOnScrollListener(scrollListener)
        presenter.onInit()
    }

    override fun loadNews(news: List<News>) {
        with(binding.recyclerView.adapter as NewsAdapter) {
            this.addNews(news)
        }
    }

    override fun clearNews() = (binding.recyclerView.adapter as NewsAdapter).clear()
    override fun showLoader(visible: Boolean) = binding.loading.toggleVisibility(visible)
    override fun responseFailed() = toastFactory.show(R.string.default_error)
    override fun responseFailure() = toastFactory.show(R.string.request_without_connection)
    override fun onRefresh() = presenter.onRefresh()
    override fun finishRefreshing() { binding.swipe.isRefreshing = false }

    companion object {
        fun newInstance() = NewsFragment()
    }
}
