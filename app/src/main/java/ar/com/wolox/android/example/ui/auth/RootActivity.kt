package ar.com.wolox.android.example.ui.auth

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.ActivityBaseBinding
import ar.com.wolox.android.example.ui.auth.login.AuthActivity
import ar.com.wolox.android.example.ui.home.HomeActivity
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.activity.WolmoActivity
import ar.com.wolox.wolmo.core.util.jumpToClearingTask
import javax.inject.Inject

class RootActivity @Inject constructor() : WolmoActivity<ActivityBaseBinding>() {
    @Inject internal lateinit var userSession: UserSession

    override fun layout() = R.layout.activity_base

    override fun init() {
        val initialActivity = userSession.run {
            when (username.isNullOrEmpty() || password.isNullOrEmpty()) {
                true -> AuthActivity::class.java
                false -> HomeActivity::class.java
            }
        }

        jumpToClearingTask(initialActivity)
    }
}