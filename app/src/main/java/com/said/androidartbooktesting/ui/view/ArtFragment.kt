package com.said.androidartbooktesting.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.said.androidartbooktesting.R
import com.said.androidartbooktesting.databinding.FragmentArtsBinding

class ArtFragment : Fragment(R.layout.fragment_arts) {

    private var fragmentBinding : FragmentArtsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentBinding = FragmentArtsBinding.bind(view)

        fragmentBinding!!.fab.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding = null
    }
}