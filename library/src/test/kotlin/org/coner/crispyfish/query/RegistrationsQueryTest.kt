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
}