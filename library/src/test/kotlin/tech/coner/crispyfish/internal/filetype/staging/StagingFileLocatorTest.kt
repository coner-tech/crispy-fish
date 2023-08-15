package tech.coner.crispyfish.internal.filetype.staging

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import tech.coner.crispyfish.internal.filetype.eventcontrolfile.EventControlFile
import tech.coner.crispyfish.model.EventDay
import java.nio.file.Path

class StagingFileLocatorTest {

    private lateinit var stagingFileLocator: StagingFileLocator

    @TempDir
    private lateinit var temp: Path

    @BeforeEach
    fun setup() {
        stagingFileLocator = StagingFileLocator(
            eventControlFile = EventControlFile(temp.resolve("test.ecf"))
        )
    }

    @Test
    fun itShouldLocateWithDayOne() {
        val actual = stagingFileLocator(EventDay.ONE)

        assertThat(actual).isEqualTo(StagingFile(temp.resolve("test.st1")))
    }

    @Test
    fun itShouldLocateWithDayTwo() {
        val actual = stagingFileLocator(EventDay.TWO)

        assertThat(actual).isEqualTo(StagingFile(temp.resolve("test.st2")))
    }

}
