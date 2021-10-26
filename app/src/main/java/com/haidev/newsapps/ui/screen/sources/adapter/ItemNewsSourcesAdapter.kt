package com.haidev.newsapps.ui.screen.sources.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haidev.newsapps.data.model.NewsSourcesModel
import com.haidev.newsapps.databinding.ItemNewsSourcesRowBinding

class ItemNewsSourcesAdapter(
    private val listener: (NewsSourcesModel.Response.Source) -> Unit
) :
    RecyclerView.Adapter<ItemNewsSourcesAdapter.ViewHolder>() {

    private var list = mutableListOf<NewsSourcesModel.Response.Source>()

    fun setData(list: List<NewsSourcesModel.Response.Source>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemNewsSourcesRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @ExperimentalStdlibApi
        fun bindItem(
            data: NewsSourcesModel.Response.Source
        ) {
            binding.item = data
            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemNewsSourcesRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    @ExperimentalStdlibApi
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (list.isNotEmpty()) {
            holder.bindItem(list[position])
        }
    }

    override fun getItemCount() = list.size
}