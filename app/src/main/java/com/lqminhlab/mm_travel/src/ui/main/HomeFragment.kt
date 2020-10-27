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
import com.lqminhlab.mm_travel.src.adapters.CarouselAdapter
import com.lqminhlab.mm_travel.src.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.loading_normal.*

class HomeFragment : Fragment() {

    private val adapterCarousel by lazy {
        CarouselAdapter { position, _ ->
            Log.e("HOMEEE", position.toString())
        }
    }
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialized()
        obverse()
    }

    private fun initialized() {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        carousel_home.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        carousel_home.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
            adapter = adapterCarousel
        }
        homeViewModel.getCountries()
    }

    private fun obverse() {
        homeViewModel.loadingSubject.observe(viewLifecycleOwner, Observer {
            loading_normal.visibility = if (it) View.VISIBLE else View.GONE
        })
        homeViewModel.countriesSubject.observe(viewLifecycleOwner, Observer {
            println("searchMultipleLocation: ${it.size}")
            adapterCarousel.setData(it)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            HomeFragment()
    }
}