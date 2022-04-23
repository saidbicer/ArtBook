package com.said.androidartbooktesting.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.said.androidartbooktesting.MainCoroutineRule
import com.said.androidartbooktesting.data.repository.FakeArtRepository
import com.said.androidartbooktesting.getOrAwaitValueTest
import com.google.common.truth.Truth.assertThat
import com.said.androidartbooktesting.util.Status
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ArtViewModelTest {

    @get : Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get : Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ArtViewModel

    @Before
    fun setup() {
        //Test Doubles
        viewModel = ArtViewModel(FakeArtRepository())
    }

    @Test
    fun `insert art without year returns error`() {
        viewModel.makeArt("Mona Lisa", "Da Vinci", "")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without name returns error`() {
        viewModel.makeArt("", "Da Vinci", "23")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert art without artistName returns error`() {
        viewModel.makeArt("Mona Lisa", "", "23")
        val value = viewModel.insertArtMessage.getOrAwaitValueTest()
        assertThat(value.status).isEqualTo(Status.ERROR)
    }
}