package ar.com.wolox.android.example.ui.home.news.detail

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentNewsDetailBinding
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.utils.toggleEnable
import ar.com.wolox.android.example.utils.abbreviationDayFormat
import ar.com.wolox.android.example.utils.togglePresence
import ar.com.wolox.android.example.utils.glideImage
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import javax.inject.Inject
import android.widget.LinearLayout

class NewDetailFragment @Inject constructor() : WolmoFragment<FragmentNewsDetailBinding, NewsDetailPresenter>(), NewDetailView {

    @Inject internal lateinit var toastFactory: ToastFactory

    override fun layout() = R.layout.fragment_news_detail

    override fun handleArguments(arguments: Bundle?) = arguments?.containsKey(Extras.ArgumentsFragmentDetails.NEWS)

    override fun init() {
        binding.toolbar.setNavigationIcon(R.drawable.ic_back_toolbar)
        presenter.onInit(requireArgument(Extras.ArgumentsFragmentDetails.NEWS) as News)
    }

    override fun setListeners() {
        with(binding) {
            toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
            swipe.setOnRefreshListener { presenter.onRefresh() }
            liked.setOnClickListener { presenter.onClickLiked() }
            coverImage.setOnClickListener { presenter.onClickImage() }
        }
    }

    override fun showInformation(news: News, userId: Int) {
        with(binding) {
            title.text = news.commenter
            description.text = news.comment
            news.updatedAt.abbreviationDayFormat().let {
                date.text = it
            }
            coverImage.glideImage(news.avatar, R.drawable.mock_cover)
            with(binding) {
                liked.apply {
                    setImageDrawable(
                        ContextCompat.getDrawable(
                            liked.context,
                            if (news.likes.contains(userId)) R.drawable.ic_like_on_large else R.drawable.ic_like_off_large
                        )
                    )
                }
            }
        }
    }

    override fun fullScreen(imageFullScreen: Boolean) {
        with(binding) {
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0
            )
            params.weight = if (imageFullScreen) 2f else 0.8f
            coverPanel.setLayoutParams(params)

            toolbar.togglePresence(!imageFullScreen)
            informationPanel.togglePresence(!imageFullScreen)
        }
    }

    override fun enabledLikedButton(enabled: Boolean) = binding.liked.toggleEnable(enabled)
    override fun finishRefreshing() { binding.swipe.isRefreshing = false }
    override fun responseFailed() = toastFactory.show(R.string.default_error)
    override fun responseFailure() = toastFactory.show(R.string.request_without_connection)

    override fun likedButton(likedState: Boolean) {
        toastFactory.show(R.string.message_liked)
        with(binding) {
            liked.apply {
                setImageDrawable(
                    ContextCompat.getDrawable(
                        liked.context,
                        if (likedState) R.drawable.ic_like_on_large else R.drawable.ic_like_off_large
                    )
                )
            }
        }
    }

    companion object {
        fun newInstance(news: News) = NewDetailFragment().apply {
            arguments = bundleOf(Extras.ArgumentsFragmentDetails.NEWS to news)
        }
    }
}

interface NewDetailView {

    fun showInformation(news: News, userId: Int)
    fun finishRefreshing()
    fun fullScreen(imageFullScreen: Boolean)
    fun enabledLikedButton(enabled: Boolean)
    fun likedButton(likedState: Boolean)
    fun responseFailed()
    fun responseFailure()
}