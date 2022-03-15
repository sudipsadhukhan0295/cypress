package com.demo.cypressassignment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.demo.cypressassignment.R
import com.demo.cypressassignment.databinding.RowPhotosBinding
import com.demo.cypressassignment.model.PhotoDetail

class PhotoAdapter : ListAdapter<PhotoDetail, PhotoAdapter.ViewHolder>(PhotoDiffUtilCallback()) {

    class PhotoDiffUtilCallback : DiffUtil.ItemCallback<PhotoDetail>() {
        override fun areItemsTheSame(oldItem: PhotoDetail, newItem: PhotoDetail): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotoDetail, newItem: PhotoDetail): Boolean {
            return oldItem == newItem
        }
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = DataBindingUtil.bind<RowPhotosBinding>(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val mBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.row_photos,
            parent,
            false
        ) as RowPhotosBinding

        return ViewHolder(mBinding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding?.photoDetail = getItem(position % currentList.size)

    }

    override fun getItemCount(): Int {
        return this.currentList.size * 2
    }
}