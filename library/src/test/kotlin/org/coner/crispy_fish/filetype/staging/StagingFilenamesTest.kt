package org.coner.crispy_fish.filetype.staging

import org.assertj.core.api.JUnitSoftAssertions
import org.coner.crispy_fish.filetype.staging.StagingFilenames
import org.junit.Rule
import org.junit.Test

import org.assertj.core.api.Assertions.assertThat

class StagingFilenamesTest {

    @Rule @JvmField
    val softly = JUnitSoftAssertions()

    @Test
    fun whenMatchOriginalFileDay1WithValidNameItShouldMatch() {
        val filenameOriginalDay1Valid = "foo.st1"
        assertThat(StagingFilenames.ORIGINAL_FILE_DAY_1.matcher(filenameOriginalDay1Valid).matches()).isTrue()
    }

    @Test
    fun whenMatchOriginalFileDay1WithInvalidNamesItShouldNotMatch() {
        val filenamesOriginalDay1Invalid = arrayOf("foo.st2", "foo.st1_log", "foo.txt", "foo", "foo.st1.bak", "foo.st1.000", "foo.st1.999")

        for (input in filenamesOriginalDay1Invalid) {
            softly.assertThat(StagingFilenames.ORIGINAL_FILE_DAY_1.matcher(input).matches())
                    .`as`("%s should not match ORIGINAL_FILE_DAY_1's matcher", input)
                    .isFalse
        }
    }

    @Test
    fun whenMatchOriginalFileDay2WithValidNameItShouldMatch() {
        val filenameOriginalDay2Valid = "foo.st2"
        assertThat(StagingFilenames.ORIGINAL_FILE_DAY_2.matcher(filenameOriginalDay2Valid).matches()).isTrue()
    }

    @Test
    fun whenMatchOriginalFileDay2WithInvalidNamesItShouldNotMatch() {
        val filenamesOriginalDay2Invalid = arrayOf("foo.st1", "foo.st2_log", "foo.txt", "foo", "foo.st2.bak", "foo.st2.000", "foo.st2.999")

        for (input in filenamesOriginalDay2Invalid) {
            softly.assertThat(StagingFilenames.ORIGINAL_FILE_DAY_2.matcher(input).matches())
                    .`as`("%s should not match ORIGINAL_FILE_DAY_2's matcher", input)
                    .isFalse
        }
    }

}