package tech.coner.crispyfish.query

import assertk.all
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.index
import tech.coner.crispyfish.model.*
import tech.coner.crispyfish.test.Events
import org.junit.Test

class ThsccConeKillerQueryTest {

    @Test
    fun itShouldQueryThscc2019Points8ConeKiller() {
        val event = Events.Thscc2019Points8Nccar
        val actual = ThsccConeKillerQuery(
            eventControlFile = event.eventControlFile
        ).query()

        assertThat(actual).all {
            index(0).all {
                hasPosition(1)
                registration {
                    hasCategoryAbbreviation("MAC")
                    hasHandicapAbbreviation("CAM-C")
                    hasNumber("63")
                    hasMemberNumber("000355")
                }
                conedRuns {
                    hasSize(4)
                    index(0).hasConeCount(9)
                    index(1).hasConeCount(7)
                    index(2).hasConeCount(3)
                    index(3).hasConeCount(2)
                }
            }
            index(1).all {
                hasPosition(2)
                registration {
                    hasCategoryAbbreviation("NOV")
                    hasHandicapAbbreviation("STH")
                    hasNumber("19")
                    hasMemberNumber("2019-00535")
                }
                conedRuns {
                    hasSize(3)
                    index(0).hasConeCount(7)
                    index(1).hasConeCount(3)
                    index(2).hasConeCount(1)
                }
            }
            index(2).all {
                hasPosition(3)
                registration {
                    hasCategoryAbbreviation("NOV")
                    hasHandicapAbbreviation("STR")
                    hasNumber("99")
                    hasMemberNumber("2019-00533")
                }
                conedRuns {
                    hasSize(5)
                    index(0).hasConeCount(6)
                    index(1).hasConeCount(5)
                    index(2).hasConeCount(3)
                    index(3).hasConeCount(3)
                    index(4).hasConeCount(2)

                }
            }
            index(3).all {
                hasPosition(4)
                registration {
                    hasCategoryNull()
                    hasHandicapAbbreviation("AS")
                    hasNumber("91")
                    hasMemberNumber("TBD")
                }
                conedRuns {
                    hasSize(4)
                    index(0).hasConeCount(6)
                    index(1).hasConeCount(4)
                    index(2).hasConeCount(1)
                    index(3).hasConeCount(1)
                }
            }
            index(4).all {
                hasPosition(5)
                registration {
                    hasCategoryNull()
                    hasHandicapAbbreviation("ASP")
                    hasNumber("11")
                    hasMemberNumber("2019-00087")
                }
                conedRuns {
                    hasSize(2)
                    index(0).hasConeCount(6)
                    index(1).hasConeCount(4)
                }
            }
            index(5).all {
                hasPosition(6)
                registration {
                    hasCategoryAbbreviation("NOV")
                    hasHandicapAbbreviation("CAM-T")
                    hasNumber("9")
                    hasMemberNumber("2019-00179")
                }
                conedRuns {
                    hasSize(5)
                    index(0).hasConeCount(6)
                    index(1).hasConeCount(1)
                    index(2).hasConeCount(1)
                    index(3).hasConeCount(1)
                    index(4).hasConeCount(1)
                }
            }
            index(6).all {
                hasPosition(7)
                registration {
                    hasCategoryAbbreviation("MAC")
                    hasHandicapAbbreviation("CAM-C")
                    hasNumber("23")
                    hasMemberNumber("2204")
                }
                conedRuns {
                    hasSize(4)
                    index(0).hasConeCount(5)
                    index(1).hasConeCount(5)
                    index(2).hasConeCount(3)
                    index(3).hasConeCount(2)
                }
            }
            index(7).all {
                hasPosition(8)
                registration {
                    hasCategoryAbbreviation("MAC")
                    hasHandicapAbbreviation("CAM-S")
                    hasNumber("6")
                    hasMemberNumber("2019-00248")
                }
                conedRuns {
                    hasSize(4)
                    index(0).hasConeCount(5)
                    index(1).hasConeCount(4)
                    index(2).hasConeCount(3)
                    index(3).hasConeCount(2)
                }
            }
            index(8).all {
                hasPosition(9)
                registration {
                    hasCategoryAbbreviation("NOV")
                    hasHandicapAbbreviation("STH")
                    hasNumber("2")
                    hasMemberNumber("2019-00361")
                }
                conedRuns {
                    hasSize(3)
                    index(0).hasConeCount(5)
                    index(1).hasConeCount(4)
                    index(2).hasConeCount(2)
                }
            }
            index(9).all {
                hasPosition(10)
                registration {
                    hasCategoryNull()
                    hasHandicapAbbreviation("STR")
                    hasNumber("14")
                    hasMemberNumber("1382")
                }
                conedRuns {
                    hasSize(5)
                    index(0).hasConeCount(5)
                    index(1).hasConeCount(3)
                    index(2).hasConeCount(2)
                    index(3).hasConeCount(1)
                    index(4).hasConeCount(1)
                }
            }
        }
    }
}