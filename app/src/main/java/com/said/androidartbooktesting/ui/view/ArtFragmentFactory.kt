package com.said.androidartbooktesting.ui.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.said.androidartbooktesting.ui.adapter.ArtRecyclerAdapter
import com.said.androidartbooktesting.ui.adapter.ImageRecyclerAdapter
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val imageRecyclerAdapter: ImageRecyclerAdapter,
    private val glide : RequestManager,
    private val artRecyclerAdapter: ArtRecyclerAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            ImageApiFragment::class.java.name -> ImageApiFragment(imageRecyclerAdapter)
            ArtDetailsFragment::class.java.name -> ArtDetailsFragment(glide)
            ArtFragment::class.java.name -> ArtFragment(artRecyclerAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }
}