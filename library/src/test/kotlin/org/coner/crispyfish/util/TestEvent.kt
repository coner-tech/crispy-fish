package org.coner.crispyfish.util

import org.coner.crispyfish.filetype.ecf.EventControlFile
import org.coner.crispyfish.filetype.registration.RegistrationColumns
import org.coner.crispyfish.filetype.registration.RegistrationFile
import org.coner.crispyfish.model.Registration

import java.io.File

import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

enum class TestEvent constructor(
        val registrationFileName: String?,
        private val stagingFileName: String,
        private val twoDayEvent: Boolean
) {

    THSCC_2016_POINTS_1(
            null,
            "2016-03-05 points autox 1 danville.st1",
            false
    ),
    THSCC_2016_POINTS_2(
            null,
            "2016-03-18 points autox 2 cary.st1",
            false
    ),
    THSCC_2016_POINTS_3(
            "2016 points event 3 danville.rgg",
            "2016 Points Event 3 Danville.st1",
            false
    ),
    THSCC_2016_POINTS_9(
            null,
            "2016-09-17 points autox 9 cary.st1",
            false
    );

    private val conePenalty = 2

    fun buildRegistrationFile(
            registrations: MutableList<Registration>,
            columnIndices: MutableMap<RegistrationColumns, Int>
    ): RegistrationFile {
        val url = javaClass.getResource("/$RESOURCES_PREFIX$registrationFileName")
        return RegistrationFile(
                file = File(url.toURI()),
                registrations = registrations,
                columnIndices = columnIndices
        )
    }

    val stagingFileLines: List<String>
        get() {
            val url = javaClass.getResource("/$RESOURCES_PREFIX$stagingFileName")
            try {
                return File(url.toURI()).readLines()
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
