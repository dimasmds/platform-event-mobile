package com.dicoding.event.ui.upcoming

import androidx.lifecycle.Observer
import com.dicoding.event.data.remote.model.EventsResponse
import com.dicoding.event.data.remote.repository.RemoteRepository
import com.dicoding.event.data.remote.repository.RepositoryCallback
import com.dicoding.event.utils.Error
import com.dicoding.event.utils.Loading
import com.dicoding.event.utils.Success
import com.dicoding.event.utils.ViewModelState
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mockito.`when`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import utils.LiveDataTestExecutor

object UpcomingViewModelSpec : Spek({
    Feature("Upcoming Event ViewModel") {
        val remoteRepository = mock<RemoteRepository>()
        val observer = mock<Observer<ViewModelState>>()
        val upcomingViewModel = UpcomingViewModel(remoteRepository)

        beforeEachScenario {
            LiveDataTestExecutor.setupDelegated()
            upcomingViewModel.observerState.observeForever(observer)
        }

        Scenario("Get upcoming events with success response") {
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

            When("Repository getting upcoming event") {
                `when`(remoteRepository.getUpcomingEvent(any())).thenAnswer {
                    (it.arguments[0] as RepositoryCallback).onSuccess(events)
                }

                // calls
                upcomingViewModel.getUpcomingEvent()
            }

            Then("it should be show loading") {
                verify(observer).onChanged(Loading)
            }

            Then("it should be success 🎉") {
                verify(observer).onChanged(expectedRespond)
            }
        }

        Scenario("Get upcoming events but error response") {
            val expectedResponse = Error("not found")

            When("Repository getting upcoming event") {
                `when`(remoteRepository.getUpcomingEvent(any())).thenAnswer {
                    (it.arguments[0] as RepositoryCallback).onError(expectedResponse.message)
                }

                // calls
                upcomingViewModel.getUpcomingEvent()
            }

            Then("It should be show loading") {
                verify(observer).onChanged(Loading)
            }

            Then("It should be error 💀") {
                verify(observer).onChanged(expectedResponse)
            }
        }


        afterEachScenario {
            LiveDataTestExecutor.clearDelegated()
        }
    }
})