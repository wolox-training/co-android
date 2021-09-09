package ar.com.wolox.android.example.ui.auth.login

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentLoginBinding
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import javax.inject.Inject

class AuthFragment @Inject constructor() : WolmoFragment<FragmentLoginBinding, AuthPresenter>(), AuthView {
    @Inject internal lateinit var toastFactory: ToastFactory
    @Inject internal lateinit var userSession: UserSession

    override fun layout() = R.layout.fragment_login

    override fun init() {
        with(binding) {
            userSession.run {
                usernameInput.setText(username)
                passwordInput.setText(password)
            }
        }
    }

    override fun setListeners() {
        with(binding) {
            loginButton.setOnClickListener {
                presenter.onLoginButtonClicked(usernameInput.text.toString(), passwordInput.text.toString())
            }
        }
    }

    override fun setErrors(list: List<LoginFormErrors>) {
        with(binding) {
            list.forEach() {
                if (it === LoginFormErrors.EMPTY_PASSWORD) passwordInput.setError(LoginFormErrors.EMPTY_PASSWORD.msg)
                if (it === LoginFormErrors.INVALID_EMAIL) usernameInput.setError(LoginFormErrors.INVALID_EMAIL.msg)
                if (it === LoginFormErrors.EMPTY_EMAIL) usernameInput.setError(LoginFormErrors.EMPTY_EMAIL.msg)
            }
        }
    }

    override fun setLoginUser() = toastFactory.show(R.string.successful_login)

    companion object {
        fun newInstance() = AuthFragment()
    }
}

enum class LoginFormErrors(val msg: String) {
    EMPTY_PASSWORD("This field is required"),
    EMPTY_EMAIL("This field is required"),
    INVALID_EMAIL("The email format is not valid")
}
