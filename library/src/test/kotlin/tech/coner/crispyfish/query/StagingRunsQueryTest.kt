package tech.coner.crispyfish.query

import assertk.all
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.index
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import org.junit.jupiter.api.Test
import tech.coner.crispyfish.model.EventDay
import tech.coner.crispyfish.model.run
import tech.coner.crispyfish.model.stagingRegistration
import tech.coner.crispyfish.test.Events
import tech.coner.crispyfish.test.Issue42

class StagingRunsQueryTest {

    @Test
    fun testWithThscc2016Points1() {
        val actual = Events.Thscc2016Points1Danville.eventControlFile.queryStagingRuns(eventDay = EventDay.ONE)

        assertThat(actual).all {
            hasSize(456)
            index(0).all {
                stagingRegistration().isNull()
                run().isNotNull()
            }
        }
    }

    @Test
    fun `It should not omit runs from lines which fail to parse correctly`() {
        val actual = Issue42.eventControlFile.queryStagingRuns()

        assertThat(actual).hasSize(8)
    }
}