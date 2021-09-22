package ar.com.wolox.android.example.ui.home.viewpager

import ar.com.wolox.android.example.model.TabsData
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class ViewPagerPresenter @Inject constructor() : BasePresenter<ViewPagerView>() {

    fun onSelectedTab(position: Int) {
        val list = listOf(
            TabsData(Tabs.NEWS.id, if (position == 0) Tabs.NEWS.iconOn else Tabs.NEWS.iconOff),
            TabsData(Tabs.PROFILE.id, if (position == 1) Tabs.PROFILE.iconOn else Tabs.PROFILE.iconOff)
        )

        view?.setChangeColorIcon(list)
    }
}