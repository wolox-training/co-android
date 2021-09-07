package ar.com.wolox.android.example.ui.auth

import ar.com.wolox.android.example.ui.auth.login.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthActivityModule {

    @ContributesAndroidInjector
    internal abstract fun authActivity(): AuthActivity

    @ContributesAndroidInjector
    internal abstract fun loginFragment(): LoginFragment
}