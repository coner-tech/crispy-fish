package tech.coner.crispyfish

import assertk.all
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.index
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import org.junit.jupiter.api.Test
import tech.coner.crispyfish.model.*
import tech.coner.crispyfish.test.Events

class CrispyFishEventStagingIT {

    @Test
    fun testWithThscc2016Points1() {
        val actual = Events.Thscc2016Points1Danville.queryAllStagingRuns(EventDay.ONE)

        assertThat(actual).value().all {
            hasSize(456)
            index(0).all {
                stagingRegistration().isNull()
                run().isNotNull()
            }
        }
    }

    @Test
    fun itShouldNotOmitRunsFromLinesWhichFailToParseCorrectly() {
        val actual = Events.Issue42.queryAllStagingRuns(EventDay.ONE)

        assertThat(actual).value().hasSize(8)
    }

    private fun Events.queryAllStagingRuns(
        eventDay: EventDay
    ): AllStagingRuns {
        val crispyFishClassDefinitions = CrispyFishClassDefinitions.factory(classDefinitions.file)
        val crispyFishEvents = CrispyFishEvent.factory(eventControlFile)
        val allClassDefinitions = crispyFishClassDefinitions.queryAllClassDefinitions()
        return crispyFishEvents.queryAllStagingRuns(
            eventDay = eventDay,
            allClassDefinitions = allClassDefinitions,
            registrationsBySignage = crispyFishEvents.queryAllRegistrationsBySignage(
                allClassDefinitions = allClassDefinitions,
                allRegistrations = crispyFishEvents.queryAllRegistrations(allClassDefinitions)
            )
        )
    }
}