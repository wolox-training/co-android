package ar.com.wolox.android.example.ui.auth.login

interface AuthView {
    fun setEmailError(msg: String)

    fun setPasswordError(msg: String)

    fun setLoginUser(user: String, password: String)
}