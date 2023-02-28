package com.kirchhoff.movies.ui.screens.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kirchhoff.movies.R
import com.kirchhoff.movies.core.ui.utils.viewBinding
import com.kirchhoff.movies.databinding.ActivityWithFragmentBinding

class MainActivity : AppCompatActivity() {

    private val viewBinding by viewBinding(ActivityWithFragmentBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        createUI()
    }

    private fun createUI() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, MainFragment.newInstance())
            .commit()
    }
}
