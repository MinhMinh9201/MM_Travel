package com.lqminhlab.mm_travel.src.ui.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.lqminhlab.mm_travel.R
import com.lqminhlab.mm_travel.src.resource.models.LocationModel
import kotlinx.android.synthetic.main.fragment_location_overview.*

class LocationOverviewFragment(
    private val location: LocationModel,
    private val places: Int?,
    private val pager: ViewPager?
) :
    Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location_overview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialized()
        listen()
    }

    private fun listen() {

    }

    private fun initialized() {
        text_title_location.text = location.name
        text_places_location.text = if (places != null) "$places places" else ""
        btn_scroll_to_detail.setOnClickListener {
            pager?.currentItem = 1
        }
    }
}