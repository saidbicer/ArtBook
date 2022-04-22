package com.said.androidartbooktesting.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.said.androidartbooktesting.R
import com.said.androidartbooktesting.ui.adapter.ImageRecyclerAdapter
import javax.inject.Inject

class ImageApiFragment @Inject constructor(private val imgRcyclerAdapter: ImageRecyclerAdapter) :
    Fragment(R.layout.fragment_image_api) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}