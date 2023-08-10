package tech.coner.crispyfish.query

import assertk.all
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.index
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import org.junit.jupiter.api.Test
import tech.coner.crispyfish.model.*
import tech.coner.crispyfish.test.Events
import tech.coner.crispyfish.test.Issue42

class RegistrationsQueryTest {

    @Test
    fun itShouldQueryThscc2016Points1Registrations() {
        val actual = Events.Thscc2016Points1Danville.eventControlFile.queryRegistrations()

        assertThat(actual).all {
            hasSize(90)
            index(0).all {
                hasCategoryNull()
                hasHandicapAbbreviation("AS")
                hasNumber("86")
                hasFirstName("David")
                hasLastName("Peters")
                hasCarModel("2008 Saturn Sky Redline")
                hasCarColor("Red")
            }
            index(37).all {
                hasCategoryNull()
                hasHandicapAbbreviation("STR")
                hasNumber("8")
                hasFirstName("Carlton")
                hasLastName("Whitehead")
                hasCarModel("2002 Honda S2000")
                hasCarColor("Silver")
            }
            index(65).all {
                hasCategoryAbbreviation("X")
                hasHandicapAbbreviation("GS")
                hasNumber("1")
                hasFirstName("Andrew")
                hasLastName("Pallotta")
                hasCarModel("2013 Ford Focus ST")
                hasCarColor("White")
            }
            index(89).all {
                hasCategoryAbbreviation("OF")
                hasHandicapAbbreviation("BSP")
                hasNumber("8")
                hasFirstName("Neal")
                hasLastName("Harrington")
                hasCarModel("1988 Pontiac FIERO GT")
                hasCarColor("WHITE")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2016Points2Registrations() {
        val actual = Events.Thscc2016Points2Cary.eventControlFile.queryRegistrations()

        assertThat(actual).all {
            hasSize(120)
            index(0).all {
                hasCategoryNull()
                hasHandicapAbbreviation("SS")
                hasNumber("3")
                hasFirstName("Rob")
                hasLastName("Lupella")
                hasCarModel("2002 Chevrolet Corvette Z06")
                hasCarColor("Electron Blue")
            }
            index(43).all {
                hasCategoryNull()
                hasHandicapAbbreviation("STR")
                hasFirstName("Carlton")
                hasLastName("Whitehead")
                hasCarModel("2002 Honda S2000")
                hasCarColor("Silver")
            }
            index(79).all {
                hasCategoryAbbreviation("NOV")
                hasHandicapAbbreviation("DS")
                hasNumber("78")
                hasFirstName("Matthew")
                hasLastName("Ryan")
                hasCarModel("2005 Mini S")
                hasCarColor("Silver")
            }
            index(119).all {
                hasCategoryAbbreviation("OF")
                hasHandicapAbbreviation("BS")
                hasNumber("12")
                hasFirstName("Charles")
                hasLastName("Lankford")
                hasCarModel("2002 Maserati Spyder")
                hasCarColor("Red")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2016Points3Registrations() {
        val actual = Events.Thscc2016Points3Danville.eventControlFile.queryRegistrations()

        assertThat(actual).all {
            hasSize(112)
            index(0).all {
                hasCategoryNull()
                hasHandicapAbbreviation("STX")
                hasNumber("22")
                hasFirstName("Bill")
                hasLastName("Aycock")
                hasCarModel("2012 Volkswagen Golf R")
                hasCarColor("Gray")
            }
            index(62).all {
                hasCategoryAbbreviation("NOV")
                hasHandicapAbbreviation("CAM")
                hasNumber("28")
                hasFirstName("Aris")
                hasLastName("Mantalvanos")
                hasCarModel("2001 Ford Mustang")
                hasCarColor("White")
            }
            index(103).all {
                hasCategoryNull()
                hasHandicapAbbreviation("STR")
                hasNumber("8")
                hasFirstName("Carlton")
                hasLastName("Whitehead")
                hasCarModel("2002 Honda S2000")
                hasCarColor("Silver")
            }
            index(111).all {
                hasCategoryNull()
                hasHandicapAbbreviation("CAM")
                hasNumber("7")
                hasFirstName("Rodney")
                hasLastName("Wright")
                hasCarModel("2008 Ford mustang bullitt")
                hasCarColor("green")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2016Points9Registration() {
        val actual = Events.Thscc2016Points9Cary.eventControlFile.queryRegistrations()

        assertThat(actual).all {
            hasSize(131)
            index(0).all {
                hasCategoryNull()
                hasHandicapAbbreviation("SS")
                hasNumber("3")
                hasFirstName("Rob")
                hasLastName("Lupella")
                hasCarModel("2015 Alfa Romeo 4C")
                hasCarColor("Rosso")
            }
            index(26).all {
                hasCategoryNull()
                hasHandicapAbbreviation("ES")
                hasNumber("87")
                hasFirstName("Blair")
                hasLastName("Deffenbaugh")
                hasCarModel("1996 Mazda Miata (WTF else?)")
                hasCarColor("Blue")
            }
            index(50).all{
                hasCategoryNull()
                hasHandicapAbbreviation("STR")
                hasNumber("8")
                hasFirstName("Carlton")
                hasLastName("Whitehead")
                hasCarModel("2002 Honda S2000")
                hasCarColor("Silver")
            }
            index(130).all {
                hasCategoryAbbreviation("OF")
                hasHandicapAbbreviation("ES")
                hasNumber("62")
                hasFirstName("Dick")
                hasLastName("Sossomon")
                hasCarModel("1997 Mazda Miata")
                hasCarColor("green")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2017Points1Registrations() {
        val actual = Events.Thscc2017Points1Danville.eventControlFile.queryRegistrations()

        assertThat(actual).all {
            hasSize(72)
            index(0).all {
                hasCategoryNull()
                hasHandicapAbbreviation("SS")
                hasNumber("350")
                hasFirstName("Ted")
                hasLastName("Collier")
                hasCarModel("Ford Mustang GT-350R")
                hasCarColor("White")
            }
            index(28).all {
                hasCategoryNull()
                hasHandicapAbbreviation("HS")
                hasNumber("24")
                hasFirstName("Rob")
                hasLastName("Lupella")
                carModel().isNull()
                carColor().isNull()
            }
            index(36).all {
                hasCategoryNull()
                hasHandicapAbbreviation("STR")
                hasNumber("8")
                hasFirstName("Carlton")
                hasLastName("Whitehead")
                hasCarModel("2002 Honda S2000")
                hasCarColor("Silver")
            }
            index(66).all {
                hasCategoryAbbreviation("NOV")
                hasHandicapAbbreviation("STR")
                hasNumber("2")
                hasFirstName("Denise")
                hasLastName("Craig")
                hasCarModel("1998 BMW M Roadster")
                hasCarColor("Red")
            }
            index(71).all {
                hasCategoryAbbreviation("NOV")
                hasHandicapAbbreviation("FS")
                hasNumber("177")
                hasFirstName("Patrick")
                hasLastName("Hargett")
                hasCarModel("2012 BMW 135i")
                hasCarColor("Black")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2017Points5Registrations() {
        val actual = Events.Thscc2017Points5CaryTowneCenter.eventControlFile.queryRegistrations()

        assertThat(actual).all {
            hasSize(110)
            index(0).all {
                hasCategoryNull()
                hasHandicapAbbreviation("SS")
                hasNumber("7")
                hasFirstName("Jordan")
                hasLastName("Normark")
                hasCarModel("2005 Lotus Elise")
                hasCarColor("Orange")
            }
            index(44).all {
                hasCategoryNull()
                hasHandicapAbbreviation("STR")
                hasNumber("8")
                hasFirstName("Carlton")
                hasLastName("Whitehead")
                hasCarModel("2002 Honda S2000")
                hasCarColor("Silver")
            }
            index(75).all {
                hasCategoryAbbreviation("NOV")
                hasHandicapAbbreviation("DS")
                hasNumber("62")
                hasFirstName("Benny")
                hasLastName("Colantuono")
                hasCarModel("2015 Subaru WRX")
                hasCarColor("Grey")
            }
            index(85).all {
                hasCategoryAbbreviation("NOV")
                hasHandicapAbbreviation("CAMC")
                hasNumber("823")
                hasFirstName("Mark")
                hasLastName("Goodwillie")
                hasCarModel("1972 Mustang coupe")
                hasCarColor("Blue")
            }
            index(109).all {
                hasCategoryAbbreviation("OF")
                hasHandicapAbbreviation("SM")
                hasNumber("27")
                hasFirstName("Pitch")
                hasLastName("Woolfolk")
                hasCarModel("1972 Volkswagen Super Beetle")
                hasCarColor("Yellow!")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2017Points9Registrations() {
        val actual = Events.Thscc2017Points9Danville.eventControlFile.queryRegistrations()

        assertThat(actual).all {
            hasSize(116)
            index(1).all {
                hasCategoryNull()
                hasHandicapAbbreviation("DS")
                hasNumber("278")
                hasFirstName("Jack")
                hasLastName("Alvrus")
                hasCarModel("2015 Scion FRS")
                hasCarColor("Black")
            }
            index(41).all {
                hasCategoryAbbreviation("NOV")
                hasHandicapAbbreviation("ES")
                hasNumber("93")
                hasFirstName("Byron")
                hasLastName("Goode")
                hasCarModel("1993 Mazda Miata (LE)")
                hasCarColor("Black")
            }
            index(111).all {
                hasCategoryNull()
                hasHandicapAbbreviation("STR")
                hasNumber("8")
                hasFirstName("Carlton")
                hasLastName("Whitehead")
                hasCarModel("2002 Honda S2000")
                hasCarColor("Silver")
            }
            index(115).all {
                hasCategoryAbbreviation("NOV")
                hasHandicapAbbreviation("ES")
                hasNumber("169")
                hasFirstName("Chris")
                hasLastName("Zaionz")
                hasCarModel("2001 Mazda Miata LS")
                carColor().isNull()
            }
        }
    }

    @Test
    fun itShouldQueryThscc2018Points1Registrations() {
        val actual = Events.Thscc2018Points1Danville.eventControlFile.queryRegistrations()

        assertThat(actual).all {
            hasSize(129)
            index(0).all {
                hasCategoryNull()
                hasHandicapAbbreviation("SS")
                hasNumber("11")
                hasFirstName("Harry")
                hasLastName("Johnson")
                hasCarModel("2006 Lotus Elise")
                hasCarColor("White")
            }
            index(45).all {
                hasCategoryNull()
                hasHandicapAbbreviation("HS")
                hasNumber("17")
                hasFirstName("Carlton")
                hasLastName("Whitehead")
                hasCarModel("2017 Mazda 6")
                hasCarColor("Soul Red")
            }
            index(112).all {
                hasCategoryAbbreviation("X")
                hasHandicapAbbreviation("STX")
                hasNumber("97")
                hasFirstName("David")
                hasLastName("Spratte")
                hasCarModel("2016 Scion FR-S")
                hasCarColor("Asphalt")
            }
            index(123).all {
                hasCategoryAbbreviation("MAC")
                hasHandicapAbbreviation("CAM-C")
                hasNumber("40")
                hasFirstName("Josh")
                hasLastName("Fowler")
                hasCarModel("2011 Ford Mustang")
                hasCarColor("White")
            }
            index(128).all {
                hasCategoryAbbreviation("OF")
                hasHandicapAbbreviation("XP")
                hasNumber("256")
                hasFirstName("Paul")
                hasLastName("Morro")
                hasCarModel("1970 Triumph TR 6")
                hasCarColor("Black")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2018JustForFunRegistrations() {
        val actual = Events.Thscc2018JustForFunCary.eventControlFile.queryRegistrations()

        assertThat(actual).all {
            hasSize(130)
            index(0).all {
                hasCategoryNull()
                hasHandicapAbbreviation("AS")
                hasNumber("111")
                hasFirstName("Jason")
                hasLastName("Tucker")
                hasCarModel("2006 Lotus Elise")
                hasCarColor("Laser Blue")
            }
            index(19).all {
                hasCategoryNull()
                hasHandicapAbbreviation("ES")
                hasNumber("17")
                hasFirstName("Carlton")
                hasLastName("Whitehead")
                hasCarModel("2002 Mazda Miata")
                hasCarColor("Green")
            }
            index(68).all {
                hasCategoryNull()
                hasHandicapAbbreviation("XP")
                hasNumber("23")
                hasFirstName("Bryan")
                hasLastName("Nichols")
                hasCarModel("1989 Nissan Skyline GTR")
                hasCarColor("Blue")
            }
            index(96).all {
                hasCategoryAbbreviation("NOV")
                hasHandicapAbbreviation("SM")
                hasNumber("19")
                hasFirstName("Tim")
                hasLastName("Matijow")
                hasCarModel("2004 Audi A4")
                hasCarColor("silver")
            }
            index(104).all {
                hasCategoryAbbreviation("NOV")
                hasHandicapAbbreviation("SMF")
                hasNumber("3")
                hasFirstName("Anthony")
                hasLastName("Finochio")
                hasCarModel("2005 Mini Cooper S")
                hasCarColor("Blue")
            }
            index(117).all {
                hasCategoryAbbreviation("NOV")
                hasHandicapAbbreviation("DSP")
                hasNumber("298")
                hasFirstName("Roy")
                hasLastName("Skiles")
                hasCarModel("2006 Mazda RX-8")
                hasCarColor("Galaxy Grey")
            }
            index(129).all {
                hasCategoryAbbreviation("OF")
                hasHandicapAbbreviation("BSP")
                hasNumber("8")
                hasFirstName("Neal")
                hasLastName("Harrington")
                hasCarModel("1988 Pontiac FIERO GT")
                hasCarColor("WHITE")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2018WeddingcrossRegistrations() {
        val actual = Events.Thscc2018WeddingcrossDanville.eventControlFile.queryRegistrations()

        assertThat(actual).all {
            hasSize(83)
            index(0).all {
                hasCategoryNull()
                hasHandicapAbbreviation("STR")
                hasNumber("41")
                hasFirstName("Nick")
                hasLastName("Babin")
                hasCarModel("2016 Mazda Miata")
                hasCarColor("Red")
            }
            index(31).all {
                hasCategoryNull()
                hasHandicapAbbreviation("CS")
                hasNumber("39")
                hasFirstName("John")
                hasLastName("Joyner")
                hasCarModel("2014 BMW 335i")
                hasCarColor("Estoril Blue")
            }
            index(43).all {
                hasCategoryAbbreviation("X")
                hasHandicapAbbreviation("CAM-C")
                hasNumber("57")
                hasFirstName("Gordon")
                hasLastName("Maciulewicz")
                hasCarModel("2018 Nissan Rental Maxima")
                hasCarColor("TBD")
            }
            index(47).all {
                hasCategoryAbbreviation("X")
                hasHandicapAbbreviation("STR")
                hasNumber("196")
                hasFirstName("Maria")
                hasLastName("Pallotta")
                hasCarModel("2016 Mazda Miata")
                hasCarColor("Grey")
                runNumber(1) {
                    hasTime("43.666")
                    isDidNotFinish()
                }
            }
            index(60).all {
                hasCategoryAbbreviation("X")
                hasHandicapAbbreviation("STR")
                hasNumber("96")
                hasFirstName("Andrew")
                hasLastName("Pallotta")
                hasCarModel("2016 Mazda MX5")
                hasCarColor("White")
            }
            index(79).all {
                hasCategoryNull()
                hasHandicapAbbreviation("STR")
                hasNumber("8")
                hasFirstName("Carlton")
                hasLastName("Whitehead")
                hasCarModel("2002 Honda S2000")
                hasCarColor("Silver")
                rawResult {
                    hasPosition(11)
                    hasTime("42.335")
                }
                paxResult {
                    hasPosition(17)
                    hasTime("34.841")
                }
                runNumber(1) {
                    hasTime("44.134")
                    isClean()
                }
                runNumber(2) {
                    hasTime("42.838")
                    hasConeCount(1)
                }
            }
            index(82).all {
                hasCategoryAbbreviation("NOV")
                hasHandicapAbbreviation("STR")
                hasNumber("5")
                hasFirstName("Joseph")
                hasLastName("Zavala")
                hasCarModel("2003 Toyota Mr2 spyder")
                hasCarColor("Silver")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2018Points8Registrations() {
        val actual = Events.Thscc2018Points8Cary.eventControlFile.queryRegistrations()

        assertThat(actual).all {
            hasSize(129)
            index(0).all {
                hasCategoryAbbreviation("NOV")
                hasHandicapAbbreviation("HS")
                hasNumber("27")
                hasFirstName("Andrew")
                hasLastName("Ahr")
                hasCarModel("2009 Acura TSX")
            }
            index(28).all {
                hasCategoryNull()
                hasHandicapAbbreviation("HS")
                hasNumber("99")
                hasFirstName("Austin")
                hasLastName("Culbertson")
                hasCarModel("2014 Ford Fiesta ST")
                hasCarColor("Green")
            }
            index(82).all {
                hasCategoryAbbreviation("MAC")
                hasHandicapAbbreviation("CAM-S")
                hasNumber("722")
                hasFirstName("Jake")
                hasLastName("Nicholas")
                hasCarModel("2000 Corvette")
                hasCarColor("Gray")
            }
            index(123).all {
                hasCategoryNull()
                hasHandicapAbbreviation("STR")
                hasNumber("8")
                hasFirstName("Carlton")
                hasLastName("Whitehead")
                hasCarModel("2002 Honda S2000")
                hasCarColor("Silver")
            }
            index(128).all {
                hasCategoryAbbreviation("NOV")
                hasHandicapAbbreviation("STR")
                hasNumber("5")
                hasFirstName("Joseph")
                hasLastName("Zavala")
                hasCarModel("1999 Mazda Miata")
                carColor().isNull()
            }
        }
    }

    /**
     * https://github.com/coner-tech/crispy-fish/issues/42
     * https://github.com/coner-tech/coner-trailer/issues/64
     */
    @Test
    fun `It should query all registrations even those having raw time but lacking raw position`() {
        val actual = Issue42.eventControlFile.queryRegistrations()

        assertThat(actual).all {
            hasSize(3)
            index(0).all {
                rawResult().isNull()
                paxResult().isNull()
            }
            index(1).all {
                rawResult().isNotNull()
                paxResult().isNotNull()
            }
            index(2).all {
                rawResult().isNull()
                paxResult().isNull()
            }
        }
    }
}