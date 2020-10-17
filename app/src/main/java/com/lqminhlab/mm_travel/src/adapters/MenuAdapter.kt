package com.lqminhlab.mm_travel.src.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MenuAdapter(fm: FragmentManager, fragments : List<Fragment>) : FragmentPagerAdapter(fm){

    private val fragmens : List<Fragment> = fragments

    override fun getCount(): Int = fragmens.size

    override fun getItem(position: Int): Fragment = if(position < fragmens.size) fragmens[position] else fragmens.first()
}