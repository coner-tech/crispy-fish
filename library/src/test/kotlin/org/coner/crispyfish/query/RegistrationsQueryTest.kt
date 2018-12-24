package org.coner.crispyfish.query

import assertk.all
import assertk.assert
import assertk.assertions.*
import org.coner.crispyfish.model.*
import org.coner.crispyfish.test.Events
import org.junit.Test

class RegistrationsQueryTest {

    @Test
    fun itShouldQueryThscc2016Points1Registrations() {
        val actual = Events.Thscc2016Points1Danville.eventControlFile.queryRegistrations()

        assert(actual).all {
            hasSize(90)
            index(0) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("AS")
                it.hasNumber("86")
                it.hasFirstName("David")
                it.hasLastName("Peters")
                it.hasCarModel("2008 Saturn Sky Redline")
                it.hasCarColor("Red")
            }
            index(37) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("STR")
                it.hasNumber("8")
                it.hasFirstName("Carlton")
                it.hasLastName("Whitehead")
                it.hasCarModel("2002 Honda S2000")
                it.hasCarColor("Silver")
            }
            index(65) {
                it.hasCategoryAbbreviation("X")
                it.hasHandicapAbbreviation("GS")
                it.hasNumber("1")
                it.hasFirstName("Andrew")
                it.hasLastName("Pallotta")
                it.hasCarModel("2013 Ford Focus ST")
                it.hasCarColor("White")
            }
            index(89) {
                it.hasCategoryAbbreviation("OF")
                it.hasHandicapAbbreviation("BSP")
                it.hasNumber("8")
                it.hasFirstName("Neal")
                it.hasLastName("Harrington")
                it.hasCarModel("1988 Pontiac FIERO GT")
                it.hasCarColor("WHITE")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2016Points2Registrations() {
        val actual = Events.Thscc2016Points2Cary.eventControlFile.queryRegistrations()

        assert(actual).all {
            hasSize(120)
            index(0) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("SS")
                it.hasNumber("3")
                it.hasFirstName("Rob")
                it.hasLastName("Lupella")
                it.hasCarModel("2002 Chevrolet Corvette Z06")
                it.hasCarColor("Electron Blue")
            }
            index(43) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("STR")
                it.hasFirstName("Carlton")
                it.hasLastName("Whitehead")
                it.hasCarModel("2002 Honda S2000")
                it.hasCarColor("Silver")
            }
            index(79) {
                it.hasCategoryAbbreviation("NOV")
                it.hasHandicapAbbreviation("DS")
                it.hasNumber("78")
                it.hasFirstName("Matthew")
                it.hasLastName("Ryan")
                it.hasCarModel("2005 Mini S")
                it.hasCarColor("Silver")
            }
            index(119) {
                it.hasCategoryAbbreviation("OF")
                it.hasHandicapAbbreviation("BS")
                it.hasNumber("12")
                it.hasFirstName("Charles")
                it.hasLastName("Lankford")
                it.hasCarModel("2002 Maserati Spyder")
                it.hasCarColor("Red")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2016Points3Registrations() {
        val actual = Events.Thscc2016Points3Danville.eventControlFile.queryRegistrations()

        assert(actual).all {
            hasSize(112)
            index(0) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("STX")
                it.hasNumber("22")
                it.hasFirstName("Bill")
                it.hasLastName("Aycock")
                it.hasCarModel("2012 Volkswagen Golf R")
                it.hasCarColor("Gray")
            }
            index(62) {
                it.hasCategoryAbbreviation("NOV")
                it.hasHandicapAbbreviation("CAM")
                it.hasNumber("28")
                it.hasFirstName("Aris")
                it.hasLastName("Mantalvanos")
                it.hasCarModel("2001 Ford Mustang")
                it.hasCarColor("White")
            }
            index(103) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("STR")
                it.hasNumber("8")
                it.hasFirstName("Carlton")
                it.hasLastName("Whitehead")
                it.hasCarModel("2002 Honda S2000")
                it.hasCarColor("Silver")
            }
            index(111) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("CAM")
                it.hasNumber("7")
                it.hasFirstName("Rodney")
                it.hasLastName("Wright")
                it.hasCarModel("2008 Ford mustang bullitt")
                it.hasCarColor("green")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2016Points9Registration() {
        val actual = Events.Thscc2016Points9Cary.eventControlFile.queryRegistrations()

        assert(actual).all {
            hasSize(131)
            index(0) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("SS")
                it.hasNumber("3")
                it.hasFirstName("Rob")
                it.hasLastName("Lupella")
                it.hasCarModel("2015 Alfa Romeo 4C")
                it.hasCarColor("Rosso")
            }
            index(26) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("ES")
                it.hasNumber("87")
                it.hasFirstName("Blair")
                it.hasLastName("Deffenbaugh")
                it.hasCarModel("1996 Mazda Miata (WTF else?)")
                it.hasCarColor("Blue")
            }
            index(50) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("STR")
                it.hasNumber("8")
                it.hasFirstName("Carlton")
                it.hasLastName("Whitehead")
                it.hasCarModel("2002 Honda S2000")
                it.hasCarColor("Silver")
            }
            index(130) {
                it.hasCategoryAbbreviation("OF")
                it.hasHandicapAbbreviation("ES")
                it.hasNumber("62")
                it.hasFirstName("Dick")
                it.hasLastName("Sossomon")
                it.hasCarModel("1997 Mazda Miata")
                it.hasCarColor("green")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2017Points1Registrations() {
        val actual = Events.Thscc2017Points1Danville.eventControlFile.queryRegistrations()

        assert(actual).all {
            hasSize(72)
            index(0) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("SS")
                it.hasNumber("350")
                it.hasFirstName("Ted")
                it.hasLastName("Collier")
                it.hasCarModel("Ford Mustang GT-350R")
                it.hasCarColor("White")
            }
            index(28) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("HS")
                it.hasNumber("24")
                it.hasFirstName("Rob")
                it.hasLastName("Lupella")
                it.hasCarModel("")
                it.hasCarColor("")
            }
            index(36) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("STR")
                it.hasNumber("8")
                it.hasFirstName("Carlton")
                it.hasLastName("Whitehead")
                it.hasCarModel("2002 Honda S2000")
                it.hasCarColor("Silver")
            }
            index(66) {
                it.hasCategoryAbbreviation("NOV")
                it.hasHandicapAbbreviation("STR")
                it.hasNumber("2")
                it.hasFirstName("Denise")
                it.hasLastName("Craig")
                it.hasCarModel("1998 BMW M Roadster")
                it.hasCarColor("Red")
            }
            index(71) {
                it.hasCategoryAbbreviation("NOV")
                it.hasHandicapAbbreviation("FS")
                it.hasNumber("177")
                it.hasFirstName("Patrick")
                it.hasLastName("Hargett")
                it.hasCarModel("2012 BMW 135i")
                it.hasCarColor("Black")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2017Points5Registrations() {
        val actual = Events.Thscc2017Points5CaryTowneCenter.eventControlFile.queryRegistrations()

        assert(actual).all {
            hasSize(110)
            index(0) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("SS")
                it.hasNumber("7")
                it.hasFirstName("Jordan")
                it.hasLastName("Normark")
                it.hasCarModel("2005 Lotus Elise")
                it.hasCarColor("Orange")
            }
            index(44) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("STR")
                it.hasNumber("8")
                it.hasFirstName("Carlton")
                it.hasLastName("Whitehead")
                it.hasCarModel("2002 Honda S2000")
                it.hasCarColor("Silver")
            }
            index(75) {
                it.hasCategoryAbbreviation("NOV")
                it.hasHandicapAbbreviation("DS")
                it.hasNumber("62")
                it.hasFirstName("Benny")
                it.hasLastName("Colantuono")
                it.hasCarModel("2015 Subaru WRX")
                it.hasCarColor("Grey")
            }
            index(85) {
                it.hasCategoryAbbreviation("NOV")
                it.hasHandicapAbbreviation("CAMC")
                it.hasNumber("823")
                it.hasFirstName("Mark")
                it.hasLastName("Goodwillie")
                it.hasCarModel("1972 Mustang coupe")
                it.hasCarColor("Blue")
            }
            index(109) {
                it.hasCategoryAbbreviation("OF")
                it.hasHandicapAbbreviation("SM")
                it.hasNumber("27")
                it.hasFirstName("Pitch")
                it.hasLastName("Woolfolk")
                it.hasCarModel("1972 Volkswagen Super Beetle")
                it.hasCarColor("Yellow!")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2017Points9Registrations() {
        val actual = Events.Thscc2017Points9Danville.eventControlFile.queryRegistrations()

        assert(actual).all {
            hasSize(116)
            index(1) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("DS")
                it.hasNumber("278")
                it.hasFirstName("Jack")
                it.hasLastName("Alvrus")
                it.hasCarModel("2015 Scion FRS")
                it.hasCarColor("Black")
            }
            index(41) {
                it.hasCategoryAbbreviation("NOV")
                it.hasHandicapAbbreviation("ES")
                it.hasNumber("93")
                it.hasFirstName("Byron")
                it.hasLastName("Goode")
                it.hasCarModel("1993 Mazda Miata (LE)")
                it.hasCarColor("Black")
            }
            index(111) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("STR")
                it.hasNumber("8")
                it.hasFirstName("Carlton")
                it.hasLastName("Whitehead")
                it.hasCarModel("2002 Honda S2000")
                it.hasCarColor("Silver")
            }
            index(115) {
                it.hasCategoryAbbreviation("NOV")
                it.hasHandicapAbbreviation("ES")
                it.hasNumber("169")
                it.hasFirstName("Chris")
                it.hasLastName("Zaionz")
                it.hasCarModel("2001 Mazda Miata LS")
                it.hasCarColor("")
            }
        }
    }
}