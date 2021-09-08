package ar.com.wolox.android.example.ui.auth

import ar.com.wolox.android.example.ui.auth.login.AuthFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthModule {

    @ContributesAndroidInjector
    internal abstract fun authActivity(): AuthActivity

    @ContributesAndroidInjector
    internal abstract fun authFragment(): AuthFragment
}