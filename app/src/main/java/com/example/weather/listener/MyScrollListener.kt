package com.example.weather.listener

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MyScrollListener : RecyclerView.OnScrollListener() {
    private var lastDy = 0
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            val lm = recyclerView.layoutManager as LinearLayoutManager?
            val pos =
                if (lastDy > 0) lm!!.findLastVisibleItemPosition() else lm!!.findFirstVisibleItemPosition()
            recyclerView.smoothScrollToPosition(pos)
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        lastDy = dy
    }
}