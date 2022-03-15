package com.demo.cypressassignment.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BothSideScroll(
    private val layoutManager: LinearLayoutManager, private val data: Int
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val firstItemVisible: Int = layoutManager.findFirstVisibleItemPosition()
        if (firstItemVisible != 1 && (firstItemVisible % data == 1)) {
            layoutManager.scrollToPosition(1)
        } else if (firstItemVisible != 1 && firstItemVisible > data && (firstItemVisible % data > 1)
        ) {
            layoutManager.scrollToPosition(firstItemVisible % data)
        } else if (firstItemVisible == 0) {
            layoutManager.scrollToPositionWithOffset(
                data, -recyclerView.computeHorizontalScrollOffset()
            )
        }
    }
}