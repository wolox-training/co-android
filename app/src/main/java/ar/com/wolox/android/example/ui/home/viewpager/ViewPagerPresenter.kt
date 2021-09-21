package ar.com.wolox.android.example.ui.home.viewpager

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import javax.inject.Inject

class ViewPagerPresenter @Inject constructor() : BasePresenter<ViewPagerView>() {

    fun onSelectedTab(position: Int) = view?.setChangeColorIcon(position)
}