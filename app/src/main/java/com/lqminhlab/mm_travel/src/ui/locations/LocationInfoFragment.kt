package com.lqminhlab.mm_travel.src.ui.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lqminhlab.mm_travel.R
import com.lqminhlab.mm_travel.src.adapters.ExploreAdapter
import com.lqminhlab.mm_travel.src.resource.models.AttractionModel
import com.lqminhlab.mm_travel.src.resource.models.LocationModel
import kotlinx.android.synthetic.main.fragment_location_info.*
import kotlinx.android.synthetic.main.fragment_location_overview.text_title_location

class LocationInfoFragment(
    private val location: LocationModel,
    private val attractions: List<AttractionModel>
) : Fragment() {

    private val adapterPlaces = ExploreAdapter(ExploreAdapter.VERTICAL) { position, place ->
        onPlaceSelected(
            position,
            place
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialized()
    }

    private fun onPlaceSelected(position: Int, location: LocationModel) {

    }

    private fun initialized() {
        adapterPlaces.setData(attractions.map { it.toLocation() })
        adapterPlaces.isLarge = false
        adapterPlaces.height =
            activity?.resources?.getDimension(R.dimen.height_places_to_visit)?.toInt()
        adapterPlaces.width =
            (activity?.resources!!.getDimension(R.dimen.height_places_to_visit)*3/4).toInt()
        recyclerViewPlacesToVisit.apply {
            layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            adapter = adapterPlaces
        }
        text_title_location.text = location.name
        text_country_location.text = location.timezone
        text_des_location.text = location.geo_description
    }
}