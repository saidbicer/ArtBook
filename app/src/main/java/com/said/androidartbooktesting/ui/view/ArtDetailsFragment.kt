package com.said.androidartbooktesting.ui.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.said.androidartbooktesting.R
import com.said.androidartbooktesting.databinding.FragmentArtDetailsBinding
import com.said.androidartbooktesting.ui.viewmodel.ArtViewModel
import com.said.androidartbooktesting.util.Status
import javax.inject.Inject


class ArtDetailsFragment @Inject constructor(private val glide: RequestManager) :
    Fragment(R.layout.fragment_art_details) {

    private var fragmentBinding: FragmentArtDetailsBinding? = null

    lateinit var viewModel: ArtViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity()).get(ArtViewModel::class.java)

        fragmentBinding = FragmentArtDetailsBinding.bind(view)

        fragmentBinding?.let { binding ->
            binding.ivArt.setOnClickListener {
                findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment())
            }

            binding.btnSave.setOnClickListener {
                viewModel.makeArt(
                    binding.etName.text.toString(),
                    binding.etArtist.text.toString(),
                    binding.etYear.text.toString()
                )
            }

        }


        subscribeToObservers()

        //Clic back button action
        val calback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(calback)
    }

    private fun subscribeToObservers() {
        viewModel.selectenImageUrl.observe(viewLifecycleOwner, Observer { url ->
            fragmentBinding?.let { binding ->
                glide.load(url).into(binding.ivArt)
            }
        })

        viewModel.insertArtMessage.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    Toast.makeText(requireContext(), "Success", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                    viewModel.resertInsertArtMsg()
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(),  it.message ?: "Success", Toast.LENGTH_LONG).show()
                }

                Status.LOADING -> {

                }
            }
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        fragmentBinding = null
    }
}