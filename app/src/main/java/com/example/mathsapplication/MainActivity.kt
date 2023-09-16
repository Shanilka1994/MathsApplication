package com.example.mathsapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(AddFragment(),"Add")
        fragmentAdapter.addFragment(SubtractionFragment(),"Subtract")
        fragmentAdapter.addFragment(MultiplicationFragment(),"Multiply")
        fragmentAdapter.addFragment(DIvisionFragment(),"Divide")

        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)

    }
}

