package ar.com.wolox.android.example.ui.auth.signup

import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentSignupBinding
import ar.com.wolox.wolmo.core.fragment.WolmoFragment

class SignUpFragment : WolmoFragment<FragmentSignupBinding, SignUpPresenter>(), SignUpView {

    override fun layout() = R.layout.fragment_signup

    override fun init() {}

    companion object {
        fun newInstance() = SignUpFragment()
    }
}
