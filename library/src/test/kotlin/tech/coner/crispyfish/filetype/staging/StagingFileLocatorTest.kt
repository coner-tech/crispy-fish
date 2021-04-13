package tech.coner.crispyfish.filetype.staging

import org.assertj.core.api.Assertions.assertThat
import tech.coner.crispyfish.filetype.ecf.EventControlFile
import tech.coner.crispyfish.model.EventDay
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.io.File
import java.nio.file.Path
import java.nio.file.Paths

@RunWith(MockitoJUnitRunner::class)
class StagingFileLocatorTest {

    private lateinit var stagingFileLocator: StagingFileLocator

    @Mock
    lateinit var stagingFileAssistant: StagingFileAssistant
    @Mock
    lateinit var eventControlFile: EventControlFile
    @Mock
    lateinit var eventControlFilePath: Path
    @Mock
    lateinit var eventControlFileFile: File
    @Mock
    lateinit var eventControlFileParentAsFile: File
    @Mock
    lateinit var stagingFilenameFilter: StagingFilenameFilter

    @Before
    fun setup() {
        stagingFileLocator = StagingFileLocator(
                eventControlFile = eventControlFile,
                stagingFileAssistant = stagingFileAssistant
        )
    }

    @Test
    fun whenLocateWithSimpleStagingItShouldLocateStaging() {
        `when`(eventControlFile.file).thenReturn(eventControlFileFile)
        `when`(eventControlFileFile.parentFile).thenReturn(eventControlFileParentAsFile)
        `when`(stagingFileAssistant.buildStagingFilenameFilter(
                Mockito.same(eventControlFile) ?: eventControlFile,
                Mockito.any(EventDay::class.java) ?: EventDay.ONE
        )).thenReturn(stagingFilenameFilter)
        val ecfPath = File("foo.ecf")
        val files = arrayOf(ecfPath)
        `when`(eventControlFileParentAsFile.listFiles(stagingFilenameFilter)).thenReturn(files)

        val actual = stagingFileLocator.locate(EventDay.ONE)

        assertThat(actual).isEqualTo(ecfPath)
    }

    @Test
    fun whenSelectFileWithSimpleStagingItShouldSelectIt() {
        val expected = Paths.get("foo.st1").toFile()
        val files = arrayOf(expected)

        val actual = stagingFileLocator.selectFile(files)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun whenSelectFileWithSimpleStagingAndSomeGarbageItShouldSelectExpected() {
        val expected = Paths.get("foo.st1").toFile()
        val files = arrayOf(Paths.get("foo.txt").toFile(), Paths.get("foo.html").toFile(), expected, Paths.get("foo.st1_log").toFile())

        val actual = stagingFileLocator.selectFile(files)

        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun whenSelectFileWithDayTwoItShouldSelectIt() {
        val expected = Paths.get("foo.st2").toFile()
        val files = arrayOf(Paths.get("foo.txt").toFile(), Paths.get("foo.html").toFile(), Paths.get("foo.st1_log").toFile(), Paths.get("foo.st1").toFile(), expected, Paths.get("foo.st2_log").toFile())

        val actual = stagingFileLocator.selectFile(files, EventDay.TWO)

        assertThat(actual).isEqualTo(expected)
    }

}
