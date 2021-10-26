package com.haidev.newsapps.ui.screen.article

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.haidev.newsapps.data.model.NewsArticleModel
import com.haidev.newsapps.databinding.ItemNewsArticleRowBinding

class ItemNewsArticleAdapter(
    private val listener: (NewsArticleModel.Response.Article) -> Unit
) :
    RecyclerView.Adapter<ItemNewsArticleAdapter.ViewHolder>() {

    private var list = mutableListOf<NewsArticleModel.Response.Article>()

    fun setData(list: List<NewsArticleModel.Response.Article>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemNewsArticleRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @ExperimentalStdlibApi
        fun bindItem(
            data: NewsArticleModel.Response.Article
        ) {
            binding.item = data
            itemView.setOnClickListener {
                listener(data)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            ItemNewsArticleRowBinding.inflate(
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