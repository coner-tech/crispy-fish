package tech.coner.crispyfish.internal.filetype.registration

import assertk.assertThat
import assertk.assertions.isEqualTo
import org.junit.jupiter.api.Test
import tech.coner.crispyfish.test.Events

internal class RegistrationLineColumnReaderTest {

    lateinit var reader: RegistrationLineColumnReader

    @Test
    fun itShouldReadRegistrationOfCarltonWhiteheadAtThscc2016Points3() {
        val eventControlFile = Events.Thscc2016Points3Danville.eventControlFile
        val registrationFile = RegistrationFileLocator(eventControlFile).invoke()
        reader = RegistrationLineColumnReader(registrationFile)
        val index = 103

        assertThat(reader.get(index, RegistrationColumn.Number)).isEqualTo("8")
        assertThat(reader.get(index, RegistrationColumn.Class)).isEqualTo("str")
        assertThat(reader.get(index, RegistrationColumn.FirstName)).isEqualTo("Carlton")
        assertThat(reader.get(index, RegistrationColumn.LastName)).isEqualTo("Whitehead")
        assertThat(reader.get(index, RegistrationColumn.CarModel)).isEqualTo("2002 Honda S2000")
        assertThat(reader.get(index, RegistrationColumn.CarColor)).isEqualTo("Silver")
    }

}