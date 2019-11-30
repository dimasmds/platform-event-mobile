package com.dicoding.event.data.remote.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.event.data.remote.model.EventsResponse
import com.dicoding.event.data.remote.network.Services
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import retrofit2.mock.Calls

class RemoteRepositoryTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var services: Services

    @Mock
    private lateinit var repositoryCallback: RepositoryCallback

    private lateinit var remoteRepository: RemoteRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        remoteRepository = RemoteRepository(services)
    }

    @Test
    fun `getting upcoming event with success respond`() {

        val expectedRespond = EventsResponse(error = false, message = "Success", events = List(2) {
            EventsResponse.Event(
                eventId = it.toString(),
                eventName = "Event $it",
                eventCategory = "Category $it",
                eventDate = "Date $it",
                eventPoster = "Poster $it"
            )
        })

        Mockito.`when`(services.getUpcomingEvent()).thenReturn(Calls.response(expectedRespond))
        remoteRepository.getUpcomingEvent(repositoryCallback)
        Mockito.verify(repositoryCallback).onSuccess(expectedRespond.events)
    }

    @Test
    fun `getting upcoming event but failed`() {
        val expectedRespond = EventsResponse(
            error = true,
            message = "Failed to get Upcoming Events",
            events = listOf()
        )

        `when`(services.getUpcomingEvent()).thenReturn(Calls.response(expectedRespond))
        remoteRepository.getUpcomingEvent(repositoryCallback)
        verify(repositoryCallback).onError(expectedRespond.message)
    }
}