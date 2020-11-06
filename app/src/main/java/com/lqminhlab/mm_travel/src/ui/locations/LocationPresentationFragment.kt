package com.lqminhlab.mm_travel.src.ui.locations

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.lqminhlab.mm_travel.R
import com.lqminhlab.mm_travel.src.adapters.MenuAdapter
import com.lqminhlab.mm_travel.src.extenstions.toBitmap
import com.lqminhlab.mm_travel.src.resource.models.LocationModel
import com.lqminhlab.mm_travel.src.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_location_presentation.*
import kotlinx.android.synthetic.main.loading_normal.*
import kotlinx.coroutines.*
import java.net.URL

class LocationPresentationFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var location: LocationModel
    private lateinit var adapter: MenuAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_presentation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialized()
        obverse()
        listen()
    }

    private fun obverse() {
        homeViewModel.loadingSubject.observe(viewLifecycleOwner, Observer {
            loading_normal.visibility = if (it) View.VISIBLE else View.GONE
        })
        homeViewModel.attractionsSubject.observe(viewLifecycleOwner, Observer {
            val fm: FragmentManager = activity?.supportFragmentManager!!
            adapter = MenuAdapter(
                fm,
                listOf(
                    LocationOverviewFragment(location, it.size, pager_location_presentation),
                    LocationInfoFragment(location, it)
                )
            )
            pager_location_presentation.adapter = adapter
        })
    }

    private fun initialized() {
        val json: String? = arguments?.getString(PARAMS)
        location = Gson().fromJson<LocationModel>(json, LocationModel::class.java)
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val loadBackground: Deferred<Bitmap?> = GlobalScope.async {
            URL(location.photo.images.large?.url ?: location.photo.images.original?.url).toBitmap()
        }
        homeViewModel.setLoading(true)
        GlobalScope.launch(Dispatchers.IO) {
            val bitmap: Bitmap? = loadBackground.await()
            homeViewModel.getAttractionByLocation(location.location_id ?: "")
            withContext(Dispatchers.Main) {
                if (bitmap != null)
                    image_location_presentation_background.setImageBitmap(bitmap)
            }
        }
    }

    private fun listen() {
        btn_back.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    companion object {
        const val PARAMS = "location"
    }
}