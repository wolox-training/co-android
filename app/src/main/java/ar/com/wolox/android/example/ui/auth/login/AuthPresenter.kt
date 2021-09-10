package ar.com.wolox.android.example.ui.auth.login

import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class AuthPresenter @Inject constructor(private val userSession: UserSession) : BasePresenter<AuthView>() {

    fun onLoginButtonClicked(email: String, passwordForm: String) {
        val list: MutableList<LoginFormErrors> = mutableListOf()
        val validEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()

        list.run {
            if (!validEmail) add(LoginFormErrors.INVALID_EMAIL)
            if (passwordForm.isEmpty()) add(LoginFormErrors.EMPTY_PASSWORD)
            if (email.isEmpty()) add(LoginFormErrors.EMPTY_EMAIL)

            if (isNotEmpty()) {
                view?.setErrors(list)
                return
            }
        }

        userSession.run {
            username = email
            password = passwordForm
        }
        view?.setLoginUser()
    }
}
