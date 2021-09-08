package ar.com.wolox.android.example.ui.auth

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityBaseBinding
import ar.com.wolox.android.example.ui.auth.login.AuthFragment
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import javax.inject.Inject

class AuthActivity @Inject constructor() : WolmoActivity<ActivityBaseBinding>() {
    override fun layout() = R.layout.activity_base

    override fun init() {
        replaceFragment(binding.activityBaseContent.id, AuthFragment.newInstance())
    }
}