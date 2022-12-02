package cn.jan29th.campusnews.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import cn.jan29th.campusnews.News
import cn.jan29th.campusnews.R

class NewsAdapter(val newsList: List<News>) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var image: ImageView = view.findViewById(R.id.newsImage)
        var title: TextView = view.findViewById(R.id.newsTitle)
        var desc: TextView = view.findViewById(R.id.newsDetails)
        var publishTime: TextView = view.findViewById(R.id.newsTime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]
        holder.image.setImageResource(news.imageId)
        holder.title.text = news.title
        holder.desc.text = news.desc
        holder.publishTime.text = news.publishTime.toString()
    }

    override fun getItemCount() = newsList.size
}

