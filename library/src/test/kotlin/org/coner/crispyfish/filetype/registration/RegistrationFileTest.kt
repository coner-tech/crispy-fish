package org.coner.crispyfish.filetype.registration

import assertk.assertThat
import assertk.assertions.isNotNull
import assertk.assertions.isNotSameAs
import org.coner.crispyfish.test.Events
import org.junit.Test

class RegistrationFileTest {

    @Test
    fun itShouldReturnColumnReader() {
        val registrationFile = Events.Thscc2016Points3Danville.eventControlFile.registrationFile()

        val actual = registrationFile.columnReader()

        assertThat(actual).isNotNull()
    }

    @Test
    fun itShouldReturnUniqueColumnReaders() {
        val registrationFile = Events.Thscc2016Points3Danville.eventControlFile.registrationFile()

        val first = registrationFile.columnReader()
        val second = registrationFile.columnReader()

        assertThat(first).isNotSameAs(second)
    }
}