package com.said.androidartbooktesting.ui.viewmodel

import com.said.androidartbooktesting.data.repository.FakeArtRepository
import org.junit.Before

class ArtViewModelTest {

    private lateinit var viewModel : ArtViewModel

    @Before
    fun setup(){
        //Test Doubles

        viewModel = ArtViewModel(FakeArtRepository())


    }


}