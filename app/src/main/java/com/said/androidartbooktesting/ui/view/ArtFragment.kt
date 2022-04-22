package com.said.androidartbooktesting.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.said.androidartbooktesting.R
import com.said.androidartbooktesting.databinding.FragmentArtsBinding
import com.said.androidartbooktesting.ui.adapter.ArtRecyclerAdapter
import com.said.androidartbooktesting.ui.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtFragment @Inject constructor(
    private val artRecyclerAdapter: ArtRecyclerAdapter
) : Fragment(R.layout.fragment_arts) {

    private var fragmentBinding: FragmentArtsBinding? = null
    lateinit var viewModel: ArtViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)
        fragmentBinding = FragmentArtsBinding.bind(view)

        subscribleObservers()

        fragmentBinding?.let {
            it.recylerViewArt.adapter = artRecyclerAdapter
            it.recylerViewArt.layoutManager = LinearLayoutManager(context)

            ItemTouchHelper(swipeCallBack).attachToRecyclerView(it.recylerViewArt)

            it.fab.setOnClickListener {
                findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
            }
        }
    }

    private fun subscribleObservers() {
        viewModel.artList.observe(viewLifecycleOwner, Observer {
            artRecyclerAdapter.arts = it
        })
    }

    private val swipeCallBack =
        object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val layoutPosition = viewHolder.layoutPosition
                val selectedArt = artRecyclerAdapter.arts[layoutPosition]
                viewModel.deleteArt(selectedArt)
            }

        }

    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding = null
    }
}