package com.example.madcamp_first

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.example.madcamp_first.ui.main.SectionsPagerAdapter

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

    }
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val root = inflater.inflate(R.layout.activity_main, container, false)
//        for (int i = 0; i < tabLayout.getTabCount(); i++) {
//            //noinspection ConstantConditions
//            TextView tv=(TextView)LayoutInflater.from(this).inflate(R.layout.custom_tab,null)
//            tv.setTextColor(customColor)
//            tabLayout.getTabAt(i).setCustomView(tv);
//
//        }
//    }
}