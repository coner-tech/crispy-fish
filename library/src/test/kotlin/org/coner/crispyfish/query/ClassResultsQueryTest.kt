package org.coner.crispyfish.query

import org.assertj.core.api.SoftAssertions
import org.assertj.core.data.Index
import org.assertj.core.data.Index.atIndex
import org.coner.crispyfish.test.Events
import org.coner.crispyfish.util.ResultConditions.driverFinished
import org.junit.Test

class ClassResultsQueryTest {

    private lateinit var query: ClassResultsQuery

    @Test
    fun testWithThscc2016Points1() {
        val testEvent = Events.Thscc2016Points1Danville
        query = ClassResultsQuery(
                classDefinitionFile = testEvent.classDefinitions.file,
                eventControlFile = testEvent.eventControlFile
        )

        val classResults = query.query()

        val softly = SoftAssertions()
        softly.assertThat(classResults).hasSize(22)

        val str = classResults.filter { it.key.abbreviation == "STR" }.values.single()
        softly.assertThat(str)
                .has(driverFinished(1, "", "STR", "127"), atIndex(0))
                .has(driverFinished(2, "", "STR", "8"), atIndex(1))
                .has(driverFinished(9, "", "STR", "2"), atIndex(8))
                .has(driverFinished(10, "", "STR", "10"), atIndex(9))

        val `as` = classResults.filter { it.key.abbreviation == "AS" }.values.single()
        softly.assertThat(`as`)
                .has(driverFinished(1, "", "AS", "86"), atIndex(0))
                .has(driverFinished(2, "", "AS", "3"), atIndex(1))
                .has(driverFinished(3, "", "AS", "69"), atIndex(2))
                .has(driverFinished(4, "", "AS", "44"), atIndex(3)) // "DNF"

        softly.assertAll()
    }
}