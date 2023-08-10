package tech.coner.crispyfish.filetype.ecf

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class EventControlFileAssistantTest {

    private lateinit var eventControlFileAssistant: EventControlFileAssistant

    @BeforeEach
    fun setup() {
        eventControlFileAssistant = EventControlFileAssistant()
    }

    @Test
    fun whenIsEcfWithValidPathItShouldReturnTrue() {
        val file = File("foo.ecf")

        val actual = eventControlFileAssistant.isEventControlFile(file)

        Assertions.assertThat(actual).isTrue()
    }

    @Test
    fun whenIsEcfWithRealPathItShouldReturnTrue() {
        val file = File("/home/me/Projects/Club/2016/2016-03-05 points autox 1 danville.ecf")

        val actual = eventControlFileAssistant.isEventControlFile(file)

        Assertions.assertThat(actual).isTrue()
    }

    @Test
    fun whenIsEcfWithInvalidPathItShouldReturnFalse() {
        val file = File("foo.wrong")

        val actual = eventControlFileAssistant.isEventControlFile(file)

        Assertions.assertThat(actual).isFalse()
    }

}
