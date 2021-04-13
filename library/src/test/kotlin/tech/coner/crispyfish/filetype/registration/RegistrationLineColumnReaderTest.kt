package tech.coner.crispyfish.filetype.registration

import assertk.assertThat
import assertk.assertions.isEqualTo
import tech.coner.crispyfish.filetype.registration.RegistrationColumn.*
import tech.coner.crispyfish.test.Events
import org.junit.Test

internal class RegistrationLineColumnReaderTest {

    lateinit var reader: RegistrationLineColumnReader

    @Test
    fun itShouldReadRegistrationOfCarltonWhiteheadAtThscc2016Points3() {
        val event = Events.Thscc2016Points3Danville
        val registrationFile = event.eventControlFile.registrationFileLocator.locate()
        reader = RegistrationLineColumnReader(registrationFile)
        val index = 103

        assertThat(reader.get(index, Number)).isEqualTo("8")
        assertThat(reader.get(index, Class)).isEqualTo("str")
        assertThat(reader.get(index, FirstName)).isEqualTo("Carlton")
        assertThat(reader.get(index, LastName)).isEqualTo("Whitehead")
        assertThat(reader.get(index, CarModel)).isEqualTo("2002 Honda S2000")
        assertThat(reader.get(index, CarColor)).isEqualTo("Silver")
    }

}