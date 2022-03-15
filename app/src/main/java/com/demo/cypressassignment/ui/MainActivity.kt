package com.demo.cypressassignment.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.cypressassignment.R
import com.demo.cypressassignment.databinding.ActivityMainBinding
import com.demo.cypressassignment.utils.BothSideScroll
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewmodel: MainViewModel by viewModels()

    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewmodel = viewmodel
        binding.lifecycleOwner = this
        viewmodel.getPhotos()
        setUI()
    }

    private fun setUI() {
        binding.apply {
            val adapter = AlbumAdapter()

            val layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)

            rvAlbum.setHasFixedSize(true)
            rvAlbum.layoutManager = layoutManager

            val mDividerItemDecoration =
                DividerItemDecoration(rvAlbum.context, layoutManager.orientation)

            rvAlbum.addItemDecoration(mDividerItemDecoration)

            /*rvAlbum.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val firstItemVisible: Int = layoutManager.findFirstVisibleItemPosition()
                    if (firstItemVisible != 1 && (firstItemVisible % adapter.currentList.size == 1)) {
                        layoutManager.scrollToPosition(1)
                    } else if (firstItemVisible != 1 && firstItemVisible > adapter.currentList.size
                        && (firstItemVisible % adapter.currentList.size > 1)
                    ) {
                        layoutManager.scrollToPosition(firstItemVisible % adapter.currentList.size)
                    } else if (firstItemVisible == 0) {
                        layoutManager.scrollToPositionWithOffset(
                            adapter.currentList.size,
                            -recyclerView.computeHorizontalScrollOffset()
                        )
                    }
                }
            })*/

            rvAlbum.adapter = adapter

            this@MainActivity.viewmodel.album.observe(this@MainActivity) { response ->
                adapter.submitList(response)
                binding.rvAlbum.addOnScrollListener(BothSideScroll(layoutManager, adapter.currentList.size))
            }
        }
    }
}