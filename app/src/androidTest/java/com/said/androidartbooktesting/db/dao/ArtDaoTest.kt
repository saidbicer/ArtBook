package com.said.androidartbooktesting.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.google.common.truth.Truth.assertThat
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.said.androidartbooktesting.data.db.MyDatabase
import com.said.androidartbooktesting.data.db.dao.ArtDao
import com.said.androidartbooktesting.data.db.entity.Art
import com.said.androidartbooktesting.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@SmallTest                  //Small -> Unit, Medium -> integration, Large -> Ui test
@ExperimentalCoroutinesApi //Coroutinelerle işlem yapılacak
class ArtDaoTest {

    @get : Rule
    var instantTastExecutorRole = InstantTaskExecutorRule()

    private lateinit var dao: ArtDao
    private lateinit var database: MyDatabase

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(), MyDatabase::class.java
        )
            .allowMainThreadQueries() //this is a test case, we don't want other thread pools
            .build()

        dao = database.artDao()
    }


    @After
    fun tearDown() {
        database.close()
    }


    @Test
    fun insertArt() = runBlocking {
        val exampleArt = Art(1, "Mona Lisa", "Da Vinci", 1700, "test.com")
        dao.insertArt(exampleArt)

        val list = dao.observerArts().getOrAwaitValue()
        assertThat(list).contains(exampleArt)
    }

    @Test
    fun deleteArt() = runBlocking {
        val exampleArt = Art(1, "Mona Lisa", "Da Vinci", 1700, "test.com")
        dao.insertArt(exampleArt)
        dao.deleteArt(exampleArt)
        val list = dao.observerArts().getOrAwaitValue()
        assertThat(list).doesNotContain(exampleArt)
    }

}