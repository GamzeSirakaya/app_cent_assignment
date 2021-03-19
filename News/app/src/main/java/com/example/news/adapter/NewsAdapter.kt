package com.example.news.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.databinding.RecyclerItemBinding
import com.example.news.model.Article


class NewsAdapter(val newList: ArrayList<Article>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {


    class NewsViewHolder(var view: RecyclerItemBinding) : RecyclerView.ViewHolder(view.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<RecyclerItemBinding>(
            inflater,
            R.layout.recycler_item,
            parent,
            false
        )
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.view.articles = newList[position]

    }

    override fun getItemCount(): Int {
        return newList.size
    }

    fun newListUpdate(news: List<Article>) {
        newList.clear()
        newList.addAll(news)
        notifyDataSetChanged()

    }

}
   /* companion object {
        private val diffUtilCallBack =
            object  : DiffUtil.ItemCallback<Article>(){
                override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                    return oldItem.url == newItem.url
                }
                override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                    return oldItem==newItem
                }

            }

    }*/
/*private var OnItemClickListener:((Article)->Unit)?=null
    fun setOnItemClickListener(listener:(Article)->Unit){
        OnItemClickListener=listener
    }
*/
