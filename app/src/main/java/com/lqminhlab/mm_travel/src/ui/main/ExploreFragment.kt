package com.lqminhlab.mm_travel.src.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lqminhlab.mm_travel.R
import com.lqminhlab.mm_travel.src.adapters.ExploreAdapter
import com.lqminhlab.mm_travel.src.viewmodel.ExploreViewModel
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.loading_normal.*

class ExploreFragment : Fragment() {

    private val featuredAdapter: ExploreAdapter by lazy {
        ExploreAdapter(ExploreAdapter.HORIZONTAL) { position, _ ->
            Log.e("EXPLORE", position.toString())
        }
    }
    private val recommendAdapter: ExploreAdapter by lazy {
        ExploreAdapter(ExploreAdapter.VERTICAL) { position, _ ->
            Log.e("EXPLORE", position.toString())
        }
    }
    private val euroAdapter: ExploreAdapter by lazy {
        ExploreAdapter(ExploreAdapter.VERTICAL) { position, _ ->
            Log.e("EXPLORE", position.toString())
        }
    }
    private lateinit var exploreViewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialized()
        obverse()
    }

    private fun obverse() {
        exploreViewModel.loadingSubject.observe(viewLifecycleOwner, {
            loading_normal.visibility = if (it) View.VISIBLE else View.GONE
        })
        exploreViewModel.recommendSubject.observe(viewLifecycleOwner, Observer {
            println("hhhhhhhhhh0: ${it.size}")
            recommendAdapter.setData(it)
        })
        exploreViewModel.euroSubject.observe(viewLifecycleOwner, Observer {
            println("hhhhhhhhhh1: ${it.size}")
            euroAdapter.setData(it)
        })
        exploreViewModel.featureSubject.observe(viewLifecycleOwner, Observer {
            println("hhhhhhhhhh2: ${it.size}")
            featuredAdapter.setData(it)
        })
    }

    private fun initialized() {
        exploreViewModel = ViewModelProviders.of(this).get(ExploreViewModel::class.java)
        recyclerViewExploreRecommend.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
            adapter = recommendAdapter
        }
        recyclerViewExploreInEuro.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
            adapter = euroAdapter
        }
        recyclerViewExploreFeatured.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = featuredAdapter
        }
//        recyclerViewExploreFeatured.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
//        recyclerViewExploreFeatured.apply {
//            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
//            adapter = featuredAdapter
//        }
        exploreViewModel.getAttractions()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ExploreFragment()
    }
}