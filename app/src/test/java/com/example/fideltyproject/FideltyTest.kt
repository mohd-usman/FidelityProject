package com.example.fideltyproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.fideltyproject.activities.MainViewModel
import com.example.fideltyproject.model.SearchAnimeResponse
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import java.util.concurrent.Callable

class FideltyTest {

    lateinit var viewModel: MainViewModel

    @Before
    fun init() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> Schedulers.trampoline() }
        viewModel = Mockito.mock(MainViewModel::class.java)
    }

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var rule: MockitoRule = MockitoJUnit.rule()

    @Test
    fun `Test the getData Function`() {
        val list = arrayListOf<SearchAnimeResponse.SearchAnimeItem>(
            SearchAnimeResponse.SearchAnimeItem(
                "anything", 23, "Naruto", "Tv", "http:/",231.1,"1-12","3-06",4
            ),
            SearchAnimeResponse.SearchAnimeItem(
                "One", 45, "Baruto", "Movie", "http:/",232.1,"1-4","3-06",220
            ),
            SearchAnimeResponse.SearchAnimeItem(
                "Two", 34, "the king", "Tv", "http:/",84.9,"1-12","3-06",1
            ),
            SearchAnimeResponse.SearchAnimeItem(
                "Three", 2, "Something", "Tv", "http:/",6.4,"1-12","3-06",1
            )
        )
        val randomData = SearchAnimeResponse.SearchAnimeItem(
            "Four", 4, "alsdfkj", "Movie", "http:/",52.1,"1-12","3-06",500
        )

        Assert.assertEquals(randomData.imageUrl,"Four")

    }
}