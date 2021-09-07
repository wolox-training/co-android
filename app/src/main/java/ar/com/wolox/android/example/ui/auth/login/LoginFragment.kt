package ar.com.wolox.android.example.ui.auth.login

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentLoginBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class LoginFragment private constructor() : WolmoFragment<FragmentLoginBinding, LoginPresenter>(), LoginView {
    override fun layout() = R.layout.fragment_login

    override fun init() {
    }

    override fun setListeners() {
        with(binding) {
            loginButton.setOnClickListener {
                presenter.onLoginButtonClicked()
            }
        }
    }

    companion object {
        fun newInstance() = LoginFragment()
    }
}