package com.lqminhlab.mm_travel.src.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MenuAdapter(fm: FragmentManager, fragments: List<Fragment>) : FragmentPagerAdapter(fm) {

    private var fragments: List<Fragment> = fragments

    fun refresh(newFragments: List<Fragment>) {
        this.fragments = newFragments
        notifyDataSetChanged()
    }

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment =
        if (position < fragments.size) fragments[position] else fragments.first()
}