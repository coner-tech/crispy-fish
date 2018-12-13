package org.coner.crispyfish.filetype.staging

import org.junit.*
import org.junit.rules.TemporaryFolder
import org.junit.runner.RunWith

import org.assertj.core.api.Assertions.assertThat
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class StagingFilenameFilterTest {

    private lateinit var stagingFilenameFilter: StagingFilenameFilter

    @Rule @JvmField
    val dir = TemporaryFolder()

    @Before
    fun setup() {
        stagingFilenameFilter = StagingFilenameFilter(
                EVENT_CONTROL_FILE_ORIGINAL_STAGING_BASE_NAME,
                STAGING_ORIGINAL_FILE_PATTERN
        )
    }

    @Test
    fun whenExtensionIsNormalStagingItShouldAccept() {
        val name = "bar.st1"

        val actual = stagingFilenameFilter.accept(dir.root, name)

        assertThat(actual).isTrue()
    }

    @Test
    fun whenExtensionIsAbsentItShouldReject() {
        val name = "bar"

        val actual = stagingFilenameFilter.accept(dir.root, name)

        assertThat(actual).isFalse()
    }

    @Test
    fun whenExtensionIsPresentButWrongItShouldReject() {
        val name = "bar.txt"

        val actual = stagingFilenameFilter.accept(dir.root, name)

        assertThat(actual).isFalse()
    }

    @Test
    fun whenExtensionIsStagingLogItShouldReject() {
        val name = "bar.st1_log"

        val actual = stagingFilenameFilter.accept(dir.root, name)

        assertThat(actual).isFalse()
    }

    companion object {

        private val EVENT_CONTROL_FILE_ORIGINAL_STAGING_BASE_NAME = "bar"
        private val STAGING_ORIGINAL_FILE_PATTERN = StagingFilenames.ORIGINAL_FILE_DAY_1
    }
}
