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

    @Test
    fun itShouldQueryThscc2018Points1Registrations() {
        val actual = Events.Thscc2018Points1Danville.eventControlFile.queryRegistrations()

        assert(actual).all {
            hasSize(129)
            index(0) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("SS")
                it.hasNumber("11")
                it.hasFirstName("Harry")
                it.hasLastName("Johnson")
                it.hasCarModel("2006 Lotus Elise")
                it.hasCarColor("White")
            }
            index(45) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("HS")
                it.hasNumber("17")
                it.hasFirstName("Carlton")
                it.hasLastName("Whitehead")
                it.hasCarModel("2017 Mazda 6")
                it.hasCarColor("Soul Red")
            }
            index(112) {
                it.hasCategoryAbbreviation("X")
                it.hasHandicapAbbreviation("STX")
                it.hasNumber("97")
                it.hasFirstName("David")
                it.hasLastName("Spratte")
                it.hasCarModel("2016 Scion FR-S")
                it.hasCarColor("Asphalt")
            }
            index(123) {
                it.hasCategoryAbbreviation("MAC")
                it.hasHandicapAbbreviation("CAM-C")
                it.hasNumber("40")
                it.hasFirstName("Josh")
                it.hasLastName("Fowler")
                it.hasCarModel("2011 Ford Mustang")
                it.hasCarColor("White")
            }
            index(128) {
                it.hasCategoryAbbreviation("OF")
                it.hasHandicapAbbreviation("XP")
                it.hasNumber("256")
                it.hasFirstName("Paul")
                it.hasLastName("Morro")
                it.hasCarModel("1970 Triumph TR 6")
                it.hasCarColor("Black")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2018JustForFunRegistrations() {
        val actual = Events.Thscc2018JustForFunCary.eventControlFile.queryRegistrations()

        assert(actual).all {
            hasSize(130)
            index(0) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("AS")
                it.hasNumber("111")
                it.hasFirstName("Jason")
                it.hasLastName("Tucker")
                it.hasCarModel("2006 Lotus Elise")
                it.hasCarColor("Laser Blue")
            }
            index(19) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("ES")
                it.hasNumber("17")
                it.hasFirstName("Carlton")
                it.hasLastName("Whitehead")
                it.hasCarModel("2002 Mazda Miata")
                it.hasCarColor("Green")
            }
            index(68) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("XP")
                it.hasNumber("23")
                it.hasFirstName("Bryan")
                it.hasLastName("Nichols")
                it.hasCarModel("1989 Nissan Skyline GTR")
                it.hasCarColor("Blue")
            }
            index(96) {
                it.hasCategoryAbbreviation("NOV")
                it.hasHandicapAbbreviation("SM")
                it.hasNumber("19")
                it.hasFirstName("Tim")
                it.hasLastName("Matijow")
                it.hasCarModel("2004 Audi A4")
                it.hasCarColor("silver")
            }
            index(104) {
                it.hasCategoryAbbreviation("NOV")
                it.hasHandicapAbbreviation("SMF")
                it.hasNumber("3")
                it.hasFirstName("Anthony")
                it.hasLastName("Finochio")
                it.hasCarModel("2005 Mini Cooper S")
                it.hasCarColor("Blue")
            }
            index(117) {
                it.hasCategoryAbbreviation("NOV")
                it.hasHandicapAbbreviation("DSP")
                it.hasNumber("298")
                it.hasFirstName("Roy")
                it.hasLastName("Skiles")
                it.hasCarModel("2006 Mazda RX-8")
                it.hasCarColor("Galaxy Grey")
            }
            index(129) {
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
    fun itShouldQueryThscc2018WeddingcrossRegistrations() {
        val actual = Events.Thscc2018WeddingcrossDanville.eventControlFile.queryRegistrations()

        assert(actual).all {
            hasSize(83)
            index(0) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("STR")
                it.hasNumber("41")
                it.hasFirstName("Nick")
                it.hasLastName("Babin")
                it.hasCarModel("2016 Mazda Miata")
                it.hasCarColor("Red")
            }
            index(31) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("CS")
                it.hasNumber("39")
                it.hasFirstName("John")
                it.hasLastName("Joyner")
                it.hasCarModel("2014 BMW 335i")
                it.hasCarColor("Estoril Blue")
            }
            index(43) {
                it.hasCategoryAbbreviation("X")
                it.hasHandicapAbbreviation("CAM-C")
                it.hasNumber("57")
                it.hasFirstName("Gordon")
                it.hasLastName("Maciulewicz")
                it.hasCarModel("2018 Nissan Rental Maxima")
                it.hasCarColor("TBD")
            }
            index(47) {
                it.hasCategoryAbbreviation("X")
                it.hasHandicapAbbreviation("STR")
                it.hasNumber("196")
                it.hasFirstName("Maria")
                it.hasLastName("Pallotta")
                it.hasCarModel("2016 Mazda Miata")
                it.hasCarColor("Grey")
            }
            index(60) {
                it.hasCategoryAbbreviation("X")
                it.hasHandicapAbbreviation("STR")
                it.hasNumber("96")
                it.hasFirstName("Andrew")
                it.hasLastName("Pallotta")
                it.hasCarModel("2016 Mazda MX5")
                it.hasCarColor("White")
            }
            index(79) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("STR")
                it.hasNumber("8")
                it.hasFirstName("Carlton")
                it.hasLastName("Whitehead")
                it.hasCarModel("2002 Honda S2000")
                it.hasCarColor("Silver")
            }
            index(82) {
                it.hasCategoryAbbreviation("NOV")
                it.hasHandicapAbbreviation("STR")
                it.hasNumber("5")
                it.hasFirstName("Joseph")
                it.hasLastName("Zavala")
                it.hasCarModel("2003 Toyota Mr2 spyder")
                it.hasCarColor("Silver")
            }
        }
    }

    @Test
    fun itShouldQueryThscc2018Points8Registrations() {
        val actual = Events.Thscc2018Points8Cary.eventControlFile.queryRegistrations()

        assert(actual).all {
            hasSize(129)
            index(0) {
                it.hasCategoryAbbreviation("NOV")
                it.hasHandicapAbbreviation("HS")
                it.hasNumber("27")
                it.hasFirstName("Andrew")
                it.hasLastName("Ahr")
                it.hasCarModel("2009 Acura TSX")
            }
            index(28) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("HS")
                it.hasNumber("99")
                it.hasFirstName("Austin")
                it.hasLastName("Culbertson")
                it.hasCarModel("2014 Ford Fiesta ST")
                it.hasCarColor("Green")
            }
            index(82) {
                it.hasCategoryAbbreviation("MAC")
                it.hasHandicapAbbreviation("CAM-S")
                it.hasNumber("722")
                it.hasFirstName("Jake")
                it.hasLastName("Nicholas")
                it.hasCarModel("2000 Corvette")
                it.hasCarColor("Gray")
            }
            index(123) {
                it.hasCategoryNull()
                it.hasHandicapAbbreviation("STR")
                it.hasNumber("8")
                it.hasFirstName("Carlton")
                it.hasLastName("Whitehead")
                it.hasCarModel("2002 Honda S2000")
                it.hasCarColor("Silver")
            }
            index(128) {
                it.hasCategoryAbbreviation("NOV")
                it.hasHandicapAbbreviation("STR")
                it.hasNumber("5")
                it.hasFirstName("Joseph")
                it.hasLastName("Zavala")
                it.hasCarModel("1999 Mazda Miata")
                it.hasCarColor("")
            }
        }
    }
}