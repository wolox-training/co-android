package ar.com.wolox.android.example.ui.home.news.detail

import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.NewsRepository
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsDetailPresenter @Inject constructor(
    private val newsRepository: NewsRepository,
    private val userSession: UserSession
) : CoroutineBasePresenter<NewDetailView>() {

    private lateinit var news: News
    private var imageFullscreen = false

    fun onInit(news: News) {
        this.news = news
        userSession.user?.id?.let {
            view?.showInformation(news, it)
        }
    }

    fun onRefresh() = launch {
        networkRequest(newsRepository.getInformationNew(news.id)) {
            onResponseSuccessful { response, _ ->
                response?.let {
                    news.commenter = it.commenter
                    news.comment = it.comment
                    news.date = it.date
                    news.avatar = it.avatar
                    news.likes = it.likes.toMutableList()
                }
                userSession.user?.id?.let { view?.showInformation(news, it) }
            }
            onResponseFailed { _, _ -> view?.responseFailed() }
            onCallFailure { view?.responseFailure() }
            view?.finishRefreshing()
        }
    }

    fun onClickLiked() = launch {
        view?.enabledLikedButton(false)
        networkRequest(newsRepository.toggleLiked(news.id)) {
            onResponseSuccessful { _, _ ->
                userSession.user?.id?.let {
                    if (news.likes.contains(it)) news.likes.remove(it) else news.likes.add(it)
                    view?.likedButton(news.likes.contains(it))
                }
            }
            onResponseFailed { _, _ -> view?.responseFailed() }
            onCallFailure { view?.responseFailure() }
            view?.enabledLikedButton(true)
        }
    }

    fun onClickImage() {
        view?.fullScreen(!imageFullscreen)
        imageFullscreen = !imageFullscreen
    }
}