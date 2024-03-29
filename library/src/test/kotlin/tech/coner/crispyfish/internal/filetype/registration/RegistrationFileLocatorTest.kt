package tech.coner.crispyfish.internal.filetype.registration

import assertk.assertThat
import assertk.assertions.isSameFileAs
import org.junit.jupiter.api.Test
import tech.coner.crispyfish.test.Events

class RegistrationFileLocatorTest {

    @Test
    fun itShouldLocateRegistrationFile() {
        val eventControlFile = Events.Thscc2016Points3Danville.eventControlFile
        val locator = RegistrationFileLocator(eventControlFile)

        val actual = locator()

        val expected = eventControlFile.file.resolveSibling("2016 Points Event 3 Danville.rgg")
        assertThat(actual).file().isSameFileAs(expected)
    }
}