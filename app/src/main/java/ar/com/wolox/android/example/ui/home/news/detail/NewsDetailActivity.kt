package ar.com.wolox.android.example.ui.home.news.detail

import android.content.Context
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityBaseBinding
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import javax.inject.Inject
import ar.com.wolox.wolmo.core.util.jumpTo

class NewsDetailActivity @Inject constructor() : WolmoActivity<ActivityBaseBinding>() {

    override fun layout() = R.layout.activity_base

    override fun init() {
        replaceFragment(
            binding.activityBaseContent.id,
            NewDetailFragment.newInstance(requireArgument(Extras.ArgumentsFragmentDetails.NEWS))
        )
    }

    companion object {

        fun start(context: Context, news: News) = context.jumpTo(
            NewsDetailActivity::class.java,
            Extras.ArgumentsFragmentDetails.NEWS to news
        )
    }
}
