package com.said.androidartbooktesting.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.said.androidartbooktesting.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject lateinit var fragmentFactory: ArtFragmentFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.fragmentFactory = fragmentFactory
        setContentView(R.layout.activity_main)

        //https://blog.mindorks.com/mvi-architecture-android-tutorial-for-beginners-step-by-step-guide
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupObserver() {
        TODO("Not yet implemented")
    }

    private fun setupViewModel() {
        TODO("Not yet implemented")
    }

    private fun setupUI() {
        TODO("Not yet implemented")
    }
}