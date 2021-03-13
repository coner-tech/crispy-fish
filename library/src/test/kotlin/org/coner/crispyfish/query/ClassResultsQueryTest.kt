package org.coner.crispyfish.query

import org.assertj.core.api.SoftAssertions
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

        val str = classResults.filter { it.key?.abbreviation == "STR" }.values.single()
        softly.assertThat(str)
                .has(driverFinished(1, "", "STR", "127"), atIndex(0))
                .has(driverFinished(2, "", "STR", "8"), atIndex(1))
                .has(driverFinished(9, "", "STR", "2"), atIndex(8))
                .has(driverFinished(10, "", "STR", "10"), atIndex(9))

        val `as` = classResults.filter { it.key?.abbreviation == "AS" }.values.single()
        softly.assertThat(`as`)
                .has(driverFinished(1, "", "AS", "86"), atIndex(0))
                .has(driverFinished(2, "", "AS", "3"), atIndex(1))
                .has(driverFinished(3, "", "AS", "69"), atIndex(2))
                .has(driverFinished(4, "", "AS", "44"), atIndex(3)) // "DNF"

        softly.assertAll()
    }

    @Test
    fun testWithThscc2016Points2() {
        val testEvent = Events.Thscc2016Points2Cary
        query = ClassResultsQuery(
                classDefinitionFile = testEvent.classDefinitions.file,
                eventControlFile = testEvent.eventControlFile
        )

        val classResults = query.query()

        val softly = SoftAssertions()
        softly.assertThat(classResults).hasSize(25)

        val str = classResults.filter { it.key?.abbreviation == "STR" }.values.single()
        softly.assertThat(str)
                .has(driverFinished(1, "", "STR", "8"), atIndex(0))
                .has(driverFinished(2, "", "STR", "86"), atIndex(1))
                .has(driverFinished(3, "", "STR", "42"), atIndex(2))
                .has(driverFinished(4, "", "STR", "32"), atIndex(3))
                .has(driverFinished(5, "", "STR", "43"), atIndex(4))
                .has(driverFinished(6, "", "STR", "20"), atIndex(5))

        val nov = classResults.filter { it.key?.abbreviation == "NOV" }.values.single()
        softly.assertThat(nov)
                .has(driverFinished(1, "NOV", "DS", "78"), atIndex(0))
                .has(driverFinished(2, "NOV", "STF", "18"), atIndex(1))
                .has(driverFinished(3, "NOV", "STR", "68"), atIndex(2))

        softly.assertAll()
    }

    @Test
    fun testWithThscc2016Points3() {
        val testEvent = Events.Thscc2016Points3Danville
        query = ClassResultsQuery(
                classDefinitionFile = testEvent.classDefinitions.file,
                eventControlFile = testEvent.eventControlFile
        )

        val classResults = query.query()

        val softly = SoftAssertions()
        softly.assertThat(classResults).hasSize(23)

        val str = classResults.filter { it.key?.abbreviation == "STR" }.values.single()
        softly.assertThat(str)
                .hasSize(6)
                .has(driverFinished(1, "", "STR", "8"), atIndex(0))
                .has(driverFinished(2, "", "STR", "86"), atIndex(1))
                .has(driverFinished(3, "", "STR", "14"), atIndex(2))
                .has(driverFinished(4, "", "STR", "42"), atIndex(3))
                .has(driverFinished(5, "", "STR", "4"), atIndex(4))
                .has(driverFinished(6, "", "STR", "32"), atIndex(5))

        val nov = classResults.filter { it.key?.abbreviation == "NOV" }.values.single()
        softly.assertThat(nov)
                .hasSize(32)
                // first place
                .has(driverFinished(1, "NOV", "HS", "128"), atIndex(0))
                // last with an actual time
                .has(driverFinished(27, "NOV", "FSP", "73"), atIndex(26))
                // DFL with a DNF
                .has(driverFinished(28, "NOV", "AM", "77"), atIndex(27))
                // all of the below with a total time of "DNS"
                // TODO: find out if "check-in" status influences "DNS" drivers' presence/absence in class results
                .has(driverFinished(29, "NOV", "SM", "12"), atIndex(28))
                .has(driverFinished(30, "NOV", "BS", "43"), atIndex(29))
                .has(driverFinished(31, "NOV", "STU", "33"), atIndex(30))
                .has(driverFinished(32, "NOV", "STR", "40"), atIndex(31))

        softly.assertAll()
    }

    @Test
    fun testWithThscc2016Points9() {
        val testEvent = Events.Thscc2016Points9Cary
        query = ClassResultsQuery(
                classDefinitionFile = testEvent.classDefinitions.file,
                eventControlFile = testEvent.eventControlFile
        )

        val classResults = query.query()

        val softly = SoftAssertions()
        softly.assertThat(classResults).hasSize(29)

        val str = classResults.filter { it.key?.abbreviation == "STR" }.values.single()
        softly.assertThat(str)
                .hasSize(6)
                .has(driverFinished(1, "", "STR", "8"), atIndex(0))
                .has(driverFinished(6, "", "STR", "35"), atIndex(5))

        val nov = classResults.filter { it.key?.abbreviation == "NOV" }.values.single()
        softly.assertThat(nov)
                .hasSize(38)
                .has(driverFinished(1, "NOV", "DS", "11"), atIndex(0))
                .has(driverFinished(38, "NOV", "SM", "333"), atIndex(37))

        softly.assertAll()
    }

    @Test
    fun testWithThscc2019Points8() {
        val testEvent = Events.Thscc2019Points8Nccar
        query = ClassResultsQuery(
                classDefinitionFile = testEvent.classDefinitions.file,
                eventControlFile = testEvent.eventControlFile
        )

        val classResults = query.query()

        val softly = SoftAssertions()
        softly.assertThat(classResults).hasSize(30)

        val str = classResults.filter { it.key?.abbreviation == "STR" }.values.single()
        softly.assertThat(str)
                .hasSize(8)
                .has(driverFinished(1, "", "STR", "41"), atIndex(0))
                .has(driverFinished(8, "", "STR", "24"), atIndex(7))

        val nov = classResults.filter { it.key?.abbreviation == "NOV" }.values.single()
        softly.assertThat(nov)
                .hasSize(35)
                // boundaries of results with times
                .has(driverFinished(1, "NOV", "HS", "18"), atIndex(0))
                .has(driverFinished(28, "NOV", "GS", "024"), atIndex(27))
                // boundaries of "DNF" results
                .has(driverFinished(29, "NOV", "AS", "007"), atIndex(28))
                .has(driverFinished(30, "NOV", "AS", "77"), atIndex(29))
                // boundaries of "DNS" results
                .has(driverFinished(31, "NOV", "CAM-C", "197"), atIndex(30))
                .has(driverFinished(35, "NOV", "STU", "555"), atIndex(34))

        val cam = classResults.filter { it.key?.abbreviation == "MAC" }.values.single()
        softly.assertThat(cam)
                .hasSize(6)
                .has(driverFinished(1, "MAC", "CAM-C", "72"), atIndex(0))
                .has(driverFinished(6, "MAC", "CAM-S", "6"), atIndex(5))

        softly.assertAll()
    }
}