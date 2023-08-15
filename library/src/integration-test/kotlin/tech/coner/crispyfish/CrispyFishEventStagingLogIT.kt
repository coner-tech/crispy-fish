package tech.coner.crispyfish

import assertk.all
import assertk.assertThat
import assertk.assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import tech.coner.crispyfish.model.*
import tech.coner.crispyfish.test.Events

class CrispyFishEventStagingLogIT {


    @ParameterizedTest
    @EnumSource(StagingLogFileTestCase::class)
    fun itShouldReadExpectedStagingLogFiles(case: StagingLogFileTestCase) {
        val crispyFishEvent = case.event.factory()
        val crispyFishClassDefinitions = case.event.classDefinitions.factory()
        val allClassDefinitions = crispyFishClassDefinitions.queryAllClassDefinitions()
        val allRegistrations = crispyFishEvent.queryAllRegistrations(allClassDefinitions)
        val actual = crispyFishEvent.queryAllStagingLogRows(
            eventDay = EventDay.ONE,
            allClassDefinitions = allClassDefinitions,
            registrationsBySignage = crispyFishEvent.queryAllRegistrationsBySignage(
                allClassDefinitions = allClassDefinitions,
                allRegistrations = allRegistrations
            )
        )

        assertThat(actual).value().all {
            hasSize(case.expectedSize)
            case.rowsWithNoRegistrationOrRun.forEach { rowWithNoRegistrationOrRun ->
                index(rowWithNoRegistrationOrRun.index).all {
                    timestamp().isEqualTo(rowWithNoRegistrationOrRun.expectedTimestamp)
                    sequenceRow().isEqualTo(rowWithNoRegistrationOrRun.expectedSequenceRow)
                    stagingRun().all {
                        stagingRegistration().isNull()
                        registration().isNull()
                        run().isBlank()
                    }
                }
            }
            case.rowsWithJunkRegistrationWithoutRun.forEach { rowWithJunkRegistrationWithoutRun ->
                index(rowWithJunkRegistrationWithoutRun.index).all {
                    timestamp().isEqualTo(rowWithJunkRegistrationWithoutRun.expectedTimestamp)
                    sequenceRow().isEqualTo(rowWithJunkRegistrationWithoutRun.expectedSequenceRow)
                    stagingRun().all {
                        stagingRegistration().isNotNull()
                        registration().isNull()
                        run().isBlank()
                    }
                }
            }
            case.rowsWithRegistrationAndRun.forEach { rowWithRegistrationAndRun ->
                index(rowWithRegistrationAndRun.index).all {
                    timestamp().isEqualTo(rowWithRegistrationAndRun.expectedTimestamp)
                    sequenceRow().isEqualTo(rowWithRegistrationAndRun.expectedSequenceRow)
                    stagingRun().all {
                        stagingRegistration().isNotNull()
                        registration().isNotNull()
                        run().isFinished()
                    }
                }
            }
        }
    }

}

enum class StagingLogFileTestCase(
    val event: Events,
    val expectedSize: Int,
    val rowsWithNoRegistrationOrRun: List<IndexOfInterest>,
    val rowsWithJunkRegistrationWithoutRun: List<IndexOfInterest>,
    val rowsWithRegistrationAndRun: List<IndexOfInterest>,
    val rowsWithoutRegistrationButWithRun: List<IndexOfInterest>
) {
    THSCC_2016_POINTS_1(
        event = Events.Thscc2016Points1Danville,
        expectedSize = 829,
        rowsWithNoRegistrationOrRun = listOf(
            IndexOfInterest(1, "03/05/16 10:04:14", 0),
            IndexOfInterest(13, "03/05/16 10:08:08", 1),
            IndexOfInterest(413, "03/05/16 12:47:26", 225)
        ),
        rowsWithJunkRegistrationWithoutRun = listOf(
            IndexOfInterest(15, "03/05/16 10:18:50", 1),
            IndexOfInterest(17, "03/05/16 10:19:57", 1),
            IndexOfInterest(18, "03/05/16 10:20:08", 1),
            IndexOfInterest(19, "03/05/16 10:21:17", 1)
        ),
        rowsWithRegistrationAndRun = listOf(
            IndexOfInterest(221, "03/05/16 11:33:28", 129),
            IndexOfInterest(359, "03/05/16 12:32:47", 213),
            IndexOfInterest(571, "03/05/16 13:40:27", 319)
        ),
        rowsWithoutRegistrationButWithRun = emptyList(),
    ),
    THSCC_2016_POINTS_2(
        event = Events.Thscc2016Points2Cary,
        expectedSize = 767,
        rowsWithNoRegistrationOrRun = listOf(
            IndexOfInterest(2, "03/19/16 10:10:18", 0),
            IndexOfInterest(3, "03/19/16 10:10:21", 1)
        ),
        rowsWithJunkRegistrationWithoutRun = listOf(
            IndexOfInterest(8, "03/19/16 10:17:09", 1),
            IndexOfInterest(485, "03/19/16 14:13:47", 334),
            IndexOfInterest(543, "03/19/16 14:43:53", 379),
            IndexOfInterest(566, "03/19/16 14:55:19", 394),
            IndexOfInterest(606, "03/19/16 15:18:29", 429),
            IndexOfInterest(613, "03/19/16 15:22:31", 434)
        ),
        rowsWithRegistrationAndRun = listOf(
            IndexOfInterest(76, "03/19/16 10:50:37", 43),
            IndexOfInterest(266, "03/19/16 12:07:34", 156),
            IndexOfInterest(468, "03/19/16 14:05:08", 324),
            IndexOfInterest(469, "03/19/16 14:05:15", 324)
        ),
        rowsWithoutRegistrationButWithRun = emptyList(),
    ),
    THSCC_2016_POINTS_3(
        event = Events.Thscc2016Points3Danville,
        expectedSize = 1130,
        rowsWithNoRegistrationOrRun = listOf(
            IndexOfInterest(47, "04/24/16 10:10:58", 0),
            IndexOfInterest(49, "04/24/16 10:13:10", 0),
        ),
        rowsWithJunkRegistrationWithoutRun = listOf(
            IndexOfInterest(495, "04/24/16 12:20:57", 169)
        ),
        rowsWithRegistrationAndRun = listOf(
            IndexOfInterest(1, "03/22/16 19:09:36", 1),
            IndexOfInterest(70, "04/24/16 10:24:45", 10)
        ),
        rowsWithoutRegistrationButWithRun = mutableListOf(
            IndexOfInterest(6, "04/23/16 08:54:00", 0),
            IndexOfInterest(7, "04/23/16 08:54:37", 1),
            IndexOfInterest(8, "04/23/16 08:55:39", 2),
            IndexOfInterest(45, "04/23/16 10:09:36", 39)
        ),
    ),
    THSCC_2016_POINTS_9(
        event = Events.Thscc2016Points9Cary,
        expectedSize = 1444,
        rowsWithNoRegistrationOrRun = emptyList(), //TODO
        rowsWithJunkRegistrationWithoutRun = emptyList(), // TODO
        rowsWithRegistrationAndRun = emptyList(), // TODO
        rowsWithoutRegistrationButWithRun = emptyList(), // TODO
    ),
    THSCC_2017_POINTS_1(
        event = Events.Thscc2017Points1Danville,
        expectedSize = 1290,
        rowsWithNoRegistrationOrRun = emptyList(), //TODO
        rowsWithJunkRegistrationWithoutRun = emptyList(), // TODO
        rowsWithRegistrationAndRun = emptyList(), // TODO
        rowsWithoutRegistrationButWithRun = emptyList(), // TODO
    ),
    THSCC_2017_POINTS_5(
        event = Events.Thscc2017Points5CaryTowneCenter,
        expectedSize = 1237,
        rowsWithNoRegistrationOrRun = emptyList(), //TODO
        rowsWithJunkRegistrationWithoutRun = emptyList(), // TODO
        rowsWithRegistrationAndRun = emptyList(), // TODO
        rowsWithoutRegistrationButWithRun = emptyList(), // TODO
    ),
    THSCC_2017_POINTS_9(
        event = Events.Thscc2017Points9Danville,
        expectedSize = 884,
        rowsWithNoRegistrationOrRun = emptyList(), //TODO
        rowsWithJunkRegistrationWithoutRun = emptyList(), // TODO
        rowsWithRegistrationAndRun = emptyList(), // TODO
        rowsWithoutRegistrationButWithRun = emptyList(), // TODO
    ),
    THSCC_2018_JUST_FOR_FUN(
        event = Events.Thscc2018JustForFunCary,
        expectedSize = 1043,
        rowsWithNoRegistrationOrRun = emptyList(), //TODO
        rowsWithJunkRegistrationWithoutRun = emptyList(), // TODO
        rowsWithRegistrationAndRun = emptyList(), // TODO
        rowsWithoutRegistrationButWithRun = emptyList(), // TODO
    ),
    THSCC_2018_POINTS_1(
        event = Events.Thscc2018Points1Danville,
        expectedSize = 1791,
        rowsWithNoRegistrationOrRun = emptyList(), //TODO
        rowsWithJunkRegistrationWithoutRun = emptyList(), // TODO
        rowsWithRegistrationAndRun = emptyList(), // TODO
        rowsWithoutRegistrationButWithRun = emptyList(), // TODO
    ),
    THSCC_2018_POINTS_8(
        event = Events.Thscc2018Points8Cary,
        expectedSize = 1038,
        rowsWithNoRegistrationOrRun = emptyList(), //TODO
        rowsWithJunkRegistrationWithoutRun = emptyList(), // TODO
        rowsWithRegistrationAndRun = emptyList(), // TODO
        rowsWithoutRegistrationButWithRun = emptyList(), // TODO
    ),
    THSCC_2018_WEDDINGCROSS(
        event = Events.Thscc2018WeddingcrossDanville,
        expectedSize = 517,
        rowsWithNoRegistrationOrRun = emptyList(), //TODO
        rowsWithJunkRegistrationWithoutRun = emptyList(), // TODO
        rowsWithRegistrationAndRun = emptyList(), // TODO
        rowsWithoutRegistrationButWithRun = emptyList(), // TODO
    ),
    THSCC_2019_POINTS_9(
        event = Events.Thscc2019Points8Nccar,
        expectedSize = 2758,
        rowsWithNoRegistrationOrRun = emptyList(), //TODO
        rowsWithJunkRegistrationWithoutRun = emptyList(), // TODO
        rowsWithRegistrationAndRun = emptyList(), // TODO
        rowsWithoutRegistrationButWithRun = emptyList(), // TODO
    );

    data class IndexOfInterest(
        val index: Int,
        val expectedTimestamp: String,
        val expectedSequenceRow: Int
    )
}