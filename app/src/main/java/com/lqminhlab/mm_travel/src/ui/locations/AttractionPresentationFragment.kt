package com.lqminhlab.mm_travel.src.ui.locations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lqminhlab.mm_travel.R
import com.lqminhlab.mm_travel.src.resource.models.AttractionModel

class AttractionPresentationFragment(val attraction : AttractionModel) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_attraction_presentation, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initialized()
    }

    private fun initialized() {

    }
}