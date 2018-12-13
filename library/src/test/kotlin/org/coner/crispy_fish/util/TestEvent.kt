package org.coner.crispy_fish.util

import com.google.common.io.Resources
import org.apache.commons.io.FileUtils
import org.coner.crispy_fish.filetype.ecf.EventControlFile

import java.io.File

import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

enum class TestEvent constructor(private val stagingFileName: String, private val twoDayEvent: Boolean) {

    THSCC_2016_POINTS_1(
            "2016-03-05 points autox 1 danville.st1",
            false
    ),
    THSCC_2016_POINTS_2(
            "2016-03-18 points autox 2 cary.st1",
            false
    ),
    THSCC_2016_POINTS_3(
            "2016 Points Event 3 Danville.st1",
            false
    ),
    THSCC_2016_POINTS_9(
            "2016-09-17 points autox 9 cary.st1",
            false
    );

    private val conePenalty = 2

    val stagingFileLines: List<String>
        get() {
            val url = Resources.getResource(RESOURCES_PREFIX + stagingFileName)
            try {
                return FileUtils.readLines(File(url.toURI()))
            } catch (e: Exception) {
                throw RuntimeException("Unable to get staging file line for " + this.toString(), e)
            }

        }

    fun buildEventControlFileMock(): EventControlFile {
        val eventControlFile = mock(EventControlFile::class.java)
        `when`(eventControlFile.conePenalty).thenReturn(conePenalty)
        `when`(eventControlFile.isTwoDayEvent).thenReturn(twoDayEvent)
        `when`(eventControlFile.path).thenThrow(UnsupportedOperationException::class.java)
        return eventControlFile
    }

    companion object {

        private val RESOURCES_PREFIX = (TestEvent::class.java.canonicalName + "/").replace(".", "/")
    }

}
