package org.coner.crispyfish.filetype.registration

import assertk.assert
import assertk.assertions.isEqualTo
import org.coner.crispyfish.test.Events
import org.junit.Test

class RegistrationLineColumnReaderTest {

    lateinit var reader: RegistrationLineColumnReader

    @Test
    fun itShouldReadRegistrationOfCarltonWhiteheadAtThscc2016Points3() {
        val event = Events.Thscc2016Points3Danville
        val registrationFile = event.eventControlFile.registrationFileLocator.locate()
        reader = RegistrationLineColumnReader(registrationFile)
        val index = 103

        assert(reader.readNumber(index)).isEqualTo("8")
        assert(reader.readClass(index)).isEqualTo("str")
        assert(reader.readFirstName(index)).isEqualTo("Carlton")
        assert(reader.readLastName(index)).isEqualTo("Whitehead")
        assert(reader.readCarModel(index)).isEqualTo("2002 Honda S2000")
        assert(reader.readCarColor(index)).isEqualTo("Silver")
    }

}