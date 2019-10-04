package org.coner.crispyfish.filetype.registration

import assertk.assertThat
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

        assertThat(reader.readNumber(index)).isEqualTo("8")
        assertThat(reader.readClass(index)).isEqualTo("str")
        assertThat(reader.readFirstName(index)).isEqualTo("Carlton")
        assertThat(reader.readLastName(index)).isEqualTo("Whitehead")
        assertThat(reader.readCarModel(index)).isEqualTo("2002 Honda S2000")
        assertThat(reader.readCarColor(index)).isEqualTo("Silver")
    }

}