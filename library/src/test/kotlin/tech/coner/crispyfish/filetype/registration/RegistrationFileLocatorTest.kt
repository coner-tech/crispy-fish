package tech.coner.crispyfish.filetype.registration

import assertk.assertThat
import assertk.assertions.isSameFileAs
import org.junit.jupiter.api.Test
import tech.coner.crispyfish.test.Events

class RegistrationFileLocatorTest {

    @Test
    fun itShouldLocateRegistrationFile() {
        val locator = Events.Thscc2016Points3Danville.eventControlFile.registrationFileLocator

        val actual = locator.locate()

        val expected = locator.eventControlFile.file.resolveSibling("2016 Points Event 3 Danville.rgg")
        assertThat(actual).file().isSameFileAs(expected)
    }
}