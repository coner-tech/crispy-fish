package tech.coner.crispyfish.query

import assertk.all
import assertk.assertThat
import assertk.assertions.*
import org.junit.Test
import tech.coner.crispyfish.filetype.ecf.EventControlFile
import tech.coner.crispyfish.model.*
import tech.coner.crispyfish.test.Events
import tech.coner.crispyfish.test.Issue42
import kotlin.run

class StagingRunsQueryTest {

    lateinit var query: StagingRunsQuery

    @Test
    fun testWithThscc2016Points1() {
        val testEvent = Events.Thscc2016Points1Danville
        query = StagingRunsQuery(
            stagingFile = testEvent.eventControlFile.stagingFile(eventDay = EventDay.ONE)
        )

        val actual = query.query()

        assertThat(actual).all {
            hasSize(456)
            index(0).all {
                stagingLineRegistration().isNull()
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