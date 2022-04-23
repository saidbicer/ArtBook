package com.said.androidartbooktesting.view


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.said.androidartbooktesting.launchFragmentInHiltContainer
import com.said.androidartbooktesting.ui.view.ArtDetailsFragment
import com.said.androidartbooktesting.ui.view.ArtDetailsFragmentDirections
import com.said.androidartbooktesting.ui.view.ArtFragmentFactory
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify
import javax.inject.Inject
import com.said.androidartbooktesting.R
import com.said.androidartbooktesting.data.db.entity.Art
import com.said.androidartbooktesting.data.repo.FakeArtRepositoryAndroid
import com.said.androidartbooktesting.getOrAwaitValue
import com.said.androidartbooktesting.ui.viewmodel.ArtViewModel

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class ArtDetailsFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    lateinit var fragmentFactory: ArtFragmentFactory

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testNavigationFromArtDetailsToImageAPI() {
        val navController = Mockito.mock(NavController::class.java)

        launchFragmentInHiltContainer<ArtDetailsFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }

        Espresso.onView(ViewMatchers.withId(R.id.imageView)).perform(ViewActions.click())
        Mockito.verify(navController).navigate(
            ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageApiFragment()
        )
    }

    @Test
    fun testOnBackPressed() {
        val navController = Mockito.mock(NavController::class.java)
        launchFragmentInHiltContainer<ArtDetailsFragment>(
            factory = fragmentFactory
        ) {
            Navigation.setViewNavController(requireView(), navController)
        }

        pressBack()
        verify(navController).popBackStack()
    }

    @Test
    fun testSave() {
        val testViewModel = ArtViewModel(FakeArtRepositoryAndroid())
        launchFragmentInHiltContainer<ArtDetailsFragment>(
            factory = fragmentFactory
        ) {
            viewModel = testViewModel
        }

        onView(withId(R.id.tvArtName)).perform(replaceText("Mona Lisa"))
        onView(withId(R.id.tvArtistName)).perform(replaceText("Da Vinci"))
        onView(withId(R.id.tvYearName)).perform(replaceText("1700"))
        onView(withId(R.id.btnSave)).perform(click())

        assertThat(
            testViewModel.artList.getOrAwaitValue().contains(
                Art(
                    2,
                    "Mona Lisa",
                    "Da Vinci",
                    1700, ""
                )
            )
        )

    }

}