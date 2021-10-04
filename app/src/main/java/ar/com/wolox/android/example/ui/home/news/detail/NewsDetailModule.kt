package ar.com.wolox.android.example.ui.home.news.detail

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class NewsDetailModule {

    @ContributesAndroidInjector
    internal abstract fun newDetailActivity(): NewsDetailActivity

    @ContributesAndroidInjector
    internal abstract fun newDetailFragment(): NewDetailFragment
}