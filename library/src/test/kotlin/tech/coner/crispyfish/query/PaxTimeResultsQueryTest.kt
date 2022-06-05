package tech.coner.crispyfish.query

import org.assertj.core.api.SoftAssertions
import org.assertj.core.data.Index.atIndex
import tech.coner.crispyfish.test.Events
import tech.coner.crispyfish.util.ResultConditions.driverFinished
import org.junit.Test

class PaxTimeResultsQueryTest {

    private lateinit var paxTimeResultsQuery: PaxTimeResultsQuery

    @Test
    fun testWithThscc2016Points1() {
        val testEvent = Events.Thscc2016Points1Danville
        paxTimeResultsQuery = PaxTimeResultsQuery(
            eventControlFile = testEvent.eventControlFile
        )

        val rawResults = paxTimeResultsQuery.query()

        val softly = SoftAssertions()
        softly.assertThat(rawResults)
                .hasSize(89)
                .has(driverFinished(1, "X", "GS", "1"), atIndex(0))
                .has(driverFinished(2, "X", "GS", "46"), atIndex(1))
                .has(driverFinished(3, "X", "CS", "37"), atIndex(2))
                .has(driverFinished(4, "", "HS", "24"), atIndex(3))
                .has(driverFinished(5, "", "STR", "127"), atIndex(4))
                .has(driverFinished(6, "", "ES", "84"), atIndex(5))
                .has(driverFinished(7, "", "ES", "184"), atIndex(6))
                .has(driverFinished(8, "", "STR", "8"), atIndex(7))
                .has(driverFinished(9, "", "STR", "86"), atIndex(8))
                .has(driverFinished(10, "", "CS", "8"), atIndex(9))
                .has(driverFinished(79, "NOV", "STU", "33"), atIndex(78))
                .has(driverFinished(80, "NOV", "STR", "18"), atIndex(79))
                .has(driverFinished(81, "", "CSP", "211"), atIndex(80))
                .has(driverFinished(82, "", "DSP", "17"), atIndex(81))
                .has(driverFinished(83, "NOV", "HCS", "73"), atIndex(82))
                .has(driverFinished(84, "NOV", "HCS", "226"), atIndex(83))
                .has(driverFinished(85, "", "CSP", "26"), atIndex(84))
                .has(driverFinished(86, "", "GS", "12"), atIndex(85))
                .has(driverFinished(87, "", "AS", "44"), atIndex(86))
                .has(driverFinished(88, "", "BSP", "28"), atIndex(87))
                .has(driverFinished(89, "", "SMF", "76"), atIndex(88))
        softly.assertAll()
    }

    @Test
    fun testIssue25IsFixed() {
        val testEvent = Events.Thscc2016Points1Danville
        paxTimeResultsQuery = PaxTimeResultsQuery(
            eventControlFile = testEvent.eventControlFile
        )

        val rawResults = paxTimeResultsQuery.query()

        val softly = SoftAssertions()
        softly.assertThat(rawResults)
                .has(driverFinished(37, "", "HS", "124"), atIndex(36))
                .has(driverFinished(64, "NOV", "GS", "133"), atIndex(63))
        softly.assertAll()
    }

    @Test
    fun testWithThscc2016Points2() {
        val testEvent = Events.Thscc2016Points2Cary
        paxTimeResultsQuery = PaxTimeResultsQuery(
            eventControlFile = testEvent.eventControlFile
        )

        val rawResults = paxTimeResultsQuery.query()

        val softly = SoftAssertions()
        softly.assertThat(rawResults)
                .hasSize(114)
                .has(driverFinished(1, "X", "GS", "9"), atIndex(0))
                .has(driverFinished(2, "", "HS", "24"), atIndex(1))
                .has(driverFinished(3, "X", "GS", "19"), atIndex(2))
                .has(driverFinished(4, "", "STR", "8"), atIndex(3))
                .has(driverFinished(5, "", "ES", "71"), atIndex(4))
                .has(driverFinished(6, "", "CS", "15"), atIndex(5))
                .has(driverFinished(7, "", "CS", "51"), atIndex(6))
                .has(driverFinished(8, "", "ES", "2"), atIndex(7))
                .has(driverFinished(9, "", "CS", "8"), atIndex(8))
                .has(driverFinished(10, "", "HS", "99"), atIndex(9))
                .has(driverFinished(114, "NOV", "ES", "981"), atIndex(113))
                .has(driverFinished(113, "NOV", "BSP", "281"), atIndex(112))
                .has(driverFinished(112, "", "AS", "44"), atIndex(111))
                .has(driverFinished(111, "", "SMF", "76"), atIndex(110))
                .has(driverFinished(110, "", "DSP", "51"), atIndex(109))
                .has(driverFinished(109, "NOV", "HCS", "226"), atIndex(108))
                .has(driverFinished(108, "NOV", "ES", "45"), atIndex(107))
                .has(driverFinished(107, "NOV", "HS", "35"), atIndex(106))
                .has(driverFinished(106, "", "BSP", "28"), atIndex(105))
                .has(driverFinished(105, "", "ASP", "4"), atIndex(104))
        softly.assertAll()
    }

    @Test
    fun testWithThscc2016Points3() {
        val testEvent = Events.Thscc2016Points3Danville
        paxTimeResultsQuery = PaxTimeResultsQuery(
            eventControlFile = testEvent.eventControlFile
        )

        val rawResults = paxTimeResultsQuery.query()

        val softly = SoftAssertions()
        softly.assertThat(rawResults)
                .hasSize(101)
                .has(driverFinished(1, "X", "cs", "9"), atIndex(0))
                .has(driverFinished(2, "X", "bs", "804"), atIndex(1))
                .has(driverFinished(3, "X", "gs", "64"), atIndex(2))
                .has(driverFinished(4, "", "ds", "15"), atIndex(3))
                .has(driverFinished(5, "", "hs", "24"), atIndex(4))
                .has(driverFinished(6, "X", "cs", "78"), atIndex(5))
                .has(driverFinished(7, "X", "cs", "81"), atIndex(6))
                .has(driverFinished(8, "X", "cs", "37"), atIndex(7))
                .has(driverFinished(9, "X", "es", "18"), atIndex(8))
                .has(driverFinished(10, "", "str", "8"), atIndex(9))
                .has(driverFinished(100, "", "stx", "44"), atIndex(99))
                .has(driverFinished(99, "NOV", "fsp", "73"), atIndex(98))
                .has(driverFinished(98, "", "stx", "54"), atIndex(97))
                .has(driverFinished(97, "NOV", "hs", "99"), atIndex(96))
                .has(driverFinished(96, "NOV", "cam", "182"), atIndex(95))
                .has(driverFinished(95, "NOV", "csp", "26"), atIndex(94))
                .has(driverFinished(94, "", "cs", "19"), atIndex(93))
                .has(driverFinished(93, "NOV", "es", "0"), atIndex(92))
                .has(driverFinished(92, "", "am", "1"), atIndex(91))
                .has(driverFinished(91, "", "csp", "3"), atIndex(90))
        softly.assertAll()
    }

    @Test
    fun testWithThscc2016Points9() {
        val testEvent = Events.Thscc2016Points9Cary
        paxTimeResultsQuery = PaxTimeResultsQuery(
            eventControlFile = testEvent.eventControlFile
        )

        val rawResults = paxTimeResultsQuery.query()

        val softly = SoftAssertions()
        softly.assertThat(rawResults)
                .hasSize(128)
                .has(driverFinished(1, "", "GS", "78"), atIndex(0))
                .has(driverFinished(2, "", "HS", "24"), atIndex(1))
                .has(driverFinished(3, "", "CS", "78"), atIndex(2))
                .has(driverFinished(4, "", "CS", "15"), atIndex(3))
                .has(driverFinished(5, "", "STR", "8"), atIndex(4))
                .has(driverFinished(6, "", "CS", "5"), atIndex(5))
                .has(driverFinished(7, "", "CS", "116"), atIndex(6))
                .has(driverFinished(8, "", "GS", "46"), atIndex(7))
                .has(driverFinished(9, "", "ES", "7"), atIndex(8))
                .has(driverFinished(10, "", "CS", "8"), atIndex(9))
                .has(driverFinished(128, "NOV", "SM", "333"), atIndex(127))
                .has(driverFinished(127, "NOV", "XP", "45"), atIndex(126))
                .has(driverFinished(126, "", "SM", "51"), atIndex(125))
                .has(driverFinished(125, "", "CS", "11"), atIndex(124))
                .has(driverFinished(124, "", "BM", "2"), atIndex(123))
                .has(driverFinished(123, "NOV", "CAM", "41"), atIndex(122))
                .has(driverFinished(122, "NOV", "SM", "7"), atIndex(121))
                .has(driverFinished(121, "NOV", "GS", "347"), atIndex(120))
                .has(driverFinished(120, "NOV", "SMF", "2"), atIndex(119))
                .has(driverFinished(119, "OF", "ES", "62"), atIndex(118))
                .has(driverFinished(118, "NOV", "STR", "56"), atIndex(117))
        softly.assertAll()
    }
}