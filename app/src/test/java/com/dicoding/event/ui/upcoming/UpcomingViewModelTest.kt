package com.dicoding.event.ui.upcoming

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.dicoding.event.data.remote.model.EventsResponse
import com.dicoding.event.data.remote.repository.RemoteRepository
import com.dicoding.event.data.remote.repository.RepositoryCallback
import com.dicoding.event.utils.Error
import com.dicoding.event.utils.Loading
import com.dicoding.event.utils.Success
import com.dicoding.event.utils.ViewModelState
import com.nhaarman.mockitokotlin2.any
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class UpcomingViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var remoteRepository: RemoteRepository

    @Mock
    lateinit var observer: Observer<ViewModelState>

    lateinit var upcomingViewModel: UpcomingViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        upcomingViewModel = UpcomingViewModel(remoteRepository)
        upcomingViewModel.observerState.observeForever(observer)
    }

    @Test
    fun `Get Upcoming Events With Success Respond`() {
        val events = List(2) {
            EventsResponse.Event(
                eventId = it.toString(),
                eventName = "Event $it",
                eventCategory = "Category $it",
                eventDate = "Date $it",
                eventPoster = "Poster $it"
            )
        }
        val expectedRespond = Success(events)

        `when`(remoteRepository.getUpcomingEvent(any())).thenAnswer {
            (it.arguments[0] as RepositoryCallback).onSuccess(events)
        }

        upcomingViewModel.getUpcomingEvent()

        verify(observer).onChanged(Loading)
        verify(observer).onChanged(expectedRespond)
        verify(remoteRepository).getUpcomingEvent(any())
    }

    @Test
    fun `Get Upcoming Events But Failed Respond`() {
        val errorMessage = "Not Found"
        val expectedRespond = Error(errorMessage)

        `when`(remoteRepository.getUpcomingEvent(any())).thenAnswer {
            (it.arguments[0] as RepositoryCallback).onError(errorMessage)
        }

        upcomingViewModel.getUpcomingEvent()

        verify(observer).onChanged(Loading)
        verify(observer).onChanged(expectedRespond)
        verify(remoteRepository).getUpcomingEvent(any())
    }
}