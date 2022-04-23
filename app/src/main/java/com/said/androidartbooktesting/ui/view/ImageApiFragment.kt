package com.said.androidartbooktesting.ui.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.said.androidartbooktesting.R
import com.said.androidartbooktesting.databinding.FragmentImageApiBinding
import com.said.androidartbooktesting.ui.adapter.ImageRecyclerAdapter
import com.said.androidartbooktesting.ui.viewmodel.ArtViewModel
import com.said.androidartbooktesting.util.Status
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageApiFragment @Inject constructor(private val imgRcyclerAdapter: ImageRecyclerAdapter) :
    Fragment(R.layout.fragment_image_api) {

    private lateinit var viewModel: ArtViewModel

    private var fragmentBinding: FragmentImageApiBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        fragmentBinding = FragmentImageApiBinding.bind(view)

        fragmentBinding?.let { binding ->
            binding.recylerViewImage.adapter = imgRcyclerAdapter
            binding.recylerViewImage.layoutManager = GridLayoutManager(requireContext(), 3)

            var job : Job? = null
            binding.etSearch.addTextChangedListener {
                job?.cancel()
                job = lifecycleScope.launch {
                    delay(1000)
                    it?.let {
                        if(it.toString().isNotEmpty()){
                            viewModel.searchForImage(it.toString())
                        }
                    }
                }
            }
        }

        imgRcyclerAdapter.setOnItemClickListener {
            findNavController().popBackStack()
            viewModel.setSelectedImage(it)
        }



        subscribeToObservers()
    }


    fun subscribeToObservers() {
        viewModel.imageList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    val urls = it.data?.hits?.map { imageResult -> imageResult.previewURL!! }
                    val list = listOf<String>()

                    imgRcyclerAdapter.images = urls ?: list
                    fragmentBinding?.progressBar?.visibility = View.GONE

                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                    fragmentBinding?.progressBar?.visibility = View.GONE

                }
                Status.LOADING -> {
                    fragmentBinding?.progressBar?.visibility = View.VISIBLE
                }
            }
        })
    }
}