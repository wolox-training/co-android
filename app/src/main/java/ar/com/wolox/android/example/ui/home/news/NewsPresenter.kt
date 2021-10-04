package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.NewsRepository
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsPresenter @Inject constructor(
    private val repository: NewsRepository,
    private val userSession: UserSession
) : CoroutineBasePresenter<NewsView>() {

    private var currentPage = 1

    private var loading: Boolean = false
        set(value) {
            field = value

            view?.showLoader(value)
        }

    fun onInit() {
        userSession.user?.id?.let {
            view?.initAdapter(it)
            newsRequest()
        }
    }

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

            view?.finishRefreshing()
            loading = false
        }
    }
}
