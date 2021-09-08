package ar.com.wolox.android.example.ui.auth.login

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.core.util.ToastFactory
import javax.inject.Inject

class AuthPresenter @Inject constructor(private val toastFactory: ToastFactory) : BasePresenter<AuthView>() {

    fun onLoginButtonClicked(email: String, password: String) {
        if (password.isEmpty()) view?.setPasswordError("This field is required")
        if (email.isEmpty()) {
            view?.setEmailError("This field is required")
            return
        }

        val validEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (!validEmail) {
            view?.setEmailError("The email format is not valid")
            return
        }

        toastFactory.show("Successful login.")
        view?.setLoginUser(email, password)
    }
}