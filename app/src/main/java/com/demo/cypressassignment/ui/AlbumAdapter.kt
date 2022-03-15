package com.demo.cypressassignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.cypressassignment.utils.BothSideScroll
import com.demo.cypressassignment.R
import com.demo.cypressassignment.databinding.RowAlbumListBinding
import com.demo.cypressassignment.model.AlbumDetailDto

class AlbumAdapter :
    ListAdapter<AlbumDetailDto, AlbumAdapter.ViewHolder>(AlbumDiffUtilCallback()) {
    private val viewPool = RecyclerView.RecycledViewPool()

    class ViewHolder(val binding: RowAlbumListBinding) : RecyclerView.ViewHolder(binding.root)

    class AlbumDiffUtilCallback : DiffUtil.ItemCallback<AlbumDetailDto>() {
        override fun areItemsTheSame(oldItem: AlbumDetailDto, newItem: AlbumDetailDto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AlbumDetailDto, newItem: AlbumDetailDto): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_album_list,
            parent,
            false
        ) as RowAlbumListBinding
        return ViewHolder(mBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = getItem(position % currentList.size)

        holder.binding.album = data
        val adapter = PhotoAdapter()
        val layoutManager =
            LinearLayoutManager(holder.itemView.context, LinearLayoutManager.HORIZONTAL, false)
        holder.binding.rvImage.layoutManager = layoutManager
        holder.binding.rvImage.setHasFixedSize(true)

        holder.binding.rvImage.addOnScrollListener(BothSideScroll(layoutManager, data.list?.size!!))

        /*holder.binding.rvImage.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val firstItemVisible: Int = layoutManager.findFirstVisibleItemPosition()
                if (firstItemVisible != 1 && (firstItemVisible % data.list?.size!! == 1)) {
                    layoutManager.scrollToPosition(1)
                } else if (firstItemVisible != 1 && firstItemVisible > data.list?.size!! && (firstItemVisible % data.list?.size!! > 1)
                ) {
                    layoutManager.scrollToPosition(firstItemVisible % data.list?.size!!)
                } else if (firstItemVisible == 0) {
                    layoutManager.scrollToPositionWithOffset(
                        data.list?.size!!, -recyclerView.computeHorizontalScrollOffset()
                    )
                }
            }
        })*/

        holder.binding.rvImage.setRecycledViewPool(viewPool)
        holder.binding.rvImage.adapter = adapter
        adapter.submitList(getItem(position % currentList.size).list!!)

    }

    override fun getItemCount(): Int {
        return this.currentList.size * 2
    }
}