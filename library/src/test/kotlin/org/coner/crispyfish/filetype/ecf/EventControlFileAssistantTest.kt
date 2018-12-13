package org.coner.crispyfish.filetype.ecf

import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test

import java.nio.file.Paths

class EventControlFileAssistantTest {

    private lateinit var eventControlFileAssistant: EventControlFileAssistant

    @Before
    fun setup() {
        eventControlFileAssistant = EventControlFileAssistant()
    }

    @Test
    fun whenIsEcfWithValidPathItShouldReturnTrue() {
        val path = Paths.get("foo.ecf")

        val actual = eventControlFileAssistant.isEventControlFilePath(path)

        Assertions.assertThat(actual).isTrue()
    }

    @Test
    fun whenIsEcfWithRealPathItShouldReturnTrue() {
        val path = Paths.get("/home/me/Projects/Club/2016/2016-03-05 points autox 1 danville.ecf")

        val actual = eventControlFileAssistant.isEventControlFilePath(path)

        Assertions.assertThat(actual).isTrue()
    }

    @Test
    fun whenIsEcfWithInvalidPathItShouldReturnFalse() {
        val path = Paths.get("foo.wrong")

        val actual = eventControlFileAssistant.isEventControlFilePath(path)

        Assertions.assertThat(actual).isFalse()
    }

}
