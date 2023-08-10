package tech.coner.crispyfish.filetype.staging

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import tech.coner.crispyfish.filetype.classdefinition.ClassDefinitionFile
import tech.coner.crispyfish.filetype.ecf.EventControlFile
import tech.coner.crispyfish.model.EventDay
import java.nio.file.Path

class StagingFileLocatorTest {

    private lateinit var stagingFileLocator: StagingFileLocator

    @TempDir
    private lateinit var temp: Path
    private lateinit var eventControlFile: EventControlFile

    @BeforeEach
    fun setup() {
        eventControlFile = EventControlFile(
            file = temp.resolve("test.ecf").toFile(),
            classDefinitionFile = ClassDefinitionFile(temp.resolve("test.def").toFile()),
            isTwoDayEvent = false,
        )
        stagingFileLocator = StagingFileLocator(
                eventControlFile = eventControlFile,
        )
    }

    @Test
    fun itShouldLocateWithDayOne() {
        val actual = stagingFileLocator.locate(EventDay.ONE)

        assertThat(actual).isEqualTo(temp.resolve("test.st1").toFile())
    }

    @Test
    fun itShouldLocateWithDayTwo() {
        val actual = stagingFileLocator.locate(EventDay.TWO)

        assertThat(actual).isEqualTo(temp.resolve("test.st2").toFile())
    }

}
