package org.coner.crispyfish.filetype.registration

import assertk.assert
import assertk.assertions.hasExtension
import assertk.assertions.isNotNull
import assertk.assertions.prop
import org.coner.crispyfish.model.EventDay
import org.coner.crispyfish.test.Events
import org.junit.Test

class RegistrationFileLocatorTest {

    @Test
    fun itShouldLocateRegistrationFile() {
        val locator = Events.Thscc2016Points3Danville.eventControlFile.registrationFileLocator

        val actual = locator.locate()

        assert(actual).isNotNull{ registrationFile ->
            registrationFile.prop(RegistrationFile::file).isNotNull { file ->
                file.hasExtension("rgg")
            }
        }
    }
}