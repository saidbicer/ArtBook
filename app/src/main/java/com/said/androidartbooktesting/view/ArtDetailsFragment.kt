package com.said.androidartbooktesting.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.said.androidartbooktesting.R
import com.said.androidartbooktesting.databinding.FragmentArtDetailsBinding
import javax.inject.Inject


class ArtDetailsFragment @Inject constructor(private val  glide : RequestManager) : Fragment(R.layout.fragment_art_details) {

    private var fragmentBinding : FragmentArtDetailsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentBinding = FragmentArtDetailsBinding.bind(view)

        fragmentBinding!!.ivArt.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
        }


        //Clic back button action
        val calback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(calback)
    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding = null
    }
}