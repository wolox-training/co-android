package ar.com.wolox.android.example.ui.auth.login

import ar.com.wolox.android.example.BaseConfiguration.Companion.HEADER_CLIENT
import ar.com.wolox.android.example.BaseConfiguration.Companion.HEADER_TOKEN
import ar.com.wolox.android.example.BaseConfiguration.Companion.HEADER_UID
import ar.com.wolox.android.example.model.LoginData
import ar.com.wolox.android.example.model.TokenInfo
import ar.com.wolox.android.example.network.builder.networkRequest
import ar.com.wolox.android.example.network.repository.LoginRepository
import ar.com.wolox.android.example.utils.UserSession
import ar.com.wolox.wolmo.core.presenter.CoroutineBasePresenter
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthPresenter @Inject constructor(
    private val loginRepository: LoginRepository,
    private val userSession: UserSession
) : CoroutineBasePresenter<AuthView>() {

    fun onLoginButtonClicked(email: String, password: String) = launch {
        val list: MutableList<LoginFormErrors> = mutableListOf()
        val validEmail = androidx.core.util.PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()

        list.run {
            if (!validEmail) add(LoginFormErrors.INVALID_EMAIL)
            if (password.isEmpty()) add(LoginFormErrors.EMPTY_PASSWORD)
            if (email.isEmpty()) add(LoginFormErrors.EMPTY_EMAIL)

            if (isNotEmpty()) {
                view?.setErrors(list)
                return@launch
            }
        }

        view?.showLoader(true)
        networkRequest(loginRepository.loginUser(LoginData(email, password))) {
            onResponseSuccessful { response, headers ->
                userSession.apply {
                    user = response?.data
                    username = email
                    this.password = password
                    tokenInfo = TokenInfo(
                        headers?.get(HEADER_CLIENT),
                        headers?.get(HEADER_TOKEN),
                        headers?.get(HEADER_UID)
                    )
                }

                view?.setLoginUser()
            }
            onResponseFailed { _, _ -> view?.showErrorLogin(ResponseStatus.ERROR_CREDENTIALS) }
            onCallFailure { view?.showErrorLogin(ResponseStatus.DEFAULT_ERROR) }
        }
        view?.showLoader(false)
    }

    fun onSignUpButtonClicked() = view?.goToSignUp()

    fun onLinkClicked() = view?.openBrowser(URL)

    companion object {
        private const val URL = "www.wolox.com.ar"
    }
}

enum class ResponseStatus {
    ERROR_CREDENTIALS,
    DEFAULT_ERROR,
    WITHOUT_CONNECTION
}
