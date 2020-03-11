package com.dicoding.event.data.remote.repository

import com.dicoding.event.data.remote.model.EventsResponse
import com.dicoding.event.data.remote.network.Services
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.mockito.Mockito.`when`
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import retrofit2.mock.Calls

object RemoteRepositorySpec : Spek({

    Feature("Repository") {
        val services = mock<Services>()
        val repositoryCallback = mock<RepositoryCallback>()
        val remoteRepository = RemoteRepository(services)
        var expectedRespond: EventsResponse? = null


        Scenario("getting upcoming event with success respond") {
            Given("Expected response with success") {
                expectedRespond = EventsResponse(error = false, message = "Success", events = List(2) {
                    EventsResponse.Event(
                        eventId = it.toString(),
                        eventName = "Event $it",
                        eventCategory = "Category $it",
                        eventDate = "Date $it",
                        eventPoster = "Poster $it"
                    )
                })
            }

            When("service get upcoming event") {
                `when`(services.getUpcomingEvent()).thenReturn(Calls.response(expectedRespond))

                // call
                remoteRepository.getUpcomingEvent(repositoryCallback)
            }
            Then("it should be success 🎉") {
                verify(repositoryCallback).onSuccess(expectedRespond?.events)
            }
        }

        Scenario("getting upcoming event but failed") {
            Given("Expected response with error") {
                expectedRespond = EventsResponse(
                    error = true,
                    message = "Failed to get Upcoming Events",
                    events = listOf()
                )
            }

            When("service get upcoming event") {
                `when`(services.getUpcomingEvent()).thenReturn(Calls.response(expectedRespond))

                // call
                remoteRepository.getUpcomingEvent(repositoryCallback)
            }

            Then("it should be failed 💀") {
                verify(repositoryCallback).onError((expectedRespond as EventsResponse).message)
            }
        }
    }
})