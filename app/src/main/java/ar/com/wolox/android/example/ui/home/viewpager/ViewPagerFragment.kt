package ar.com.wolox.android.example.ui.home.viewpager

import androidx.viewpager.widget.ViewPager
import ar.com.wolox.android.R
import ar.com.wolox.android.databinding.FragmentViewpagerBinding
import ar.com.wolox.android.example.ui.home.news.NewsFragment
import ar.com.wolox.android.example.ui.home.profile.ProfileFragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject

class ViewPagerFragment private constructor() : WolmoFragment<FragmentViewpagerBinding, ViewPagerPresenter>(), ViewPagerView {
    @Inject
    internal lateinit var newsFragment: NewsFragment

    @Inject
    internal lateinit var profileFragment: ProfileFragment

    lateinit var tabLayout: TabLayout

    override fun layout() = R.layout.fragment_viewpager

    override fun init() {
        tabLayout = binding.tabLayout
        binding.pager.adapter = SimpleFragmentPagerAdapter(childFragmentManager).apply {
            addFragments(
                newsFragment to getString(R.string.title_tab_news),
                profileFragment to getString(R.string.title_tab_profile)
            )
        }
        tabLayout.setupWithViewPager(binding.pager)
    }

    override fun setListeners() {
        binding.pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageSelected(position: Int) {
                presenter.onSelectedTab(position)
            }
        })
        presenter.onSelectedTab(0)
    }

    override fun setChangeColorIcon(position: Int) {
        binding.tabLayout.apply {
            when (position) {
                0 -> {
                    getTabAt(0)?.setIcon(R.drawable.ic_news_list_on)
                    getTabAt(1)?.setIcon(R.drawable.ic_profile_off)
                }
                1 -> {
                    getTabAt(0)?.setIcon(R.drawable.ic_news_list_off)
                    getTabAt(1)?.setIcon(R.drawable.ic_profile_on)
                }
            }
        }
    }

    companion object {
        fun newInstance() = ViewPagerFragment()
    }
}