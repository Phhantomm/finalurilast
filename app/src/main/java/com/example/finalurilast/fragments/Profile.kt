package com.example.finalurilast.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.finalurilast.R
import com.example.finalurilast.adapter.ViewPagerFragmentAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class Profile:Fragment(R.layout.profile_layout) {
    private lateinit var viewPager2: ViewPager2
    private lateinit var tabLayout: TabLayout
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabTitle = arrayOf("Page 1","Page 2")
        viewPager2 = view.findViewById(R.id.viewPager2)
        tabLayout = view.findViewById(R.id.tabLayout)
        val viewpager = viewPager2
        val layout = tabLayout
        viewpager.adapter = ViewPagerFragmentAdapter(childFragmentManager,lifecycle)
        TabLayoutMediator(layout,viewpager) { tab, position ->
            tab.text = tabTitle[position]
        }

    }
}