package ar.com.wolox.android.example.ui.home.news

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentNewsBinding
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.ui.home.news.adapter.EndlessRecyclerOnScrollListener
import ar.com.wolox.android.example.ui.home.news.adapter.NewsAdapter
import ar.com.wolox.android.example.ui.home.news.detail.NewsDetailActivity
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.android.example.utils.toggleVisibility
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import javax.inject.Inject

class NewsFragment @Inject constructor() : WolmoFragment<FragmentNewsBinding, NewsPresenter>(),
    NewsView, SwipeRefreshLayout.OnRefreshListener, NewsAdapter.NewsListener {

    @Inject internal lateinit var userSession: UserSession
    @Inject internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_news

    lateinit var scrollListener: EndlessRecyclerOnScrollListener

    override fun init() {
        scrollListener = object : EndlessRecyclerOnScrollListener() {
            override fun onLoadMoreNews() {
                presenter.loadNews()
            }
        }

        with(binding) {
            swipe.setOnRefreshListener(this@NewsFragment)
            recyclerView.addOnScrollListener(scrollListener)
        }

        presenter.onInit()
    }

    override fun onResume() {
        super.onResume()
        presenter.onRefresh()
    }

    override fun initAdapter(userId: Int) {
        binding.recyclerView.adapter = NewsAdapter(this@NewsFragment, userId)
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
    override fun openDetail(news: News) = NewsDetailActivity.start(requireContext(), news)

    companion object {
        fun newInstance() = NewsFragment()
    }
}

interface NewsView {
    fun initAdapter(userId: Int)
    fun loadNews(news: List<News>)
    fun clearNews()
    fun showLoader(visible: Boolean)
    fun responseFailed()
    fun responseFailure()
    fun finishRefreshing()
}
