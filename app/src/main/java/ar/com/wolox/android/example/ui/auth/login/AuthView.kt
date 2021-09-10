package ar.com.wolox.android.example.ui.auth.login

interface AuthView {
    fun setErrors(list: List<LoginFormErrors>)

    fun setLoginUser()

    fun goToSignUp()

    fun openBrowser(url: String)
}
