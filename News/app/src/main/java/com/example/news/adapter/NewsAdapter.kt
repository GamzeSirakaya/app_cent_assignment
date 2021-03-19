package com.example.news.adapter
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.news.R
import com.example.news.util.ItemClickListener
import com.example.news.databinding.RecyclerItemBinding
import com.example.news.model.Article
import com.example.news.view.HomeFragment
import com.example.news.view.NewsDetailFragment


class NewsAdapter :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(),ItemClickListener {


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
        holder.view.articles = differ.currentList[position]

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

   /* fun newListUpdate(news: List<Article>) {
        differ.currentList.clear()
        differ.currentList.addAll(news)
        notifyDataSetChanged()

    }*/

    override fun itemClickListener(view: View) {

    Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_newsDetailFragment)
    }
     val differ= AsyncListDiffer(this, diffUtilCallBack)
     companion object {
         private val diffUtilCallBack =
             object  : DiffUtil.ItemCallback<Article>(){
                 override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                     return oldItem.url == newItem.url
                 }
                 override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                     return oldItem==newItem
                 }

             }

     }
}

/*private var OnItemClickListener:((Article)->Unit)?=null
    fun setOnItemClickListener(listener:(Article)->Unit){
        OnItemClickListener=listener
    }
*/
