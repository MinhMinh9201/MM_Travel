package com.lqminhlab.mm_travel.src.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import com.google.android.material.tabs.TabLayout
import com.lqminhlab.mm_travel.R
import com.lqminhlab.mm_travel.src.adapters.MenuAdapter
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment() {

    private lateinit var adapter: MenuAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val fragments: List<Fragment> = listOf(
            HomeFragment.newInstance(),
            PhotoFragment.newInstance(),
            ExploreFragment.newInstance(),
            ActivityFragment.newInstance(),
            ProfileFragment.newInstance()
        )
        val fm: FragmentManager = activity?.supportFragmentManager!!
        adapter = MenuAdapter(fm, fragments)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listener()
    }

    private fun listener() {
        pager_menu.adapter = adapter
        tab_layout_menu.setupWithViewPager(pager_menu)

        tab_layout_menu.getTabAt(0)!!.setIcon(R.drawable.ic_home_light)
        tab_layout_menu.getTabAt(1)!!.setIcon(R.drawable.ic_photo)
        tab_layout_menu.getTabAt(2)!!.setIcon(R.drawable.ic_explore)
        tab_layout_menu.getTabAt(3)!!.setIcon(R.drawable.ic_activity)
        tab_layout_menu.getTabAt(4)!!.setIcon(R.drawable.ic_profile)

        tab_layout_menu.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(p0: TabLayout.Tab?) {
                    if(p0?.position != 0)tab_layout_menu.setBackgroundColor(activity?.getColor(R.color.colorOnPrimary)!!)
                    else tab_layout_menu.setBackgroundColor(activity?.getColor(android.R.color.transparent)!!)
                    when (p0?.position) {
                        0 -> p0.setIcon(R.drawable.ic_home_light)
                        1 -> p0.setIcon(R.drawable.ic_photo_dark)
                        2 -> p0.setIcon(R.drawable.ic_explore_dark)
                        3 -> p0.setIcon(R.drawable.ic_activity_dark)
                        4 -> p0.setIcon(R.drawable.ic_profile_dark)
                    }
                }

                override fun onTabUnselected(p0: TabLayout.Tab?) {
                    when (p0?.position) {
                        0 -> p0.setIcon(R.drawable.ic_home)
                        1 -> p0.setIcon(R.drawable.ic_photo)
                        2 -> p0.setIcon(R.drawable.ic_explore)
                        3 -> p0.setIcon(R.drawable.ic_activity)
                        4 -> p0.setIcon(R.drawable.ic_profile)
                    }
                }

                override fun onTabReselected(p0: TabLayout.Tab?) {

                }

            }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        clearFindViewByIdCache()
    }
}