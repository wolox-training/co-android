package ar.com.wolox.android.example.ui.auth

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class RootModule {

    @ContributesAndroidInjector
    internal abstract fun rootActivity(): RootActivity
}