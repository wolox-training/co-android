package ar.com.wolox.android.example.ui.auth.login

import androidx.core.view.isVisible
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentLoginBinding
import ar.com.wolox.android.example.ui.auth.signup.SignUpActivity
import ar.com.wolox.android.example.ui.home.HomeActivity
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import ar.com.wolox.wolmo.core.util.openBrowser
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
            signupButton.setOnClickListener { presenter.onSignUpButtonClicked() }
            woloxLink.setOnClickListener { presenter.onLinkClicked() }
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

    override fun setLoginUser() {
        toastFactory.show(R.string.successful_login)
        HomeActivity.start(requireContext())
    }

    override fun goToSignUp() = SignUpActivity.start(requireContext())

    override fun openBrowser(url: String) = requireContext().openBrowser(url)

    override fun showErrorLogin(status: ResponseStatus) {
        when (status) {
            ResponseStatus.ERROR_CREDENTIALS -> toastFactory.show(R.string.request_error_login)
            else -> toastFactory.show(R.string.default_error)
        }
    }

    override fun showLoader(visible: Boolean) { binding.loaderLogin.isVisible = visible }

    companion object {
        fun newInstance() = AuthFragment()
    }
}

enum class LoginFormErrors(val msg: String) {
    EMPTY_PASSWORD("This field is required"),
    EMPTY_EMAIL("This field is required"),
    INVALID_EMAIL("The email format is not valid")
}
