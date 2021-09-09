package ar.com.wolox.android.example.ui.auth.login

import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class AuthPresenter @Inject constructor(private val userSession: UserSession) : BasePresenter<AuthView>() {

    fun onLoginButtonClicked(email: String, password: String) {
        val list: MutableList<LoginFormErrors> = mutableListOf()
        val validEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (!validEmail) list.add(LoginFormErrors.INVALID_EMAIL)
        if (password.isEmpty()) list.add(LoginFormErrors.EMPTY_PASSWORD)
        if (email.isEmpty()) list.add(LoginFormErrors.EMPTY_EMAIL)

        if (list.isNotEmpty()) {
            view?.setErrors(list)
            return
        }

        userSession.username = email
        userSession.password = password
        view?.setLoginUser()
    }
}
