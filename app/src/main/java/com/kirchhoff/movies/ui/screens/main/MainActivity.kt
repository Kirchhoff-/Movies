package com.kirchhoff.movies.ui.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.kirchhoff.movies.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createUI()
    }

    private fun createUI() {
        val vpMain: ViewPager = findViewById(R.id.vpMain)
        val navigationView: BottomNavigationView = findViewById(R.id.navigationView)

        vpMain.apply {
            adapter = MainPagerAdapter(supportFragmentManager)
            offscreenPageLimit = 3
            addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
                override fun onPageSelected(position: Int) {
                    navigationView.menu.getItem(position).isChecked = true
                }
            })
        }

        navigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_movies -> vpMain.currentItem = 0
                R.id.item_tv -> vpMain.currentItem = 1
                R.id.item_persons -> vpMain.currentItem = 2
            }
            true
        }
    }
}
