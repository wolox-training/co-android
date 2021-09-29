package ar.com.wolox.android.example.ui.home.news.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener() : RecyclerView.OnScrollListener() {
    private var reachedThreshold = 5
    private var loading = true
    private var previousTotal = 0
    private var totalNews = 0
    private var lastNewVisible = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        with((recyclerView.layoutManager as LinearLayoutManager)) {
            lastNewVisible = findLastVisibleItemPosition()
            totalNews = itemCount
        }

        if (loading) {
            if (totalNews > previousTotal) {
                loading = false
                previousTotal = totalNews
            }
        }

        if (!loading && lastNewVisible >= (totalNews - reachedThreshold)) {
            loading = true
            onLoadMoreNews()
        }
    }

    abstract fun onLoadMoreNews()
}
