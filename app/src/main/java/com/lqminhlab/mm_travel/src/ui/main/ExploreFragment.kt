package com.lqminhlab.mm_travel.src.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.lqminhlab.mm_travel.R
import com.lqminhlab.mm_travel.src.adapters.ExploreAdapter
import com.lqminhlab.mm_travel.src.resource.models.LocationModel
import com.lqminhlab.mm_travel.src.ui.locations.LocationPresentationFragment
import com.lqminhlab.mm_travel.src.viewmodel.ExploreViewModel
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.android.synthetic.main.loading_normal.*

class ExploreFragment : Fragment() {

    private val featuredAdapter: ExploreAdapter by lazy {
        ExploreAdapter(ExploreAdapter.HORIZONTAL) { _, locationModel ->
            openLocationPresent(location= locationModel)
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

    private fun openLocationPresent(location : LocationModel) {
        val bundle = bundleOf(LocationPresentationFragment.PARAMS to Gson().toJson(location))
        findNavController().navigate(R.id.action_menuFragment_to_locationPresentionFragment, bundle)
    }

    private fun obverse() {
        exploreViewModel.loadingSubject.observe(viewLifecycleOwner, {
            loading_normal.visibility = if (it) View.VISIBLE else View.GONE
        })
        exploreViewModel.recommendSubject.observe(viewLifecycleOwner, Observer {
            recommendAdapter.setData(it)
        })
        exploreViewModel.euroSubject.observe(viewLifecycleOwner, Observer {
            euroAdapter.setData(it)
        })
        exploreViewModel.featureSubject.observe(viewLifecycleOwner, Observer {
            featuredAdapter.setData(it)
        })
    }

    private fun initialized() {
        exploreViewModel = ViewModelProviders.of(this).get(ExploreViewModel::class.java)
        recyclerViewExploreRecommend.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = recommendAdapter
        }
        recyclerViewExploreInEuro.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = euroAdapter
        }
        recyclerViewExploreFeatured.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            adapter = featuredAdapter
        }
        exploreViewModel.getLocations()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ExploreFragment()
    }
}