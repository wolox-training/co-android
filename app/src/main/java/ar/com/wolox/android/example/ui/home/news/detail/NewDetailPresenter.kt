package ar.com.wolox.android.example.ui.home.news.detail

import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.NewsRepository
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewDetailPresenter @Inject constructor(private val newsRepository: NewsRepository, private val userSession: UserSession) : CoroutineBasePresenter<NewDetailView>() {

    private lateinit var new: News
    private var imageFullscreen = false

    fun onInit(new: News) {
        this.new = new
        userSession.user?.id?.let {
            view?.showInformation(new, it)
        }
    }

    fun onRefresh() = launch {
        networkRequest(newsRepository.getInformationNew(new.id)) {
            onResponseSuccessful { response, _ ->
                response?.let {
                    new.update(it)
                }
                userSession.user?.id?.let {
                    view?.showInformation(new, it)
                }
            }
            onResponseFailed { _, _ -> }
            onCallFailure { }
            view?.finishRefreshing()
        }
    }

    fun onClickLiked() = launch {
        view?.enabledLikedButton(false)
        networkRequest(newsRepository.toggleLiked(new.id)) {
            onResponseSuccessful { _, _ ->
                userSession.user?.id?.let {
                    if (new.likes.contains(it)) {
                        new.likes.remove(it)
                    } else {
                        new.likes.add(it)
                    }
                    view?.likedButton(new.likes.contains(it))
                }
            }
            onResponseFailed { _, _ -> view?.responseFailed() }
            onCallFailure { view?.responseFailure() }
            view?.enabledLikedButton(true)
        }
    }

    fun onClickImage() {
        view?.fullsScreen(!imageFullscreen)
        imageFullscreen = !imageFullscreen
    }
}