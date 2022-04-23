package com.said.androidartbooktesting.view


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.said.androidartbooktesting.R
import com.said.androidartbooktesting.getOrAwaitValue
import com.said.androidartbooktesting.launchFragmentInHiltContainer
import com.google.common.truth.Truth.assertThat
import com.said.androidartbooktesting.data.repo.FakeArtRepositoryAndroid
import com.said.androidartbooktesting.ui.adapter.ImageRecyclerAdapter
import com.said.androidartbooktesting.ui.view.ArtFragmentFactory
import com.said.androidartbooktesting.ui.view.ImageApiFragment
import com.said.androidartbooktesting.ui.viewmodel.ArtViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ImageApiFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory : ArtFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testSelectImage() {
        val navController = Mockito.mock(NavController::class.java)
        val selectedImageUrl = "atilsamancioglu.com"
        val testViewModel = ArtViewModel(FakeArtRepositoryAndroid())

        launchFragmentInHiltContainer<ImageApiFragment>(
            factory = fragmentFactory,
        ) {
            Navigation.setViewNavController(requireView(),navController)
            imgRcyclerAdapter.images = listOf(selectedImageUrl)
            viewModel = testViewModel
        }

        Espresso.onView(ViewMatchers.withId(R.id.recylerViewImage)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageRecyclerAdapter.ViewHolder>(
                0,click()
            )

        )

        Mockito.verify(navController).popBackStack()

        assertThat(testViewModel.selectenImageUrl.getOrAwaitValue()).isEqualTo(selectedImageUrl)

    }
}

