package com.lqminhlab.mm_travel.src.ui.attractions_presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lqminhlab.mm_travel.R
import com.lqminhlab.mm_travel.src.resource.models.AttractionModel
import com.lqminhlab.mm_travel.src.resource.models.LocationModel
import kotlinx.android.synthetic.main.fragment_location_info.*
import kotlinx.android.synthetic.main.fragment_location_overview.*
import kotlinx.android.synthetic.main.fragment_location_overview.text_places_location
import kotlinx.android.synthetic.main.fragment_location_overview.text_title_location

class LocationInfoFragment(
    private val location: LocationModel,
    private val attractions: List<AttractionModel>
) : Fragment() {

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

    private fun initialized() {
        text_title_location.text = location.name
        text_country_location.text = location.timezone
        text_des_location.text = location.geo_description
    }
}