package org.coner.crispyfish.filetype.registration

import assertk.assert
import assertk.assertions.isNotNull
import org.junit.Test
import org.coner.crispyfish.util.TestEvent

class RegistrationFileTest {

    @Test
    fun itShouldReturnColumnReader() {
        val registrationFile = TestEvent.THSCC_2016_POINTS_3.buildRegistrationFile()

        val actual = registrationFile.columnReader()

        assert(actual).isNotNull()
    }
}