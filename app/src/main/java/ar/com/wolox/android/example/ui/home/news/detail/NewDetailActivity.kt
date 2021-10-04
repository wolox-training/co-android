package ar.com.wolox.android.example.ui.home.news.detail

import android.content.Context
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityBaseBinding
import ar.com.wolox.android.example.model.News
import ar.com.wolox.android.example.utils.Extras
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import ar.com.wolox.wolmo.core.util.jumpTo
import javax.inject.Inject

class NewDetailActivity @Inject constructor() : WolmoActivity<ActivityBaseBinding>() {

    override fun layout() = R.layout.activity_base

    override fun init() {
        replaceFragment(
            binding.activityBaseContent.id,
            NewDetailFragment.newInstance(requireArgument(Extras.ArgumentsFragmentDetails.NEW))
        )
    }

    companion object {

        fun start(context: Context, new: News) = context.jumpTo(
            NewDetailActivity::class.java,
            Extras.ArgumentsFragmentDetails.NEW to new
        )
    }
}
