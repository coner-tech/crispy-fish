package org.coner.crispyfish.query

import org.assertj.core.api.SoftAssertions
import org.coner.crispyfish.test.Events
import org.junit.Ignore
import org.junit.Test

import org.assertj.core.data.Index.atIndex
import org.coner.crispyfish.util.ResultConditions.driverFinished
import org.coner.crispyfish.util.ResultConditions.driverNameNotNullOrEmpty

class RawTimeResultsQueryTest {

    private lateinit var rawTimeResultsQuery: RawTimeResultsQuery

    @Test
    @Throws(QueryException::class)
    fun testWithThscc2016Points1() {
        val testEvent = Events.Thscc2016Points1Danville
        rawTimeResultsQuery = RawTimeResultsQuery(
                classDefinitionFile = testEvent.classDefinitions.file,
                eventControlFile = testEvent.eventControlFile
        )

        val rawResults = rawTimeResultsQuery.query()

        val softly = SoftAssertions()
        softly.assertThat(rawResults)
                .hasSize(89)
                .has(driverFinished(1, "", "STR", "127"), atIndex(0))
                .has(driverFinished(2, "X", "CSP", "162"), atIndex(1))
                .has(driverFinished(3, "X", "GS", "1"), atIndex(2))
                .has(driverFinished(4, "X", "CSP", "62"), atIndex(3))
                .has(driverFinished(5, "", "STR", "8"), atIndex(4))
                .has(driverFinished(6, "X", "CS", "37"), atIndex(5))
                .has(driverFinished(7, "", "STR", "86"), atIndex(6))
                .has(driverFinished(8, "X", "GS", "46"), atIndex(7))
                .has(driverFinished(9, "", "ES", "84"), atIndex(8))
                .has(driverFinished(10, "", "ES", "184"), atIndex(9))
                .has(driverFinished(79, "", "HS", "41"), atIndex(78))
                .has(driverFinished(80, "", "DS", "67"), atIndex(79))
                .has(driverFinished(81, "", "DSP", "17"), atIndex(80))
                .has(driverFinished(82, "NOV", "HS", "17"), atIndex(81))
                .has(driverFinished(83, "", "CSP", "26"), atIndex(82))
                .has(driverFinished(84, "NOV", "HCS", "73"), atIndex(83))
                .has(driverFinished(85, "NOV", "HCS", "226"), atIndex(84))
                .has(driverFinished(86, "", "GS", "12"), atIndex(85))
                .has(driverFinished(87, "", "SMF", "76"), atIndex(86))
                .has(driverFinished(88, "", "BSP", "28"), atIndex(87))
                .has(driverFinished(89, "", "AS", "44"), atIndex(88))
        softly.assertAll()
    }

    @Test
    @Throws(QueryException::class)
    fun testWithThscc2016Points2() {
        val testEvent = Events.Thscc2016Points2Cary
        rawTimeResultsQuery = RawTimeResultsQuery(
                classDefinitionFile = testEvent.classDefinitions.file,
                eventControlFile = testEvent.eventControlFile
        )

        val rawResults = rawTimeResultsQuery.query()

        val softly = SoftAssertions()
        softly.assertThat(rawResults)
                .hasSize(114)
                .has(driverFinished(1, "", "STR", "8"), atIndex(0))
                .has(driverFinished(2, "", "STR", "86"), atIndex(1))
                .has(driverFinished(3, "X", "GS", "9"), atIndex(2))
                .has(driverFinished(4, "", "CS", "15"), atIndex(3))
                .has(driverFinished(5, "", "CS", "51"), atIndex(4))
                .has(driverFinished(6, "", "STR", "42"), atIndex(5))
                .has(driverFinished(7, "", "STU", "197"), atIndex(6))
                .has(driverFinished(8, "", "HS", "24"), atIndex(7))
                .has(driverFinished(9, "", "CS", "8"), atIndex(8))
                .has(driverFinished(10, "", "STR", "32"), atIndex(9))
                .has(driverFinished(114, "NOV", "ES", "981"), atIndex(113))
                .has(driverFinished(113, "NOV", "BSP", "281"), atIndex(112))
                .has(driverFinished(112, "NOV", "ES", "45"), atIndex(111))
                .has(driverFinished(111, "", "AS", "44"), atIndex(110))
                .has(driverFinished(110, "NOV", "HCS", "226"), atIndex(109))
                .has(driverFinished(109, "NOV", "HS", "35"), atIndex(108))
                .has(driverFinished(108, "NOV", "HS", "103"), atIndex(107))
                .has(driverFinished(107, "NOV", "HS", "17"), atIndex(106))
                .has(driverFinished(106, "", "DSP", "51"), atIndex(105))
                .has(driverFinished(105, "", "SMF", "76"), atIndex(104))
        softly.assertAll()
    }

    @Test
    @Throws(QueryException::class)
    fun testWithThscc2016Points3() {
        val testEvent = Events.Thscc2016Points3Danville
        rawTimeResultsQuery = RawTimeResultsQuery(
                classDefinitionFile = testEvent.classDefinitions.file,
                eventControlFile = testEvent.eventControlFile
        )

        val rawResults = rawTimeResultsQuery.query()

        val softly = SoftAssertions()
        softly.assertThat(rawResults)
                .hasSize(100)
                .has(driverFinished(1, "X", "bs", "804"), atIndex(0))
                .has(driverFinished(2, "X", "cs", "9"), atIndex(1))
                .has(driverFinished(3, "", "asp", "9"), atIndex(2))
                .has(driverFinished(4, "", "am", "1"), atIndex(3))
                .has(driverFinished(5, "", "str", "8"), atIndex(4))
                .has(driverFinished(6, "", "ds", "15"), atIndex(5))
                .has(driverFinished(7, "", "str", "86"), atIndex(6))
                .has(driverFinished(8, "X", "gs", "64"), atIndex(7))
                .has(driverFinished(9, "X", "cs", "78"), atIndex(8))
                .has(driverFinished(10, "X", "cs", "81"), atIndex(9))
                .has(driverFinished(100, "NOV", "am", "77"), atIndex(99))
                .has(driverFinished(99, "NOV", "hs", "99"), atIndex(98))
                .has(driverFinished(98, "", "stx", "54"), atIndex(97))
                .has(driverFinished(97, "NOV", "fsp", "73"), atIndex(96))
                .has(driverFinished(96, "NOV", "es", "0"), atIndex(95))
                .has(driverFinished(95, "", "cs", "19"), atIndex(94))
                .has(driverFinished(94, "NOV", "cam", "182"), atIndex(93))
                .has(driverFinished(93, "", "hs", "93"), atIndex(92))
                .has(driverFinished(92, "NOV", "hs", "44"), atIndex(91))
                .has(driverFinished(91, "NOV", "csp", "26"), atIndex(90))
        softly.assertAll()
    }

    @Test
    @Throws(QueryException::class)
    fun testWithThscc2016Points9() {
        val testEvent = Events.Thscc2016Points9Cary
        rawTimeResultsQuery = RawTimeResultsQuery(
                classDefinitionFile = testEvent.classDefinitions.file,
                eventControlFile = testEvent.eventControlFile
        )

        val rawResults = rawTimeResultsQuery.query()

        val softly = SoftAssertions()
        softly.assertThat(rawResults)
                .hasSize(128)
                .has(driverFinished(1, "", "XP", "58"), atIndex(0))
                .has(driverFinished(2, "", "ASP", "41"), atIndex(1))
                .has(driverFinished(3, "", "STR", "8"), atIndex(2))
                .has(driverFinished(4, "", "GS", "78"), atIndex(3))
                .has(driverFinished(5, "", "CS", "78"), atIndex(4))
                .has(driverFinished(6, "", "STR", "73"), atIndex(5))
                .has(driverFinished(7, "", "CS", "15"), atIndex(6))
                .has(driverFinished(8, "", "CS", "5"), atIndex(7))
                .has(driverFinished(9, "", "STR", "32"), atIndex(8))
                .has(driverFinished(10, "", "CS", "116"), atIndex(9))
                .has(driverFinished(128, "NOV", "SM", "333"), atIndex(127))
                .has(driverFinished(127, "", "SM", "51"), atIndex(126))
                .has(driverFinished(126, "NOV", "XP", "45"), atIndex(125))
                .has(driverFinished(125, "", "CS", "11"), atIndex(124))
                .has(driverFinished(124, "NOV", "GS", "347"), atIndex(123))
                .has(driverFinished(123, "OF", "ES", "62"), atIndex(122))
                .has(driverFinished(122, "NOV", "CAM", "41"), atIndex(121))
                .has(driverFinished(121, "NOV", "DS", "35"), atIndex(120))
                .has(driverFinished(120, "NOV", "ES", "21"), atIndex(119))
                .has(driverFinished(119, "NOV", "STR", "56"), atIndex(118))
                .has(driverFinished(118, "NOV", "STF", "98"), atIndex(117))
        softly.assertAll()
    }

    @Test
    @Ignore(value = "Relatively minor issue, ignore until #23 is resolved")
    @Throws(QueryException::class)
    fun testIssue23IsFixed() {
        val testEvent = Events.Thscc2016Points9Cary
        rawTimeResultsQuery = RawTimeResultsQuery(
                classDefinitionFile = testEvent.classDefinitions.file,
                eventControlFile = testEvent.eventControlFile
        )

        val rawResults = rawTimeResultsQuery.query()

        val softly = SoftAssertions()
        softly.assertThat(rawResults)
                .has(driverNameNotNullOrEmpty(), atIndex(7)) // position 8, CS 15
                .has(driverNameNotNullOrEmpty(), atIndex(99)) // position 100, ES 11
        softly.assertAll()
    }
}