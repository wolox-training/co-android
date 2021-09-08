package ar.com.wolox.android.example.ui.auth.login

import android.content.Context
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentLoginBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class AuthFragment : WolmoFragment<FragmentLoginBinding, AuthPresenter>(), AuthView {
    override fun layout() = R.layout.fragment_login

    override fun init() {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val emailUser = sharedPref.getString("emailUser", "")
        val passwordUser = sharedPref.getString("passwordUser", "")
        with(binding) {
            usernameInput.setText(emailUser)
            passwordInput.setText(passwordUser)
        }
    }

    override fun setListeners() {
        with(binding) {
            loginButton.setOnClickListener {
                presenter.onLoginButtonClicked(usernameInput.text.toString(), passwordInput.text.toString())
            }
        }
    }

    override fun setEmailError(msg: String) {
        binding.usernameInput.setError(msg)
    }

    override fun setPasswordError(msg: String) {
        binding.passwordInput.setError(msg)
    }

    override fun setLoginUser(user: String, password: String) {

        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with(sharedPref.edit()) {
            putString("emailUser", user)
            putString("passwordUser", password)
            commit()
        }
    }

    companion object {
        fun newInstance() = AuthFragment()
    }
}