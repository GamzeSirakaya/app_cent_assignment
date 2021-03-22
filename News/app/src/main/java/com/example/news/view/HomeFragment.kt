package com.example.news.view


import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.adapter.NewsAdapter
import com.example.news.model.Article
import com.example.news.model.NewResponse
import com.example.news.util.searchQuery
import com.example.news.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.recycler_item.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(),SearchView.OnQueryTextListener {
    private lateinit var viewModel: HomeViewModel
    private val newsAdapter = NewsAdapter()
    private lateinit var responseList: MutableList<Article>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)

            }

            findNavController().navigate(
                R.id.action_homeFragment_to_newsDetailFragment,
                bundle
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        //viewModel.refreshData()
        responseList = mutableListOf()
        setupRecyclerView()
        observeLiveData()

    }

    fun observeLiveData() {
        viewModel.newResponse.observe(viewLifecycleOwner, Observer { newResponse ->
            newResponse?.let {
                newsAdapter.differ.submitList(newResponse)
               responseList.addAll(it)

            }

        })
        viewModel.newsError.observe(viewLifecycleOwner, Observer { newsError ->
            newsError?.let {
                if (it) {
                    noResult.visibility = View.VISIBLE
                } else {
                    noResult.visibility = View.GONE

                }
            }

        })
        viewModel.newsLoading.observe(viewLifecycleOwner, Observer { newsLoading ->
            newsLoading?.let {
                if (it) {
                    news_recycler_view.visibility = View.GONE
                    noResult.visibility = View.GONE
                    progress.visibility = View.VISIBLE
                } else {
                    progress.visibility = View.GONE



                }
            }

        })

    }

    private fun setupRecyclerView() {

        news_recycler_view.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

   /* override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
           inflater.inflate(R.menu.search, menu)
           val menuItem = menu.findItem(R.id.action_search)
          if(menuItem!=null){
           val searchView = menuItem.actionView as SearchView
           searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
               override fun onQueryTextSubmit(query: String?): Boolean {
                   return true
               }

               override fun onQueryTextChange(newText: String?): Boolean {
                   if (newText!!.isNotEmpty()) {
                       responseList.clear()
                       val search = newText.toLowerCase(Locale.getDefault())

                      viewModel.newResponse.observe(viewLifecycleOwner, Observer { newresponse ->
                           newresponse?.let {
                               if (newresponse.contains(search) == true) {
                                   responseList.addAll(newresponse)
                                   newsAdapter.differ.submitList(newresponse)
                               }
                           }
                       })

                   }
                   else{
                       responseList.clear()
                       responseList.addAll(responseList)
                       newsAdapter.differ.submitList(responseList)
                   }
                   return true
               }

           })
          }
           super.onCreateOptionsMenu(menu, inflater)
       }
*/
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)
        val menuItem = menu.findItem(R.id.action_search)
        val searchView = menuItem.actionView as SearchView
        searchView.setOnQueryTextListener(this)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        onQueryTextChange(query)
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newsAdapter.differ.submitList(newText?.searchQuery(responseList))
        return true
    }
}










