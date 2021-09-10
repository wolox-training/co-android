package ar.com.wolox.android.example.ui.auth

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityBaseBinding
import ar.com.wolox.android.example.ui.auth.login.AuthFragment
import ar.com.wolox.android.example.ui.home.HomeFragment
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import javax.inject.Inject

class RootActivity @Inject constructor() : WolmoActivity<ActivityBaseBinding>() {
    @Inject internal lateinit var userSession: UserSession

    override fun layout() = R.layout.activity_base

    override fun init() {
        userSession.run {
            val initialFragment = when (username.isNullOrEmpty() || password.isNullOrEmpty()) {
                true -> AuthFragment.newInstance()
                false -> HomeFragment . newInstance()
            }

            replaceFragment(binding.activityBaseContent.id, initialFragment)
        }
    }
}