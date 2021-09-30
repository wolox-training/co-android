package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.NewsRepository
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsPresenter @Inject constructor(private val repository: NewsRepository) : CoroutineBasePresenter<NewsView>() {

    private var currentPage = 1

    fun onInit() = newsRequest()
    fun loadNews() = newsRequest(++currentPage)

    fun onRefresh() {
        currentPage = 1
        view?.clearNews()
        newsRequest()
    }

    private fun newsRequest(page: Int = 1) = launch {
        networkRequest(repository.getNews(page)) {
            onResponseSuccessful { response, _ ->
                currentPage = page
                response?.let { view?.loadNews(it.news) }
            }
            onResponseFailed { _, _ -> view?.responseFailed() }
            onCallFailure { view?.responseFailure() }

            view?.apply {
                finishRefreshing()
                showLoader(false)
            }
        }
    }
}
