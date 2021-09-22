package ar.com.wolox.android.example.ui.home

import ar.com.wolox.android.example.ui.home.news.NewsFragment
import ar.com.wolox.android.example.ui.home.profile.ProfileFragment
import ar.com.wolox.android.example.ui.home.viewpager.ViewPagerFragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap

@Module(subcomponents = [(HomeActivitySubcomponent::class)])
abstract class HomeModule {

    @Binds
    @IntoMap
    @ClassKey(HomeActivity::class)
    internal abstract fun bindHomeActivityFactory(
        builder: HomeActivitySubcomponent.Builder
    ): AndroidInjector.Factory<*>

    @ContributesAndroidInjector
    internal abstract fun viewPagerFragment(): ViewPagerFragment

    @ContributesAndroidInjector
    internal abstract fun newsFragment(): NewsFragment

    @ContributesAndroidInjector
    internal abstract fun profileFragment(): ProfileFragment
}
