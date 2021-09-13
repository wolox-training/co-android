package ar.com.wolox.android.example.ui.auth.signup

import android.content.Context
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityBaseBinding
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import ar.com.wolox.wolmo.core.util.jumpTo
import javax.inject.Inject

class SignUpActivity @Inject constructor() : WolmoActivity<ActivityBaseBinding>() {
    override fun layout() = R.layout.activity_base

    override fun init() {
        replaceFragment(binding.activityBaseContent.id, SignUpFragment.newInstance())
    }

    companion object {
        fun start(context: Context) = context.jumpTo(SignUpActivity::class.java)
    }
}
